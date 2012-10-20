package com.andexp.skinmixer.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.drawablecreation.PreviewManager;
import com.andexp.skinmixer.drawablecreation.PreviewManager.ImagePreviewProcessListener;
import com.andexp.skinmixer.path.SkinImagePath;
import com.andexp.skinmixer.utils.StripeHack;

public class FragmentPreviewDisplay extends Fragment {
	PreviewManager mPreviewManager;

	private View root;

	private ImageView ivBackground;
	private ImageView ivForeground;
	private ImageView ivNumberHoursTens;
	private ImageView ivNumberHoursUnits;
	private ImageView ivNumberMinutesTens;
	private ImageView ivNumberMinutesUnits;
	private ImageView ivDots;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_skinpreview, null);
		ivBackground = (ImageView) root.findViewById(R.id.skinpreview_background);
		ivForeground = (ImageView) root.findViewById(R.id.skinpreview_foreground);
		ivNumberHoursTens = (ImageView) root.findViewById(R.id.skinpreview_hours_tens);
		ivNumberHoursUnits = (ImageView) root.findViewById(R.id.skinpreview_hours_units);
		ivNumberMinutesTens = (ImageView) root.findViewById(R.id.skinpreview_minutes_tens);
		ivNumberMinutesUnits = (ImageView) root.findViewById(R.id.skinpreview_minutes_units);
		ivDots = (ImageView) root.findViewById(R.id.skinpreview_dots);
		StripeHack.apply(root);

		mPreviewManager = new PreviewManager(root.getContext(), new ImagePreview());
		return root;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public void setImageType(String path, SkinPartType skinPart) {
		switch (skinPart) {
		case BACKGROUND:
		case FOREGROUND:
			if (mPreviewManager != null)
				mPreviewManager.launchSkinPartPreviewCreation(path, skinPart);
			break;
		case DOTS:
			ivDots.setImageDrawable(Drawable.createFromPath(path + SkinImagePath.DOTS));
			break;
		case NUMBER_0:
			ivNumberHoursTens.setImageDrawable(Drawable.createFromPath(path + SkinImagePath.NUMBER[1]));
			ivNumberHoursUnits.setImageDrawable(Drawable.createFromPath(path + SkinImagePath.NUMBER[3]));
			ivNumberMinutesTens.setImageDrawable(Drawable.createFromPath(path + SkinImagePath.NUMBER[3]));
			ivNumberMinutesUnits.setImageDrawable(Drawable.createFromPath(path + SkinImagePath.NUMBER[7]));
			break;
		default:
			break;
		}
	}

	class ImagePreview implements ImagePreviewProcessListener {
		public ImagePreview() {
		}

		@Override
		public void onSkinPartPreviewBeginned() {
			// TODO spinner
		}

		@Override
		public void onSkinPartPreviewFinished(Bitmap previewBitmap, SkinPartType skinPart) {
			setImageBitmap(previewBitmap, skinPart);
		}

	}

	private void setImageBitmap(Bitmap bitmap, SkinPartType skinPart) {
		switch (skinPart) {
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
