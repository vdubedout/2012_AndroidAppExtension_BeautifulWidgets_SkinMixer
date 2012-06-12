package com.andexp.skinmixer.bw;

import java.io.File;

import com.andexp.skinmixer.bw.part.base.SkinPartImpl;
import com.andexp.skinmixer.bw.part.base.SkinPart;

public class SkinExtraLoader {
	public static SkinPartImpl loadSkinPart(String compressedSkinInfo){
		String[] textChunk = compressedSkinInfo.split(File.separator);
		String directoryName = textChunk[0];
		int skinPart = Integer.parseInt(textChunk[1]);
		int clockType = Integer.parseInt(textChunk[2]);

		return new SkinPart(directoryName, skinPart, clockType);
	}

	/***
	 * Type directoryName/partType/clockType
	 * @param skinPart
	 * @return compressed skin Part
	 */
	public static String exportSkinPartInfo(SkinPartImpl skinPart){
		String[] data = getSkinDataOf(skinPart);
		StringBuilder compressedSkin = new StringBuilder();
		compressedSkin.append(data[0]).append(File.separator);
		compressedSkin.append(data[1]).append(File.separator);
		compressedSkin.append(data[2]);
		return compressedSkin.toString();
	}

	private static String[] getSkinDataOf(SkinPartImpl skinPart) {
		String[] data = new String[3];
		data[0] = skinPart.getSkinPartData().directoryName;
		data[1] = String.valueOf(skinPart.getSkinPartType());
		data[2] = String.valueOf(skinPart.getSkinPartData().clockType);

		return data;
	}
}
