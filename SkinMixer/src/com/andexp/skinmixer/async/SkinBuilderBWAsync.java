package com.andexp.skinmixer.async;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import android.os.AsyncTask;

import com.andexp.skinmixer.MyApplication;
import com.andexp.skinmixer.R;
import com.andexp.skinmixer.bw.Skin;
import com.andexp.skinmixer.bw.SkinImpl;
import com.andexp.skinmixer.bw.creation.SkinCreator;
import com.andexp.skinmixer.bw.part.base.BaseSkinPart;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.bw.part.base.text.SkinDataWriter;
import com.andexp.skinmixer.utils.MLog;
import com.andexp.skinmixer.utils.SDCard;


public class SkinBuilderBWAsync extends AsyncTask<Void, Integer, Boolean> {
	public interface AsyncListener{
		public void SetProgressBarMax(int progressMax);
		public void OnUpdate(int value);
		public void OnFail(String reason);
	}
	AsyncListener mAsyncListener;

	final static int PROGRESS_MAX = 12;
	SkinImpl myNewSkin;
	String failMessage;

	public SkinBuilderBWAsync(SkinImpl mySkin, AsyncListener asyncListener){
		myNewSkin = mySkin;
		mAsyncListener = asyncListener;
		mAsyncListener.SetProgressBarMax(PROGRESS_MAX);
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		Boolean worked = true;

		try{

			generateDirectoryName();
			publishProgress((int)1);
			
			createSkinData();
			publishProgress((int)2);
			
			SkinCreator create = new SkinCreator((Skin) myNewSkin);
			publishProgress((int)3);

			create.directory();
			publishProgress((int)4);

			create.preview();
			publishProgress((int)5);

			create.noMediaFile();
			publishProgress((int)6);

			create.copyBackground();
			publishProgress((int)7);

			create.copyBackgroundNumbers();
			publishProgress((int)8);

			create.copyNumbers();
			publishProgress((int)9);

			create.copyDots();
			publishProgress((int)10);

			create.copyAmPm();
			publishProgress((int)11);

			SkinDataWriter write = new SkinDataWriter(myNewSkin.getSkinData());
			write.createTextFile();
			publishProgress((int)12);

		} catch (IOException e) {
			MLog.e("Error during copy : "+ e.getMessage());
			prepareFailMessage(e.getMessage());
			worked = false;
			SDCard.deleteDir(new File(SDCard.getSuperClockSkinPath() + myNewSkin.getSkinData().directoryName +	File.separator));
		} catch (Exception e) {
			MLog.e("Error during creation");
			failMessage = "Error during creation";
			worked = false;
			SDCard.deleteDir(new File(SDCard.getSuperClockSkinPath() + myNewSkin.getSkinData().directoryName +	File.separator));
		}

		return worked;
	}


	private void prepareFailMessage(String addedMessage){
		String[] message = addedMessage.split(File.separator);
		int length = message.length;

		String skinDirectory = message[length-2];
		String fileMissing = message[length-1].split(" ")[0];
		//TODO send a enum with error to fail
		failMessage = MyApplication.getCustomApplicationContext().getString(R.string.skincreator_lbl_skin_uncomplete, skinDirectory, fileMissing);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		mAsyncListener.OnUpdate(values[0]);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if(result == false){
			displayError();
		}
	}

	private void displayError(){
		mAsyncListener.OnFail(failMessage);
	}

	public void generateDirectoryName(){
		String skinName = myNewSkin.getSkinData().skinName;

		myNewSkin.getSkinData().directoryName = BaseSkinPart.PREFIX_GEN_SKINNAME + GetCharSafeString(skinName);
		int i = 1;
		while (isDirectoryNameAllreadyUsed(myNewSkin.getSkinData().directoryName))
		{ 
			myNewSkin.getSkinData().directoryName += i;
			i++;
		}
	}

	private String GetCharSafeString(String skinName) {
		String output;
		output = skinName.toLowerCase(Locale.ENGLISH);
		output = output.replaceAll("[^a-z0-9]", "");

		return output;
	}

	private boolean isDirectoryNameAllreadyUsed(String directoryName){
		//TODO directoryName checker
		return false;
	}

	public void createSkinData(){
		SkinData data = myNewSkin.getSkinData();
		data.generatedFrom = createGeneratedFrom();
		data.idBackground = myNewSkin.getSkinPart(ESkinPart.BACKGROUND).getSkinPartData().directoryName;
		data.idBackgroundNumber = myNewSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).getSkinPartData().directoryName;
		data.idNumber = myNewSkin.getSkinPart(ESkinPart.NUMBER_0).getSkinPartData().directoryName;
		data.numberSkinType = myNewSkin.getSkinPart(ESkinPart.NUMBER_0).getSkinPartData().clockType;
	}

	private String createGeneratedFrom(){
		StringBuilder builder = new StringBuilder();
		builder.append(getAuthor(myNewSkin.getSkinPart(ESkinPart.BACKGROUND)));
		builder.append(getAuthor(myNewSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS)));
		builder.append(getAuthor(myNewSkin.getSkinPart(ESkinPart.NUMBER_0)));
		return builder.toString();
	}

	private String getAuthor(SkinPartImpl sp){
		if(sp != null && sp.getSkinPartData().author!= null)
			return sp.getSkinPartData().author+File.separator;
		else return "";
	}
}
