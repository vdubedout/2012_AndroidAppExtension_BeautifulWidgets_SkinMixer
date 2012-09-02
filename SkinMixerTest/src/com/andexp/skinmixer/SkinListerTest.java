package com.andexp.skinmixer;

import android.os.Environment;
import android.test.AndroidTestCase;

import com.andexp.skinmixer.utils.SDCardSkinPath;
import com.andexp.skinmixer.utils.SkinLister;

public class SkinListerTest extends AndroidTestCase{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		String testPath = modifyBasePathToTest();
//		TestingAssets assets = new TestingAssets(getContext(), testPath);
	}
	
//	private String modifyBasePathToTest() {
//		String testPath;
//		SDCardSkinPath.BASE_PATH += "/test";
//		testPath = Environment.getExternalStorageDirectory() + SDCardSkinPath.BASE_PATH;
//		return testPath;
//	}

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
