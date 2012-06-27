package com.andexp.skinmixer.test;

import android.test.AndroidTestCase;

import com.andexp.skinmixer.SkinFile;

public class SkinTest extends AndroidTestCase{

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSkinNotNull(){
		assertNotNull(new SkinFile(""));
	}
	
	public void testSkinPathCorrectlySet() {
		SkinFile mSkinFile = new SkinFile("toto");
		assertNotNull(mSkinFile.getPath());
		assertSame("toto", mSkinFile.getPath());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
