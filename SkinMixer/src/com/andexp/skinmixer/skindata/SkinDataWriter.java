package com.andexp.skinmixer.skindata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;

import com.andexp.skinmixer.R;
import com.andexp.skinmixer.utils.MLog;

public class SkinDataWriter {
	private SkinData data;
	private Context context;
	private String mOutputPath;
	
	public SkinDataWriter(Context applicationContext, String outputPath, SkinData data) {
		this.mOutputPath = outputPath;
		this.data = data;
		this.context = applicationContext;
	}
	
	public void createTextFile(){
		//skinname=%1$s\nauthor=%2$s\n\ngeneratedby=%3$s\nbackgroundid=%4$s\nbackgroundnumbersid=%5$s\nnumbersid=%6$s\nnumbersskintype%7$s\n
		String text = context.getString(R.string.skintext, 
				data.skinName, 
				data.author,
				data.generatedFrom,
				data.idBackground,
				data.idBackgroundNumber,
				data.idNumber,
				data.numberSkinType);
		writeText(text);
	}

	private void writeText(String textToWrite){
		File textFile = new File(mOutputPath);

		MLog.v("copy textfile : "+textFile.toString());
		BufferedWriter buffer;
		try {
			buffer = new BufferedWriter(new FileWriter(textFile, true));
			buffer.append(textToWrite);
			buffer.newLine();
			buffer.close();
		} catch (IOException e) {
			MLog.d("Cannot write skin text: "+e.getMessage());
		} 
	}
}
