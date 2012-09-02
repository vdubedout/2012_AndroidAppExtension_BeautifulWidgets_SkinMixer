package com.andexp.skinmixer.utils;

import java.io.File;
import java.util.ArrayList;

public class SkinLister extends SDCardSkinPath {
	private static SkinLister mInstance;
	ArrayList<String> mSuperClockSkins;

	private SkinLister() {
		mSuperClockSkins = new ArrayList<String>();
	}

	public static SkinLister getInstance() {
		if (mInstance == null)
			mInstance = new SkinLister();
		return mInstance;
	}

	public ArrayList<String> getSuperClockSkinList() {
		String[] skins = getSkinsIn(getSuperClockPath());
		mSuperClockSkins = populateSkins(skins, mSuperClockSkins);
		return mSuperClockSkins; 
	}

	protected String[] getSkinsIn(String path) {
		File dir = new File(path);
		String[] skins = dir.list();
		if(skins==null) skins = new String[0];
		return skins;
	}

	protected ArrayList<String> populateSkins(String[] skins, ArrayList<String> skinList) {
		if (skins != null) {
			for (String string : skins) {
				skinList.add(string);
			}
		}
		
		return skinList;
	}
}
