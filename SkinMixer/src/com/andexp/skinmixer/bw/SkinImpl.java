package com.andexp.skinmixer.bw;

import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.bw.part.base.text.SkinData;

public interface SkinImpl {
	
	public SkinData getSkinData();
	public SkinPartImpl getSkinPart(int skinPartType);
	
	public void setFromRepertory(String repertoryName, int clockType); //TODO merge with below
	public void setSkinPartByRepertory(String directoryName, int skinPartType,  int clockType);
	public void setSkinPart(SkinPartImpl skinPart);
	
	public boolean isSkinHasMinimumToGenerate();
	public void fillMissingSkinParts();
	public boolean isSkinComplete();
	public void build(SkinBuilderListener listener);
	
}
