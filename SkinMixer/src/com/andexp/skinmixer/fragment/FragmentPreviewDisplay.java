package com.andexp.skinmixer.fragment;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.drawablecreation.PreviewManager;
import com.andexp.skinmixer.drawablecreation.PreviewManager.ImagePreviewProcessListener;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentPreviewDisplay extends Fragment {
	PreviewManager mPreviewManager;

	private ImageView iv_background;
	private ImageView iv_foreground;
	private ImageView[] iv_numbers;
	private ImageView iv_dots;

	private View root;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.fragment_skinpreview, null);
		iv_background = (ImageView) root.findViewById(R.id.skinpreview_background);
		iv_foreground = (ImageView) root.findViewById(R.id.skinpreview_foreground);
		iv_numbers = new ImageView[4];
//		iv_numbers[0] = (ImageView) root.findViewById(R.id.skinpreview_)
		
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
		if (mPreviewManager != null)
			mPreviewManager.launchSkinPartPreviewCreation(path, skinPart);
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

	public void setImageBitmap(Bitmap bitmap, SkinPartType skinPart) {
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
		iv_background.setImageBitmap(bitmap);
	}

	private void setForegroundBitmap(Bitmap bitmap) {
		iv_foreground.setImageBitmap(bitmap);
	}

}
