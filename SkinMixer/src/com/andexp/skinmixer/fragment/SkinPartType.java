package com.andexp.skinmixer.fragment;

import com.andexp.skinmixer.skin.SkinGroupType;

public enum SkinPartType {
	//formatter:off
	BACKGROUND(0, "background.png"), 
	FOREGROUND(1, "background_numbers.png"), 
	NUMBER_0(2, "number_0.png"), 
	NUMBER_1(3, "number_1.png"), 
	NUMBER_2(4, "number_2.png"), 
	NUMBER_3(5, "number_3.png"), 
	NUMBER_4(6, "number_4.png"), 
	NUMBER_5(7, "number_5.png"), 
	NUMBER_6(8, "number_6.png"), 
	NUMBER_7(9, "number_7.png"), 
	NUMBER_8(10, "number_8.png"), 
	NUMBER_9(11, "number_9.png"), 
	DOTS(12, "dots.png"), 
	AM(13, "am.png"), 
	PM(14, "pm.png");
	//formatter:on

	private int mValue;
	private String mFileName;

	SkinPartType(int value, String fileName) {
		this.mValue = value;
		this.mFileName = fileName;
	}

	public int getValue() {
		return this.mValue;
	}
	
	public String getFileName(){
		return mFileName;
	}
	
	public static SkinPartType getType(int index){
		for (SkinPartType type : SkinPartType.values()) {
			if(type.getValue()== index)
				return type;
		}
		throw new IndexOutOfBoundsException("index:"+index+ " > values Number"+SkinGroupType.values());
	}
}
