package com.andexp.skinmixer.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.AssetManager;

public class Assets {
	public static final String ASSETS_PATH_PREVIEW = "custompreview.jpg";
	public static final String ASSETS_PATH_BACKGROUND_VOID = "void_background.png";
	public static final String ASSETS_PATH_BACKGROUND_NUMBERS_VOID = "void_background_numbers.png";
	public static final String ASSETS_PATH_NUMBERS_DEFAULT = "numbers/";
	public static final String NUMBERS_IMAGE_EXTENSION = ".png";

	public static void copySkinPreviewTo(String outputPathFile, 
			Context applicationContext){
		copyFileToDirectory(ASSETS_PATH_PREVIEW, outputPathFile, applicationContext);
	}

	public static void copyVoidBackgroundTo(String outputPathFile, 
			Context applicationContext){
		copyFileToDirectory(ASSETS_PATH_BACKGROUND_VOID, outputPathFile, applicationContext);
	}

	public static void copyVoidBackgroundNumbersTo(String outputPathFileName, 
			Context applicationContext){
		copyFileToDirectory( ASSETS_PATH_BACKGROUND_NUMBERS_VOID, outputPathFileName, applicationContext);
	}

	public static void copyVoidNumbersTo(String outputPathFileName, 
			Context applicationContext){
		for (int i = 0; i < 10; i++) {
			copyFileToDirectory(ASSETS_PATH_BACKGROUND_NUMBERS_VOID+i+NUMBERS_IMAGE_EXTENSION, 
					outputPathFileName, applicationContext);
		}
	}

	private static void copyFileToDirectory(String fileNameInAssets, 
			String outputPathFile, Context applicationContext){
		AssetManager assetManager = applicationContext.getAssets();

		try{
			InputStream in = assetManager.open(fileNameInAssets);
			String outputFile = outputPathFile;
			MLog.v("copy preview : "+outputFile);
			OutputStream out = new FileOutputStream(outputFile);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
		}catch(Exception e){
			MLog.e("make preview stream problem"+e.getMessage());
		}
	}

	private static void copyFile(InputStream in, OutputStream out)throws IOException {
		byte [] buffer = new byte[1024];
		int read;
		while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
		}

	}
}
