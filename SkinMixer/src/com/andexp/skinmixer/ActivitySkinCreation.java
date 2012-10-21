package com.andexp.skinmixer;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.skin.SkinGroupType;
import com.andexp.skinmixer.skindata.SkinData;
import com.andexp.skinmixer.skindata.SkinDataReader;

public class ActivitySkinCreation extends SherlockFragmentActivity implements SkinReaderListener{
	
	private String[] mSkinGroupPaths;
	private SkinData[] mSkinDataFromGroups;

	EditText et_skinName;
	private FragmentListDesigners mFragmentDesignersList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skincreation);
		bindFragment();
		
		mSkinGroupPaths = getIntent().getExtras().getStringArray(SkinGroupType.class.toString());
		et_skinName = (EditText) findViewById(R.id.skincreation_skinname);
		
		new Thread(new SkinNameSearcherRunner(this, et_skinName)).run();
		new Thread(new SkinDataLoading(mSkinGroupPaths, this)).run();
	}

	private void bindFragment() {
		FragmentManager fragManager = getSupportFragmentManager();
		Fragment fragment = fragManager.findFragmentById(R.id.skincreation_designerList);
		mFragmentDesignersList = (FragmentListDesigners) fragment;
	}
	
	class SkinNameSearcherRunner implements Runnable {
		private final Context mContext;
		private final EditText mEditText;
		public SkinNameSearcherRunner(Context ctx, EditText editText) {
			this.mContext = ctx;
			this.mEditText = editText;
		}
		public void run() {
			SharedPreferences prefs = mContext.getSharedPreferences("SkinMixer", Context.MODE_PRIVATE);
			int skinNumber = prefs.getInt("lastGeneratedNumber", 0)+1;
			String baseSkinName = mContext.getString(R.string.sm_base_skinname);
			ArrayList<String> superClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
			
			while(isSkinNameInList(baseSkinName+skinNumber, superClockSkinPathList)){
				skinNumber++;
			}
			mEditText.setHint(baseSkinName+skinNumber);
		}
	}

	protected boolean isSkinNameInList(String skinName, ArrayList<String> superClockSkinPathList) {
		boolean isInList = false;
		for (String skinPath : superClockSkinPathList) {
			if(skinPath.toLowerCase().contains(skinName.toLowerCase())){
				isInList = true;
			}
		}
		return isInList;
	}
	
	
	class SkinDataLoading implements Runnable{
		private final SkinReaderListener mListener;
		private final String[] mGroupPaths;

		public SkinDataLoading(String[] paths, SkinReaderListener listener) {
			this.mGroupPaths = paths;
			this.mListener = listener;
		}
		
		@Override
		public void run() {
			ArrayList<String> mDifferentsPaths = getUniqueGroupPaths(mGroupPaths);
			SkinData[] data = new SkinData[mDifferentsPaths.size()];
			for (int i = 0; i < mDifferentsPaths.size(); i++) {
				data[i] = new SkinDataReader(mDifferentsPaths.get(i)).getData();
			}
			this.mListener.skinDataLoaded(data);
		}

		private ArrayList<String> getUniqueGroupPaths(String[] paths) {
			ArrayList<String> pathList = new ArrayList<String>();
			for (String path : paths) {
				boolean isInList = false;
				for (int i = 0; i < pathList.size(); i++) {
					if(pathList.get(i).contentEquals(path)){
						isInList = true;
					}
				}
				if(!isInList)
					pathList.add(path);
				
			}
			
			return pathList;
		}
		
	}

	@Override
	public void skinDataLoaded(SkinData[] data) {
		mSkinDataFromGroups = data;
		if(mFragmentDesignersList!=null)
			mFragmentDesignersList.setListAdapter(this, data);
	}

}
