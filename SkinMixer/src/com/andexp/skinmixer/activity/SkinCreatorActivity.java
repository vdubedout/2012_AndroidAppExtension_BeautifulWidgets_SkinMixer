package com.andexp.skinmixer.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.main;
import com.andexp.skinmixer.async.ReadSkinPartTextTask;
import com.andexp.skinmixer.async.ReadSkinPartTextTask.ReadSkinAsyncListener;
import com.andexp.skinmixer.bw.SkinBuilderListener;
import com.andexp.skinmixer.bw.SkinFactory;
import com.andexp.skinmixer.bw.SkinImpl;
import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.utils.Extra;
import com.andexp.skinmixer.utils.MLog;

public class SkinCreatorActivity extends RoboActivity implements ReadSkinAsyncListener, SkinBuilderListener{
	@InjectView(R.id.skincreator_progressbar)					ProgressBar progressBar;
	@InjectView(R.id.skincreator_btn_close)						Button btn_close;
	@InjectView(R.id.skincreator_lbl_backgrounddesigner)		TextView tv_authorBackground;
	@InjectView(R.id.skincreator_lbl_backgroundnumbersdesigner)	TextView tv_authorBackgroundNumber;
	@InjectView(R.id.skincreator_lbl_numbersdesigner)			TextView tv_authorNumber;
	@InjectView(R.id.skincreator_btn_donatebackground)			Button btn_background;
	@InjectView(R.id.skincreator_btn_donatebackgroundnumbers) 	Button btn_backgroundNumber;
	@InjectView(R.id.skincreator_btn_donatenumbers)				Button btn_numbers;

	@InjectExtra(Extra.SKINDATE) 	long mSkinCreationDate;

	private SkinImpl mySkin;
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skincreator);
		mContext = this;

		try{
			mySkin = SkinFactory.getHoldedSkin(mSkinCreationDate);
		} catch (Exception e){
			MLog.e("No skin transfered");
		}

		initializeCloseButtonOnClickListener();

		new ReadSkinPartTextTask(mySkin, this).execute();
	}


	private void initializeCloseButtonOnClickListener() {
		btn_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SkinCreatorActivity.this, main.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
	}


	@Override
	public void OnSkinTextLoaded(SkinData backgroundData,
			SkinData backgroundNumbersData, SkinData numbersData) {
		displayAuthor(tv_authorBackground, 
				R.string.skincreator_lbl_background,
				backgroundData.author);
		InitButtonDonate(btn_background, backgroundData.donate);

		displayAuthor(tv_authorBackgroundNumber, 
				R.string.skincreator_lbl_backgroundNumber, 
				backgroundNumbersData.author);
		InitButtonDonate(btn_backgroundNumber, backgroundNumbersData.donate);

		displayAuthor(tv_authorNumber, 
				R.string.skincreator_lbl_numbers, 
				numbersData.author);

		InitButtonDonate(btn_numbers, numbersData.donate);

		mySkin.build(this);
	}

	private void displayAuthor(TextView tv_authors, int base_text, String data){
		String tempText  = String.format(this.getResources().getString(base_text), data);
		tv_authors.setText(tempText);
		tv_authors.setVisibility(View.VISIBLE);
	}

	private void InitButtonDonate(View view, final String url){
		if(url != null && url.length()>8){
			view.setVisibility(View.VISIBLE);
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					mContext.startActivity(i);
				}
			});
		}
	}


	@Override
	public void SetProgressBarMax(int progressMax) {
		progressBar.setMax(progressMax);
	}


	@Override
	public void OnNewStep(int value) {
		progressBar.setProgress(value);
	}


	@Override
	public void OnFail(String errorMessage) {
		// TODO Auto-generated method stub

	}


	@Override
	public void OnComplete() {
		// TODO Auto-generated method stub

	}
}
