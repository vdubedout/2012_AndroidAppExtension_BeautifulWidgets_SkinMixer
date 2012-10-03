package com.andexp.skinmixer;

import com.andexp.skinmixer.fragment.FragmentSkinPartList;
import com.andexp.skinmixer.fragment.SkinPartType;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skinmixer);
        
        FragmentSkinPartList fragmentBackgroundList = (FragmentSkinPartList) getFragmentManager().findFragmentById(R.id.skinmixer_fragment_background);
        fragmentBackgroundList.setSkinPartType(SkinPartType.BACKGROUND);
    }
}