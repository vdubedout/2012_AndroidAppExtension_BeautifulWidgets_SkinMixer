package com.andexp.skinmixer;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.andexp.skinmixer.skin.SkinGroupType;

public class ActivitySkinCreation extends SherlockActivity{
	private String[] mSkinGroupPaths;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skincreation);
		mSkinGroupPaths = getIntent().getExtras().getStringArray(SkinGroupType.class.toString());
	}

}
