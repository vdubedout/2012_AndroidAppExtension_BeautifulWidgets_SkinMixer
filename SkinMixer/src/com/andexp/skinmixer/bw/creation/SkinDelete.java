package com.andexp.skinmixer.bw.creation;

import java.io.File;

import com.andexp.skinmixer.utils.MLog;
import com.andexp.skinmixer.utils.SDCard;

public class SkinDelete {
	String mySkinName;
	
	public SkinDelete(String skinName) {
		mySkinName = skinName;
	}
	
	public void deleteAll(){
		File data = new File(SDCard.getSuperClockSkinPath() + mySkinName +	File.separator);
		MLog.d("Delete Directory : "+data.toString());
		deleteDir(data);
	}
	
	private static boolean deleteDir(File dir) {
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
