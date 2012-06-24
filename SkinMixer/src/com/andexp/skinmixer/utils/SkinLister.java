package com.andexp.skinmixer.utils;

import java.util.ArrayList;

public class SkinLister extends SkinDir{
	private static SkinLister mInstance;

	public SkinLister() {
	}

	public static SkinLister getInstance() {
		if(mInstance==null) mInstance = new SkinLister();
		return mInstance;
	}

	public ArrayList<String> getSuperClockSkins() {
		return new ArrayList<String>();
	}
}
