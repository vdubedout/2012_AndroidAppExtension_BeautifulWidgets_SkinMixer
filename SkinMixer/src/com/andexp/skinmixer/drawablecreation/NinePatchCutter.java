package com.andexp.skinmixer.drawablecreation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.andexp.skinmixer.fragment.SkinPartType;

public class NinePatchCutter {
	private Context mContext;

	public NinePatchCutter(Context ctx) {
		mContext = ctx;
	}
	
	public Bitmap[][] getBitmapCutted(String path, SkinPartType skinPartType){
		try{
			Bitmap bitmapToCut = loadBitmapFromSDCard(path);
			int widthBoundsMap[] = getWidthBoundsMap(bitmapToCut);
			int heightBoundsMap[] = getHeightBoundsMap(bitmapToCut);
			return null;
		} catch (Exception e){
			return null;
		}
	}

	private Bitmap loadBitmapFromSDCard(String path) throws IOException {
		if(new File(path).exists())
			return BitmapFactory.decodeFile(path);
		else throw new IOException("Image not found");
	}

	private ArrayList<Integer> getWidthBoundsMap(Bitmap bitmapToCut) {
		int[] pixels = new int[bitmapToCut.getWidth()];
		bitmapToCut.getPixels(pixels, 0, bitmapToCut.getWidth() - 1, 0, 0, bitmapToCut.getWidth() - 1, 1);
		return getLineBounds(pixels);
	}

	private ArrayList<Integer> getLineBounds(int[] pixelLine) {
		ArrayList<Integer> mBounds = new ArrayList<Integer>();
		
		int length = pixelLine.length;
		if(pixelLine[0] == Color.BLACK)
			mBounds.add(0);
		for (int i=1; i<length; i++) {
			if(pixelLine[i] == Color.BLACK && pixelLine[i-1] != Color.BLACK)
				mBounds.add(i);
			else if(pixelLine[i]!=Color.BLACK && pixelLine[i-1] == Color.BLACK);
				mBounds.add(i);
		}
		if(pixelLine[length - 1] == Color.BLACK)
			mBounds.add(length - 1);
		
		return mBounds;
	}

	private int[] getHeightBoundsMap(Bitmap bitmapToCut) {
		// TODO Auto-generated method stub
		return null;
	}
}
