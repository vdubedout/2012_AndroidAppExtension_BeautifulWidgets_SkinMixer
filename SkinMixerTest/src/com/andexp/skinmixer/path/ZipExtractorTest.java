package com.andexp.skinmixer.path;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.LocalAssetsTest;
import com.andexp.skinmixer.TestingAssets;
import com.andexp.skinmixer.path.SDCardSkinPath;
import com.andexp.skinmixer.zipextractor.ZipExtractor;

public class ZipExtractorTest extends InstrumentationTestCase {
	final String baseTestDirectory = "tests/";
	TestingAssets assets;
	
	ZipExtractor mZipExtractor;
	Context mContext;
	String testPath;
	SDCardSkinPath path;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		testPath = getBaseTestPath();
		assets = new TestingAssets(mContext, testPath);
		assets.extract();
	}

	private String getBaseTestPath() {
		path = new SDCardSkinPath();
		path.setBasePath(SDCardSkinPath.BASE_PATH + baseTestDirectory);
		testPath = path.getBasePath();
		return testPath;
	}
	
	public void testSDCardMounted(){
		String state = Environment.getExternalStorageState();
		assertTrue(state.equalsIgnoreCase(Environment.MEDIA_MOUNTED));
	}
	
	public void testDestinationPathCreated(){
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
		assets.delete();
		path.resetBasePath();
		super.tearDown();
	}
}
