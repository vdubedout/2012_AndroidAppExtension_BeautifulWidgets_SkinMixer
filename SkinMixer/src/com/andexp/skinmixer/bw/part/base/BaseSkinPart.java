package com.andexp.skinmixer.bw.part.base;

import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.bw.part.base.text.SkinDataReader;
import com.andexp.skinmixer.utils.SDCard;

public abstract class BaseSkinPart {
	public static final String PREFIX_GEN_SKINNAME = "GEN-";
	public static final String EXTENSION_JPG = ".jpg";
	public static final String EXTENSION_PNG = ".png";
	
	public SkinData data;
	
	public BaseSkinPart(String directoryName, int clockType){
		this.data = new SkinData();
		this.data.directoryName = directoryName;
		this.data.clockType = clockType;
	}
	
	public String getDirectoryPath(){
		StringBuilder path = new StringBuilder();
		path.append(SDCard.getClockSkinPath(this.data.clockType));
		
		return path.toString();
	}
	
	public SkinData readDataText(){
		SkinDataReader reader = new SkinDataReader(data.directoryName, this.data.clockType);
		data = reader.read();
		return data;
	}
}
