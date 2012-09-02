package com.andexp.skinmixer.utils;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.TestingAssets;

public class SkinListerTest extends InstrumentationTestCase{
	private TestingAssets assets;
	private Context mContext;
	private SkinLister lister;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		
		lister = SkinLister.getInstance();
		lister.setBasePath(SDCardSkinPath.BASE_PATH+"tests/");
		
		assets = new TestingAssets(mContext, lister.getBasePath());
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
	
	public void testSuperClockListNotEmpty(){
		assertTrue(SkinLister.getInstance().getSuperClockSkinList().size() >= 1);
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
//		assets.delete();
//		mSdCardPath.resetBasePath();
	}
	
	
}
