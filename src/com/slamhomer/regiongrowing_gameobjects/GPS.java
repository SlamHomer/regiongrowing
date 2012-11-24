package com.slamhomer.regiongrowing_gameobjects;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GPS{
	final static String FILENAME_HOMELOC = "homeLoc";
	private static String latitude = null;
	private static String longitude = null;

	public static String getLatitude() {
		return latitude;
	}


	protected static void setLatitude(String latitude) {
		System.out.println("OLD LATITUDE: "+GPS.latitude);
		GPS.latitude = latitude;
		System.out.println("NEW LATITUDE: "+GPS.latitude);
	}


	public static String getLongitude() {
		return longitude;
	}


	protected static void setLongitude(String longitude) {
		System.out.println("OLD LONGITUDE: "+GPS.longitude);
		GPS.longitude = longitude;
		System.out.println("NEW LONGITUDE: "+GPS.longitude);
	}


	//Methode die die Home Location und den akt.
	//Context als Parameter bekommt und diese local -privat- speichert
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
