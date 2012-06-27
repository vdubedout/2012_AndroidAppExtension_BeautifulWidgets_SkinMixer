package com.andexp.skinmixer.test;

import java.io.IOException;

import android.test.AndroidTestCase;
import android.test.IsolatedContext;

import com.andexp.skinmixer.utils.SDCardSkinPathLoader;

public class SDCardSkinPathLoaderTest extends AndroidTestCase {
	IsolatedContext mContext;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = new IsolatedContext(null, getContext());
	}

	public void testSDCardClassExists(){
		assertNotNull(SDCardSkinPathLoader.getInstance());
	}

	public void testSDCardSingleton(){
		assertSame(SDCardSkinPathLoader.getInstance(), SDCardSkinPathLoader.getInstance());
	}

	public void testGetSkinPathsNotNull(){
		assertNotNull(SDCardSkinPathLoader.getInstance().getSuperClockPath());
		assertNotNull(SDCardSkinPathLoader.getInstance().getClassicClockPath());
	}

	public void testGetSkinPathsCorrect(){
		assertTrue(SDCardSkinPathLoader.getInstance().getSuperClockPath().toLowerCase().contains("data/beautifulwidgets/scskins"));
		assertTrue(SDCardSkinPathLoader.getInstance().getClassicClockPath().toLowerCase().contains("data/beautifulwidgets/skins"));
	}

	public void testGetSuperClockPathNotNull() {
		try{
			assertNotNull(SDCardSkinPathLoader.getInstance().getSuperClockDirectory());
			assertNotNull(SDCardSkinPathLoader.getInstance().getClassicClockDirectory());
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public void testSuperClockPathCorrect(){
		try {
			assertTrue(SDCardSkinPathLoader.getInstance().getSuperClockDirectory().toString().contains("data/beautifulwidgets/scskins"));
			assertFalse(SDCardSkinPathLoader.getInstance().getSuperClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testClassicClockPathCorrect(){
		try {
			assertTrue(SDCardSkinPathLoader.getInstance().getClassicClockDirectory().toString().contains("data/beautifulwidgets/skins"));
			assertFalse(SDCardSkinPathLoader.getInstance().getClassicClockDirectory().toString().contains("//"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileExists(){
		try {
			assertTrue(SDCardSkinPathLoader.getInstance().getSuperClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testIsClassicClockFileExists() {
		try {
			assertTrue(SDCardSkinPathLoader.getInstance().getClassicClockDirectory().exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testIsSuperClockFileIsDirectory(){
		try {
			assertTrue(SDCardSkinPathLoader.getInstance().getSuperClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testIsClassicClockFileIsDirectory(){
		try {
			assertTrue(SDCardSkinPathLoader.getInstance().getClassicClockDirectory().isDirectory());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
