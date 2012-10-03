package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.andexp.skinmixer.path.SkinLister;

public class FragmentSkinPartList extends ListFragment implements OnSkinPartClickListener {
	private SkinPartType mType;
	private ArrayList<String> mSuperClockSkinPathList;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ListView listView = getListView();
		listView.setSelector(android.R.color.transparent);
		getListView().setClickable(false);
		getListView().setFocusable(false);
	}

	public void setSkinPartType(SkinPartType skinPart) {
		mType = skinPart;
		setLayoutForType(mType);
	}

	private void setLayoutForType(SkinPartType skinPart) {
		switch (skinPart) {
		case BACKGROUND:
		case BACKGROUND_NUMBERS:
			setMultiImageSkinPartAdapter();
			break;
		default:
			setMonoImageSkinPartAdapter();
			break;
		}
	}

	private void setMultiImageSkinPartAdapter() {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMonoImageSkinPart(getActivity(), mSuperClockSkinPathList, mType,
				this));
	}

	private void setMonoImageSkinPartAdapter() {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMultiImageSkinPart(getActivity(), mSuperClockSkinPathList, this));
	}

	@Override
	public void OnSkinPartClick(int arrayPosition, View v) {
		Toast.makeText(getActivity(), "click on " + mSuperClockSkinPathList.get(arrayPosition),
				1000).show();
	}
}
