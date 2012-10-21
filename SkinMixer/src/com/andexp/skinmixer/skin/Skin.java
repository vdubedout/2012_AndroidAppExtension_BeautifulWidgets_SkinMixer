package com.andexp.skinmixer.skin;

import android.os.Bundle;

import com.andexp.skinmixer.displaygroup.SkinPartType;
import com.andexp.skinmixer.skindata.SkinData;

public class Skin implements SkinImpl {
	public Skin() {
	}
	
	@Override
	public SkinData getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SkinPartImpl getPart(SkinPartType partType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPart(SkinPartImpl skinPart, SkinPartType partType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SkinGroupImpl getGroup(SkinGroupType groupType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroup(SkinGroupImpl skinGroup, SkinGroupType groupType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSkinComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bundle toBundle() {
		// TODO Auto-generated method stub
		return null;
	}
}
