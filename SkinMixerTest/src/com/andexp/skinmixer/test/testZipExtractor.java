package com.andexp.skinmixer.test;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.utils.SkinDir;
import com.andexp.skinmixer.utils.ZipExtractor;

public class testZipExtractor extends InstrumentationTestCase {
	ZipExtractor mZipExtractor;
	String testPath;
	Context mContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		testPath = Environment.getExternalStorageDirectory() + SkinDir.BASE_PATH + "tests/";
		deleteDir(new File(testPath));
		mZipExtractor = new ZipExtractor(testPath);
	}
	
	public void testZipExtractorNotNull() {
		assertNotNull(mZipExtractor);
	}
	
	public void testDestinationPathCreated(){
		mZipExtractor.createDestinationPath();
		assertTrue(new File(testPath).isDirectory());
	}
	
	public void testFilesWellExtracted(){
		extractZips();
		controlExtraction();
	}

	private void extractZips() {
		for (String mZip : LocalAssetsTest.mAssetsTestFiles) {
			try {
				mZipExtractor.extractZipFile(mContext.getAssets().open(mZip, AssetManager.ACCESS_STREAMING));
			} catch (IOException e) {
				e.printStackTrace();
				fail("Fail getting assets");
			}
		}
	}
	
	private void controlExtraction(){
		for (String mZip : LocalAssetsTest.mAssetsTestFiles) {
			File mFile = new File(testPath+mZip.substring(0, mZip.indexOf(".")));
			assertFalse(mFile.toString().contains("."));
			assertTrue(mFile.isDirectory());
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
//		deleteDir(new File(testPath));
		super.tearDown();
	}
	
	public static boolean deleteDir(File dir) {
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}

	
}
