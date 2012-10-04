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
	private final int OFFSET_IMAGE = 1;
	private Context mContext;

	public NinePatchCutter(Context ctx) {
		mContext = ctx;
	}

	public Bitmap[][] getBitmapCutted(String path, SkinPartType skinPartType) {
		try {
			Bitmap bitmapToCut = loadBitmapFromSDCard(path);
			ArrayList<Integer> widthBoundsMap = getWidthBoundsMap(bitmapToCut);
			ArrayList<Integer> heightBoundsMap = getHeightBoundsMap(bitmapToCut);
			Bitmap[][] bitmapProcessed = getBitmapCutted(bitmapToCut, widthBoundsMap,
					heightBoundsMap);
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	private Bitmap loadBitmapFromSDCard(String path) throws IOException {
		if (new File(path).exists())
			return BitmapFactory.decodeFile(path);
		else
			throw new IOException("Image not found");
	}

	private ArrayList<Integer> getWidthBoundsMap(Bitmap bitmapToCut) {
		int[] pixelLine = new int[bitmapToCut.getWidth()];
		bitmapToCut.getPixels(pixelLine, 0, bitmapToCut.getWidth() - 1, 0, 0,
				bitmapToCut.getWidth() - 1, 1);
		return getLineBounds(pixelLine);
	}

	private ArrayList<Integer> getLineBounds(int[] pixelLine) {
		ArrayList<Integer> mBounds = new ArrayList<Integer>();

		int length = pixelLine.length;
		mBounds.add(0);
		if (pixelLine[0] == Color.BLACK)
			mBounds.add(0);
		for (int i = 1; i < length; i++) {
			if (pixelLine[i] == Color.BLACK && pixelLine[i - 1] != Color.BLACK)
				mBounds.add(i);
			else if (pixelLine[i] != Color.BLACK && pixelLine[i - 1] == Color.BLACK)
				mBounds.add(i);
		}
		if (pixelLine[length - 1] == Color.BLACK)
			mBounds.add(length - 1);
		mBounds.add(length - 1);

		return mBounds;
	}

	private ArrayList<Integer> getHeightBoundsMap(Bitmap bitmapToCut) {
		int[] pixelLine = new int[bitmapToCut.getHeight()];
		bitmapToCut.getPixels(pixelLine, 0, 1, 0, 0, 1, bitmapToCut.getHeight() - 1);
		return getLineBounds(pixelLine);
	}

	private Bitmap[][] getBitmapCutted(Bitmap bitmapToCut, ArrayList<Integer> widthBoundsMap,
			ArrayList<Integer> heightBoundsMap) {
		Bitmap[][] bitmapPreviewArray = new Bitmap[widthBoundsMap.size() - 1][3];

		for (int widthBoundIndex = 0; widthBoundIndex < widthBoundsMap.size(); widthBoundIndex++) {
			for (int heightBoundIndex = 0; heightBoundIndex < heightBoundsMap.size(); heightBoundIndex++) {
				int x = getStartingValue(widthBoundIndex, widthBoundsMap);
				int y = getStartingValue(heightBoundIndex, heightBoundsMap);
				int width = getSizeValue(widthBoundIndex, widthBoundsMap);
				int height = getSizeValue(heightBoundIndex, heightBoundsMap);
				Bitmap tmpBmp = Bitmap.createBitmap(bitmapToCut, x, y, width, height); 
				bitmapPreviewArray[widthBoundIndex][heightBoundIndex] = tmpBmp;
			}
		}

		return null;
	}

	private int getStartingValue(int boundIndex, ArrayList<Integer>boundsMap) {
		if(boundIndex==0)
			return boundsMap.get(boundIndex)+OFFSET_IMAGE;
		else return boundsMap.get(boundIndex);
	}

	private int getSizeValue(int boundIndex, ArrayList<Integer> boundsMap) {
		return boundsMap.get(boundIndex + 1) - boundsMap.get(boundIndex);
	}
}
