package com.andexp.skinmixer.test;

import java.io.IOException;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import com.andexp.skinmixer.utils.SDCard;

public class SDCardTest extends AndroidTestCase {
	IsolatedContext mContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new IsolatedContext(null, getContext());
	}

	public void testSDCardClassExists(){
		assertNotNull(SDCard.getInstance());
	}

	public void testSDCardSingleton(){
		assertSame(SDCard.getInstance(), SDCard.getInstance());
	}

	public void testGetSkinPathNotNull(){
		assertNotNull(SDCard.getInstance().getSuperClockSkinPath());
	}

	public void testGetSkinPathCorrect(){
		assertTrue(SDCard.getInstance().getSuperClockSkinPath().toLowerCase().contains("data/beautifulwidgets/scskins"));
	}

	public void testGetSuperClockPathNotNull() {
		try{
			assertNotNull(SDCard.getInstance().getSuperClockDirectory());
		} catch (IOException e){

		}
	}

	public void testSuperClockPathCorrect(){
		try {
			assertFalse(SDCard.getInstance().getSuperClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockPathInGoodPath(){
		String path;
		try {
			path = SDCard.getInstance().getSuperClockDirectory().toString();
			assertTrue(path.toLowerCase().contains("data/beautifulwidgets/scskins"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileExists(){
		try {
			assertTrue(SDCard.getInstance().getSuperClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileIsDirectory(){
		try {
			assertTrue(SDCard.getInstance().getSuperClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
