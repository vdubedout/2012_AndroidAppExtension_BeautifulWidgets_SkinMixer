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

public class FragmentSkinPartList extends SherlockListFragment implements OnSkinPartClickListener {
	ImagePreviewProcessListener mImageCreationListener;
	PreviewManager mPreviewManager;

	private SkinPartType mSkinPartType;
	private ArrayList<String> mSuperClockSkinPathList;
	private OnFragmentSkinListClick mListClickListener;

	public FragmentSkinPartList(Context context, SkinPartType type,
			OnFragmentSkinListClick listClick) {
		this.mSkinPartType = type;
		this.mListClickListener = listClick;
		setLayoutForType(context, mSkinPartType);
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

	private void setLayoutForType(Context context, SkinPartType skinPart) {
		switch (skinPart) {
		case BACKGROUND:
		case FOREGROUND:
		case DOTS:
			setMonoImageSkinPartAdapter(context, skinPart);
			break;
		default:
			setMultiImageSkinPartAdapter(context, skinPart);
			break;
		}
	}

	private void setMultiImageSkinPartAdapter(Context context, SkinPartType skinPart) {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMultiImageSkinPart(context, mSuperClockSkinPathList, skinPart,
				this));
	}

	private void setMonoImageSkinPartAdapter(Context context, SkinPartType skinPart) {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMonoImageSkinPart(context, mSuperClockSkinPathList, skinPart,
				this));
	}

	@Override
	public void OnSkinPartClick(int arrayPosition, View v) {
		String path = mSuperClockSkinPathList.get(arrayPosition);
		mListClickListener.onFragmentSkinListClick(path, mSkinPartType);
	}
}
