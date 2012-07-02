package com.andexp.skinmixer;

import java.io.File;

public class SkinDirectory {
	String mPath; //data/beautifulwidgets/[test/]scskins||skins/<directory_name>
	String mDirectoryName;
	String mClockType;
	
	public SkinDirectory(String path) {
		if(path == null) throw new NullPointerException("Path provided null");
		mPath = path;
	}

	public String getPath() {
		return mPath;
	}
	
	private String loadLocalVariableFrom(String path){
		int index = path.indexOf(File.separator, mPath.length()-1);
//		if(index != -1) path;
		return null;
	}
}
