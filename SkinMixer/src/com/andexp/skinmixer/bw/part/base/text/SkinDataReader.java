package com.andexp.skinmixer.bw.part.base.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.andexp.skinmixer.utils.MLog;
import com.andexp.skinmixer.utils.SDCard;

public class SkinDataReader {
	public static final String EXTENSION_TXT = ".txt";

	private File myTextFile;
	private SkinData skinData;

	public SkinDataReader(String directoryName, int clockType){
		SetMyTextFile(directoryName, clockType);
		skinData = new SkinData();
		skinData.directoryName = directoryName;
	}

	private void SetMyTextFile(String directoryName, int clockType) {
		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(SDCard.getClockSkinPath(clockType));
		pathBuilder.append(directoryName);
		pathBuilder.append(File.separator);
		pathBuilder.append(directoryName).append(EXTENSION_TXT);
		myTextFile = new File(pathBuilder.toString());
	}

	public SkinData read(){
		if(myTextFile.isFile()){
			ArrayList<String> textLines = tryExtractingDataFromFile(myTextFile);
			loadSkinDataFromTextArray(textLines);
		}
		return skinData;
	}

	private ArrayList<String> tryExtractingDataFromFile(File myTextFile){
		ArrayList<String> textLines = null;

		try {
			textLines = extractDataFromSkinTextFile(myTextFile);
		} catch (FileNotFoundException e) {
			MLog.e("Text File not found : "+e.getMessage());
		} catch (IOException e) {
			MLog.e("Text File IOException : "+e.getMessage());
		}

		return textLines;
	}

	private ArrayList<String> extractDataFromSkinTextFile(File myTextFile) throws FileNotFoundException, IOException {
		ArrayList<String> textLines = new ArrayList<String>();

		FileInputStream fileInputStream = new FileInputStream(myTextFile);
		InputStreamReader inputStream = new InputStreamReader(fileInputStream , Charset.forName("UTF-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStream);

		String lineData = bufferedReader.readLine();
		while (lineData != null) {
			textLines.add(lineData);
			lineData=bufferedReader.readLine();
		}

		fileInputStream.close();

		return textLines;
	}

	private void loadSkinDataFromTextArray(ArrayList<String> textLines){
		if(textLines!=null){
			for (int i = 0; i < textLines.size(); i++){
				for (int j = 0; j < SkinDataName.TAGS.length; j++) {
					if(textLines.get(i).toLowerCase().contains(SkinDataName.TAGS[j])){
						extractDataFromLine(textLines.get(i), j);
						break;
					}
				}
			}
		}
	}

	private void extractDataFromLine(String textLine, int dataNameId) {
		String data = extractDataFromLine(textLine);		

		switch (dataNameId) {
		case SkinDataName.SKINNAME :
			skinData.skinName = data;
			break;
		case SkinDataName.AUTHOR:
			skinData.author = data;
			break;
		case SkinDataName.URL:
			skinData.url = data;
			break;
		case SkinDataName.DONATE:
			skinData.donate = data;
			break;
		case SkinDataName.GENERATEDBY:
			skinData.generatedFrom = data;
			break;
		case SkinDataName.BACKGROUND:
			skinData.idBackground = data;
			break;
		case SkinDataName.BACKGROUNDNUMBER:
			skinData.idBackgroundNumber = data;
			break;
		case SkinDataName.NUMBER:
			skinData.idNumber = data;
			break;
		case SkinDataName.NUMBER_SKINTYPE:
			skinData.setNumberSkinType(data);
			break;
		}
	}

	private String extractDataFromLine(String textLine){
		if(textLine != null
				&& containsAssociation(textLine) 
				&& containsTagValue(textLine))
			return getTagValue(textLine);
		else return null;
	}
	
	private boolean containsAssociation(String textLine){
		if(textLine.split("=", 2).length > 1)
			return true;
		else return false;
	}
	
	private boolean containsTagValue(String textLine){
		if(textLine.split("=", 2)[1].replaceAll(" ", "").length() > 1)
			return true;
		else return false;
	}
	
	private String getTagValue(String textLine){
		return textLine.split("=", 2)[1];
	}


}
