package com.andexp.skinmixer.drawablecreation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

public class NinePatchCutter {
	private final int OFFSET_IMAGE = 1;

	public NinePatchCutter() {
	}

	public Bitmap[][] getBitmapNinePatches(String path) {
		try {
			Bitmap bitmapToCut = getBitmapFromSDCard(path);
			ArrayList<Integer> columnBoundsMap = getColumnBoundsMap(bitmapToCut);
			ArrayList<Integer> rowBoundsMap = getRowBoundsMap(bitmapToCut);
			Bitmap[][] bitmapProcessed = getBitmapCutted(bitmapToCut, columnBoundsMap, rowBoundsMap);
			return bitmapProcessed;
		} catch (Exception e) {
			return null;
		}
	}

	protected Bitmap getBitmapFromSDCard(String path) throws IOException {
		if (new File(path).exists())
			return BitmapFactory.decodeFile(path);
		else
			throw new IOException("Image not found");
	}

	protected ArrayList<Integer> getColumnBoundsMap(Bitmap bitmapToCut) {
		int[] pixelLine = new int[bitmapToCut.getWidth()];
		bitmapToCut.getPixels(pixelLine, 0, bitmapToCut.getWidth() - 1, 0, 0,
				bitmapToCut.getWidth() - 1, 1);
		return getLineBounds(pixelLine);
	}

	protected ArrayList<Integer> getLineBounds(int[] pixelLine) {
		ArrayList<Integer> mBounds = new ArrayList<Integer>();

		int length = pixelLine.length;
		mBounds.add(1);
		if (pixelLine[0] == Color.BLACK)
			mBounds.add(1);
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

	protected ArrayList<Integer> getRowBoundsMap(Bitmap bitmapToCut) {
		int[] pixelLine = new int[bitmapToCut.getHeight()];
		bitmapToCut.getPixels(pixelLine, 0, 1, 0, 0, 1, bitmapToCut.getHeight() - 1);
		return getLineBounds(pixelLine);
	}

	protected Bitmap[][] getBitmapCutted(Bitmap bitmapToCut, ArrayList<Integer> columnsBoundsMap,
			ArrayList<Integer> rowsBoundsMap) {
		Bitmap[][] bitmapPreviewArray = new Bitmap[columnsBoundsMap.size() - 1][rowsBoundsMap.size() - 1];

		for (int rowBoundIndex = 0; rowBoundIndex < columnsBoundsMap.size(); rowBoundIndex++) {
			for (int columnBoundIndex = 0; columnBoundIndex < rowsBoundsMap.size(); columnBoundIndex++) {
				int x = columnsBoundsMap.get(columnBoundIndex);
				int y = rowsBoundsMap.get(rowBoundIndex);
				int width = getSizeValue(rowBoundIndex, rowsBoundsMap);
				int height = getSizeValue(columnBoundIndex, columnsBoundsMap);
				Bitmap tmpBmp = Bitmap.createBitmap(bitmapToCut, x, y, width, height); 
				bitmapPreviewArray[rowBoundIndex][columnBoundIndex] = tmpBmp;
			}
		}

		return bitmapPreviewArray;
	}

	private int getSizeValue(int boundIndex, ArrayList<Integer> boundsMap) {
		return boundsMap.get(boundIndex + 1) - boundsMap.get(boundIndex);
	}
}
