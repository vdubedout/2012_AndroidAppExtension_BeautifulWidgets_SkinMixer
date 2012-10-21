package com.andexp.skinmixer.skin;

import com.andexp.skinmixer.displaygroup.SkinPartType;

public enum SkinGroupType {
	//formatter:off
	BACKGROUND(0),
	FOREGROUND(1),
	NUMBERS(2),
	AMPM(3),
	DOTS(4);
	//formatter:on

	private int mValue;
	
	private SkinGroupType(int value) {
		this.mValue = value;
	}
	
	public int getValue(){
		return mValue;
	}
	
	public static SkinGroupType getSkinGroupType(int index){
		for (SkinGroupType type : SkinGroupType.values()) {
			if(type.getValue() == index)
				return type;
		}
		throw new IndexOutOfBoundsException("index:"+index+ " > values Number"+SkinGroupType.values());
	}
	
	public SkinPartType[] getContainedSkinPartType(){
		SkinPartType[] partTypes;
		if(BACKGROUND.getValue() == mValue){
			partTypes = new SkinPartType[]{
					SkinPartType.BACKGROUND
			};
		} else if (FOREGROUND.getValue() == mValue){
			partTypes = new SkinPartType[]{
					SkinPartType.FOREGROUND
			};
		} else if (AMPM.getValue() == mValue){
			partTypes = new SkinPartType[]{
					SkinPartType.AM,
					SkinPartType.PM
			};
		} else if (DOTS.getValue() == mValue){
			partTypes = new SkinPartType[]{
					SkinPartType.DOTS
			};
		} else {
			partTypes = new SkinPartType[]{
				SkinPartType.NUMBER_0,
				SkinPartType.NUMBER_1,
				SkinPartType.NUMBER_2,
				SkinPartType.NUMBER_3,
				SkinPartType.NUMBER_4,
				SkinPartType.NUMBER_5,
				SkinPartType.NUMBER_6,
				SkinPartType.NUMBER_7,
				SkinPartType.NUMBER_8,
				SkinPartType.NUMBER_9
			};
		}
		return partTypes;
	}
}
