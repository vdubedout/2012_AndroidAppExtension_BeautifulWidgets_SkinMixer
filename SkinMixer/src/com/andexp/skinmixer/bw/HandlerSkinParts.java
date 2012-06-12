//package com.andexp.skinmixer.bw;
//
//import java.io.File;
//
//import android.os.Bundle;
//
//import com.andexp.skinmixer.bw.part.base.EClockType;
//import com.andexp.skinmixer.bw.part.base.ESkinPart;
//import com.andexp.skinmixer.bw.part.specific.Background;
//import com.andexp.skinmixer.bw.part.specific.BackgroundNumbers;
//import com.andexp.skinmixer.bw.part.specific.Numbers;
//import com.andexp.skinmixer.utils.Extra;
//import com.andexp.skinmixer.utils.MLog;
//
//public class HandlerSkinParts {
//	public Background background;
//	public BackgroundNumbers backgroundNumbers;
//	public Numbers numbers;
//
//	public HandlerSkinParts(){}
//
//	public boolean isSkinPartNull(){
//		if(background == null){
//			MLog.v("background not loaded");
//			return false;
//		} else if(backgroundNumbers == null){
//			MLog.v("background numbers not loaded");
//			return false;
//		} else if(numbers == null){
//			MLog.v("numbers not loaded");
//			return false;
//		} else return true;
//	}
//
//	public void loadSkinPart(String compressedSkinInfo){
//		String[] textChunk = compressedSkinInfo.split(File.separator);
//		String directoryName = textChunk[0];
//		int skinPart = Integer.parseInt(textChunk[1]);
//		int clockType = Integer.parseInt(textChunk[2]);
//
//		loadSkinPart(directoryName, skinPart, clockType);
//	}
//
//	public void loadSkinPart(String directoryName, int skinPart, int clockType){
//		MLog.v("skinpart="+skinPart+" | skinId="+directoryName+" | clocktype="+clockType);
//
//		switch (skinPart) {
//		case ESkinPart.BACKGROUND:
//			background = new Background(directoryName);
//			break;
//		case ESkinPart.BACKGROUND_NUMBERS:
//			backgroundNumbers = new BackgroundNumbers(directoryName);
//			break;
//		case ESkinPart.NUMBERS:
//			numbers = new Numbers(directoryName, clockType);
//			break;
//		}
//	}
//
//	/***
//	 * Type directoryName/partType/clockType
//	 * @param skinPart
//	 * @return compressed skin Part
//	 */
//	public String exportSkinPartInfo(int skinPart){
//		String[] data = getSkinDataOf(skinPart);
//		StringBuilder compressedSkin = new StringBuilder();
//		compressedSkin.append(data[0]).append(File.separator);
//		compressedSkin.append(data[1]).append(File.separator);
//		compressedSkin.append(data[2]);
//		return compressedSkin.toString();
//	}
//
//	private String[] getSkinDataOf(int skinPart) {
//		String[] data = new String[3];
//		data[0] = GetDirectoryNameOf(skinPart);
//		data[1] = String.valueOf(skinPart);
//		data[2] = GetClockTypeOf(skinPart);
//
//		return data;
//	}
//
//	private String GetDirectoryNameOf(int skinPart){
//		switch (skinPart) {
//		case ESkinPart.BACKGROUND:
//			return background.data.directoryName ;
//		case ESkinPart.BACKGROUND_NUMBERS:
//			return backgroundNumbers.data.directoryName;
//		default:
//			return numbers.data.directoryName;
//		}
//	}
//
//	private String GetClockTypeOf(int skinPart){
//		if(skinPart == ESkinPart.NUMBERS)
//			return String.valueOf(numbers.clockType);
//		else return String.valueOf(EClockType.SUPERCLOCK);
//	}
//
//	public Bundle toBundle(){
//		Bundle myBundle = new Bundle();
//		if(background != null)
//			myBundle.putString(Extra.SKINPART_BACKGROUND, background.data.directoryName);
//		if(backgroundNumbers != null)
//			myBundle.putString(Extra.SKINPART_BACKGROUNDNUMBERS, backgroundNumbers.data.directoryName);
//		if(numbers != null){
//			myBundle.putString(Extra.SKINPART_NUMBERS, numbers.data.directoryName);
//			myBundle.putInt(Extra.SKIN_CLOCKTYPE, numbers.clockType);
//		}
//
//		return myBundle;
//	}
//
//	public void LoadFromBundle(Bundle dataSaved){
//		if(dataSaved.getString(Extra.SKINPART_BACKGROUND) != null)
//			background = new Background(dataSaved.getString(Extra.SKINPART_BACKGROUND));
//		if(dataSaved.getString(Extra.SKINPART_BACKGROUNDNUMBERS) != null)
//			backgroundNumbers = new BackgroundNumbers(dataSaved.getString(Extra.SKINPART_BACKGROUNDNUMBERS));
//		if(dataSaved.getString(Extra.SKINPART_NUMBERS) != null)
//			numbers = new Numbers(dataSaved.getString(Extra.SKINPART_NUMBERS), dataSaved.getInt(Extra.SKIN_CLOCKTYPE));
//	}
//}
