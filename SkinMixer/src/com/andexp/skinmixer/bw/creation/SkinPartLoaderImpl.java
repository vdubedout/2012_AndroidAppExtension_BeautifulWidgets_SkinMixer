package com.andexp.skinmixer.bw.creation;

import com.andexp.skinmixer.bw.SkinImpl;

public interface SkinPartLoaderImpl {
	// LOADING SKIN PARTS
	// Load different skin parts to generated Skin
	// link dots to a particular skin part
	// link am/pm to a particular skin part
	void loadSkin(SkinImpl skin);
	void linkDotsTo(int eskinPart);
	void linkAmPmTo(int eskinPart);
	SkinImpl getPreparedSkin();
}
