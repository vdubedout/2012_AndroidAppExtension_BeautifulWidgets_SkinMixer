package com.andexp.skinmixer.drawablecreation;

import com.andexp.skinmixer.fragment.SkinPartType;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.Rect;

public class BitmapComposer {
	public BitmapComposer() {
	}

	public Bitmap getAssembledBitmap(Bitmap[][] bitmapArray, SkinPartType skinType) {
		if(skinType == SkinPartType.BACKGROUND){
			return getAssembledBackground(bitmapArray);
		} else if(skinType == SkinPartType.BACKGROUND_NUMBERS) {
			return getAssembledBackgroundNumber(bitmapArray);
		} else return null;
	}

	private Bitmap getAssembledBackground(Bitmap[][] bitmapArray) {
		int width = 168;
		int height = 96;
		Canvas canvas = new Canvas(Bitmap.createBitmap(width, height, Config.ARGB_8888));
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
		drawBackgroundCorners(bitmapArray, width, height, canvas, paint);
		Rect middleTop = new Rect(bitmapArray[0][0].getWidth(), 0, width-1-bitmapArray[0][2].getWidth(), bitmapArray[0][1].getHeight());
		canvas.drawBitmap(bitmapArray[0][1], null, middleTop, paint);
		canvas.drawBitmap(bitmapArray[2][1], null, new Rect(height-1-bitmapArray[2][0].getWidth(), 0, width-1-bitmapArray[2][2].getWidth(), bitmapArray[0][1].getHeight()), paint);
		
		return null;
	}

	private void drawBackgroundCorners(Bitmap[][] bitmapArray, int width, int height, Canvas canvas, Paint paint) {
		canvas.drawBitmap(bitmapArray[0][0], 0, 0, paint);
		canvas.drawBitmap(bitmapArray[0][2], width-1 - bitmapArray[0][2].getWidth(), 0, paint);
		canvas.drawBitmap(bitmapArray[2][0], 0, height-1-bitmapArray[2][0].getHeight(), paint);
		canvas.drawBitmap(bitmapArray[2][2], width-1 - bitmapArray[2][2].getWidth(), height-1-bitmapArray[2][0].getHeight(), paint);
	}

	private Bitmap getAssembledBackgroundNumber(Bitmap[][] bitmapArray) {
		int width = 146;
		int height = 60;
		return null;
	}

}
