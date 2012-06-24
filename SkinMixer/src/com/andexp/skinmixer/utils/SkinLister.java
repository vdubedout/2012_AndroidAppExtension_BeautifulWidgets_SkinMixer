package com.andexp.skinmixer.utils;

public class SkinLister {
	private static SkinLister mInstance;

	public SkinLister() {
	}

	public static SkinLister getInstance() {
		if(mInstance==null) mInstance = new SkinLister();
		return mInstance;
	}
}
