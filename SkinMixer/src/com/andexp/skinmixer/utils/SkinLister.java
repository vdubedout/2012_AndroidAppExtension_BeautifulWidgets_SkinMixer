package com.andexp.skinmixer.utils;

import java.util.ArrayList;

public class SkinLister {
	private static SkinLister mInstance;

	public SkinLister() {
	}

	public static SkinLister getInstance() {
		if(mInstance==null) mInstance = new SkinLister();
		return mInstance;
	}

	public ArrayList<String> getSuperClockSkinList() {
		return new ArrayList<String>();
	}
}
