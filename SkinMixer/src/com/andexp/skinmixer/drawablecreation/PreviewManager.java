package com.andexp.skinmixer.drawablecreation;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.andexp.skinmixer.skin.SkinGroupType;

public class PreviewManager {
	public interface ImagePreviewProcessListener {
		public void onSkinPartPreviewBeginned();

		public void onSkinPartPreviewFinished(Bitmap previewBitmap, SkinGroupType groupType);
	}

	private static final String VOID_PREVIEW_BACKGROUND = "voidpreviewbackground.png";

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

	public void launchSkinPartPreviewCreation(String path, SkinGroupType skinGroupType) {
		new Thread(new ImageCreationRunnable(path, skinGroupType)).run();
	}

	public void getFullPreviewBitmap(String backgroundPath, String backgroundNumberPath,
			String numbersPath) throws IOException {
		mVoidBackgroundPreview = getVoidPreviewBackground();
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
		private SkinGroupType mGroupType;

		public ImageCreationRunnable(String path, SkinGroupType skinGroupType) {
			mGroupType = skinGroupType;
			mPath = path + skinGroupType.getContainedSkinPartType()[0].getFileName();
		}

		@Override
		public void run() {
			Bitmap[][] bitmapArray = mNinePatchCutter.getBitmapNinePatches(mPath);
			Bitmap bitmapPreview = mBitmapComposer.getAssembledBitmap(bitmapArray, mGroupType);
			mStatusListener.onSkinPartPreviewFinished(bitmapPreview, mGroupType);
		}
	}

}
