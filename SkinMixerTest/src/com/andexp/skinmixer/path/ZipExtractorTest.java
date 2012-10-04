package com.andexp.skinmixer.path;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.LocalAssetsTest;
import com.andexp.skinmixer.zipextractor.ZipExtractor;

public class ZipExtractorTest extends InstrumentationTestCase {
	final String baseTestDirectory = "tests/";
	TestingAssets assets;
	
	ZipExtractor mZipExtractor;
	Context mContext;
	String testPath;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();

		assets = new TestingAssets(mContext);
		assets.extract();
		
		testPath = SkinLister.getInstance().getBasePath();
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
		String mSuperClockDir = SkinLister.getInstance().getSuperClockPath();
		for(String mSkin : LocalAssetsTest.SUPER_SKINS){
			assertTrue(new File(mSuperClockDir+mSkin).isDirectory());
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		assets.delete();
		super.tearDown();
	}
}
