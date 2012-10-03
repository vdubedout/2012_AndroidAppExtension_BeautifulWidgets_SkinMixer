package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import com.andexp.skinmixer.path.SkinLister;

import android.app.ListFragment;

public class FragmentSkinPartList extends ListFragment {
	private SkinPartType mType;
	
	public void setSkinPartType(SkinPartType skinPart){
		mType = skinPart;
		setLayoutForType(mType);
	}

	private void setLayoutForType(SkinPartType skinPart) {
		switch (skinPart) {
		case NUMBER_0:
			setMonoImageSkinPartAdapter();
			break;
		default:
			setMultiImageSkinPartAdapter();
			break;
		}
		
	}

	private void setMultiImageSkinPartAdapter() {
		ArrayList<String> superClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMonoImageSkinPart(getActivity(), superClockSkinPathList, mType));
	}

	private void setMonoImageSkinPartAdapter() {
		
	}
	
}
