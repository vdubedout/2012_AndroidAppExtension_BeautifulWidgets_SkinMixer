package com.andexp.skinmixer.utils;

import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

public class StripeHack {
	public static void apply(View myView) {
		BitmapDrawable drawable = (BitmapDrawable) myView.getBackground();
		drawable.setTileModeY(TileMode.REPEAT);
	}
}
