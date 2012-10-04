package com.andexp.skinmixer.path;

import java.io.File;
import java.io.IOException;

import com.andexp.skinmixer.LocalAssetsTest;
import com.andexp.skinmixer.path.SDCardSkinPath;
import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.zipextractor.ZipExtractor;

import android.content.Context;
import android.content.res.AssetManager;

public class TestingAssets {
	public static String[] SKIN_DIRECTORY_ZIP = new String[]{"scskins.zip","skins.zip"};
	public static String[] SUPER_SKINS = new String[]{"bordering-ice-red", "golden", "icsphoenix"};
	public static String[] CLASSIC_SKINS = new String[]{"kawaplus","sunburn"};
	private ZipExtractor mZipExtractor;
	private Context mContext;
	private String mPath;
	private SkinLister lister;
	
	public TestingAssets(Context context) {
		mContext = context;
		
		lister = SkinLister.getInstance();
		lister.setBasePath(SDCardSkinPath.BASE_PATH+"tests/");

		mPath = lister.getBasePath();
		mZipExtractor = new ZipExtractor(mPath);
	}
	
	public void extract() {
		for (String mZip : LocalAssetsTest.SKIN_DIRECTORY_ZIP) {
				try {
					mZipExtractor.extractZipFile(mContext.getAssets().open(mZip, AssetManager.ACCESS_STREAMING));
				} catch (IOException e) {
					System.out.print("Error extracting zip files");
				}
		}
	}
	
	
	public void delete(){
		deleteDir(new File(mPath));
		lister.resetBasePath();
	}
	
	protected static boolean deleteDir(File dir) {
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
