package com.andexp.skinmixer;

import java.io.IOException;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import com.andexp.skinmixer.path.SDCardSkinPath;

public class SDCardSkinPathTest extends AndroidTestCase {
	IsolatedContext mContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new IsolatedContext(null, getContext());
	}

	public void testSDCardClassExists(){
		assertNotNull(SDCardSkinPath.getInstance());
	}

	public void testSDCardSingleton(){
		assertSame(SDCardSkinPath.getInstance(), SDCardSkinPath.getInstance());
	}

	public void testGetSkinPathsNotNull(){
		assertNotNull(SDCardSkinPath.getInstance().getSuperClockPath());
		assertNotNull(SDCardSkinPath.getInstance().getClassicClockPath());
	}

	public void testGetSkinPathsCorrect(){
		assertTrue(SDCardSkinPath.getInstance().getSuperClockPath().toLowerCase().contains("data/beautifulwidgets/scskins"));
		assertTrue(SDCardSkinPath.getInstance().getClassicClockPath().toLowerCase().contains("data/beautifulwidgets/skins"));
	}

	public void testGetSuperClockPathNotNull() {
		try{
			assertNotNull(SDCardSkinPath.getInstance().getSuperClockDirectory());
			assertNotNull(SDCardSkinPath.getInstance().getClassicClockDirectory());
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void testSuperClockPathCorrect(){
		try {
			assertTrue(SDCardSkinPath.getInstance().getSuperClockDirectory().toString().contains("data/beautifulwidgets/scskins"));
			assertFalse(SDCardSkinPath.getInstance().getSuperClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testClassicClockPathCorrect(){
		try {
			assertTrue(SDCardSkinPath.getInstance().getClassicClockDirectory().toString().contains("data/beautifulwidgets/skins"));
			assertFalse(SDCardSkinPath.getInstance().getClassicClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileExists(){
		try {
			assertTrue(SDCardSkinPath.getInstance().getSuperClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testIsClassicClockFileExists() {
		try {
			assertTrue(SDCardSkinPath.getInstance().getClassicClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileIsDirectory(){
		try {
			assertTrue(SDCardSkinPath.getInstance().getSuperClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testIsClassicClockFileIsDirectory(){
		try {
			assertTrue(SDCardSkinPath.getInstance().getClassicClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
