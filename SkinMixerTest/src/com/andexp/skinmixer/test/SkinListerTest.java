package com.andexp.skinmixer.test;

import android.test.AndroidTestCase;

import com.andexp.skinmixer.utils.SkinLister;

public class SkinListerTest extends AndroidTestCase{
	
	
	public void testSkinListerNotNull() {
		assertNotNull(SkinLister.getInstance());
	}
	
	public void testSkinListerSingleton() {
		assertSame(SkinLister.getInstance(), SkinLister.getInstance());
	}
	
	public void testSuperClockListNotNull(){
		assertNotNull(SkinLister.getInstance().getSuperClockSkinList());
	}
	
	
	
}
