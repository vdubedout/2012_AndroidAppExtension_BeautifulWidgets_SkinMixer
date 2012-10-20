package com.andexp.skinmixer.fragment;

public enum SkinPartType {
	//formatter:off
	BACKGROUND(0), 
	FOREGROUND(1), 
	NUMBER_0(2), 
	NUMBER_1(3), 
	NUMBER_2(4), 
	NUMBER_3(5), 
	NUMBER_4(6), 
	NUMBER_5(7), 
	NUMBER_6(8), 
	NUMBER_7(9), 
	NUMBER_8(10), 
	NUMBER_9(11), 
	DOTS(12), 
	AM(13), 
	PM(14), 
	PREVIEW(15), 
	TEXTFILE(16), 
	FIRST_SKIN_IMAGE(BACKGROUND.getValue()), 
	LAST_SKIN_IMAGE(PM.getValue()), 
	VOID(100);
	//formatter:on

	private int value;
	

	SkinPartType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static SkinPartType getSkinpartFromPartName(int skinpart_names_binder) {
		switch (skinpart_names_binder) {
		case 0:
			return SkinPartType.BACKGROUND;
		case 1:
			return SkinPartType.FOREGROUND;
		case 2:
			return SkinPartType.NUMBER_0;
		case 3:
			return SkinPartType.DOTS;
		case 4:
			return SkinPartType.AM;
		default:
			return SkinPartType.BACKGROUND;
		}
	}
}
