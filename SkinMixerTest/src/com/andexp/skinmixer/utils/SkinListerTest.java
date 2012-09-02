package com.andexp.skinmixer.utils;

import android.test.AndroidTestCase;

import com.andexp.skinmixer.TestingAssets;
import com.andexp.skinmixer.utils.SDCardSkinPath;
import com.andexp.skinmixer.utils.SkinLister;

public class SkinListerTest extends AndroidTestCase{
	private SDCardSkinPath mSdCardPath;
	private TestingAssets assets;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mSdCardPath = SDCardSkinPath.getInstance();
		mSdCardPath.setBasePath(SDCardSkinPath.BASE_PATH+"tests/");
		assets = new TestingAssets(getContext(), mSdCardPath.getBasePath());
		assets.extract();
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
		assets.delete();
		mSdCardPath.resetBasePath();
	}
	
	
}
