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
import com.slamhomer.regiongrowing_gameobjects.LocalPlayer;
import com.slamhomer.regiongrowing_gameobjects.Player;

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
	   
	    //wenn update erfolglos dann setzte fehler code
	    if(convertUpdate(res) == false){ 
		   Network.setLastCode("Fehler beim Update");
	   }
	    
	}
	
	/*
	 * Methode um den übergebenen String in die Gamedaten umzuwandeln und zu setzen.
	 * Gibt true zurück, wenn res != null ist und die Daten gesetzt werden können.
	 * TODO: DailyTasks auswerten
	 */
	private static boolean convertUpdate(String res){
		if(res != null && res.length() > 0){
			LocalPlayer tmp_local = new LocalPlayer(null, 0, 0, 0, null, false);
			Player tmp_player = new Player(null, 0, 0, 0);
			
			int index = 0;
			int task_index = res.indexOf("taskname");
			int lastIndex = 0;
			String tmp ="";
			int varCase = 1;
			int pos = 0;
			
			// Local Player
			while(varCase <= 5){
				index = res.indexOf(":", index+1);
				for(int i = 1;res.charAt(index+i) != ';';i++){
					tmp = tmp + res.charAt(index+i);
				}
				//TODO: richtige fehlerbehandlung
				switch(varCase){
					case 1:
						//name
						if(tmp != ""){
							tmp_local.setName(tmp);
						}else{
							tmp_local.setName("LEER");
						}
						break;
					case 2:
						//inf
						if(tmp != ""){
							tmp_local.setInfluence(Integer.valueOf(tmp));
						}else{
							tmp_local.setInfluence(-1);
						}
						break;
					case 3:
						//lat
						if(tmp != ""){
							tmp_local.setpLatitude(Double.valueOf(tmp));
						}else{
							tmp_local.setpLatitude(0);
						}
						break;
					case 4: 
						//long
						if(tmp != ""){
							tmp_local.setpLongitude(Double.valueOf(tmp));
						}else{
							tmp_local.setpLongitude(0);
						}
						break;
					case 5:
						//ingame
						if(tmp != ""){
							tmp_local.setInGame(Boolean.valueOf(tmp));
						}else{
							tmp_local.setInGame(false);
						}
						break;
				}
				tmp = "";
				varCase++;
			}
			
			Gamemanager.setLocalPlayer(tmp_local);
			
			tmp ="";
			varCase = 1;
			
			if(Gamemanager.getLocalPlayer().isInGame() == true){
				while (index != -1 && pos < 6 && index < task_index) {
					// Enemy Player
					tmp_player = new Player(null, 0, 0, 0);
					System.out.println("### while (index != -1 && pos < 6)###");
					System.out.println("index: "+index);
					System.out.println("pos: "+pos);
					System.out.println("varCase: "+varCase);
					System.out.println("tmp: "+tmp);
					System.out.println("#####################################");
					
					while (varCase <= 4 && index >= lastIndex && index < task_index) {
						index = res.indexOf(":", index + 1);
						
						if (index != -1 && index >= lastIndex && index < task_index) {
							for (int i = 1; res.charAt(index + i) != ';'; i++) {
								tmp = tmp + res.charAt(index + i);
							}
							
							System.out.println("### AFTER GET CHAR###");
							System.out.println("index: "+index);
							System.out.println("pos: "+pos);
							System.out.println("varCase: "+varCase);
							System.out.println("tmp: "+tmp);
							System.out.println("#####################################");
							
							//TODO: richtige fehlerbehandlung
							switch (varCase) {
							case 1:
								//name
								if (tmp != "") {
									tmp_player.setName(tmp);
								} else {
									tmp_player.setName("LEER");
								}
								break;
							case 2:
								//inf
								if (tmp != "") {
									tmp_player.setInfluence(Integer
											.valueOf(tmp));
								} else {
									tmp_player.setInfluence(-1);
								}
								break;
							case 3:
								//lat
								if (tmp != "") {
									tmp_player.setpLatitude(Double.valueOf(tmp));
								} else {
									tmp_player.setpLatitude(0);
								}
								break;
							case 4:
								//long
								if (tmp != "") {
									tmp_player.setpLongitude(Double.valueOf(tmp));
								} else {
									tmp_player.setpLongitude(0);
								}
								break;
							}
							tmp = "";
							varCase++;
							
							System.out.println("### AFTER SWITCH###");
							System.out.println("index: "+index);
							System.out.println("pos: "+pos);
							System.out.println("varCase: "+varCase);
							System.out.println("tmp: "+tmp);
							System.out.println("#####################################");
						}
					}
					
					System.out.println("### AFTER ADD PLAYER ###");
					System.out.println("index: "+index);
					System.out.println("pos: "+pos);
					System.out.println("varCase: "+varCase);
					System.out.println("tmp: "+tmp);
					System.out.println("#####################################");
					
					Gamemanager.addEnemyPlayer(tmp_player, pos);
					tmp = "";
					varCase = 1;
					pos++;
					lastIndex = index;
				}
			}
			
			return true;
		}else{
			return false;
		}
	}

}
