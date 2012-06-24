package com.andexp.skinmixer.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class SDCard {
	private static String BASE_PATH = "data/beautifulwidgets/";
	private static String SUPERCLOCK_PATH = "scskins/";
	private static SDCard mInstance;
	private static Context mContext;
	
	
	public SDCard(Context context) {
		mContext = context;
	}
	
	
	public static SDCard getInstance(Context context) {
		if(mInstance == null) mInstance = new SDCard(context);
		return mInstance;
	}

	public File getSuperClockDirectory() {
		String pathString = Environment.getExternalStorageDirectory()+File.separator+BASE_PATH+SUPERCLOCK_PATH;
		File mSuperClockDirectory = new File(pathString);
		mSuperClockDirectory.mkdirs();
		return mSuperClockDirectory;
	}


	public String getSuperClockSkinPath() {
		return new String();
	}
	
}
