package com.slamhomer.regiongrowing_network;

import java.io.IOException;
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
import org.apache.http.util.EntityUtils;

import android.widget.EditText;

public class RegThread extends Thread{
	private EditText name = null;
	private EditText password = null;
	private EditText email = null;
	//private Context context = null;
	
	public RegThread(final EditText name,final EditText password, final EditText email){
		//this.context = context;
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	public void run(){
		// Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.slamhomer.com/region/register.php");
	    String res = null;
	    
	    try {
	        // Add data
	    	
	    	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("name", this.name.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("pw", this.password.getText().toString()));
	        nameValuePairs.add(new BasicNameValuePair("email", this.email.getText().toString())); 
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));


	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        
/*	        HttpEntity entity = response.getEntity();
	        InputStream is = entity.getContent();
	        
	        res = Network.convertStreamToString(is);*/
	        
	        final HttpEntity tmpEnt = response.getEntity();
			String tmpString = new String(EntityUtils.toString(tmpEnt, "ISO-8859-1"));
			res = tmpString;
	        
	    } catch (ClientProtocolException e) {
	    	System.out.println("ClientProtocolException");
	    } catch (IOException e) {
	    	System.out.println("IOException");
	    }
	    
	    Network.setLastCode(res);
	}

}
