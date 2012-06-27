package com.andexp.skinmixer.utils;

import java.io.File;
import java.io.IOException;

import android.os.Environment;

public class SDCardSkinPathLoader {
	public static String BASE_PATH = "/data/beautifulwidgets/";
	private static String SUPERCLOCK_PATH = "scskins/";
	private static String CLASSICCLOCK_PATH = "skins/";
	private static SDCardSkinPathLoader mInstance;

	public SDCardSkinPathLoader() {
	}

	public static SDCardSkinPathLoader getInstance() {
		if (mInstance == null)
			mInstance = new SDCardSkinPathLoader();
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
