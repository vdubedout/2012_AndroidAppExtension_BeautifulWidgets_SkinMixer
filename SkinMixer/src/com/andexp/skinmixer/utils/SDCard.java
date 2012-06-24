package com.andexp.skinmixer.utils;

import java.io.File;

import android.os.Environment;

public class SDCard {
	private static String BASE_PATH = "data/beautifulwidgets/";
	private static String SUPERCLOCK_PATH = "scskins/";
	private static SDCard mInstance;
	
	
	public SDCard() {
	}
	
	
	public static SDCard getInstance() {
		if(mInstance == null) mInstance = new SDCard();
		return mInstance;
	}

	public File getSuperClockDirectory() {
		File mSuperClockDirectory = new File(getSuperClockSkinPath());
		mSuperClockDirectory.mkdirs();
		return mSuperClockDirectory;
	}


	public String getSuperClockSkinPath() {
		return Environment.getExternalStorageDirectory()+File.separator+BASE_PATH+SUPERCLOCK_PATH;
	}
	
}
