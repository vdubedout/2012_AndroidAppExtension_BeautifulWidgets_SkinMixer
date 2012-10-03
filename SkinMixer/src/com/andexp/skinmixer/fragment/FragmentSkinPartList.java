package com.andexp.skinmixer.fragment;

import java.util.ArrayList;

import com.andexp.skinmixer.path.SkinLister;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentSkinPartList extends ListFragment implements OnSkinPartClickListener{
	private SkinPartType mType;
	private ArrayList<String> mSuperClockSkinPathList;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		getListView().setClickable(false);
	}

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
		mSuperClockSkinPathList = SkinLister.getInstance().getSuperClockSkinPathList();
		setListAdapter(new AdapterMonoImageSkinPart(getActivity(), mSuperClockSkinPathList, mType, this));
	}

	private void setMonoImageSkinPartAdapter() {
		
	}

	@Override
	public void OnSkinPartClick(int arrayPosition, View v) {
		Toast.makeText(getActivity(), "click on "+mSuperClockSkinPathList.get(arrayPosition), 2000).show();
	}
	
}
