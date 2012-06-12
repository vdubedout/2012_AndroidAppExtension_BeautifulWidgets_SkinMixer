package com.andexp.skinmixer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.andexp.skinmixer.activity.SkinComposerActivity;
import com.andexp.skinmixer.utils.Conf;
import com.andexp.skinmixer.R;

public class main extends Activity {
	ImageButton btn_mix;
	ImageButton btn_saved;
//	ImageButton btn_downloadNewSkins;
//	ImageButton btn_setTextColor;
//	ImageButton btn_shareToOtherUsers;
//	ImageButton btn_grabOtherUsersOne;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        
        loadConfiguration();
        initializeWidgets();
        initializeOnClickListeners();
        
    }
	
	private void loadConfiguration(){
		Conf.load(getApplicationContext());
	}
	
	private void initializeWidgets(){
		btn_mix = (ImageButton) findViewById(R.id.main_buttonMix);
		btn_saved = (ImageButton) findViewById(R.id.main_buttonSaved);
//		btn_downloadNewSkins = (ImageButton) findViewById(R.id.main_buttonDownload);
//		btn_setTextColor = (ImageButton) findViewById(R.id.main_buttonColorSwitcher);
//		btn_shareToOtherUsers = (ImageButton) findViewById(R.id.main_buttonShare);
//		btn_grabOtherUsersOne = (ImageButton) findViewById(R.id.main_buttonGrabFromOtherUsers);
	}
	
	private void initializeOnClickListeners(){
		btn_mix.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(main.this, SkinComposerActivity.class);
				startActivity(i);
			}
		});
		
	}
}