package com.andexp.skinmixer.async;

import android.os.AsyncTask;

import com.andexp.skinmixer.bw.SkinImpl;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.text.SkinData;

public class ReadSkinPartTextTask extends AsyncTask<Void, Void, Void> {
	public interface ReadSkinAsyncListener{
		public void OnSkinTextLoaded(SkinData backgroundData, SkinData backgroundNumbersData, SkinData numbersData);
	}
	
	SkinImpl mySkin;
	ReadSkinAsyncListener mListener;
	SkinData mBackgroundData;
	SkinData mBackgroundNumbersData;
	SkinData mNumbersData;
	
	public ReadSkinPartTextTask(SkinImpl skin, ReadSkinAsyncListener listener){
		mySkin = skin;
		mListener = listener;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		readData();
		return null;
	}
	
	private void readData(){
		mBackgroundData = mySkin.getSkinPart(ESkinPart.BACKGROUND).readDataText();
		mBackgroundNumbersData = mySkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).readDataText();
		mNumbersData = mySkin.getSkinPart(ESkinPart.NUMBER_0).readDataText();
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		mListener.OnSkinTextLoaded(mBackgroundData, mBackgroundNumbersData, mNumbersData);
	}
}
