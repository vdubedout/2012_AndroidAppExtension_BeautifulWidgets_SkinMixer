package com.andexp.skinmixer.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SDCardSkinPath {
	public static final String BASE_PATH = "/data/beautifulwidgets/";
	public static final String SUPERCLOCK_PATH = "scskins/";
	public static final String CLASSICCLOCK_PATH = "skins/";
	private static SDCardSkinPath mInstance;
	
	String mBasePath, mSuperClockPath, mClassicClockPath;
	
	public SDCardSkinPath() {
		mBasePath = BASE_PATH;
		mSuperClockPath = SUPERCLOCK_PATH;
		mClassicClockPath = CLASSICCLOCK_PATH;
	}

	public SDCardSkinPath(String basePath) {
		mBasePath = basePath;
		mSuperClockPath = SUPERCLOCK_PATH;
		mClassicClockPath = CLASSICCLOCK_PATH;
	}

	public static SDCardSkinPath getInstance() {
		if (mInstance == null)
			mInstance = new SDCardSkinPath();
		return mInstance;
	}

	public File getSuperClockDirectory() throws IOException {
		return getFileFromPath(getSuperClockPath());
	}

	public String getBasePath() {
		return Environment.getExternalStorageDirectory() + mBasePath;
	}
	
	public String getSuperClockPath() {
		return getBasePath() + mSuperClockPath;
	}

	public File getClassicClockDirectory() throws IOException {
		return getFileFromPath(getClassicClockPath());
	}
	
	public String getClassicClockPath() {
		return getBasePath() + mClassicClockPath;
	}
	
	public File getFileFromPath(String path) throws IOException {
		if(Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) throw new IOException("SDCard not present or ready");
		File mFile = new File(path);
		mFile.mkdirs();
		return mFile;
	}

	
}
