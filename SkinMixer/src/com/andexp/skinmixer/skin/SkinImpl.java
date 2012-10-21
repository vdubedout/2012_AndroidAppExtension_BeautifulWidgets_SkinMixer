package com.andexp.skinmixer.skin;

import android.os.Bundle;

import com.andexp.skinmixer.fragment.SkinPartType;
import com.andexp.skinmixer.skindata.SkinData;

public interface SkinImpl {
	public SkinData getData(); //if not read, read file

	public SkinPartImpl getPart(SkinPartType partType);
	public void setPart(SkinPartImpl skinPart, SkinPartType partType);
	
	public SkinGroupImpl getGroup(SkinGroupType groupType);
	public void setGroup(SkinGroupImpl skinGroup, SkinGroupType groupType);
	
	public boolean isSkinComplete();
//	public void build(AsyncBuildListener listener);

	public Bundle toBundle();
	
}
