package com.andexp.skinmixer.skindata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.andexp.skinmixer.path.SkinLister;
import com.andexp.skinmixer.utils.MLog;

public class SkinDataReader {
	public static final String EXTENSION_TXT = ".txt";

	private File mTextFile;
	private SkinData mSkinData;

	public SkinDataReader(String directoryPath) {
		mTextFile = getTextFileFromPath(directoryPath);
	}

	private File getTextFileFromPath(String directoryName) {
		String name = SkinLister.getNameFromPath(directoryName);
		if (name != null)
			return new File(directoryName + name + EXTENSION_TXT);
		else return null;
	}

	public SkinData getData() {
		if (mTextFile.isFile()) {
			ArrayList<String> textLines = tryExtractingDataFromFile(mTextFile);
			loadSkinDataFromTextArray(textLines);
		}
		return mSkinData;
	}

	private ArrayList<String> tryExtractingDataFromFile(File myTextFile) {
		ArrayList<String> textLines = null;

		try {
			textLines = extractDataFromSkinTextFile(myTextFile);
		} catch (FileNotFoundException e) {
			MLog.e("Text File not found : " + e.getMessage());
		} catch (IOException e) {
			MLog.e("Text File IOException : " + e.getMessage());
		}

		return textLines;
	}

	private ArrayList<String> extractDataFromSkinTextFile(File myTextFile)
			throws FileNotFoundException, IOException {
		ArrayList<String> textLines = new ArrayList<String>();

		FileInputStream fileInputStream = new FileInputStream(myTextFile);
		InputStreamReader inputStream = new InputStreamReader(fileInputStream,
				Charset.forName("UTF-8"));
		BufferedReader bufferedReader = new BufferedReader(inputStream);

		String lineData = bufferedReader.readLine();
		while (lineData != null) {
			textLines.add(lineData);
			lineData = bufferedReader.readLine();
		}

		fileInputStream.close();

		return textLines;
	}

	private void loadSkinDataFromTextArray(ArrayList<String> textLines) {
		mSkinData = new SkinData();
		if (textLines != null) {
			for (int i = 0; i < textLines.size(); i++) {
				for (int j = 0; j < SkinDataName.TAGS.length; j++) {
					if (textLines.get(i).toLowerCase().contains(SkinDataName.TAGS[j])) {
						extractDataFromLine(textLines.get(i), j);
						break;
					}
				}
			}
		}
	}

	private SkinData extractDataFromLine(String textLine, int dataNameId) {
		String data = extractDataFromLine(textLine);

		switch (dataNameId) {
		case SkinDataName.SKINNAME:
			mSkinData.skinName = data;
			break;
		case SkinDataName.AUTHOR:
			mSkinData.author = data;
			break;
		case SkinDataName.URL:
			mSkinData.url = data;
			break;
		case SkinDataName.DONATE:
			mSkinData.donate = data;
			break;
		case SkinDataName.GENERATEDBY:
			mSkinData.generatedFrom = data;
			break;
		case SkinDataName.BACKGROUND:
			mSkinData.idBackground = data;
			break;
		case SkinDataName.BACKGROUNDNUMBER:
			mSkinData.idBackgroundNumber = data;
			break;
		case SkinDataName.NUMBER:
			mSkinData.idNumber = data;
			break;
		case SkinDataName.NUMBER_SKINTYPE:
			mSkinData.setNumberSkinType(data);
			break;
		}
		
		return mSkinData;
	}

	private String extractDataFromLine(String textLine) {
		if (textLine != null && containsAssociation(textLine) && containsTagValue(textLine))
			return getTagValue(textLine);
		else
			return null;
	}

	private boolean containsAssociation(String textLine) {
		if (textLine.split("=", 2).length > 1)
			return true;
		else
			return false;
	}

	private boolean containsTagValue(String textLine) {
		if (textLine.split("=", 2)[1].replaceAll(" ", "").length() > 1)
			return true;
		else
			return false;
	}

	private String getTagValue(String textLine) {
		return textLine.split("=", 2)[1];
	}

}
