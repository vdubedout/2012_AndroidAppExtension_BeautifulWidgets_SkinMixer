package com.andexp.skinmixer;

import com.andexp.skinmixer.skin.SkinGroupType;

public interface OnPreviewCompleteListener {
	public void OnPreviewComplete(String[] mGroupPartPaths);
	public void OnPreviewUncomplete(SkinGroupType[] groupsMissing);
}
