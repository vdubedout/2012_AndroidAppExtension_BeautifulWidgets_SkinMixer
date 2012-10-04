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
			ArrayList<Integer> heightBoundsMap = mNinePatchCutter.getHeightBoundsMap(backgroundBitmap);
			assertNotNull(heightBoundsMap);
			assertEquals(4, heightBoundsMap.size());
			
			ArrayList<Integer> widthBoundsMap = mNinePatchCutter.getWidthBoundsMap(backgroundBitmap);
			assertNotNull(widthBoundsMap);
			assertEquals(4, widthBoundsMap.size());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	

	@Override
	protected void tearDown() throws Exception {
		mAssets.delete();
		super.tearDown();
	}
}
