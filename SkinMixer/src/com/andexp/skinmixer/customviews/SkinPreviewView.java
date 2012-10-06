package com.andexp.skinmixer.customviews;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.fragment.SkinPartType;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SkinPreviewView extends RelativeLayout {
	private ImageView iv_background;
	private ImageView iv_foreground;
	
	public SkinPreviewView(Context context) {
		super(context);
	}
	
	public SkinPreviewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initializeViews();
	}

	private void initializeViews() {
		iv_background = (ImageView) this.findViewById(R.id.skinpreview_background);
		iv_foreground = (ImageView) this.findViewById(R.id.skinpreview_foreground);
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
