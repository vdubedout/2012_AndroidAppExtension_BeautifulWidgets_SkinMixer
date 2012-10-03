package com.andexp.skinmixer.drawablecreation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.andexp.skinmixer.path.SkinPartImagePath;

public class PreviewManager {
	public interface ProcessListener {
		public void onFinish(Bitmap previewBitmap);
	}

	private static final String VOID_PREVIEW_BACKGROUND = "voidpreviewbackground.png";
	private String mBackgroundPath;
	private String mBackgroundNumberPath;
	private String[] mNumberPath = new String[4];
	private ProcessListener mStatusListener;
	private Context mContext;

	private Bitmap mVoidBackgroundPreview;

	public PreviewManager(Context context, ProcessListener listener) {
		mContext = context;
		mStatusListener = listener;

	}

	public void getPreviewBitmap(String backgroundPath, String backgroundNumberPath,
			String numbersPath) throws IOException {
		initializePaths(backgroundPath, backgroundNumberPath, numbersPath);
		mVoidBackgroundPreview = getVoidPreviewBackground();
		Canvas outputCanvas = new Canvas(mVoidBackgroundPreview);
		processBackgroundToCanvas(outputCanvas);
		processBackgroundNumbersToCanvas(outputCanvas);
		processNumbersToCanvas(outputCanvas);
	}

	private void processBackgroundToCanvas(Canvas outputCanvas) {
		Bitmap background[][] = getBackgroundCutted();
		
	}

	private Bitmap[][] getBackgroundCutted() {
		// TODO Auto-generated method stub
		return null;
	}

	private void processBackgroundNumbersToCanvas(Canvas outputCanvas) {
		
	}

	private void processNumbersToCanvas(Canvas outputCanvas) {
		
	}

	private Bitmap getVoidPreviewBackground() throws IOException {
		if (mVoidBackgroundPreview == null) {
			InputStream in = mContext.getAssets().open(VOID_PREVIEW_BACKGROUND);
			mVoidBackgroundPreview = BitmapFactory.decodeStream(in);
		}
		return mVoidBackgroundPreview;
	}

	private void initializePaths(String backgroundPath, String backgroundNumberPath,
			String numbersPath) {
		mBackgroundPath = backgroundPath + File.separator + SkinPartImagePath.BACKGROUND;
		mBackgroundNumberPath = backgroundNumberPath + File.separator
				+ SkinPartImagePath.BACKGROUND_NUMBERS;
		mNumberPath[0] = numbersPath + File.separator + SkinPartImagePath.NUMBER[0];
		mNumberPath[1] = numbersPath + File.separator + SkinPartImagePath.NUMBER[1];
		mNumberPath[2] = numbersPath + File.separator + SkinPartImagePath.NUMBER[2];
		mNumberPath[3] = numbersPath + File.separator + SkinPartImagePath.NUMBER[3];
	}

}
