package com.andexp.skinmixer.test;

import java.io.IOException;

import com.andexp.skinmixer.utils.SkinLister;

import android.test.AndroidTestCase;

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
	
	public void testAssetsTestFilesPresent() {
		try {
			assertNotNull(getContext().getAssets().list(""));
		} catch (IOException e) {
			fail("Not any files listed : "+e.getMessage());
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
