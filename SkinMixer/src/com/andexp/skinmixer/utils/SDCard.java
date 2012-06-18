package com.andexp.skinmixer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;

import com.andexp.skinmixer.bw.part.base.EClockType;

public class SDCard {
	public static boolean isMounted(){
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			return true;
		} else return false;
	}

	public static String getClockSkinPath(int clockType){
		if(clockType == EClockType.NORMALCLOCK)
			return getNormalClockSkinPath();
		else return getSuperClockSkinPath();
	}
	
	static String pathSuperClock = "/data/beautifulwidgets/scskins/";
	public static String getSuperClockSkinPath(){
		return Environment.getExternalStorageDirectory() + pathSuperClock;
	}

	static String pathNormalClock = "/data/beautifulwidgets/skins/";
	public static String getNormalClockSkinPath(){
		return Environment.getExternalStorageDirectory() + pathNormalClock;
	}
	
	public static void copyFile(String sourceFilePath, String destationFilePath) throws IOException{
		java.io.FileInputStream sourceFile = new java.io.FileInputStream(sourceFilePath);
		java.io.FileOutputStream destinationFile = new FileOutputStream(destationFilePath);

		byte buffer[] = new byte[512*1024];
		int nbLecture;

		while ((nbLecture = sourceFile.read(buffer)) != -1) {
			destinationFile.write(buffer, 0, nbLecture);
		}

		destinationFile.close();
		sourceFile.close();
	}
	
	public static boolean deleteDir(File dir) {
		MLog.d("Delete Directory : "+dir.toString());
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}
}
