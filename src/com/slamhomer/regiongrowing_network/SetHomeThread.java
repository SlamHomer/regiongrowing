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

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;

public class SetHomeThread extends Thread{
	private double lat = 0.0;
	private double lon = 0.0;
	private String name = null;
	
	public SetHomeThread(){
		this.lat = Gamemanager.getLocalPlayer().getpLatitude();
		this.lon = Gamemanager.getLocalPlayer().getpLongitude();
		this.name = Gamemanager.getLocalPlayer().getName();
	}
	
	public void run(){
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/standort.php");
	    String res = null;
	    
	    try {
	        // Add data
	    	
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	    	nameValuePairs.add(new BasicNameValuePair("name", this.name));
	        nameValuePairs.add(new BasicNameValuePair("lat", String.valueOf(lat)));
	        nameValuePairs.add(new BasicNameValuePair("lon", String.valueOf(lon)));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        res = Network.convertStreamToString(is);
	        
	        System.out.println("RES: "+res);
	        System.out.println("Name: "+this.name);
	        System.out.println("LAT: "+this.lat);
	        System.out.println("LONG: "+this.lon);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	    
	    Network.setLastCode(res);
	}

}


