package com.andexp.skinmixer.test;

import com.andexp.skinmixer.utils.SkinLister;

import android.test.AndroidTestCase;

public class SkinListerTest extends AndroidTestCase{

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSkinListerNotNull() {
		assertNotNull(new SkinLister());
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}
