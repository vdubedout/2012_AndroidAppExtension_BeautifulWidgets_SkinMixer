package com.andexp.skinmixer;

import java.io.IOException;

import com.andexp.skinmixer.zipextractor.ZipExtractor;

import android.content.Context;
import android.content.res.AssetManager;

public class TestingAssets {
	private final int DEFAULT_ASSETS_FILE_NUMBER=3;
	public static String[] SKIN_DIRECTORY_ZIP = new String[]{"scskins.zip","skins.zip"};
	public static String[] SUPER_SKINS = new String[]{"bordering-ice-red", "golden", "icsphoenix"};
	public static String[] CLASSIC_SKINS = new String[]{"kawaplus","sunburn"};
	private ZipExtractor mZipExtractor;
	private Context mContext;	
	
	public TestingAssets(Context context, String path) {
		mContext = context;
		mZipExtractor = new ZipExtractor(path);
	}
	
	public void extractZips() {
		for (String mZip : LocalAssetsTest.SKIN_DIRECTORY_ZIP) {
				try {
					mZipExtractor.extractZipFile(mContext.getAssets().open(mZip, AssetManager.ACCESS_STREAMING));
				} catch (IOException e) {
					System.out.print("Error extracting zip files");
				}
		}
	}
}
