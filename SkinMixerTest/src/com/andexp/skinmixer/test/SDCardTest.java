package com.andexp.skinmixer.test;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import com.andexp.skinmixer.utils.SDCard;

public class SDCardTest extends AndroidTestCase {
	IsolatedContext mContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new IsolatedContext(null, getContext());
	}

	public void testSDCardClassExists(){
		assertNotNull(SDCard.getInstance());
	}
	
	public void testSDCardSingleton(){
		assertSame(SDCard.getInstance(), SDCard.getInstance());
	}
	
	public void testGetSkinPathNotNull(){
		assertNotNull(SDCard.getInstance().getSuperClockSkinPath());
	}
	
	public void testGetSkinPathCorrect(){
		assertTrue(SDCard.getInstance().getSuperClockSkinPath().toLowerCase().contains("data/beautifulwidgets/scskins"));
	}
	
	public void testGetSuperClockPathNotNull() {
		assertNotNull(SDCard.getInstance().getSuperClockDirectory());
	}
	
	public void testSuperClockPathCorrect(){
		assertFalse(SDCard.getInstance().getSuperClockDirectory().toString().contains("//"));
	}
	
	public void testIsSuperClockPathInGoodPath(){
		String path = SDCard.getInstance().getSuperClockDirectory().toString();
		assertTrue(path.toLowerCase().contains("data/beautifulwidgets/scskins"));
	}
	
	public void testIsSuperClockFileExists(){
		assertTrue(SDCard.getInstance().getSuperClockDirectory().exists());
	}
	
	public void testIsSuperClockFileIsDirectory(){
		assertTrue(SDCard.getInstance().getSuperClockDirectory().isDirectory());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
