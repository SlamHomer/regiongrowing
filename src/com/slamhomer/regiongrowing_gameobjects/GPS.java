package com.slamhomer.regiongrowing_gameobjects;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;

public class GPS{
	final static String FILENAME_HOMELOC = "homeLoc";
	
	//Methode die die Home Location und den akt.
	//Context als Parameter bekommt und diese local -privat- speichert
	public int saveHomeLoc(final String location, final Context context){
		
		FileOutputStream fos;
		try {
			fos = context.openFileOutput(FILENAME_HOMELOC, Context.MODE_PRIVATE);
			fos.write(location.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		return 1;

	}
	
	//Methode die die Home Location zurückliefert
	public String getHomeLoc(final Context context){
		FileReader fr = null;
		BufferedReader br = null;
		String returnString = null;
		
		try {
			fr = new FileReader(FILENAME_HOMELOC);
			br = new BufferedReader(fr);
			
			returnString = br.readLine();
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fehler";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "I/O Exception";
		}
		
		return returnString;
	}
}
