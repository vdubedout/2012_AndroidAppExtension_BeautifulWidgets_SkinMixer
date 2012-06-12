package com.andexp.skinmixer.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.bw.SkinBundler;
import com.andexp.skinmixer.bw.SkinFactory;
import com.andexp.skinmixer.bw.SkinImpl;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.utils.Extra;
import com.andexp.skinmixer.utils.MLog;
import com.andexp.skinmixer.utils.StripeUnbugger;

public class SkinComposerActivity extends RoboActivity{
	static final int RESULT_CODE = 10;
	static final String DEFAULT_AUTHOR = "SkinMixer";
	static final int DISPLAYED_NUMBERS = 4;
	
	@InjectView(R.id.skinmixer_display_background)			View display_changeBackground;
	@InjectView(R.id.skinmixer_display_backgroundNumbers)	View display_changeBackgroundNumbers;
	@InjectView(R.id.skinmixer_display_numbers)				View display_changeNumbers;
	@InjectView(R.id.skinmixer_btn_createSkin)				Button btn_createSkin;
	@InjectView(R.id.skinmixer_iv_background)				ImageView iv_background;
	@InjectView(R.id.skinmixer_iv_backgroundNumbers)		ImageView iv_backgroundNumbers;
	@InjectView(R.id.skinmixer_et_skinname)					EditText et_skinName;
	@InjectView(R.id.skinmixer_lbl_no_background)			TextView tv_noskin_background;
	@InjectView(R.id.skinmixer_lbl_no_backgroundNumbers)	TextView tv_noskin_backgroundNumbers;
	@InjectView(R.id.skinmixer_lbl_no_numbers)				TextView tv_noskin_numbers;
	
	ImageView[] iv_numbers = new ImageView[DISPLAYED_NUMBERS];
	SkinImpl skin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skinmixer);

		debugStripeRepeat();
		initializeNumbersImageView();
		initializeOnClickListeners();
		skin = SkinFactory.getNewSkin();
	}

	private void debugStripeRepeat() {
		StripeUnbugger.apply((View) findViewById(R.id.skinmixer_rl_background));
	}

	private void initializeNumbersImageView() {
		iv_numbers[0] = (ImageView) findViewById(R.id.skinmixer_iv_number_0);
		iv_numbers[1] = (ImageView) findViewById(R.id.skinmixer_iv_number_1);
		iv_numbers[2] = (ImageView) findViewById(R.id.skinmixer_iv_number_2);
		iv_numbers[3] = (ImageView) findViewById(R.id.skinmixer_iv_number_3);
	}

	private void initializeOnClickListeners() {
		display_changeBackground.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SkinComposerActivity.this, ChangeOneImagePartActivity.class);
				i.putExtra(Extra.SKIN_PART, ESkinPart.BACKGROUND);
				startActivityForResult(i, ESkinPart.BACKGROUND);
			}
		});

		display_changeBackgroundNumbers.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SkinComposerActivity.this, ChangeOneImagePartActivity.class);
				i.putExtra(Extra.SKIN_PART, ESkinPart.BACKGROUND_NUMBERS);
				startActivityForResult(i, ESkinPart.BACKGROUND_NUMBERS);
			}
		});

		display_changeNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SkinComposerActivity.this, ChangeMultiImagePartActivity.class);
				startActivityForResult(i, ESkinPart.NUMBER_0);
			}
		});

		btn_createSkin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				loadSkinNameToSkin();
				checkSuperClockSkin();
				
				if(skin.isSkinComplete()){
					Intent i = new Intent(SkinComposerActivity.this,SkinCreatorActivity.class);
					long date = System.currentTimeMillis();
					SkinFactory.holdSkin(skin, date);
					i.putExtra(Extra.SKINDATE, date);
					startActivity(i);
				} else Toast.makeText(SkinComposerActivity.this, R.string.warning_skinpartnull, Toast.LENGTH_LONG).show();
			}
		});

	}
	
	protected void loadSkinNameToSkin(){
		String skinName = "";
		if(et_skinName.getText().toString().isEmpty()) fillSkinNameAlert(); // Dire de remplir un nom
		else skinName = et_skinName.getText().toString();
		skin.getSkinData().setSkinName(skinName);
	}
	
	protected void fillSkinNameAlert(){
		//
	}
	
	protected void checkSuperClockSkin(){
		
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		MLog.v("OnActivityForResult requestcode="+requestCode);

		if(resultCode == RESULT_OK){
			loadExtras(data.getExtras(), requestCode);
			loadPreviewPartFromExtra(requestCode);
		}
	}

	private void loadExtras(Bundle extras, int skinPart){
		String directoryName = extras.getString(Extra.SKIN_DIRECTORYNAME);
		int clockType = extras.getInt(Extra.SKIN_CLOCKTYPE);

		skin.setSkinPartByRepertory(directoryName, skinPart, clockType);
	}

	private void loadPreviewPartFromExtra(int skinPart){
		switch (skinPart) {
		case ESkinPart.BACKGROUND:
			iv_background.setImageDrawable(skin.getSkinPart(ESkinPart.BACKGROUND).getDrawable()); //TODO asyncmode
			tv_noskin_background.setVisibility(View.GONE);
			break;
		case ESkinPart.BACKGROUND_NUMBERS:
			iv_backgroundNumbers.setImageDrawable(skin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).getDrawable()); //TODO asyncmode
			tv_noskin_backgroundNumbers.setVisibility(View.GONE);
			break;
		case ESkinPart.NUMBER_0:
			loadNumberPreview();
			tv_noskin_numbers.setVisibility(View.GONE);
			break;
		}
	}

	private void loadNumberPreview() {
		for (int i = 0; i < DISPLAYED_NUMBERS; i++) {
			iv_numbers[i].setImageDrawable(skin.getSkinPart(ESkinPart.NUMBER_0 + i).getDrawable()); //TODO asyncmode
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState = skin.toBundle();
		if(!et_skinName.getText().toString().isEmpty())
			outState.putString(Extra.SKIN_SKINNAME, et_skinName.getText().toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		skin = SkinBundler.getSkinFromBundle(savedInstanceState);
		if(savedInstanceState.getString(Extra.SKIN_SKINNAME) != null)
			et_skinName.setText(savedInstanceState.getString(Extra.SKIN_SKINNAME));
	}
}
