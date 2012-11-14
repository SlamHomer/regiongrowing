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
import android.widget.EditText;


public class Network{
	static String GLOBAL_LOGIN = null; //OMFG DIRTY !!!!
	static String GLOBAL_REG = null; //OMFG DIRTY !!!!
	
	
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
	
	
	
	public static String postDataLogin(final EditText name, final EditText password) {
	    
		new Thread(new Runnable() {
			public void run() {
				System.out.println("THREAD!!!!!");
				HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/test.php");
			    //String res = null;
			    
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
			        
			        GLOBAL_LOGIN = convertStreamToString(is);
			       
			        
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
			}
		}).start();
		// Create a new HttpClient and Post Header
	    
	    
	    return GLOBAL_LOGIN;
	} 
	
	public static String postDataReg(final EditText name,final EditText password, final EditText email) {
	    
		new Thread(new Runnable() {
			public void run() {
				System.out.println("THREAD!!!!!");
				// Create a new HttpClient and Post Header
			    HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/register.php");
			    //String res = null;
			    
			    try {
			        // Add data
			    	
			    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        nameValuePairs.add(new BasicNameValuePair("name", name.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("pw", password.getText().toString()));
			        nameValuePairs.add(new BasicNameValuePair("email", email.getText().toString())); 
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        GLOBAL_REG = response.toString();
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
			}
				
			}).start();
		
	    
	    return GLOBAL_REG;
	}

}
