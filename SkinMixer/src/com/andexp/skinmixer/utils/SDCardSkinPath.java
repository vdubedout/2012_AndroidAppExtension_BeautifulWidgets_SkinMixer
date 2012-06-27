package com.andexp.skinmixer.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SDCardSkinPath {
	public static String BASE_PATH = "/data/beautifulwidgets/";
	public static String SUPERCLOCK_PATH = "scskins/";
	public static String CLASSICCLOCK_PATH = "skins/";
	private static SDCardSkinPath mInstance;

	public SDCardSkinPath() {
	}

	public static SDCardSkinPath getInstance() {
		if (mInstance == null)
			mInstance = new SDCardSkinPath();
		return mInstance;
	}

	public File getSuperClockDirectory() throws IOException {
		return getFileFromPath(getSuperClockPath());
	}

	public String getSuperClockPath() {
		return Environment.getExternalStorageDirectory() + BASE_PATH + SUPERCLOCK_PATH;
	}

	public File getClassicClockDirectory() throws IOException {
		return getFileFromPath(getClassicClockPath());
	}
	
	public String getClassicClockPath() {
		return Environment.getExternalStorageDirectory() + BASE_PATH + CLASSICCLOCK_PATH;
	}
	
	public File getFileFromPath(String path) throws IOException {
		if(Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) throw new IOException("SDCard not present or ready");
		File mFile = new File(path);
		mFile.mkdirs();
		return mFile;
	}
	
}
