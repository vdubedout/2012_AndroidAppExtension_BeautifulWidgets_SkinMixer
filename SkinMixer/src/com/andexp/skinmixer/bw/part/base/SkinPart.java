package com.andexp.skinmixer.bw.part.base;

import java.io.File;

import android.graphics.drawable.Drawable;

import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.utils.SDCard;

public class SkinPart extends BaseSkinPart implements SkinPartImpl {
	
	private final String DEFAULT_REPERTORY_NAME = "%repertoryname%";
	public final String[] FILE_NAME = new String[]{
		"background.png",
		"background_numbers.png",
		"number_0.png",
		"number_1.png",
		"number_2.png",
		"number_3.png",
		"number_4.png",
		"number_5.png",
		"number_6.png",
		"number_7.png",
		"number_8.png",
		"number_9.png",
		"dots.png",
		"am.png",
		"pm.png",
		DEFAULT_REPERTORY_NAME+".jpg",
		DEFAULT_REPERTORY_NAME+".txt"
		};
	
	private String mFileName;
	private int mSkinPartType;
	private Drawable mDrawable;
	
	public SkinPart(String directoryName, int skinPartType, int clockType) {
		super(directoryName, clockType);
		this.mFileName = getSkinPartName(directoryName, skinPartType);
		this.mSkinPartType = skinPartType;
	} 
	
	private String getSkinPartName(String directoryName, int skinPartType){
		String myFileName = FILE_NAME[skinPartType];
		
		if(myFileName.contains(DEFAULT_REPERTORY_NAME))
			myFileName.replace(DEFAULT_REPERTORY_NAME, directoryName);
		
		return myFileName;
	}
	
	public String getImagePath(){
		StringBuilder builder = new StringBuilder();
		builder.append(SDCard.getClockSkinPath(this.data.clockType));
		builder.append(this.data.directoryName);
		builder.append(File.separator);
		builder.append(getFileName());
		return builder.toString();
	}
	
	public String getFileName(){
		return mFileName;
	}

	public Drawable getDrawable(){
		if(mDrawable == null) mDrawable = Drawable.createFromPath(getImagePath());
		return mDrawable;
	}

	
	@Override
	public int getSkinPartType() {
		return mSkinPartType;
	}

	@Override
	public int getClockType() {
		return this.data.clockType;
	}

	@Override
	public SkinData getSkinPartData() {
		return this.data;
	}
}
