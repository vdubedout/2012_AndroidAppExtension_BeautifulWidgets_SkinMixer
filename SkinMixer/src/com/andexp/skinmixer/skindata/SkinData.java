package com.andexp.skinmixer.skindata;


public class SkinData {
	//BeautifulWidgetsData
	public String directoryName;
	public String skinName;
	public String author;
	public String url;
	public String donate;
	public int clockType;
	
	// SkinMixerData
	public String generatedFrom;
	public String idBackground;
	public String idBackgroundNumber;
	public String idNumber;
	public int numberSkinType;
	
	public String getDirectoryName() {
		return directoryName;
	}
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	public String getSkinName() {
		return skinName;
	}
	public void setSkinName(String skinName) {
		this.skinName = skinName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDonate() {
		if(donate!=null && donate.length() > 1 && !donate.startsWith("http://") && !donate.startsWith("https://"))
			donate ="http://"+donate;
		
		return donate;
	}
	public void setDonate(String donate) {
		this.donate = donate;
	}
	public int getClockType() {
		return clockType;
	}
	public void setClockType(int clockType) {
		this.clockType = clockType;
	}
	public String getGeneratedBy() {
		return generatedFrom;
	}
	public void setGeneratedBy(String generatedBy) {
		this.generatedFrom = generatedBy;
	}
	public String getIdBackground() {
		return idBackground;
	}
	public void setIdBackground(String idBackground) {
		this.idBackground = idBackground;
	}
	public String getIdBackgroundNumber() {
		return idBackgroundNumber;
	}
	public void setIdBackgroundNumber(String idBackgroundNumber) {
		this.idBackgroundNumber = idBackgroundNumber;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public int getNumberSkinType() {
		return numberSkinType;
	}
	public void setNumberSkinType(String numberSkinType) {
		this.numberSkinType = Integer.valueOf(numberSkinType);
	}
	public void setNumberSkinType(int numberSkinType) {
		this.numberSkinType = numberSkinType;
	}
	
	public SkinData(){

	}
	
}
