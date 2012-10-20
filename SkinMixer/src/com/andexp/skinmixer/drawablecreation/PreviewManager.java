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
		public void onSkinPartPreviewBeginned();

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

	public void launchSkinPartPreviewCreation(String path, SkinPartType skinPart) {
		new Thread(new ImageCreationRunnable(path, skinPart)).run();
	}

	public void getFullPreviewBitmap(String backgroundPath, String backgroundNumberPath,
			String numbersPath) throws IOException {
		initializePaths(backgroundPath, backgroundNumberPath, numbersPath);
		mVoidBackgroundPreview = getVoidPreviewBackground();
	}

	private void initializePaths(String backgroundPath, String backgroundNumberPath,
			String numbersPath) {
		mBackgroundPath = backgroundPath + SkinImagePath.BACKGROUND;
		mBackgroundNumberPath = backgroundNumberPath + SkinImagePath.BACKGROUND_NUMBERS;
		mNumberPath[0] = numbersPath + SkinImagePath.NUMBER[0];
		mNumberPath[1] = numbersPath + SkinImagePath.NUMBER[1];
		mNumberPath[2] = numbersPath + SkinImagePath.NUMBER[2];
		mNumberPath[3] = numbersPath + SkinImagePath.NUMBER[3];
	}

	private Bitmap getVoidPreviewBackground() throws IOException {
		if (mVoidBackgroundPreview == null) {
			InputStream in = mContext.getAssets().open(VOID_PREVIEW_BACKGROUND);
			mVoidBackgroundPreview = BitmapFactory.decodeStream(in);
		}
		return mVoidBackgroundPreview;
	}

	class ImageCreationRunnable implements Runnable {
		private String mPath;
		private SkinPartType mSkinPart;

		public ImageCreationRunnable(String path, SkinPartType skinPart) {
			mSkinPart = skinPart;
			mPath = getFinalPath(path, mSkinPart);
		}

		private String getFinalPath(String path, SkinPartType skinPart) {
			if (skinPart == SkinPartType.BACKGROUND)
				return path + SkinImagePath.BACKGROUND;
			else
				return path + SkinImagePath.BACKGROUND_NUMBERS;
		}

		@Override
		public void run() {
			Bitmap[][] bitmapArray = mNinePatchCutter.getBitmapNinePatches(mPath);
			Bitmap bitmapSkinPartPreview = mBitmapComposer.getAssembledBitmap(bitmapArray,
					mSkinPart);
			mStatusListener.onSkinPartPreviewFinished(bitmapSkinPartPreview, mSkinPart);
		}
	}

}
