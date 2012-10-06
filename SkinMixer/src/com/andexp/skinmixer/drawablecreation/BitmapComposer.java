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
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
	}

	public Bitmap getAssembledBitmap(Bitmap[][] bitmapArray, SkinPartType skinType) {
		if (skinType == SkinPartType.BACKGROUND) {
			return getAssembledBackground(bitmapArray);
		} else if (skinType == SkinPartType.BACKGROUND_NUMBERS) {
			return getAssembledForeground(bitmapArray);
		} else
			return null;
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
		int maxIndex = bitmapArray[0].length - 1;
		//topleft
		canvas.drawBitmap(bitmapArray[0][0], 0, 0, mPaint);
		//topRight
		canvas.drawBitmap(bitmapArray[0][maxIndex],
				width - 1 - bitmapArray[0][maxIndex].getWidth(), 0, mPaint);
		//bottomLeft
		canvas.drawBitmap(bitmapArray[2][0], 0, height - bitmapArray[2][0].getHeight(), mPaint);
		//bottomRight
		canvas.drawBitmap(bitmapArray[2][maxIndex],
				width - 1 - bitmapArray[2][maxIndex].getWidth(),
				height - bitmapArray[2][0].getHeight(), mPaint);
	}

	private void drawBackgroundStretchedBitmaps(Bitmap[][] bitmapArray, int width, int height,
			Canvas canvas) {
		drawBackgroundTop(bitmapArray, width, canvas);
		drawBackgroundBottom(bitmapArray, width, height, canvas);
		drawFarLeftCenter(bitmapArray, height, canvas);
		drawFarRightCenter(bitmapArray, width, height, canvas);
		drawBackgroundCenter(bitmapArray, width, height, canvas);
	}

	private void drawBackgroundTop(Bitmap[][] bitmapArray, int width, Canvas canvas) {
		Rect middleTop = new Rect(bitmapArray[0][0].getWidth(), 0, width - 1
				- bitmapArray[0][2].getWidth(), bitmapArray[0][1].getHeight());
		canvas.drawBitmap(bitmapArray[0][1], null, middleTop, mPaint);
	}

	private void drawBackgroundBottom(Bitmap[][] bitmapArray, int width, int height, Canvas canvas) {
		Rect middleBottom = new Rect(bitmapArray[2][0].getWidth(), height
				- bitmapArray[2][1].getHeight(), width - 1 - bitmapArray[2][2].getWidth(),
				height);
		canvas.drawBitmap(bitmapArray[2][1], null, middleBottom, mPaint);
	}

	private void drawFarLeftCenter(Bitmap[][] bitmapArray, int height, Canvas canvas) {

		Rect middleLeft = new Rect(0, bitmapArray[0][1].getHeight(), bitmapArray[1][0].getWidth(),
				height - bitmapArray[2][0].getHeight());
		canvas.drawBitmap(bitmapArray[1][0], null, middleLeft, mPaint);
	}

	private void drawFarRightCenter(Bitmap[][] bitmapArray, int width, int height, Canvas canvas) {
		int maxIndex = bitmapArray[0].length - 1;
		Rect middleRight = new Rect(width - 1 - bitmapArray[1][maxIndex].getWidth(),
				bitmapArray[0][maxIndex].getHeight(), width - 1, height
						- bitmapArray[2][maxIndex].getHeight());
		canvas.drawBitmap(bitmapArray[1][maxIndex], null, middleRight, mPaint);
	}

	private void drawBackgroundCenter(Bitmap[][] bitmapArray, int width, int height, Canvas canvas) {
		Rect center = new Rect(bitmapArray[1][0].getWidth(), bitmapArray[0][1].getHeight(), width
				- 1 - bitmapArray[1][2].getWidth(), height - 1 - bitmapArray[2][1].getHeight());
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

	private void drawForegroundStretchedBitmaps(Bitmap[][] bitmapArray, int width, int height,
			Canvas canvas) {
		int centerLeftBounds = width / 2 - bitmapArray[0][2].getWidth() / 2;
		int centerRightBounds = width / 2 + bitmapArray[0][2].getWidth() / 2
				+ bitmapArray[0][2].getWidth() % 2;

		drawFarLeftCenter(bitmapArray, height, canvas);
		drawForegroundLeft(bitmapArray, width, height, centerLeftBounds, canvas);
		drawForegroundMiddle(bitmapArray, centerLeftBounds, centerRightBounds, width, height,
				canvas);
		drawForegroundRight(bitmapArray, centerRightBounds, width, height, canvas);

		drawFarRightCenter(bitmapArray, width, height, canvas);
	}

	private int drawForegroundLeft(Bitmap[][] bitmapArray, int width, int height, int rightBounds,
			Canvas canvas) {
		int leftBounds = bitmapArray[0][0].getWidth();
		int bottom = drawForegroundTopLeft(bitmapArray, leftBounds, rightBounds, canvas);
		bottom = drawForegroundCenterLeft(bitmapArray, leftBounds, bottom, rightBounds, height,
				canvas);
		drawForegroundBottomLeft(bitmapArray, leftBounds, bottom, rightBounds, height, canvas);
		return rightBounds;
	}

	private int drawForegroundTopLeft(Bitmap[][] bitmapArray, int leftBounds, int rightBounds,
			Canvas canvas) {
		Bitmap bitmap = bitmapArray[0][1];
		int bottom = bitmap.getHeight();
		drawBitmapInCanvas(leftBounds, 0, rightBounds, bottom, bitmap, canvas);
		return bottom;
	}

	private int drawForegroundCenterLeft(Bitmap[][] bitmapArray, int leftBounds, int TopBounds,
			int rightBounds, int height, Canvas canvas) {
		Bitmap bitmap = bitmapArray[1][1];
		int bottom = height - bitmapArray[2][1].getHeight();
		drawBitmapInCanvas(leftBounds, TopBounds, rightBounds, bottom, bitmap, canvas);
		return bottom;
	}

	private void drawForegroundBottomLeft(Bitmap[][] bitmapArray, int leftBounds, int topBounds,
			int rightBounds, int height, Canvas canvas) {
		Bitmap bitmap = bitmapArray[2][1];
		int bottomBounds = height;
		drawBitmapInCanvas(leftBounds, topBounds, rightBounds, bottomBounds, bitmap, canvas);
	}

	private int drawForegroundMiddle(Bitmap[][] bitmapArray, int centerLeftBounds,
			int centerRightBounds, int width, int height, Canvas canvas) {
		drawForegroundTopMiddle(bitmapArray, centerLeftBounds, centerRightBounds, canvas);
		drawForegroundCenterMiddle(bitmapArray, height, centerLeftBounds, centerRightBounds, canvas);
		drawForegroundBottomMiddle(bitmapArray, width, height, centerLeftBounds, canvas);
		return centerRightBounds;
	}

	private void drawForegroundTopMiddle(Bitmap[][] bitmapArray, int left, int right, Canvas canvas) {
		Bitmap bitmap = bitmapArray[0][2];
		int bitmapHeight = bitmap.getHeight();
		Rect rect = new Rect(left, 0, right, bitmapHeight);
		canvas.drawBitmap(bitmap, null, rect, mPaint);
	}

	private void drawForegroundCenterMiddle(Bitmap[][] bitmapArray, int height, int left,
			int right, Canvas canvas) {
		Bitmap bitmap = bitmapArray[1][2];
		int bitmapUpHeight = bitmapArray[0][2].getHeight();
		int bitmapDownHeight = bitmapArray[2][2].getHeight();
		Rect rect = new Rect(left, bitmapUpHeight, right, height - bitmapDownHeight);
		canvas.drawBitmap(bitmap, null, rect, mPaint);
	}

	private void drawForegroundBottomMiddle(Bitmap[][] bitmapArray, int height, int left,
			int right, Canvas canvas) {
		Bitmap bitmap = bitmapArray[2][2];
		int bitmapHeight = bitmap.getHeight();
		Rect rect = new Rect(left, height - bitmapHeight, right, height);
		canvas.drawBitmap(bitmap, null, rect, mPaint);
	}

	private void drawForegroundRight(Bitmap[][] bitmapArray, int leftBounds, int width, int height,
			Canvas canvas) {
		int rightBounds = width - bitmapArray[0][4].getWidth();
		int bottom = drawForegroundTopRight(bitmapArray[0][4], leftBounds, rightBounds, canvas);
		bottom = drawForegroundCenterRight(bitmapArray, leftBounds, bottom, rightBounds, height,
				canvas);
		drawForegroundBottomRight(bitmapArray[2][4], leftBounds, bottom, rightBounds, height,
				canvas);
	}

	private int drawForegroundTopRight(Bitmap bitmap, int leftBounds, int rightBounds, Canvas canvas) {
		int bottom = bitmap.getHeight();
		drawBitmapInCanvas(leftBounds, 0, rightBounds, bottom, bitmap, canvas);
		return bottom;
	}

	private int drawForegroundCenterRight(Bitmap[][] bitmapArray, int leftBounds, int topBounds,
			int rightBounds, int height, Canvas canvas) {
		Bitmap bitmap = bitmapArray[1][4];
		int bottom = height - bitmapArray[2][4].getHeight();
		drawBitmapInCanvas(leftBounds, topBounds, rightBounds, bottom, bitmap, canvas);
		return bottom;
	}

	private void drawForegroundBottomRight(Bitmap bitmap, int leftBounds, int topBounds,
			int rightBounds, int height, Canvas canvas) {
		int bottom = height;
		drawBitmapInCanvas(leftBounds, topBounds, rightBounds, bottom, bitmap, canvas);
	}

	private void drawBitmapInCanvas(int left, int top, int right, int bottom, Bitmap bitmap,
			Canvas canvas) {
		Rect rect = new Rect(left, top, right, bottom);
		canvas.drawBitmap(bitmap, null, rect, mPaint);
	}
}
