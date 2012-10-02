package com.andexp.skinmixer.path;

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
		assertSame(lister, SkinLister.getInstance());
	}
	
	public void testSuperClockListNotNull(){
		assertNotNull(lister.getSuperClockSkinPathList());
	}
	
	public void testSuperClockListNotEmpty(){
		assertTrue(lister.getSuperClockSkinPathList().size() >= 1);
	}
	
	public void testSuperClockListCorrectlyFilled(){
		assertEquals(3, lister.getSuperClockSkinPathList().size());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		assets.delete();
		lister.resetBasePath();
	}
}
