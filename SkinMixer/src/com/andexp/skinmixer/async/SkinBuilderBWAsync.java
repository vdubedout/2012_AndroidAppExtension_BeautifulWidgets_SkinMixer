package com.andexp.skinmixer.async;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andexp.skinmixer.MyApplication;
import com.andexp.skinmixer.R;
import com.andexp.skinmixer.bw.Skin;
import com.andexp.skinmixer.bw.SkinImpl;
import com.andexp.skinmixer.bw.creation.SkinDelete;
import com.andexp.skinmixer.bw.part.base.BaseSkinPart;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.bw.part.base.text.SkinDataWriter;
import com.andexp.skinmixer.utils.Extra;
import com.andexp.skinmixer.utils.MLog;


public class SkinBuilderBWAsync extends AsyncTask<Void, Integer, Boolean> {
	public interface AsyncListener{
		public void SetProgressBarMax(int progressMax);
		public void OnUpdate(int value);
		public void OnFail(String reason);
	}
	AsyncListener mAsyncListener;

	final static int PROGRESS_MAX = 17;
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

			// (generer le nom du skin)
			generateDirectoryNameFromSkinName();

			// creer les data du skin 

			// creer le repertoire

			// creer la preview

			// creer le nomedia

			// copier toutes les parties

			// ecrire le text file

			//			myNewSkin = new Skin();
			//			publishProgress((int)1);
			//
			//			myNewSkin.getSkinData().setAuthor(myAuthor);
			//			publishProgress((int)2);
			//
			//			myNewSkin.getSkinData().skinName = generateDirectoryNameFromSkinName(mySkinName); //TODO 
			//			publishProgress((int)3);
			//
			//			myNewSkin.createSkinData(mySkinPartsHandler);
			//			publishProgress((int)4);
			//
			//			myNewSkin.loadSkinParts(mySkinPartsHandler);
			//			publishProgress((int)5);
			//
			//			if(!myNewSkin.isSkinComplete()) throw new Exception();
			//			myNewSkin.linkDotsTo(ESkinPart.BACKGROUND_NUMBERS);
			//			publishProgress((int)6);
			//
			//			myNewSkin.linkAMPMTo(ESkinPart.NUMBERS);
			//			publishProgress((int)7);
			//
			//			myNewSkin.initializeCreator();
			//			publishProgress((int)8);
			//
			//			myNewSkin.create.directory();
			//			publishProgress((int)9);
			//
			//			myNewSkin.create.preview();
			//			publishProgress((int)10);
			//
			//			myNewSkin.create.noMediaFile();
			//			publishProgress((int)11);
			//
			//			myNewSkin.create.copyBackground();
			//			publishProgress((int)12);
			//
			//			myNewSkin.create.copyBackgroundNumbers();
			//			publishProgress((int)13);
			//
			//			myNewSkin.create.copyNumbers();
			//			publishProgress((int)14);
			//
			//			myNewSkin.create.copyDots();
			//			publishProgress((int)15);
			//
			//			myNewSkin.create.copyAmPm();
			//			publishProgress((int)16);
			//
			//			SkinDataWriter write = new SkinDataWriter(myNewSkin.data, myActivity.getApplicationContext());
			//			write.createTextFile();
			//			publishProgress((int)17);

			//		} catch (IOException e) {
			//			MLog.e("Error during copy : "+ e.getMessage());
			//			prepareFailMessage(e.getMessage());
			//			worked = false;
			//			myNewSkin.delete.deleteAll();
		} catch (Exception e) {
			MLog.e("Error during creation");
			failMessage = "Error during creation";
			worked = false;
			//			myNewSkin.delete.deleteAll();

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

	public void generateDirectoryNameFromSkinName(){
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
		data.generatedFrom = createGeneratedFrom(skinparts);
		data.idBackground = getIdBackground(skinparts);
		data.idBackgroundNumber = getIdBackgroundNumber(skinparts);
		data.idNumber = getIdNumber(skinparts);
		data.numberSkinType = getNumberSkinType(skinparts);
	}

	private String createGeneratedFrom(){
		StringBuilder builder = new StringBuilder();
		if(skinparts.background != null && skinparts.background.data.author!= null)
			builder.append(skinparts.background.data.author);
		if(skinparts.backgroundNumbers != null && skinparts.backgroundNumbers.data.author!= null)
			builder.append(File.separator).append(skinparts.backgroundNumbers.data.author);
		if(skinparts.numbers != null && skinparts.numbers.data.author!= null)
			builder.append(File.separator).append(skinparts.numbers.data.author);

		return builder.toString();
	}
}
