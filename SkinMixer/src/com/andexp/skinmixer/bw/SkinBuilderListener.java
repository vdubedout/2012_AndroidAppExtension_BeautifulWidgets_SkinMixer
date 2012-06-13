package com.andexp.skinmixer.bw;

public interface SkinBuilderListener {
	public void SetProgressBarMax(int progressMax);
	public void OnNewStep(int value);
	public void OnFail(String errorMessage);
	public void OnComplete();
	
}
