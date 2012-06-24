package com.andexp.skinmixer.test;

import java.io.IOException;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import com.andexp.skinmixer.utils.SkinDir;

public class SkinDirTest extends AndroidTestCase {
	IsolatedContext mContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new IsolatedContext(null, getContext());
	}

	public void testSDCardClassExists(){
		assertNotNull(SkinDir.getInstance());
	}

	public void testSDCardSingleton(){
		assertSame(SkinDir.getInstance(), SkinDir.getInstance());
	}

	public void testGetSkinPathsNotNull(){
		assertNotNull(SkinDir.getInstance().getSuperClockPath());
		assertNotNull(SkinDir.getInstance().getClassicClockPath());
	}

	public void testGetSkinPathsCorrect(){
		assertTrue(SkinDir.getInstance().getSuperClockPath().toLowerCase().contains("data/beautifulwidgets/scskins"));
		assertTrue(SkinDir.getInstance().getClassicClockPath().toLowerCase().contains("data/beautifulwidgets/skins"));
	}

	public void testGetSuperClockPathNotNull() {
		try{
			assertNotNull(SkinDir.getInstance().getSuperClockDirectory());
			assertNotNull(SkinDir.getInstance().getClassicClockDirectory());
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void testSuperClockPathCorrect(){
		try {
			assertTrue(SkinDir.getInstance().getSuperClockDirectory().toString().contains("data/beautifulwidgets/scskins"));
			assertFalse(SkinDir.getInstance().getSuperClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testClassicClockPathCorrect(){
		try {
			assertTrue(SkinDir.getInstance().getClassicClockDirectory().toString().contains("data/beautifulwidgets/skins"));
			assertFalse(SkinDir.getInstance().getClassicClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileExists(){
		try {
			assertTrue(SkinDir.getInstance().getSuperClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testIsClassicClockFileExists() {
		try {
			assertTrue(SkinDir.getInstance().getClassicClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileIsDirectory(){
		try {
			assertTrue(SkinDir.getInstance().getSuperClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testIsClassicClockFileIsDirectory(){
		try {
			assertTrue(SkinDir.getInstance().getClassicClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
