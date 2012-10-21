package com.andexp.skinmixer.path;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SDCardSkinPath {
	private static final String BW_BASE_PATH = "/data/beautifulwidgets/";
	private static final String SUPERCLOCK_PATH = "scskins/";
	private static final String CLASSICCLOCK_PATH = "skins/";

	private String mBeautifulWidgetsBasePath;

	public SDCardSkinPath() {
		resetBasePath();
	}

	protected void addToBasePath(String pathModifier) {
		mBeautifulWidgetsBasePath += pathModifier;
	}

	protected void resetBasePath() {
		mBeautifulWidgetsBasePath = Environment.getExternalStorageDirectory() + BW_BASE_PATH;
	}

	public String getBeautifulWidgetsPath() {
		return mBeautifulWidgetsBasePath;
	}

	public File getSuperClockDirectory() throws IOException {
		return getFileFromPath(getSuperClockPath());
	}

	public String getSuperClockPath() {
		return getBeautifulWidgetsPath() + SUPERCLOCK_PATH;
	}

	public File getClassicClockDirectory() throws IOException {
		return getFileFromPath(getClassicClockPath());
	}

	public String getClassicClockPath() {
		return getBeautifulWidgetsPath() + CLASSICCLOCK_PATH;
	}

	public File getFileFromPath(String path) throws IOException {
		if(!Environment.MEDIA_MOUNTED.equalsIgnoreCase(Environment.getExternalStorageState())) 
				throw new IOException("SDCard not present or ready");
		File mFile = new File(path);
		mFile.mkdirs();
		return mFile;
	}

}
