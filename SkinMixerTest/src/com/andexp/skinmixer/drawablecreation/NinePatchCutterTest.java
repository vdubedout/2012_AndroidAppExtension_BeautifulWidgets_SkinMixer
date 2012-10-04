package com.andexp.skinmixer.drawablecreation;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.path.TestingAssets;

public class NinePatchCutterTest extends InstrumentationTestCase {
	final String BACKGROUND_IMAGE = "golden/background.png";
	
	TestingAssets mAssets;
	private Context mContext;
	private NinePatchCutter mNinePatchCutter;

	private String mBackgroundImagePath;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		
		mAssets = new TestingAssets(mContext);
		mAssets.extract();
		
		mNinePatchCutter = new NinePatchCutter();
		mBackgroundImagePath = SkinLister.getInstance().getSuperClockPath()+BACKGROUND_IMAGE;
	}
	
	public void testBitmapLoading(){
		try {
			Bitmap backgroundBitmap = mNinePatchCutter.getBitmapFromSDCard(mBackgroundImagePath); 
			assertNotNull(backgroundBitmap);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	public void testBackgroundBounds(){
		try {
			Bitmap backgroundBitmap = mNinePatchCutter.getBitmapFromSDCard(mBackgroundImagePath);
			ArrayList<Integer> rowsBoundsMap = mNinePatchCutter.getRowBoundsMap(backgroundBitmap);
			assertNotNull(rowsBoundsMap);
			assertEquals(4, rowsBoundsMap.size());
			
			ArrayList<Integer> columnBoundsMap = mNinePatchCutter.getColumnBoundsMap(backgroundBitmap);
			assertNotNull(columnBoundsMap);
			assertEquals(4, columnBoundsMap.size());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	
	public void testBackgroundBitmapCutted(){
		Bitmap[][] cuttedBitmap = mNinePatchCutter.getBitmapNinePatches(mBackgroundImagePath);
		assertEquals(3, cuttedBitmap.length);
		assertEquals(3, cuttedBitmap[0].length);
	}

	@Override
	protected void tearDown() throws Exception {
		mAssets.delete();
		super.tearDown();
	}
}
