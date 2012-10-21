package com.andexp.skinmixer.drawablecreation;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.test.InstrumentationTestCase;

import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.path.AssetsLoader;
import com.andexp.skinmixer.skin.SkinGroupType;

public class SkinPartPreviewTest extends InstrumentationTestCase {
	final String BACKGROUND_IMAGE = "golden/background.png";
	final String FOREGROUND_IMAGE = "golden/background_numbers.png";
	
	AssetsLoader mAssets;
	private Context mContext;
	private NinePatchCutter mNinePatchCutter;
	private BitmapComposer mBitmapComposer;

	private String mBackgroundImagePath;
	private String mForegroundImagePath;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mContext = getInstrumentation().getContext();
		
		mAssets = new AssetsLoader(mContext, "test");
		mAssets.extract();
		
		mNinePatchCutter = new NinePatchCutter();
		mBitmapComposer = new BitmapComposer();
		mBackgroundImagePath = SkinLister.getInstance().getSuperClockPath()+BACKGROUND_IMAGE;
		mForegroundImagePath = SkinLister.getInstance().getSuperClockPath()+FOREGROUND_IMAGE;
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
	
	public void testImageComposer(){
		Bitmap[][] bitmapArray = mNinePatchCutter.getBitmapNinePatches(mBackgroundImagePath);
		Bitmap bitmapSkinPartPreview = mBitmapComposer.getAssembledBitmap(bitmapArray, SkinGroupType.BACKGROUND);
		assertNotNull(bitmapSkinPartPreview);
	}
	
	public void testForegroundLoaded(){
		try {
			Bitmap foregroundBitmap = mNinePatchCutter.getBitmapFromSDCard(mForegroundImagePath); 
			assertNotNull(foregroundBitmap);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	public void testForegroundBounds(){
		try {
			Bitmap foregroundBitmap = mNinePatchCutter.getBitmapFromSDCard(mForegroundImagePath);
			ArrayList<Integer> columnBoundsMap = mNinePatchCutter.getColumnBoundsMap(foregroundBitmap);
			ArrayList<Integer> rowBoundsMap = mNinePatchCutter.getRowBoundsMap(foregroundBitmap);
			
			assertNotNull(columnBoundsMap);
			assertEquals(6, columnBoundsMap.size());
			
			assertNotNull(rowBoundsMap);
			assertEquals(4, rowBoundsMap.size());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	public void testForegroundCutted(){
		Bitmap[][] bitmapNinePatches = mNinePatchCutter.getBitmapNinePatches(mForegroundImagePath);
		assertNotNull(bitmapNinePatches);
		assertEquals(3, bitmapNinePatches.length);
		assertEquals(5, bitmapNinePatches[0].length);
	}
	

	@Override
	protected void tearDown() throws Exception {
		mAssets.delete();
		super.tearDown();
	}
}
