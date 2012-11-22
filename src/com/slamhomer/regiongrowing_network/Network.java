package com.slamhomer.regiongrowing_network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Network{

private static String LastCode = null;

	public static String getLastCode() {
		return LastCode;
	}


	protected static void setLastCode(String lastCode) {
		LastCode = lastCode;
	}
	
	public static void deleteLastCode(){
		LastCode = null;
	}


	protected static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	        	sb.append(line);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
