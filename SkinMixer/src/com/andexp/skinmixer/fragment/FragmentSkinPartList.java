package com.andexp.skinmixer.fragment;

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
		// TODO Auto-generated method stub
		
	}

	private void setMonoImageSkinPartAdapter() {
		// TODO Auto-generated method stub
		
	}
	
}
