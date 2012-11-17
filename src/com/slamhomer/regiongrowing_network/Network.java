package com.slamhomer.regiongrowing_network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.slamhomer.regiongrowing.DisplayMenuActivity;
import com.slamhomer.regiongrowing.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.EditText;


public class Network{
final static String OKAYCODE = "OK";
final static String FAILCODE = "FAIL";

	private static String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append((line + "\n"));
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
	
	
	// Mit context kann auf die UI Elemente zugegriffen werden
	public static void postDataLogin(final EditText name, final EditText password, final Context context) {
	
		new Thread(new Runnable() {
			public void run() {
				System.out.println("THREAD!!!!!");
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/login.php");
				String res = null;
			    
			    try {
			        // Add data
			    	
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        nameValuePairs.add(new BasicNameValuePair("name", name.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("pw", password.getText().toString()));    
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        
			        HttpEntity entity = response.getEntity();
			        InputStream is = entity.getContent();
			        
			        res = convertStreamToString(is);
			        System.out.println("PREIF__RES: "+res);
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
			    
				if (res.equals(OKAYCODE)) {  // BUG Warum zu Teufel geht er hier immer in KRITRES !?!?!??!?
					System.out.println("OK__RES: "+res);
					Intent intent = new Intent(context, DisplayMenuActivity.class);
					context.startActivity(intent);
				}else if(res.equals(FAILCODE)){
					System.out.println("FAILRES: "+res);
					
					 // TODO: Alert "Falscher Benutzername oder Passwort"
					 
				}else{
					System.out.println("KRITRES: "+res);
					
					 // TODO: Alert "Kritischer Fehler"
					 
				}
			}
		}).start();
		   	    
	} 
	
	// Mit context kann auf die UI Elemente zugegriffen werden
	public static void postDataReg(final EditText name,final EditText password, final EditText email, final Context context) {
	    
		new Thread(new Runnable() {
			public void run() {
				System.out.println("THREAD!!!!!");
				// Create a new HttpClient and Post Header
			    HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/register.php");
			    String res = null;
			    
			    try {
			        // Add data
			    	
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        nameValuePairs.add(new BasicNameValuePair("name", name.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("pw", password.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("email", email.getText().toString())); 
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        
			        HttpEntity entity = response.getEntity();
			        InputStream is = entity.getContent();
			        
			        res = convertStreamToString(is);
			        System.out.println("RES: "+res);
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
			    
				if (res.equals(OKAYCODE)) {
					Intent intent = new Intent(context, MainActivity.class);
					context.startActivity(intent);
				} else if (res.equals(FAILCODE)) {
					/*
					 * TODO: Alert
					 */
				} else {
					/*
					 * TODO: Alert "Kritischer Fehler"
					 */
				}
			}
				
			}).start();
		
	}

}
