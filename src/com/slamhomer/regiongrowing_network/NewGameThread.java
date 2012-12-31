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

public class NewGameThread extends Thread{
	private String name = null;
	private String players = null;
	
	public NewGameThread(final String name,final int players){
		this.name = name;
		this.players = String.valueOf(players);
	}
	
	public void run(){
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/newgame.php");
	    String res = null;
	    
	    try {
	        // Add data
	    	
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("name", this.name));
	        nameValuePairs.add(new BasicNameValuePair("anzahl", this.players));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        res = Network.convertStreamToString(is);
	        System.out.println("RES: "+res);
	        
	    } catch (ClientProtocolException e) {
	    	System.out.println("ClientProtocolException");
	    } catch (IOException e) {
	    	System.out.println("IOException");
	    }
	    
	    Network.setLastCode(res);
	}
}
