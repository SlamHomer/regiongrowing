package com.slamhomer.regiongrowing_network;

import java.io.IOException;
import java.io.InputStream;
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

public class UpdateThread extends Thread{
	private String name = null;

	public UpdateThread(final String name){
		this.name = name;
	}
	
	public void run(){
		System.out.println("THREAD!!!!!");
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/update.php");
	    String res = null;
	    
	    try {
	        // Add data
	    	
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("name", this.name));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        res = Network.convertStreamToString(is);
	        
	        System.out.println("RES: "+res);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	   
	    if(convertUpdate(res) == false){ 
		   Network.setLastCode(res);
	   }
	    
	}
	
	/*
	 * Methode um den übergebenen String in die Gamedaten umzuwandeln und zu setzen.
	 * Gibt true zurück, wenn res != null ist und die Daten gesetzt werden können.
	 * TODO: impl.
	 */
	private static boolean convertUpdate(String res){
		if(res != null){
			//do shit
			
			return true;
		}else{
			return false;
		}
	}

}
