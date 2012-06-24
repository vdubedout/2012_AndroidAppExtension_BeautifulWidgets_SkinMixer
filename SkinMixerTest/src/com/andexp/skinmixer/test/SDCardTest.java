package com.andexp.skinmixer.test;

import com.andexp.skinmixer.utils.SDCard;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;
import android.util.Log;

public class SDCardTest extends AndroidTestCase {
	IsolatedContext mContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new IsolatedContext(null, getContext());
	}

	public void testSDCardClassExists(){
		assertNotNull(SDCard.getInstance(mContext));
	}
	
	public void testSDCardSingleton(){
		assertSame(SDCard.getInstance(mContext), SDCard.getInstance(mContext));
	}
	
	public void testGetSkinPathNotNull(){
		assertNotNull(SDCard.getInstance(mContext).getSuperClockSkinPath());
	}
	
	public void testGetSuperClockPathNotNull() {
		assertNotNull(SDCard.getInstance(mContext).getSuperClockDirectory());
	}
	
	public void testSuperClockPathCorrect(){
		assertFalse(SDCard.getInstance(mContext).getSuperClockDirectory().toString().contains("//"));
	}
	
	public void testIsSuperClockPathInGoodPath(){
		String path = SDCard.getInstance(mContext).getSuperClockDirectory().toString();
		assertTrue(path.toLowerCase().contains("data/beautifulwidgets/scskins"));
	}
	
	public void testIsSuperClockFileExists(){
		assertTrue(SDCard.getInstance(mContext).getSuperClockDirectory().exists());
	}
	
	public void testIsSuperClockFileIsDirectory(){
		assertTrue(SDCard.getInstance(mContext).getSuperClockDirectory().isDirectory());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
