package com.andexp.skinmixer.utils;

import java.io.File;
import java.util.ArrayList;

import com.andexp.skinmixer.bw.part.base.BaseSkinPart;
import com.andexp.skinmixer.bw.part.base.EClockType;
import com.andexp.skinmixer.bw.part.base.SkinPart;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;

public class SDCardSkinLoader {
	static String[] idToNotLoad = new String[] {
		BaseSkinPart.PREFIX_GEN_SKINNAME,
		"."
	};

	public static ArrayList<SkinPartImpl> getSuperClockSkinIdsList(int skinPartType) {
		ArrayList<SkinPartImpl> mySkinsInfos = new ArrayList<SkinPartImpl>();
		ArrayList<String> mySkinIds = loadSkinRepertoryIdsAvailable(SDCard.getClockSkinPath(EClockType.SUPERCLOCK));

		for (String id : mySkinIds) {
			mySkinsInfos.add(new SkinPart(id, skinPartType, EClockType.SUPERCLOCK)); 
		}

		return mySkinsInfos;
	}

	public static ArrayList<SkinPartImpl> getNormalClockSkinIdsList(int skinPartType){
		ArrayList<SkinPartImpl> mySkinsInfos = new ArrayList<SkinPartImpl>();
		ArrayList<String> mySkinIds = loadSkinRepertoryIdsAvailable(SDCard.getClockSkinPath(EClockType.NORMALCLOCK));

		for (String id : mySkinIds) {
			mySkinsInfos.add(new SkinPart(id, skinPartType, EClockType.NORMALCLOCK)); 
		}

		return mySkinsInfos;
	}

	public static ArrayList<String> loadSkinRepertoryIdsAvailable(String path){
		boolean isIgnored;
		File skinRepertory = new File(path);
		String[] tempSkinId = skinRepertory.list();
		ArrayList<String> futureIds = new ArrayList<String>();

		if(tempSkinId != null){
			for (String id : tempSkinId) {
				if(isSkinRepertoryIdHasChild(path+id)){
					isIgnored = false;
					for (String textToIgnore : idToNotLoad) {
						if(id.startsWith(textToIgnore)) isIgnored = true;
					}
					if(!isIgnored) futureIds.add(id);
				}
			}
		}

		return futureIds;
	}
	
	private static boolean isSkinRepertoryIdHasChild(String path){
		File skinRepertory = new File(path);
		if(skinRepertory.isDirectory() && skinRepertory.list().length >= 15)
			return true;
		else return false;
	}
}
