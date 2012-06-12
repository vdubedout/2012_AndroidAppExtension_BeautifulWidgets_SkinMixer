package com.andexp.skinmixer.bw.creation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.bw.Skin;
import com.andexp.skinmixer.bw.part.base.BaseSkinPart;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.text.SkinData;
import com.andexp.skinmixer.utils.Assets;
import com.andexp.skinmixer.utils.MLog;
import com.andexp.skinmixer.utils.SDCard;

public class SkinCreator {
	private SkinData myData; 
	private Skin myComposedSkin;
	private Context myAppContext;
	
	public SkinCreator(Skin mySkin, Context applicationContext){
		this.myData = mySkin.getSkinData();
		this.myComposedSkin = mySkin;
		this.myAppContext = applicationContext;
	}
	
	public void directory() throws Exception{
		File dir = new File(myData.getFullDirectoryPath());
		if(!dir.mkdirs())
			throw new Exception(myAppContext.getResources().getString(R.string.logs_error_create_directory));
	}

	public void preview(){
		StringBuilder previewFilePath = new StringBuilder(myData.getFullDirectoryPath());
		previewFilePath.append(myData.directoryName);
		previewFilePath.append(BaseSkinPart.EXTENSION_JPG);
		Assets.copySkinPreviewTo(previewFilePath.toString(), myAppContext);
	}

	public void noMediaFile(){
		File noMedia = new File(myData.getFullDirectoryPath()+".nomedia");
		MLog.v("Create .nomedia to :"+ noMedia.getAbsolutePath());
		BufferedWriter buffer;
		try {
			buffer = new BufferedWriter(new FileWriter(noMedia, true));
			buffer.newLine();
			buffer.close();
		} catch (IOException e) {
			MLog.d("Can't create .nomedia to "+e.getMessage());
		}
	}

	public void copyBackground() throws IOException {
		String outputFilePath = myData.getFullDirectoryPath()+ myComposedSkin.getSkinPart(ESkinPart.BACKGROUND).getFileName();
		if(myComposedSkin.getSkinPart(ESkinPart.BACKGROUND) == null){
			MLog.v("Copy Blank background "+myComposedSkin.getSkinPart(ESkinPart.BACKGROUND).getImagePath());
			Assets.copyVoidBackgroundTo(outputFilePath, myAppContext);
		} else {
			MLog.v("Copy background from : "+myComposedSkin.getSkinPart(ESkinPart.BACKGROUND).getImagePath());
			SDCard.copyFile(myComposedSkin.getSkinPart(ESkinPart.BACKGROUND).getImagePath(), outputFilePath);
		}
	}

	public void copyBackgroundNumbers() throws IOException  {
		String outputFilePath = myData.getFullDirectoryPath()+ myComposedSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).getFileName();
		if(myComposedSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS) == null){
			MLog.v("Copy Blank background numbers "+ myComposedSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).getImagePath());
			Assets.copyVoidBackgroundTo(outputFilePath, myAppContext);
		} else {
			MLog.v("Copy background from : "+myComposedSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).getImagePath());
			SDCard.copyFile(myComposedSkin.getSkinPart(ESkinPart.BACKGROUND_NUMBERS).getImagePath(), outputFilePath);
		}
	}
	
	public void copyNumbers() throws IOException {
		String outputFilePath = myData.getFullDirectoryPath();
		for(int i=0; i<10;i++){
			MLog.v("Copy numbers "+String.valueOf(i)+" from : "+myComposedSkin.getSkinPart(ESkinPart.NUMBER_0+i).getImagePath());
			SDCard.copyFile(myComposedSkin.getSkinPart(ESkinPart.NUMBER_0+i).getImagePath(),outputFilePath+myComposedSkin.getSkinPart(ESkinPart.NUMBER_0+i).getFileName());
		}
	}
	
	public void copyDots() throws IOException {
		String outputFilePath = myData.getFullDirectoryPath()+myComposedSkin.getSkinPart(ESkinPart.DOTS).getFileName();
		SDCard.copyFile(myComposedSkin.getSkinPart(ESkinPart.DOTS).getImagePath(), outputFilePath);
	}
	
	public void copyAmPm() throws IOException {
		SDCard.copyFile(myComposedSkin.getSkinPart(ESkinPart.AM).getImagePath(), myData.getFullDirectoryPath() + myComposedSkin.getSkinPart(ESkinPart.AM).getFileName());
		SDCard.copyFile(myComposedSkin.getSkinPart(ESkinPart.PM).getImagePath(), myData.getFullDirectoryPath() + myComposedSkin.getSkinPart(ESkinPart.PM).getFileName());
	}
}
