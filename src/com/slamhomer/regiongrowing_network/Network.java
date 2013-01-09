package com.slamhomer.regiongrowing_network;

import com.slamhomer.regiongrowing.Messages;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



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


/*	protected static String convertStreamToString(InputStream is) {

	    BufferedReader reader = null;
	    
		reader = new BufferedReader(new InputStreamReader(is));
	
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
	}*/
	
	public static boolean gotInternet(Context context) {
	    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    } else {
			Messages.alert("Keine Internet Verbindung", context);
			return false;
	    }
	}
}
