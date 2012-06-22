package com.andexp.skinmixer.bw;

import android.os.Bundle;

import com.andexp.skinmixer.async.SkinBuilderBWAsync;
import com.andexp.skinmixer.async.SkinBuilderBWAsync.AsyncBuildListener;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.SkinPart;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.utils.MLog;

public class Skin implements SkinImpl {
	private final static int SKINPARTS_NUMBER = ESkinPart.LAST_SKIN_IMAGE +1;
	private SkinPartImpl[] skinParts = new SkinPartImpl[SKINPARTS_NUMBER];
	private SkinData mSkinData;

	public Skin(){
		mSkinData = new SkinData();
		mSkinData.author = "SkinMixer";
	}

	@Override
	public void setFromRepertory(String repertory, int clockType) {
		for (int skinPartType = 0; skinPartType < SKINPARTS_NUMBER; skinPartType++) {
			setSkinPartByRepertory(repertory, skinPartType, clockType);
		}
	}
	
	@Override
	public SkinData getSkinData() {
		return mSkinData;
	}

	@Override
	public void setSkinPartByRepertory(String repertorySkinPart, int skinPartType, int clockType) {
		if(skinPartType == ESkinPart.NUMBER_0){
			for (int i = ESkinPart.NUMBER_0; i <= ESkinPart.NUMBER_9; i++) {
				skinParts[i] = new SkinPart(repertorySkinPart, i, clockType);
			}
		} else skinParts[skinPartType] = new SkinPart(repertorySkinPart, skinPartType, clockType);
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
		for (int i=0 ; i<skinParts.length ; i++){
			if(skinParts[i]==null) {
				MLog.e("Missing part : "+i);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isSkinHasMinimumToGenerate() {
		if(skinParts[ESkinPart.BACKGROUND] != null 
				&& skinParts[ESkinPart.BACKGROUND_NUMBERS]  != null
				&& skinParts[ESkinPart.NUMBER_0]  != null)
			return true;
		else return false;
	}

	@Override
	public void fillMissingSkinParts() {
		if(skinParts[ESkinPart.AM] == null) skinParts[ESkinPart.AM] = skinParts[ESkinPart.NUMBER_0];
		if(skinParts[ESkinPart.PM] == null) skinParts[ESkinPart.PM] = skinParts[ESkinPart.NUMBER_0];
		if(skinParts[ESkinPart.DOTS] == null) skinParts[ESkinPart.DOTS] = skinParts[ESkinPart.BACKGROUND_NUMBERS];
	}
	
	public void build(AsyncBuildListener listener){
		new SkinBuilderBWAsync(this, listener).execute((Void)null);
	}
}
