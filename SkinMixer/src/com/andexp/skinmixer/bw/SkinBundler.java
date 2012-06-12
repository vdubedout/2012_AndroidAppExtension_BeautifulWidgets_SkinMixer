package com.andexp.skinmixer.bw;

import java.io.File;

import android.os.Bundle;
import com.andexp.skinmixer.bw.part.base.ESkinPart;
import com.andexp.skinmixer.bw.part.base.text.SkinData;

public class SkinBundler {
	public static Bundle GetBundleFromSkin(SkinImpl skin){
		Bundle myBundle = new Bundle();
		SkinData myData;

		for(int skinImage = 0; skinImage <= ESkinPart.LAST_SKIN_IMAGE; skinImage++){
			if(skin.getSkinPart(skinImage) != null){
				myData = skin.getSkinPart(skinImage).getSkinPartData();
				myBundle.putString(String.valueOf(skinImage), getStringFrom(myData));			
			}
		}

		return myBundle;
	}

	private static String getStringFrom(SkinData data){
		StringBuilder builder = new StringBuilder();
		builder.append(data.directoryName);
		builder.append(File.separator);
		builder.append(data.clockType);
		return builder.toString();
	}

	public static SkinImpl getSkinFromBundle(Bundle bundle){
		SkinImpl mySkin = SkinFactory.getNewSkin();
		String myData;
		SkinData mySkinData;
		
		for(int skinImage = 0; skinImage <= ESkinPart.LAST_SKIN_IMAGE; skinImage++){
			myData = bundle.getString(String.valueOf(skinImage));
			if(myData != null){
				mySkinData = getBasicSkinDataFrom(myData);
				mySkin.setSkinPartByRepertory(mySkinData.directoryName, skinImage, mySkinData.clockType);
			}
		}
		
		return mySkin;
	}

	private static SkinData getBasicSkinDataFrom(String data){
		SkinData mySkinData = new SkinData();
		mySkinData.setDirectoryName(data.split(File.separator)[0]);
		mySkinData.setClockType(Integer.parseInt(data.split(File.separator)[1]));
		return mySkinData;
	}


}
