package com.andexp.skinmixer.path;

import java.io.File;
import java.io.IOException;

import android.test.InstrumentationTestCase;

public class TestPaths extends InstrumentationTestCase {
	private static final String DATA_BEAUTIFULWIDGETS = "data/beautifulwidgets/";
	private static final String DATA_SKINS = "skins/";
	private static final String DATA_SCSKINS = "scskins/";
	private SDCardSkinPath sdCardSkinPath;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		sdCardSkinPath = new SDCardSkinPath();
	}

	public void testSDCardClassExists() {
		assertNotNull(sdCardSkinPath);
	}

	public void testGetSkinPathsNotNull() {
		assertNotNull(sdCardSkinPath.getSuperClockPath());
		assertNotNull(sdCardSkinPath.getClassicClockPath());
	}

	public void testGetSkinPathsCorrect() {
		assertTrue(sdCardSkinPath.getSuperClockPath().toLowerCase().contains(DATA_SCSKINS));
		assertTrue(sdCardSkinPath.getClassicClockPath().toLowerCase().contains(DATA_SKINS));
	}

	public void testGetSuperClockPathNotNull() {
		try {
			assertNotNull(sdCardSkinPath.getSuperClockDirectory());
			assertNotNull(sdCardSkinPath.getClassicClockDirectory());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testSuperClockPathCorrect() {
		try {
			String dirPath = sdCardSkinPath.getSuperClockDirectory().toString() + File.separator;
			assertTrue(dirPath, dirPath.contains(DATA_SCSKINS));
			assertFalse(dirPath, dirPath.contains("//"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testClassicClockPathCorrect() {
		try {
			String dirPath = sdCardSkinPath.getClassicClockDirectory().toString() + File.separator;
			assertTrue(dirPath, dirPath.contains(DATA_SKINS));
			assertFalse(dirPath, dirPath.contains("//"));
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testIsSuperClockFileExists() {
		try {
			assertTrue(sdCardSkinPath.getSuperClockDirectory().exists());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testIsClassicClockFileExists() {
		try {
			assertTrue(sdCardSkinPath.getClassicClockDirectory().exists());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testIsSuperClockFileIsDirectory() {
		try {
			assertTrue(sdCardSkinPath.getSuperClockDirectory().isDirectory());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testIsClassicClockFileIsDirectory() {
		try {
			assertTrue(sdCardSkinPath.getClassicClockDirectory().isDirectory());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	public void testSkinListerNotNull() {
		assertNotNull(SkinLister.getInstance());
	}

	public void testSkinListerSingleton() {
		assertSame(SkinLister.getInstance(), SkinLister.getInstance());
	}

	public void testModificationBasePath() {
		String pathModifier = "test/";
		SkinLister lister = SkinLister.getInstance();
		lister.addToBasePath(lister.getBeautifulWidgetsPath() + pathModifier);

		pathReferentContains(lister.getBeautifulWidgetsPath(), DATA_BEAUTIFULWIDGETS + pathModifier);
		pathReferentContains(lister.getSuperClockPath(), DATA_BEAUTIFULWIDGETS + pathModifier
				+ DATA_SCSKINS);

		lister.resetBasePath();
		pathReferentDontContains(lister.getBeautifulWidgetsPath(), DATA_BEAUTIFULWIDGETS
				+ pathModifier);
		pathReferentDontContains(lister.getSuperClockPath(), DATA_BEAUTIFULWIDGETS + pathModifier
				+ DATA_SCSKINS);
	}

	private void pathReferentDontContains(String pathTested, String pathReferent) {
		assertFalse("fail on " + pathTested, pathTested.contains(pathReferent));
	}

	private void pathReferentContains(String pathTested, String pathReferent) {
		assertTrue("fail on " + pathTested, pathTested.contains(pathReferent));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
