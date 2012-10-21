package com.andexp.skinmixer.path;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.test.InstrumentationTestCase;


public class SkinListerTest extends InstrumentationTestCase{
	private AssetsLoader assets;
	private Context mContext;
	private SkinLister lister;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		
		assets = new AssetsLoader(mContext, "test");
		assets.extract();
		
		lister = SkinLister.getInstance();
	}

	public void testSuperClockListNotEmpty(){
		assertTrue(lister.getSuperClockSkinPathList().size() >= 1);
	}
	
	public void testListNotAddingToItself(){
		assertEquals(lister.getSuperClockSkinPathList().size(), lister.getSuperClockSkinPathList().size());
	}
	
	public void testSuperClockListCorrectlyFilled(){
		assertEquals(3, lister.getSuperClockSkinPathList().size());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		assets.delete();
	}
}
