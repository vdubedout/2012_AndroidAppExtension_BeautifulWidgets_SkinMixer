package com.andexp.skinmixer.drawablecreation;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.andexp.skinmixer.fragment.SkinPartType;

public class BitmapComposer {
	public static final int BACKGROUND_WIDTH = 386;
	public static final int BACKGROUND_HEIGHT = 221;
	public static final int FOREGROUND_WIDTH = 335;
	public static final int FOREGROUND_HEIGHT = 137;
	public static final float SCALE_FACTOR = 0.44f;
	private Paint mPaint;
	
	public BitmapComposer() {
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
	}

	public Bitmap getAssembledBitmap(Bitmap[][] bitmapArray, SkinPartType skinType) {
		if(skinType == SkinPartType.BACKGROUND){
			return getAssembledBackground(bitmapArray);
		} else if(skinType == SkinPartType.BACKGROUND_NUMBERS) {
			return getAssembledForeground(bitmapArray);
		} else return null;
	}

	private Bitmap getAssembledBackground(Bitmap[][] bitmapArray) {
		int width = BACKGROUND_WIDTH; 
		int height = BACKGROUND_HEIGHT;
		Bitmap bitmapDrawn = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapDrawn);
		drawCorners(bitmapArray, width, height, canvas);
		drawBackgroundStretchedBitmaps(bitmapArray, width, height, canvas);
		resizeBitmap(canvas);
		return bitmapDrawn;
	}

	private void drawCorners(Bitmap[][] bitmapArray, int width, int height, Canvas canvas) {
		int maxIndex = bitmapArray[0].length-1;
		canvas.drawBitmap(bitmapArray[0][0], 0, 0, mPaint);
		canvas.drawBitmap(bitmapArray[0][maxIndex], width-1 - bitmapArray[0][maxIndex].getWidth(), 0, mPaint);
		canvas.drawBitmap(bitmapArray[maxIndex][0], 0, height-1-bitmapArray[maxIndex][0].getHeight(), mPaint);
		canvas.drawBitmap(bitmapArray[maxIndex][maxIndex], width-1 - bitmapArray[maxIndex][maxIndex].getWidth(), height-1-bitmapArray[2][0].getHeight(), mPaint);
	}

	private void drawBackgroundStretchedBitmaps(Bitmap[][] bitmapArray, int width, int height,
			Canvas canvas) {
		drawBackgroundTop(bitmapArray, width, canvas);
		drawBackgroundBottom(bitmapArray, width, height, canvas);
		drawMiddleLeft(bitmapArray, height, canvas);
		drawMiddleRight(bitmapArray, width, height, canvas);
		drawBackgroundCenter(bitmapArray, width, height, canvas);
	}

	private void drawBackgroundTop(Bitmap[][] bitmapArray, int width,
			Canvas canvas) {
		Rect middleTop = new Rect(bitmapArray[0][0].getWidth(), 0, width-1-bitmapArray[0][2].getWidth(), bitmapArray[0][1].getHeight());
		canvas.drawBitmap(bitmapArray[0][1], null, middleTop, mPaint);
	}

	private void drawBackgroundBottom(Bitmap[][] bitmapArray, int width,
			int height, Canvas canvas) {
		Rect middleBottom = new Rect(bitmapArray[2][0].getWidth(), 
				height - 1 - bitmapArray[2][1].getHeight(), 
				width-1-bitmapArray[2][2].getWidth(), 
				height-1);
		canvas.drawBitmap(bitmapArray[2][1], null, middleBottom, mPaint);
	}

	private void drawMiddleLeft(Bitmap[][] bitmapArray, int height,
			Canvas canvas) {
		Rect middleLeft = new Rect(0, 
				bitmapArray[0][1].getHeight(), 
				bitmapArray[1][0].getWidth(), 
				height - 1 - bitmapArray[2][0].getHeight());
		canvas.drawBitmap(bitmapArray[1][0], null, middleLeft, mPaint);
	}

	private void drawMiddleRight(Bitmap[][] bitmapArray, int width,
			int height, Canvas canvas) {
		int maxIndex = bitmapArray[0].length-1;
		Rect middleRight = new Rect(width - 1 - bitmapArray[1][maxIndex].getWidth(), 
				bitmapArray[0][maxIndex].getHeight(), 
				width - 1, 
				height - 1 - bitmapArray[2][maxIndex].getHeight());
		canvas.drawBitmap(bitmapArray[1][maxIndex], null, middleRight, mPaint);
	}

	private void drawBackgroundCenter(Bitmap[][] bitmapArray, int width,
			int height, Canvas canvas) {
		Rect center = new Rect(bitmapArray[1][0].getWidth(),
				bitmapArray[0][1].getHeight(), 
				width - 1 - bitmapArray[1][2].getWidth(), 
				height - 1 - bitmapArray[2][1].getHeight());
		canvas.drawBitmap(bitmapArray[1][1], null, center, mPaint);
	}

	private void resizeBitmap(Canvas canvas) {
		canvas.scale(SCALE_FACTOR, SCALE_FACTOR);
	}

	private Bitmap getAssembledForeground(Bitmap[][] bitmapArray) {
		int width = FOREGROUND_WIDTH;
		int height = FOREGROUND_HEIGHT;

		Bitmap bitmapDrawn = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmapDrawn);
		drawCorners(bitmapArray, width, height, canvas);
		drawForegroundStretchedBitmaps(bitmapArray, width, height, canvas);
		resizeBitmap(canvas);
		return bitmapDrawn;
	}

	private void drawForegroundStretchedBitmaps(Bitmap[][] bitmapArray,
			int width, int height, Canvas canvas) {
		//TopMiddle
		int widthCenter = width/2;
		Bitmap bitmap = bitmapArray[0][2];
		int bitmapWidth = bitmap.getWidth();
		int bitmapHeight = bitmap.getHeight();
		Rect rect = new Rect(widthCenter - bitmapWidth/2, 0, widthCenter+bitmapWidth/2 + bitmapWidth%2, bitmapHeight);
		canvas.drawBitmap(bitmap, null, rect, mPaint);
		//BottomMiddle
		//CenterMiddle
		//TopLeft
		//TopRight
		//Left
		//Right
		//BottomLeft
		//BottomRight
		//CenterLeft
		//CenterRight
	}

}
