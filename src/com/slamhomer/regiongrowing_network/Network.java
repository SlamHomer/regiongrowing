package com.slamhomer.regiongrowing_network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
	
	public static boolean gotInternet(Context context) {
	    ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	        return true;
	    } else {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setTitle("Fehler");
			alertDialogBuilder
					.setMessage(
							"Keine Internet Verbindung")
					.setCancelable(false)
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									return;
								}
							});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
	        return false;
	    }
	}
}
