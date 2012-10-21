package com.andexp.skinmixer.skin;

import com.andexp.skinmixer.fragment.SkinPartType;
import com.andexp.skinmixer.skindata.SkinData;

public interface SkinPartImpl extends SkinGroupImpl {

	public String getPath(); // full path (path+filename)

	public String getFileName();

	public SkinPartType getPartType();
}
