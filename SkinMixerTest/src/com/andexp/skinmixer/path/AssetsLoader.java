package com.andexp.skinmixer.path;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.res.AssetManager;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.zipextractor.ZipExtractor;

public class AssetsLoader extends InstrumentationTestCase {
	public static String[] SKIN_DIRECTORY_ZIP = new String[] { "data_test.zip" };
	private ZipExtractor mZipExtractor;
	private Context mContext;
	private String mPath;
	private SkinLister mSkinLister;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		
		mSkinLister = SkinLister.getInstance();
		mPath = mSkinLister.getBasePath();
		mZipExtractor = new ZipExtractor(mPath);
		delete();
		extract();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void extract() {
		for (String mZip : SKIN_DIRECTORY_ZIP) {
			try {
				mZipExtractor.extractZipFile(mContext.getAssets().open(mZip, AssetManager.ACCESS_STREAMING));
			} catch (IOException e) {
				System.out.print("Error extracting zip files");
			}
		}
	}
	
	public void testAssetsExtracted(){
		assertTrue(mSkinLister.getSuperClockSkinPathList().size() > 1);
	}
	

	public void delete() {
		deleteDir(new File(mPath));
	}

	protected static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		return dir.delete();
	}

}
