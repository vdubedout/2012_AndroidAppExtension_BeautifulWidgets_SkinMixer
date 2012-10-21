package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.andexp.skinmixer.drawablecreation.PreviewManager;
import com.andexp.skinmixer.drawablecreation.PreviewManager.ImagePreviewProcessListener;
import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.skin.SkinGroupType;

public class FragmentSkinPartList extends SherlockListFragment implements OnSkinPartClickListener {
	ImagePreviewProcessListener mImageCreationListener;
	PreviewManager mPreviewManager;

	private SkinGroupType mSkinGroupType;
	private ArrayList<String> mSuperClockSkinPathList;
	private OnFragmentSkinListClick mListClickListener;

	public FragmentSkinPartList(Context context, SkinGroupType groupType,
			OnFragmentSkinListClick listClick) {
		this.mSkinGroupType = groupType;
		this.mListClickListener = listClick;
		setLayoutForType(context, groupType);
	}


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		removeListModifier();
	}

	private void removeListModifier() {
		ListView listView = getListView();
		listView.setSelector(android.R.color.transparent);
		getListView().setClickable(false);
		getListView().setFocusable(false);
	}

	private void setLayoutForType(Context context, SkinGroupType groupType) {
		switch (groupType) {
		case BACKGROUND:
		case FOREGROUND:
		case DOTS:
			setMonoImageSkinPartAdapter(context, groupType);
			break;
		default:
			setMultiImageSkinPartAdapter(context, groupType);
			break;
		}
	}

	private void setMultiImageSkinPartAdapter(Context context, SkinGroupType groupType) {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMultiImageSkinPart(context, mSuperClockSkinPathList, groupType,
				this));
	}

	private void setMonoImageSkinPartAdapter(Context context, SkinGroupType groupType) {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMonoImageSkinPart(context, mSuperClockSkinPathList, groupType,
				this));
	}

	@Override
	public void OnSkinPartClick(int arrayPosition, View v) {
		String path = mSuperClockSkinPathList.get(arrayPosition);
		mListClickListener.onFragmentSkinListClick(path, mSkinGroupType);
	}
}
