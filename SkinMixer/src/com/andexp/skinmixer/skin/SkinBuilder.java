package com.andexp.skinmixer.skin;

public class SkinBuilder {
	public static SkinImpl Create(String path){
		SkinImpl skin = new Skin(path);
		return skin;
	}
}
