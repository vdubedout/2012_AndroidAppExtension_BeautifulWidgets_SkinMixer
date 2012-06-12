package com.andexp.skinmixer.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import com.andexp.skinmixer.bw.part.base.BaseSkinPart;
import com.andexp.skinmixer.bw.part.base.EClockType;

import android.os.Environment;

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
	
//	data.skinName = skinName;
//	
//	data.directoryName = BaseSkinPart.PREFIX_GEN_SKINNAME + GetCharSafeString(skinName);
//	int i = 1;
//	while (isDirectoryNameAllreadyUsed(data.directoryName))
//	{ 
//		data.directoryName += i;
//		i++;
//	}
//	
//	delete = new SkinDelete(data.directoryName);
	
//	private String GetCharSafeString(String skinName) {
//	String output;
//	output = skinName.toLowerCase(Locale.ENGLISH);
//	output = output.replaceAll("[^a-z0-9]", "");
//	
//	return output;
//}
//
//private boolean isDirectoryNameAllreadyUsed(String directoryName){
//	//TODO directoryName checker
//	return false;
//}

}
