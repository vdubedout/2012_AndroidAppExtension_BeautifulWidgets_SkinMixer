package com.andexp.skinmixer;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.andexp.skinmixer.drawablecreation.PreviewManager;
import com.andexp.skinmixer.drawablecreation.PreviewManager.ImagePreviewProcessListener;
import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.skin.SkinGroupType;

public class FragmentListDesigners extends SherlockListFragment {
	private ArrayList<String> mSuperClockSkinPathList;
	private final SkinGroupType[] mPathLists;

	public FragmentListDesigners(Context context, SkinGroupType[] pathLists) {
		this.mPathLists = pathLists;
		this.setEmptyText(context.getString(R.string.sm_loadinglist));
	}
}
