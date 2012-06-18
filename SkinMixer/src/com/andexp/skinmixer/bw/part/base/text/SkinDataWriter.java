package com.andexp.skinmixer.bw.part.base.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;

import com.andexp.skinmixer.MyApplication;
import com.andexp.skinmixer.R;
import com.andexp.skinmixer.utils.MLog;

public class SkinDataWriter {
	SkinData data;
	Context context;
	
	public SkinDataWriter(SkinData data) {
		this.data = data;
		this.context = MyApplication.getCustomApplicationContext();
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
		File textFile = new File(data.getFullDirectoryPath()
				+data.directoryName
				+SkinDataReader.EXTENSION_TXT);

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
