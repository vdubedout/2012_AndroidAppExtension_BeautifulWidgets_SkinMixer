package com.andexp.skinmixer.drawablecreation;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.andexp.skinmixer.fragment.SkinPartType;

public class BitmapComposer {
	public static final int BACKGROUND_WIDTH = 580;
	public static final int BACKGROUND_HEIGHT = 333;
	public static final int BACKGROUNDNUMBERS_WIDTH = 504;
	public static final int BACKGROUNDNUMBERS_HEIGHT = 207;
	public static final float SCALE_FACTOR = 0.29f;
	
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
		int width = BACKGROUND_WIDTH; 
		int height = BACKGROUND_HEIGHT;
		Bitmap bitmapDrawn = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapDrawn);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
		drawBackgroundCorners(bitmapArray, width, height, canvas, paint);
		drawBackgroundScaledBitmaps(bitmapArray, width, height, canvas, paint);
		resizeBitmap(canvas);
		return bitmapDrawn;
	}

	private void drawBackgroundCorners(Bitmap[][] bitmapArray, int width, int height, Canvas canvas, Paint paint) {
		canvas.drawBitmap(bitmapArray[0][0], 0, 0, paint);
		canvas.drawBitmap(bitmapArray[0][2], width-1 - bitmapArray[0][2].getWidth(), 0, paint);
		canvas.drawBitmap(bitmapArray[2][0], 0, height-1-bitmapArray[2][0].getHeight(), paint);
		canvas.drawBitmap(bitmapArray[2][2], width-1 - bitmapArray[2][2].getWidth(), height-1-bitmapArray[2][0].getHeight(), paint);
	}

	private void drawBackgroundScaledBitmaps(Bitmap[][] bitmapArray, int width, int height,
			Canvas canvas, Paint paint) {
		Rect middleTop = new Rect(bitmapArray[0][0].getWidth(), 0, width-1-bitmapArray[0][2].getWidth(), bitmapArray[0][1].getHeight());
		canvas.drawBitmap(bitmapArray[0][1], null, middleTop, paint);
		Rect middleBottom = new Rect(bitmapArray[2][0].getWidth(), 
				height - 1 - bitmapArray[2][1].getHeight(), 
				width-1-bitmapArray[2][2].getWidth(), 
				height-1);
		canvas.drawBitmap(bitmapArray[2][1], null, middleBottom, paint);
		Rect middleLeft = new Rect(0, 
				bitmapArray[0][1].getHeight(), 
				bitmapArray[1][0].getWidth(), 
				height - 1 - bitmapArray[2][0].getHeight());
		canvas.drawBitmap(bitmapArray[1][0], null, middleLeft, paint);
		Rect middleRight = new Rect(width - 1 - bitmapArray[1][2].getWidth(), 
				bitmapArray[0][2].getHeight(), 
				width - 1, 
				height - 1 - bitmapArray[2][2].getHeight());
		canvas.drawBitmap(bitmapArray[1][2], null, middleRight, paint);
		Rect center = new Rect(bitmapArray[1][1].getWidth(),
				bitmapArray[0][1].getHeight(), 
				width - 1 - bitmapArray[1][2].getWidth(), 
				height - 1 - bitmapArray[2][1].getHeight());
	}

	private void resizeBitmap(Canvas canvas) {
		canvas.scale(SCALE_FACTOR, SCALE_FACTOR);
	}

	private Bitmap getAssembledBackgroundNumber(Bitmap[][] bitmapArray) {
		int width = 146;
		int height = 60;
		return null;
	}

}