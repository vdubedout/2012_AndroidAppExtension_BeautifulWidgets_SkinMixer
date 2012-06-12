

/*
 * Copyright (C) 2010 Prasanta Paul, http://as-m-going-on.blogspot.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andexp.skinmixer.utils;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

/**
 * A Wrapper class of android.util.Log
 * @author Prasanta Paul
 *
 */
public class MLog {
	public static Context ctx;

	/**
	 * Log Level- VERBOSE; use Log.v
	 */
	public static final int VERBOSE = 0xA1;

	/**
	 * Log Level- DEBUG; use Log.d.
	 */
	public static final int DEBUG = 0xA2;

	/**
	 * Log Level- INFO; use Log.i.
	 */
	public static final int INFO = 0xA3;

	/**
	 * Log Level- WARN; use Log.w.
	 */
	public static final int WARN = 0xA4;

	/**
	 * Log Level- ERROR; use Log.e.
	 */
	public static final int ERROR = 0xA5;


	private static String TAG;

	/**
	 * Enable or Disable Logging
	 */
	public static boolean enableLog = true;

	/**
	 * All logs >= logLevel will be printed
	 */
	public static int logLevel = VERBOSE;

	private static PrintStream stream;

	/**
	 * This should be called prior to using logging methods
	 * 
	 * @param context
	 * @param logfile
	 */
	public static void init(Context context, String logfile){
		TAG = Conf.TAG;
		ctx = context;
		
		PackageInfo pinfo;
		try {
			pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
			TAG = TAG + pinfo.versionCode;
		} catch (NameNotFoundException e) {
			e(e.getMessage());
		}
		
		if(!enableLog || stream != null)
			return;
		try{
			FileOutputStream out = context.openFileOutput(logfile, Context.MODE_WORLD_READABLE);
			stream = new PrintStream(out);
		}catch(Exception ex){
			Log.e(TAG, "Error in creating Log File: "+ ex.getMessage());
		}finally{
			if(stream != null)
				println("New Log file created...");
		}
	}
	
	public static void i(String msg){
		if(!enableLog || logLevel > INFO)
			return;
		Log.i(TAG, msg);
		println(TAG +" "+ msg);
	}

	public static void i(String tag, String msg){
		if(!enableLog || logLevel > INFO)
			return;
		Log.i(tag, msg);
		println(tag +" "+ msg);
	}

	public static void i(String tag, String msg, Throwable  tr){
		if(!enableLog || logLevel > INFO)
			return;
		Log.i(tag, msg, tr);
		println(tag +" "+ msg + "\n"+ Log.getStackTraceString(tr));
	}
	
	public static void v(String msg){
		if(!enableLog || logLevel > VERBOSE)
			return;
		Log.v(TAG, msg);
		println(TAG +" "+ msg);
	}

	public static void v(String tag, String msg){
		if(!enableLog || logLevel > VERBOSE)
			return;
		Log.v(tag, msg);
		println(tag +" "+ msg);
	}

	public static void v(String tag, String msg, Throwable  tr){
		if(!enableLog || logLevel > VERBOSE)
			return;
		Log.v(tag, msg, tr);
		println(tag +" "+ msg + "\n"+ Log.getStackTraceString(tr));
	}
	
	public static void d(String msg){
		if(!enableLog || logLevel > DEBUG)
			return;
		Log.d(TAG, msg);
		println(TAG +" "+ msg);
	}

	public static void d(String tag, String msg){
		if(!enableLog || logLevel > DEBUG)
			return;
		Log.d(tag, msg);
		println(tag +" "+ msg);
	}

	public static void d(String tag, String msg, Throwable  tr){
		if(!enableLog || logLevel > DEBUG)
			return;
		Log.d(tag, msg, tr);
		println(tag +" "+ msg + "\n"+ Log.getStackTraceString(tr));
	}
	
	public static void w(String msg){
		if(!enableLog || logLevel > WARN)
			return;
		Log.w(TAG, msg);
		println(TAG +" "+ msg);
	}

	public static void w(String tag, String msg){
		if(!enableLog || logLevel > WARN)
			return;
		Log.w(tag, msg);
		println(tag +" "+ msg);
	}

	public static void w(String tag, String msg, Throwable  tr){
		if(!enableLog || logLevel > WARN)
			return;
		Log.w(tag, msg, tr);
		println(tag +" "+ msg + "\n"+ Log.getStackTraceString(tr));
	}
	
	public static void e(String msg){
		if(!enableLog || logLevel > ERROR)
			return;
		Log.e(TAG, msg);
		println(TAG +" "+ msg);
	}

	public static void e(String tag, String msg){
		if(!enableLog || logLevel > ERROR)
			return;
		Log.e(tag, msg);
		println(tag +" "+ msg);
	}

	public static void e(String tag, String msg, Throwable  tr){
		if(!enableLog || logLevel > ERROR)
			return;
		Log.e(tag, msg, tr);
		println(tag +" "+ msg + "\n"+ Log.getStackTraceString(tr));
	}
	
	/**
	 * Write the string into System Output Stream
	 * @param str
	 */
	private static void println(String str){
		if(stream != null){
			stream.println(new Date().toString() +" "+ str);
			stream.flush();
		}
	}

	/**
	 * Close the stream
	 */
	public static void close(){
		stream.close();
		stream = null;
	}
}

