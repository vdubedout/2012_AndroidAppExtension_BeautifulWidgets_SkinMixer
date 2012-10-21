package com.andexp.skinmixer;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.andexp.skinmixer.fragment.FragmentPreviewDisplay;
import com.andexp.skinmixer.fragment.FragmentSkinPartList;
import com.andexp.skinmixer.fragment.OnFragmentSkinListClick;
import com.andexp.skinmixer.fragment.SkinPartType;
import com.viewpagerindicator.TabPageIndicator;

public class ActivitySkinMixer extends SherlockFragmentActivity implements OnFragmentSkinListClick, OnPreviewCompleteListener {
	private static final String MENU_SEND = "Next";
	private Menu mMenu;

	FragmentPreviewDisplay mPreview;

	private ActivitySkinMixer mActivity;
	private MenuItem mSendMenu;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		setContentView(R.layout.activity_skinmixer);
		initializeActionBar();
		initializePreview();
		initializeViewPager();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.mMenu = menu;
		mSendMenu = mMenu.add(MENU_SEND);
		mSendMenu.setIcon(R.drawable.ic_action_send)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(mPreview.isComplete()){
			//TODO next
		} else {
			//TODO display missing parts
		}
		
		return true;
	}
	
	private void initializeActionBar() {
		getSupportActionBar();
	}

	private void initializePreview() {
		mPreview = (FragmentPreviewDisplay) getSupportFragmentManager().findFragmentById(
				R.id.skinmixer_fragment_previewDisplay);
		mPreview.setPreviewCompleteListener(this);
	}

	private void initializeViewPager() {
		SkinMixerAdapter adapter = new SkinMixerAdapter(this, getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.skinmixer_viewpager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.skinmixer_viewpagerindicator);
		indicator.setViewPager(pager);
	}

	class SkinMixerAdapter extends FragmentPagerAdapter {
		private Context mContext;
		private String[] titleArray;

		public SkinMixerAdapter(Context context, FragmentManager fragmentManager) {
			super(fragmentManager);
			mContext = context;
			titleArray = mContext.getResources().getStringArray(R.array.skinpart_names);
		}

		@Override
		public Fragment getItem(int position) {
			SkinPartType type = SkinPartType.getSkinpartFromPartName(position);
			return new FragmentSkinPartList(mContext, type, mActivity);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titleArray[position].toUpperCase();
		}

		@Override
		public int getCount() {
			return titleArray.length;
		}

	}

	@Override
	public void onFragmentSkinListClick(String path, SkinPartType skinPartType) {
		mPreview.setImageType(path, skinPartType);
	}

	@Override
	public void OnPreviewComplete() {
		if(mMenu != null && mSendMenu != null){
			mSendMenu.setIcon(R.drawable.ic_action_send_clicked);
		}
	}

	@Override
	public void OnPreviewUncomplete() {
		if(mMenu != null && mSendMenu != null){
			mSendMenu.setIcon(R.drawable.ic_action_send);
		}
	}
}