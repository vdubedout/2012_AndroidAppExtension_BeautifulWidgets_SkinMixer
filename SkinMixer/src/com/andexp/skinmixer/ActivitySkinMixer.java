package com.andexp.skinmixer;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.andexp.skinmixer.fragment.FragmentPreviewDisplay;
import com.andexp.skinmixer.fragment.FragmentSkinPartList;
import com.andexp.skinmixer.fragment.OnFragmentSkinListClick;
import com.andexp.skinmixer.fragment.SkinPartType;

public class ActivitySkinMixer extends Activity implements OnFragmentSkinListClick{
	View mLabelBackgroundView;
	View mLabelBackgroundNumbersView;
	View mLabelNumbersView;
	
	FragmentPreviewDisplay mPreview;
	
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
		mPreview = (FragmentPreviewDisplay) getFragmentManager().findFragmentById(R.id.skinmixer_fragment_previewDisplay);
	}

	private void initializeFragments() {
		mFragmentBackground = getFragmentListId(R.id.skinmixer_fragment_background);
		mFragmentBackground.setSkinPartType(SkinPartType.BACKGROUND);
		mFragmentBackground.setOnClickListener(this);
		mFragmentBackgroundNumbers = getFragmentListId(R.id.skinmixer_fragment_backgroundnumbers);
		mFragmentBackgroundNumbers.setSkinPartType(SkinPartType.FOREGROUND);
		mFragmentBackgroundNumbers.setOnClickListener(this);
		mFragmentNumbers = getFragmentListId(R.id.skinmixer_fragment_numbers);
		mFragmentNumbers.setSkinPartType(SkinPartType.NUMBER_0);
		mFragmentNumbers.setOnClickListener(this);
	}

	private FragmentSkinPartList getFragmentListId(int id) {
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
	public void onFragmentSkinListClick(String path, SkinPartType skinPartType) {
		mPreview.setImageType(path, skinPartType);
	}
}