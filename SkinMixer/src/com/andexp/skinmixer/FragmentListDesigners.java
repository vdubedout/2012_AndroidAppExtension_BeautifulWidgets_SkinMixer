package com.andexp.skinmixer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockListFragment;
import com.andexp.skinmixer.skindata.SkinData;


public class FragmentListDesigners extends SherlockListFragment {
	private AdapterDesignerList adapterDesignerList;
	
	public FragmentListDesigners() {
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.setEmptyText(view.getContext().getString(R.string.sm_loadinglist));
	}
	
	public void setListAdapter(Context ctx, SkinData[] data) {
		adapterDesignerList = new AdapterDesignerList(ctx, data);
		super.setListAdapter(adapterDesignerList);
	}
}
