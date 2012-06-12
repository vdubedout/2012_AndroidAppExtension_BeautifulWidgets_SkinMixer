package com.andexp.skinmixer.bw.creation;
//package com.andexp.skinmixer.bw;
//
//import java.io.File;
//import java.util.Locale;
//
//import android.content.Context;
//
//import com.andexp.skinmixer.bw.part.base.BaseSkinPart;
//import com.andexp.skinmixer.bw.part.base.ESkinPart;
//import com.andexp.skinmixer.bw.part.base.text.SkinData;
//import com.andexp.skinmixer.bw.part.specific.AM;
//import com.andexp.skinmixer.bw.part.specific.Background;
//import com.andexp.skinmixer.bw.part.specific.BackgroundNumbers;
//import com.andexp.skinmixer.bw.part.specific.Dots;
//import com.andexp.skinmixer.bw.part.specific.Numbers;
//import com.andexp.skinmixer.bw.part.specific.PM;
 //
//public class SkinOld {
//	//TODO Clean all this, move to own file
//	
//	public Background background;
//	public BackgroundNumbers backgroundNumbers;
//	public Numbers numbers;
//	public AM am;
//	public PM pm;
//	public Dots dots;
//	public Context myAppContext;
//	
//	public SkinData data;
//	public SkinCreator create;
//	public SkinDelete delete;
//	
//	public SkinOld(Context appContext){
//		this.myAppContext = appContext;
//		data = new SkinData();
//	}
//	
//	public void make(){
////		addSkinAuthorMixer(author);
////		generateDirectoryNameFromSkinName(skinName);
////		addPartsSelected(skinParts);
////		createSkinDataFromSkinParts(skinparts);
////		linkDotsTo(skinpartType)
////		linkAMPMTo(skinpartType)
////		isAllSkinPartFilled();
////		initializeCreator();
////		makeDirectory();
////		makePreview();
////		makeNoMediaFile();
////		copyBackground();
////		copyBackgroundNumbers();
////		copyNumbers();
////		copyAmFromNumbers();
////		copyPmFromNumbers();
////		copyDotFromBackgroundNumbersSkin();
////		createTextFile();
//		
//	}
//	
//	public void addSkinAuthorMixer(String author){
//		data.author = author;
//	}
//	
//	public void generateDirectoryNameFromSkinName(String skinName){
//		data.skinName = skinName;
//		
//		data.directoryName = BaseSkinPart.PREFIX_GEN_SKINNAME + GetCharSafeString(skinName);
//		int i = 1;
//		while (isDirectoryNameAllreadyUsed(data.directoryName))
//		{ 
//			data.directoryName += i;
//			i++;
//		}
//		
//		delete = new SkinDelete(data.directoryName);
//	}
//	
//	private String GetCharSafeString(String skinName) {
//		String output;
//		output = skinName.toLowerCase(Locale.ENGLISH);
//		output = output.replaceAll("[^a-z0-9]", "");
//		
//		return output;
//	}
//	
//	private boolean isDirectoryNameAllreadyUsed(String directoryName){
//		//TODO directoryName checker
//		return false;
//	}
//	
//	public void addPartsSelected(HandlerSkinParts skinParts){
//		background = skinParts.background;
//		backgroundNumbers = skinParts.backgroundNumbers;
//		numbers = skinParts.numbers;
//	}
//	
//	public void createSkinData(HandlerSkinParts skinparts){
//		data.generatedFrom = createGeneratedFrom(skinparts);
//		data.idBackground = getIdBackground(skinparts);
//		data.idBackgroundNumber = getIdBackgroundNumber(skinparts);
//		data.idNumber = getIdNumber(skinparts);
//		data.numberSkinType = getNumberSkinType(skinparts);
//	}
//	
//	private String createGeneratedFrom(HandlerSkinParts skinparts){
//		StringBuilder builder = new StringBuilder();
//		if(skinparts.background != null && skinparts.background.data.author!= null)
//			builder.append(skinparts.background.data.author);
//		if(skinparts.backgroundNumbers != null && skinparts.backgroundNumbers.data.author!= null)
//			builder.append(File.separator).append(skinparts.backgroundNumbers.data.author);
//		if(skinparts.numbers != null && skinparts.numbers.data.author!= null)
//			builder.append(File.separator).append(skinparts.numbers.data.author);
//		
//		return builder.toString();
//	}
//	
//	private String getIdBackground(HandlerSkinParts skinparts) {
//		if(skinparts.background != null)
//			return skinparts.background.data.directoryName;
//		else return null;
//	}
//
//	private String getIdBackgroundNumber(HandlerSkinParts skinparts) {
//		if(skinparts.backgroundNumbers != null)
//			return skinparts.backgroundNumbers.data.directoryName;
//		else return null;
//	}
//
//	private String getIdNumber(HandlerSkinParts skinparts) {
//		if(skinparts.numbers != null)
//			return skinparts.numbers.data.directoryName;
//		else return null;
//	}
//
//	private int getNumberSkinType(HandlerSkinParts skinparts) {
//		if(skinparts.numbers != null)
//			return skinparts.numbers.clockType;
//		else return 0;
//	}
//	
//	public void loadSkinParts(HandlerSkinParts skinparts){
//		this.background = skinparts.background;
//		this.backgroundNumbers = skinparts.backgroundNumbers;
//		this.numbers = skinparts.numbers;
//	}
//	
//	public void linkDotsTo(int skinpartType){
//		dots = new Dots(getDirectoryNameOf(skinpartType));
//	}
//	
//	private String getDirectoryNameOf(int skinpartType){
//		switch (skinpartType) {
//		case ESkinPart.BACKGROUND:
//			return background.data.directoryName;
//		case ESkinPart.NUMBERS:
//			return numbers.data.directoryName;
//		default: //backgroundNumber
//			return backgroundNumbers.data.directoryName;
//		}
//	}
//	
//	public void linkAMPMTo(int skinpartType){
//		am = new AM(getDirectoryNameOf(skinpartType));
//		pm = new PM(getDirectoryNameOf(skinpartType));
//	}
//	
//	public boolean isSkinComplete(){
//		boolean isNull = true;
//		if(background == null) isNull = false;
//		if(backgroundNumbers == null) isNull = false;
//		if(numbers == null) isNull = false;
//		return isNull; 
//	}
//	
//	public void initializeCreator(){
//		create = new SkinCreator(this, myAppContext);		
//	}
//}
