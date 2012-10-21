package com.andexp.skinmixer.skin;

import com.andexp.skinmixer.displaygroup.SkinPartType;

public interface SkinPartImpl extends SkinGroupImpl {

	public String getPath(); // full path (path+filename)

	public String getFileName();

	public SkinPartType getPartType();
}
