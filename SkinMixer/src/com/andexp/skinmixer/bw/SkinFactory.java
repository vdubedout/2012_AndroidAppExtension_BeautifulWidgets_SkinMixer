package com.andexp.skinmixer.bw;

public class SkinFactory {
	private static SkinImpl mySkin;
	private static long mySkinSavedTimestamp;
	
	public static SkinImpl getNewSkin(){
		mySkinSavedTimestamp=0;
		return new Skin();
	}
	
	public static void holdSkin(SkinImpl skin, long savedTimestamp){
		mySkin = skin;
		mySkinSavedTimestamp = savedTimestamp;
	}
	
	public static SkinImpl getHoldedSkin(long savedTimestamp) throws Exception{
		if(mySkin!=null && savedTimestamp == mySkinSavedTimestamp){
			return mySkin;
		} else {
			throw new Exception("No skin holded");
		}
	}
}
