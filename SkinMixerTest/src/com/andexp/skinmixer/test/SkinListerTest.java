package com.andexp.skinmixer.test;

import android.test.AndroidTestCase;

import com.andexp.skinmixer.utils.SkinLister;

public class SkinListerTest extends AndroidTestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSkinListerNotNull() {
		assertNotNull(SkinLister.getInstance());
	}
	
	public void testSkinListerSingleton() {
		assertSame(SkinLister.getInstance(), SkinLister.getInstance());
	}
	
	public void testSuperClockListNotNull(){
		assertNotNull(SkinLister.getInstance().getSuperClockSkinList());
	}
	
//	public void testSuperClockListNotEmpty(){
//		assertTrue(SkinLister.getInstance().getSuperClockSkinList().size() >= 1);
//	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
}
