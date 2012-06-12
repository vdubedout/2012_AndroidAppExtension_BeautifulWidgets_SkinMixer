package com.andexp.skinmixer.utils;

import android.content.Context;

public class Conf {
	public static final String TAG = "SkinMixer:";
	
	static final String LOG_FILENAME = "SkinMixer.txt";
	
	public static void load(Context applicationContext){
		initializeLogger(applicationContext);
		
	}
	
	private static void initializeLogger(Context applicationContext){
		MLog.enableLog = true;
		MLog.logLevel = MLog.VERBOSE;
		MLog.init(applicationContext, LOG_FILENAME);
	}
	
}
