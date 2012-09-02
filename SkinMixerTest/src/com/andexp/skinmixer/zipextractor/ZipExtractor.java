package com.andexp.skinmixer.zipextractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.util.Log;

public class ZipExtractor {
	String mOutputDirectory;

	public ZipExtractor(String outputDirectory) {
		mOutputDirectory = outputDirectory;
		createDestinationPath();
	}

	public boolean createDestinationPath() {
		return new File(mOutputDirectory).mkdirs();
	}


	public void extractZipFile(InputStream inputStream)
	{
		try
		{
			ZipInputStream zipinputstream = new ZipInputStream(inputStream);
			ZipEntry zipentry = zipinputstream.getNextEntry();
			while (zipentry != null)
				zipentry = extractZipEntry(mOutputDirectory, zipinputstream, zipentry);

			zipinputstream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private ZipEntry extractZipEntry(String destinationname, ZipInputStream zipinputstream, ZipEntry zipentry)
			throws FileNotFoundException, IOException {

		String entryName = zipentry.getName();
		Log.d("Test Zip", "zipentry name : "+entryName);
		if(!zipentry.isDirectory()){
			System.out.println("entryname "+entryName);

			File newFile = new File(entryName);
			String directory = newFile.getParent();

			if(directory == null)
			{
				if(newFile.isDirectory())
					return null;
			}

			copyStreamToFile(destinationname, zipinputstream, entryName); 
		} else {
			boolean worked = new File(destinationname+entryName).mkdirs();
			Log.d("Test Zip", "Create dir : "+destinationname+entryName+" Creation Worked?"+worked);
		}
		zipinputstream.closeEntry();
		zipentry = zipinputstream.getNextEntry();


		return zipentry;
	}

	private void copyStreamToFile(String destinationname, ZipInputStream zipinputstream, String entryName)
			throws FileNotFoundException, IOException {
		byte[] buf = new byte[1024];
		int n;
		FileOutputStream fileoutputstream = new FileOutputStream(destinationname+entryName);             
		
		while ((n = zipinputstream.read(buf, 0, 1024)) > -1)
			fileoutputstream.write(buf, 0, n);

		fileoutputstream.close();
	}




}
