package com.andexp.skinmixer.zipextractor;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.LocalAssetsTest;
import com.andexp.skinmixer.utils.SDCardSkinPath;

public class ZipExtractorTest extends InstrumentationTestCase {
	final String baseTestDirectory = "tests/";

	ZipExtractor mZipExtractor;
	Context mContext;
	String testPath;
	SDCardSkinPath path;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		testPath = getBaseTestPath();
		
		mZipExtractor = new ZipExtractor(testPath);
		extractZips();
	}

	private String getBaseTestPath() {
		path = new SDCardSkinPath(SDCardSkinPath.BASE_PATH + baseTestDirectory);
		testPath = path.getBasePath();
		return testPath;
	}
	
	private void extractZips() {
		for (String mZip : LocalAssetsTest.SKIN_DIRECTORY_ZIP) {
			try {
				mZipExtractor.extractZipFile(mContext.getAssets().open(mZip, AssetManager.ACCESS_STREAMING));
			} catch (IOException e) {
				e.printStackTrace();
				fail("Fail getting assets");
			}
		}
	}
	
	
	public void testSDCardMounted(){
		String state = Environment.getExternalStorageState();
		assertTrue(state.equalsIgnoreCase(Environment.MEDIA_MOUNTED));
	}
	
	
	public void testZipExtractorNotNull() {
		assertNotNull(mZipExtractor);
	}
	
	public void testDestinationPathCreated(){
		mZipExtractor.createDestinationPath();
		assertTrue(new File(testPath).isDirectory());
	}
	
	public void testBaseDirectoryCreated(){
		for (String mZip : LocalAssetsTest.SKIN_DIRECTORY_ZIP) {
			File mFile = new File(testPath+mZip.substring(0, mZip.indexOf(".")));
			assertFalse(mFile.toString().contains("."));
			assertTrue(mFile.isDirectory());
		}
	}
	
	public void testSuperClockSkinsDirectoryCreated(){
		String mSuperClockDir = path.getSuperClockPath();
		for(String mSkin : LocalAssetsTest.SUPER_SKINS){
			assertTrue(new File(mSuperClockDir+mSkin).isDirectory());
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		deleteDir(new File(testPath));
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
