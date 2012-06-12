package com.andexp.skinmixer.bw;

import android.os.Bundle;

import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.bw.part.base.SkinPart;
import com.andexp.skinmixer.bw.part.base.text.SkinData;

public class Skin implements SkinImpl {
	private SkinPartImpl[] skinParts = new SkinPartImpl[SkinPart.SKINPARTS_NUMBER];
	private SkinData mSkinData;

	public Skin(){
		mSkinData = new SkinData();
		mSkinData.author = "SkinMixer";
	}

	@Override
	public void setFromRepertory(String repertory, int clockType) {
		for (int skinPartType = 0; skinPartType < SkinPart.SKINPARTS_NUMBER; skinPartType++) {
			setSkinPartByRepertory(repertory, skinPartType, clockType);
		}
	}
	
	@Override
	public SkinData getSkinData() {
		return mSkinData;
	}
	

	@Override
	public void setSkinPartByRepertory(String repertorySkinPart, int skinPartType, int clockType) {
		skinParts[skinPartType] = new SkinPart(repertorySkinPart, skinPartType, clockType);
	}

	@Override
	public void setSkinPart(SkinPartImpl skinPart) {
		if(isNumber(skinPart.getSkinPartType()))
			loadNumbersFrom(skinPart);
		else if(isAmPm(skinPart.getSkinPartType()))
			loadAmPmFrom(skinPart);
		else skinParts[skinPart.getSkinPartType()] = skinPart;
	}

	@Override
	public SkinPartImpl getSkinPart(int skinPartType) {
		return skinParts[skinPartType];
	}

	private boolean isNumber(int skinPartType){
		if(skinPartType >= ESkinPart.NUMBER_0 && skinPartType <= ESkinPart.NUMBER_9 )
			return true;
		else return false;
	}
	
	private boolean isAmPm(int skinPartType){
		if(skinPartType >= ESkinPart.AM && skinPartType <= ESkinPart.PM)
			return true;
		else return false;
	}

	private void loadNumbersFrom(SkinPartImpl skinPart){
		copySkinParts(ESkinPart.NUMBER_0, ESkinPart.NUMBER_9, skinPart);
	}
	
	private void loadAmPmFrom(SkinPartImpl skinPart){
		copySkinParts(ESkinPart.AM, ESkinPart.PM, skinPart);
	}
	 
	private void copySkinParts(int fromLowestSkinPart, int toHighestSkinPart, SkinPartImpl skinPart){
		SkinData data = skinPart.getSkinPartData();
		for (int number = fromLowestSkinPart; number <= toHighestSkinPart; number++) {
			if(number != skinPart.getSkinPartType())
				skinParts[number] = new SkinPart(data.directoryName,
						number, 
						data.clockType);
		}
	}

//	@Override
//	public void loadFromCompressedExtra(String extra) {
//		setSkinPart(SkinExtraLoader.loadSkinPart(extra));
//	}

	@Override
	public Bundle toBundle() {
		return SkinBundler.GetBundleFromSkin(this);
	}

	@Override
	public boolean isSkinComplete() {
		// TODO Auto-generated method stub
		return false;
	}
}
