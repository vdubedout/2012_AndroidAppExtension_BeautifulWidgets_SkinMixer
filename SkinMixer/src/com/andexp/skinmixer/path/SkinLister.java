package com.andexp.skinmixer.path;

import java.io.File;
import java.util.ArrayList;

public class SkinLister extends SDCardSkinPath {
	private static SkinLister mInstance;
	ArrayList<String> mSuperClockSkins;

	private SkinLister() {
		super();
		mSuperClockSkins = new ArrayList<String>();
	}

	public static SkinLister getInstance() {
		if (mInstance == null)
			mInstance = new SkinLister();
		return mInstance;
	}

	public ArrayList<String> getSuperClockSkinPathList() {
		// if (mSuperClockSkins.size() == 0) {
		String path = getSuperClockPath();
		String[] skins = getSkinsIn(path);
		mSuperClockSkins = populateSkinsPaths(skins, path, mSuperClockSkins);
		// }
		return mSuperClockSkins;
	}

	protected String[] getSkinsIn(String path) {
		File dir = new File(path);
		String[] skins = dir.list();
		if (skins == null)
			skins = new String[0];
		return skins;
	}

	protected ArrayList<String> populateSkinsPaths(String[] skins, String path,
			ArrayList<String> skinList) {
		skinList = new ArrayList<String>();

		if (skins != null) {
			for (String skinFolderName : skins) {
				skinList.add(path + skinFolderName + File.separator);
			}
		}

		return skinList;
	}
	
	public static String getNameFromPath(String path){
		if(path == null) return null;
		String[] split = path.split(File.separator);
		String name = split[split.length -1];
		if(name.toLowerCase().contains(".png") 
				|| name.toLowerCase().contains(".jpg")
				|| name.toLowerCase().contains(".txt")){
			name = getNameFromPath(path.replace(File.separator+name, ""));
		}
		
		if(name.toLowerCase().endsWith("scskins")
				|| name.toLowerCase().endsWith("skins")
				|| name.toLowerCase().endsWith("beautifulwidgets"))
			return null;
		else return name;
	}
}
