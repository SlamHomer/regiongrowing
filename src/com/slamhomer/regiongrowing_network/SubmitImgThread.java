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

public class SubmitImgThread extends Thread{
	private String name = null;
	private String taskname = null;
	private String bytecode = null;

	public SubmitImgThread(final String name, 
			final String taskname, final String bytecode){
		this.taskname = taskname;
		this.bytecode = bytecode;
	}

	public void run(){
			System.out.println("SUBMIT IMG THREAD!!!!!");
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://www.slamhomer.com/region/XXX.php");
			String res = null;
			try {
				// Add data

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("name", this.name));
				nameValuePairs.add(new BasicNameValuePair("taskname", this.taskname));
				nameValuePairs.add(new BasicNameValuePair("bytecode", this.bytecode));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);

				final HttpEntity tmpEnt = response.getEntity();
				String tmpString = new String(EntityUtils.toString(tmpEnt, "ISO-8859-1"));
				res = tmpString;
				
				System.out.println("RES: " + res);

			} catch (ClientProtocolException e) {
				System.out.println("ClientProtocolException");
			} catch (IOException e) {
				System.out.println("IOException");
			}
	}
}
