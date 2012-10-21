package com.andexp.skinmixer.enums;

import com.andexp.skinmixer.displaygroup.SkinPartType;

import android.test.InstrumentationTestCase;

public class EnumTests extends InstrumentationTestCase{
	public void testFileNameValues(){
		assertNotNull(SkinPartType.BACKGROUND.getFileName());
	}
	
}
