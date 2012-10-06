package com.andexp.skinmixer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.andexp.skinmixer.customviews.SkinPreviewView;
import com.andexp.skinmixer.drawablecreation.PreviewManager.ImagePreviewProcessListener;
import com.andexp.skinmixer.fragment.FragmentSkinPartList;
import com.andexp.skinmixer.fragment.SkinPartType;

public class ActivitySkinMixer extends Activity implements ImagePreviewProcessListener{
	View mLabelBackgroundView;
	View mLabelBackgroundNumbersView;
	View mLabelNumbersView;
	
	SkinPreviewView mPreview;
	
	FragmentSkinPartList mFragmentBackground;
	FragmentSkinPartList mFragmentBackgroundNumbers;
	FragmentSkinPartList mFragmentNumbers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skinmixer);
		initializeLabelViews();
		initializePreview();
		initializeOnClicksOnLabels();
		initializeFragments();

		displayFragmentWithLabelView(mLabelBackgroundView);
	}

	private void initializePreview() {
		mPreview = (SkinPreviewView) findViewById(R.id.skinpreview_preview);
	}

	private void initializeFragments() {
		mFragmentBackground = getFragmentId(R.id.skinmixer_fragment_background);
		mFragmentBackground.setSkinPartType(SkinPartType.BACKGROUND);
		mFragmentBackground.setOnImageProcessListener(this);
		mFragmentBackgroundNumbers = getFragmentId(R.id.skinmixer_fragment_backgroundnumbers);
		mFragmentBackgroundNumbers.setSkinPartType(SkinPartType.FOREGROUND);
		mFragmentBackgroundNumbers.setOnImageProcessListener(this);
		mFragmentNumbers = getFragmentId(R.id.skinmixer_fragment_numbers);
		mFragmentNumbers.setSkinPartType(SkinPartType.NUMBER_0);
		mFragmentNumbers.setOnImageProcessListener(this);
	}

	private FragmentSkinPartList getFragmentId(int id) {
		return (FragmentSkinPartList) getFragmentManager().findFragmentById(id);
	}

	private void initializeLabelViews() {
		mLabelBackgroundView = findViewById(R.id.skinmixer_backgroundlabel_layout);
		mLabelBackgroundNumbersView = findViewById(R.id.skinmixer_backgroundnumberslabel_layout);
		mLabelNumbersView = findViewById(R.id.skinmixer_numberslabel_layout);
	}

	private void initializeOnClicksOnLabels() {
		mLabelBackgroundView.setOnClickListener(new OnClickLabel());
		mLabelBackgroundNumbersView.setOnClickListener(new OnClickLabel());
		mLabelNumbersView.setOnClickListener(new OnClickLabel());
	}

	class OnClickLabel implements OnClickListener {
		@Override
		public void onClick(View v) {
			displayFragmentWithLabelView(v);
		}
	}

	private void displayFragmentWithLabelView(View v) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		if (v == mLabelBackgroundView) {
			ft.show(mFragmentBackground);
			ft.hide(mFragmentBackgroundNumbers);
			ft.hide(mFragmentNumbers);
			ft.commit();
		} else if (v == mLabelBackgroundNumbersView) {
			ft.show(mFragmentBackgroundNumbers);
			ft.hide(mFragmentBackground);
			ft.hide(mFragmentNumbers);
			ft.commit();
		} else {
			ft.show(mFragmentNumbers);
			ft.hide(mFragmentBackgroundNumbers);
			ft.hide(mFragmentBackground);
			ft.commit();
		}
	}

	@Override
	public void onSkinPartPreviewBeginned() {
		
	}

	@Override
	public void onSkinPartPreviewFinished(Bitmap previewBitmap, SkinPartType skinPart) {
		mPreview.setImageBitmap(previewBitmap, skinPart);
	}
}