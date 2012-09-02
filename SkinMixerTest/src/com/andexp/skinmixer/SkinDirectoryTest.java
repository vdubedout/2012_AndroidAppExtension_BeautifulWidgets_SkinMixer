package com.andexp.skinmixer;

import android.test.AndroidTestCase;

import com.andexp.skinmixer.SkinDirectory;

public class SkinDirectoryTest extends AndroidTestCase{

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSkinNotNull(){
		assertNotNull(new SkinDirectory(""));
	}
	
	public void testSkinPathCorrectlySet() {
		SkinDirectory mSkinFile = new SkinDirectory("toto");
		assertNotNull(mSkinFile.getPath());
		assertSame("toto", mSkinFile.getPath());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
