package com.andexp.skinmixer.bw.part.base;

import com.andexp.skinmixer.bw.part.base.text.SkinData;

import android.graphics.drawable.Drawable;

public interface SkinPartImpl {
	public SkinData getSkinPartData();
	public String getDirectoryPath();
	public String getImagePath();
	public String getFileName();
	public Drawable getDrawable();
	
	public int getSkinPartType();
	public int getClockType();
	
	public SkinData readDataText(); //TODO necessaire dans le skinpart?
}
