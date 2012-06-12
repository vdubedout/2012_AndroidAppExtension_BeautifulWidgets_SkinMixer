package com.andexp.skinmixer.bw.creation;

import com.andexp.skinmixer.bw.SkinImpl;

public interface SkinDataBuilderImpl {

	// BUILD DATA SKIN
	// Set new Author to generated skin
	// Generate directoryName 
	// Generate skin text data
	// Write generated skin data to txt file
	
	void loadDataFromSkin(SkinImpl skin);
	void loadAuthor(String author);
	void loadSkinName(String skinName);
	
	void makeRepertory();
	void makeDataTextFile() throws Exception;
}
