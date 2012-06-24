package com.andexp.skinmixer.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SDCard {
	private static String BASE_PATH = "data/beautifulwidgets/";
	private static String SUPERCLOCK_PATH = "scskins/";
	private static SDCard mInstance;

	public SDCard() {
	}

	public static SDCard getInstance() {
		if (mInstance == null)
			mInstance = new SDCard();
		return mInstance;
	}

	public File getSuperClockDirectory() throws IOException {
		if(Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) throw new IOException("SDCard not present or ready"); 
		File mSuperClockDirectory = new File(getSuperClockSkinPath());
		mSuperClockDirectory.mkdirs();
		return mSuperClockDirectory;
	}

	public String getSuperClockSkinPath() {
		return Environment.getExternalStorageDirectory() + File.separator
				+ BASE_PATH + SUPERCLOCK_PATH;
	}

}
