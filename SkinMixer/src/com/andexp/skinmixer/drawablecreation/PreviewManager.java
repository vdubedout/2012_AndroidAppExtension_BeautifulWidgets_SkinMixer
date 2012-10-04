package com.andexp.skinmixer.drawablecreation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.andexp.skinmixer.fragment.SkinPartType;
import com.andexp.skinmixer.path.SkinImagePath;

public class PreviewManager {
	public interface ImagePreviewProcessListener {
		public void onSkinPartPreviewFinished(Bitmap previewBitmap, SkinPartType skinPart);
	}

	private static final String VOID_PREVIEW_BACKGROUND = "voidpreviewbackground.png";

	private String mBackgroundPath;
	private String mBackgroundNumberPath;
	private String[] mNumberPath = new String[4];

	private ImagePreviewProcessListener mStatusListener;
	private Context mContext;

	NinePatchCutter mNinePatchCutter;
	private BitmapComposer mBitmapComposer;

	private Bitmap mVoidBackgroundPreview;

	public PreviewManager(Context context, ImagePreviewProcessListener listener) {
		mContext = context;
		mStatusListener = listener;
		mNinePatchCutter = new NinePatchCutter();
		mBitmapComposer = new BitmapComposer();
	}

	public void getSkinPartPreview(final String path, final SkinPartType skinPart) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String mPath = getFinalPath(path, skinPart);
				Bitmap[][] bitmapArray = mNinePatchCutter.getBitmapNinePatches(mPath);
				Bitmap bitmapSkinPartPreview = mBitmapComposer.getAssembledBitmap(bitmapArray, skinPart);
				mStatusListener.onSkinPartPreviewFinished(bitmapSkinPartPreview, skinPart);
			}
		}).run();
	}

	private String getFinalPath(String path, SkinPartType skinPart) {
		if (skinPart == SkinPartType.BACKGROUND)
			return File.separator + SkinImagePath.BACKGROUND;
		else
			return File.separator + SkinImagePath.BACKGROUND_NUMBERS;
	}

	public void getFullPreviewBitmap(String backgroundPath, String backgroundNumberPath,
			String numbersPath) throws IOException {
		initializePaths(backgroundPath, backgroundNumberPath, numbersPath);
		mVoidBackgroundPreview = getVoidPreviewBackground();
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
		mBackgroundPath = backgroundPath + File.separator + SkinImagePath.BACKGROUND;
		mBackgroundNumberPath = backgroundNumberPath + File.separator
				+ SkinImagePath.BACKGROUND_NUMBERS;
		mNumberPath[0] = numbersPath + File.separator + SkinImagePath.NUMBER[0];
		mNumberPath[1] = numbersPath + File.separator + SkinImagePath.NUMBER[1];
		mNumberPath[2] = numbersPath + File.separator + SkinImagePath.NUMBER[2];
		mNumberPath[3] = numbersPath + File.separator + SkinImagePath.NUMBER[3];
	}

}
