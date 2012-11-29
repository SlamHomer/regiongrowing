package com.slamhomer.regiongrowing_network;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;


public class GPS{
	final static String FILENAME_HOMELOC = "homeLoc";
	private static String latitude = null;
	private static String longitude = null;

	public static String getLatitude() {
		return latitude;
	}


	protected static void setLatitude(String latitude) {
		GPS.latitude = latitude;
	}


	public static String getLongitude() {
		return longitude;
	}


	protected static void setLongitude(String longitude) {
		GPS.longitude = longitude;
	}

	/*
	 * Methode speichert die Home Location auf dem Handy in einer Datei
	 * TODO: Verschlüsselt speichern
	 */
	public static int saveHomeLoc(final String location, final Context context){
		
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
	
	/*
	 * Methode welche aus der Home Location Datei, die Home Location ausliest und 
	 * jeweils Latitude und Longitude für andere Methoden zugänglich macht. Beide
	 * Variablen können mit get***() aufgerufen werden. 
	 */
	public static void loadHomeLoc(Context context){
		  try {
			  InputStream instream = context.openFileInput(FILENAME_HOMELOC);
		 
		      InputStreamReader inputreader = new InputStreamReader(instream);
		      BufferedReader buffreader = new BufferedReader(inputreader);
		                 
		      setLatitude(buffreader.readLine());
		      setLongitude(buffreader.readLine());
		        
		    instream.close();
		  } catch (java.io.FileNotFoundException e) {
			  System.out.println("FileNotFoundException");
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException");
		}
	}

}
