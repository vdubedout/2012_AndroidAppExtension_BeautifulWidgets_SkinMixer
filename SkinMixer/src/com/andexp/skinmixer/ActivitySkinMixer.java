package com.andexp.skinmixer;

import com.andexp.skinmixer.fragment.FragmentSkinPartList;
import com.andexp.skinmixer.fragment.SkinPartType;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class ActivitySkinMixer extends Activity {
	FragmentSkinPartList mBackgroundFragment;
	FragmentSkinPartList mBackgroundNumbersFragment;
	FragmentSkinPartList mNumbersFragment;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skinmixer);
        
        initializeFragments();
        
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