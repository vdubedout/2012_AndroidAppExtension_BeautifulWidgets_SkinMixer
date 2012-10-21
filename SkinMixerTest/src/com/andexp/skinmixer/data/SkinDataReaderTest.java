package com.andexp.skinmixer.data;

import com.andexp.skinmixer.path.AssetsLoader;

import android.test.AndroidTestCase;

public class SkinDataReaderTest extends AndroidTestCase{
	private AssetsLoader mAssets;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mAssets = new AssetsLoader(getContext(), "test");
		mAssets.extract();
		
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

}
