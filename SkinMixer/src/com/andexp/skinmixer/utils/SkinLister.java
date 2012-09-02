package com.andexp.skinmixer.utils;

import java.io.File;
import java.util.ArrayList;

public class SkinLister extends SDCardSkinPath{
	private static SkinLister mInstance;
	ArrayList<String> mSuperClockSkins;
	
	private SkinLister() {
		mSuperClockSkins = new ArrayList<String>();
	}

	public static SkinLister getInstance() {
		if(mInstance==null) mInstance = new SkinLister();
		return mInstance;
	}

	public ArrayList<String> getSuperClockSkinList() {
		File dir = new File(getSuperClockPath());
		String[] skins = dir.list();
		for (String string : skins) {
			mSuperClockSkins.add(string);
		}
		return mSuperClockSkins;
	}
}
