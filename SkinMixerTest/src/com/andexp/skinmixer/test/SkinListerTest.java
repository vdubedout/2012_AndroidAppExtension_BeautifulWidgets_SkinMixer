package com.andexp.skinmixer.test;

import java.io.IOException;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.utils.SkinLister;

public class SkinListerTest extends InstrumentationTestCase{
	private final int DEFAULT_ASSETS_FILE_NUMBER=3;
	Context localContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		localContext = getInstrumentation().getContext();
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
	
	public void testAssetsTestFilesNumber() {
		String testList[] = getLocalAssetsList();
		assertEquals(DEFAULT_ASSETS_FILE_NUMBER + 2, testList.length);
	}

	private String[] getLocalAssetsList() {
		try {
			return localContext.getAssets().list("");
		} catch (IOException e) {
			fail("Error accessing Assets list : "+e.getMessage());
		}
		return new String[]{""};
	}
	
	public void testAssetsTestFilesPresent() {
		try {
			assertNotNull(localContext.getAssets().open("scskins.zip"));
			assertNotNull(localContext.getAssets().open("skins.zip"));
		} catch (IOException e) {
			fail("Error accessing Assets list : "+e.getMessage());
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
