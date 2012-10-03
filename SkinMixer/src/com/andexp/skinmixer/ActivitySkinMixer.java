package com.andexp.skinmixer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.andexp.skinmixer.fragment.FragmentSkinPartList;
import com.andexp.skinmixer.fragment.SkinPartType;

public class ActivitySkinMixer extends Activity {
	View mBackgroundLabelView;
	View mBackgroundNumbersLabelView;
	View mNumbersLabelView;
	
	FragmentSkinPartList mBackgroundFragment;
	FragmentSkinPartList mBackgroundNumbersFragment;
	FragmentSkinPartList mNumbersFragment;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skinmixer);
        initializeLabelViews();
        initializeFragments();
        initializeOnClicksOnLabels();
    }

	private void initializeLabelViews() {
		mBackgroundLabelView = findViewById(R.id.skinmixer_backgroundlabel_layout);
		mBackgroundNumbersLabelView = findViewById(R.id.skinmixer_backgroundnumberslabel_layout);
		mNumbersLabelView = findViewById(R.id.skinmixer_numberslabel_layout);
		
	}

	private void initializeOnClicksOnLabels() {
		this.findViewById(R.id.skinmixer_backgroundlabel_layout).
	}

	private void initializeFragments() {
		mBackgroundFragment = (FragmentSkinPartList) getFragmentManager().findFragmentById(R.id.skinmixer_fragment_background);
        mBackgroundFragment.setSkinPartType(SkinPartType.BACKGROUND);
        mBackgroundNumbersFragment= (FragmentSkinPartList) getFragmentManager().findFragmentById(R.id.skinmixer_fragment_backgroundnumbers);
        mBackgroundNumbersFragment.setSkinPartType(SkinPartType.BACKGROUND_NUMBERS);
        mNumbersFragment= (FragmentSkinPartList) getFragmentManager().findFragmentById(R.id.skinmixer_fragment_numbers);
        mNumbersFragment.setSkinPartType(SkinPartType.NUMBER_0);
	}
}