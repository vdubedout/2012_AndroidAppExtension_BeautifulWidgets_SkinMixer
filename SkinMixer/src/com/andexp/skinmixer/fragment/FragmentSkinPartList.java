package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.path.SkinLister;

public class FragmentSkinPartList extends ListFragment implements OnSkinPartClickListener {
	private TextView mLabelSkinName;

	private SkinPartType mSkinPartType;
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
		mSkinPartType = skinPart;
		setLayoutForType(mSkinPartType);
		mLabelSkinName = getLabelView(skinPart);
	}

	private TextView getLabelView(SkinPartType skinPart) {
		LinearLayout parent = (LinearLayout) getView().getParent();
		if(skinPart == SkinPartType.BACKGROUND){
			return (TextView) parent.findViewById(R.id.skinmixer_backgroundlabel);
		} else if (skinPart == SkinPartType.BACKGROUND_NUMBERS){
			return (TextView) parent.findViewById(R.id.skinmixer_backgroundnumberslabel);
		} else return (TextView) parent.findViewById(R.id.skinmixer_numberslabel);
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
		setListAdapter(new AdapterMonoImageSkinPart(getActivity(), mSuperClockSkinPathList, mSkinPartType,
				this));
	}

	private void setMonoImageSkinPartAdapter() {
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMultiImageSkinPart(getActivity(), mSuperClockSkinPathList, this));
	}

	@Override
	public void OnSkinPartClick(int arrayPosition, View v) {
		changeLabelName(mSuperClockSkinPathList.get(arrayPosition));
	}


	private void changeLabelName(String path) {
		String[] splitResult = path.split("/");
		String skinName = splitResult[splitResult.length-1];
		mLabelSkinName.setText(skinName);
	}
}
