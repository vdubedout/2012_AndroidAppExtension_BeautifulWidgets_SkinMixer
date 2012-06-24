package com.andexp.skinmixer.test;

import java.io.IOException;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import com.andexp.skinmixer.utils.SDCard;

public class SDCardPathTest extends AndroidTestCase {
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

	public void testGetSkinPathsNotNull(){
		assertNotNull(SDCard.getInstance().getSuperClockSkinPath());
		assertNotNull(SDCard.getInstance().getClassicClockPath());
	}

	public void testGetSkinPathsCorrect(){
		assertTrue(SDCard.getInstance().getSuperClockSkinPath().toLowerCase().contains("data/beautifulwidgets/scskins"));
		assertTrue(SDCard.getInstance().getClassicClockPath().toLowerCase().contains("data/beautifulwidgets/skins"));
	}

	public void testGetSuperClockPathNotNull() {
		try{
			assertNotNull(SDCard.getInstance().getSuperClockDirectory());
			assertNotNull(SDCard.getInstance().getClassicClockDirectory());
		} catch (IOException e){

		}
	}

	public void testSuperClockPathCorrect(){
		try {
			assertTrue(SDCard.getInstance().getSuperClockDirectory().toString().contains("data/beautifulwidgets/scskins"));
			assertFalse(SDCard.getInstance().getSuperClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testClassicClockPathCorrect(){
		try {
			assertTrue(SDCard.getInstance().getClassicClockDirectory().toString().contains("data/beautifulwidgets/skins"));
			assertFalse(SDCard.getInstance().getClassicClockDirectory().toString().contains("//"));
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
	
	public void testIsClassicClockFileExists() {
		try {
			assertTrue(SDCard.getInstance().getClassicClockDirectory().exists());
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
