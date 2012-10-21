package com.andexp.skinmixer.fragment;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.andexp.skinmixer.OnPreviewCompleteListener;
import com.andexp.skinmixer.R;
import com.andexp.skinmixer.drawablecreation.PreviewManager;
import com.andexp.skinmixer.drawablecreation.PreviewManager.ImagePreviewProcessListener;
import com.andexp.skinmixer.skin.SkinGroupType;
import com.andexp.skinmixer.utils.StripeHack;

public class FragmentPreviewDisplay extends SherlockFragment {
	PreviewManager mPreviewManager;

	private View mRootView;

	private ImageView ivBackground;
	private ImageView ivForeground;
	private ImageView ivNumberHoursTens;
	private ImageView ivNumberHoursUnits;
	private ImageView ivNumberMinutesTens;
	private ImageView ivNumberMinutesUnits;
	private ImageView ivDots;
	private ImageView ivAM;

	private OnPreviewCompleteListener mPreviewCompleteListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_skinpreview, null);
		mPreviewManager = new PreviewManager(mRootView.getContext(), new ImagePreview());
		initializeViews(mRootView);
		StripeHack.apply(mRootView);
		return mRootView;
	}

	private void initializeViews(View root) {
		ivBackground = (ImageView) root.findViewById(R.id.skinpreview_background);
		ivForeground = (ImageView) root.findViewById(R.id.skinpreview_foreground);
		ivNumberHoursTens = (ImageView) root.findViewById(R.id.skinpreview_hours_tens);
		ivNumberHoursUnits = (ImageView) root.findViewById(R.id.skinpreview_hours_units);
		ivNumberMinutesTens = (ImageView) root.findViewById(R.id.skinpreview_minutes_tens);
		ivNumberMinutesUnits = (ImageView) root.findViewById(R.id.skinpreview_minutes_units);
		ivDots = (ImageView) root.findViewById(R.id.skinpreview_dots);
		ivAM = (ImageView) root.findViewById(R.id.skinpreview_am);
	}

	public void setPreviewCompleteListener(OnPreviewCompleteListener previewCompleteListener) {
		this.mPreviewCompleteListener = previewCompleteListener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void setImageType(String path, SkinGroupType skinGroupType) {
		applyImageType(path, skinGroupType);
		
		if(isComplete()){
			mPreviewCompleteListener.OnPreviewComplete();
		}
	}

	private void applyImageType(String path, SkinGroupType skinGroupType) {
		SkinPartType[] partType = skinGroupType.getContainedSkinPartType();
		
		switch (skinGroupType) {
		case BACKGROUND:
		case FOREGROUND:
			if (mPreviewManager != null)
				mPreviewManager.launchSkinPartPreviewCreation(path, skinGroupType);
			break;
		case DOTS:
			ivDots.setImageDrawable(Drawable.createFromPath(path + partType[0].getFileName()));
			break;
		case NUMBERS:
			ivNumberHoursTens.setImageDrawable(Drawable.createFromPath(path
					+ partType[1].getFileName()));
			ivNumberHoursUnits.setImageDrawable(Drawable.createFromPath(path
					+ partType[3].getFileName()));
			ivNumberMinutesTens.setImageDrawable(Drawable.createFromPath(path
					+ partType[3].getFileName()));
			ivNumberMinutesUnits.setImageDrawable(Drawable.createFromPath(path
					+ partType[7].getFileName()));
			break;
		case AMPM:
			ivAM.setImageDrawable(Drawable.createFromPath(path + partType[0].getFileName()));
		default:
			break;
		}
	}

	public boolean isComplete() {
		return false;
	}

	class ImagePreview implements ImagePreviewProcessListener {
		public ImagePreview() {
		}

		@Override
		public void onSkinPartPreviewBeginned() {
			// TODO spinner
		}

		@Override
		public void onSkinPartPreviewFinished(Bitmap previewBitmap, SkinGroupType skinPart) {
			setImageBitmap(previewBitmap, skinPart);
		}
	}

	private void setImageBitmap(Bitmap bitmap, SkinGroupType skinGroupType) {
		switch (skinGroupType) {
		case BACKGROUND:
			setBackgroundBitmap(bitmap);
			break;
		case FOREGROUND:
			setForegroundBitmap(bitmap);
		default:
			break;
		}
	}

	private void setBackgroundBitmap(Bitmap bitmap) {
		ivBackground.setImageBitmap(bitmap);
	}

	private void setForegroundBitmap(Bitmap bitmap) {
		ivForeground.setImageBitmap(bitmap);
	}


}
