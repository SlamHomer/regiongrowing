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
import com.slamhomer.regiongrowing_gameobjects.Task;

public class UpdateThread extends Thread{
	private String name = null;

	public UpdateThread(final String name){
		this.name = name;
	}
	
	public void run(){
			System.out.println("THREAD!!!!!");
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(
					"http://www.slamhomer.com/region/update.php");
			String res = null;
			try {
				// Add data

				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						2);
				nameValuePairs.add(new BasicNameValuePair("name", this.name));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);

				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();

				res = Network.convertStreamToString(is);

				System.out.println("RES: " + res);

			} catch (ClientProtocolException e) {
				System.out.println("ClientProtocolException");
			} catch (IOException e) {
				System.out.println("IOException");
			}
			//wenn update erfolglos dann setzte fehler code
			if (convertUpdate(res) == false) {
				Network.setLastCode("Fehler beim Update");
			}
	}
	
	/*
	 * Methode um den �bergebenen String in die Gamedaten umzuwandeln und zu setzen.
	 * Gibt true zur�ck, wenn res != null ist und die Daten gesetzt werden k�nnen.
	 */
	private static boolean convertUpdate(String res){
		if(res != null && res.length() > 0){
			
			//setzten der update indizes
			int task_index = res.indexOf("taskname");
			int enemy_index = res.indexOf("enemy");
			
			//hilfs-fkt um den localen spieler auszulesen
			convLocal(res);
			
			if(Gamemanager.getLocalPlayer().isInGame() == true){
				if (task_index != -1) {
					//hilfs-fkt um tasks auszulesen
					convTasks(task_index, res);
					if (enemy_index != -1) {
						//hilfs-fkt um die gegner auszulesen
						convEnemy(enemy_index, task_index, res);
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
	private static void convLocal(String res){
		//init variablen
		int index = 0;
		int lastIndex = index;
		int varCase = 1;
		String tmp = "";
		LocalPlayer tmp_local = new LocalPlayer(null, GPS.getLatitude(), GPS.getLongitude(), 
				0, null, false);
		
		//hier werden die einzelnen attribute durchgegangen
		while(varCase <= 5){
			if (index != -1) {
				index = res.indexOf(":", index + 1);
				/*
				 * .indexOf durchl�uft den string unendlich oft, deswegen muss �berpr�ft werden ob man 
				 * das ende schon erreicht hat
				 */
				if (index >= lastIndex) {
					//lesen der einzelnen zeichen, bis ein trennzeichen kommt
					for (int i = 1; res.charAt(index + i) != ';'; i++) {
						tmp = tmp + res.charAt(index + i);
					}
					//welches attr. ist es?
					switch (varCase) {
					case 1:
						//name
						if (tmp != "") {
							tmp_local.setName(tmp);
						} else {
							tmp_local.setName("LEER");
						}
						break;
					case 2:
						//inf
						if (tmp != "") {
							tmp_local.setInfluence(Integer.valueOf(tmp));
						} else {
							tmp_local.setInfluence(-1);
						}
						break;
					case 5:
						//ingame
						if (tmp != "") {
							tmp_local.setInGame(Boolean.valueOf(tmp));
						} else {
							tmp_local.setInGame(false);
						}
						break;
					}
				}
			}
			tmp = "";
			varCase++;
		}
		Gamemanager.setLocalPlayer(tmp_local);
	}
	
	private static void convEnemy(int enemy_index,int task_index, String res){
		//init variablen
		int index = enemy_index;
		int lastIndex = index;
		int pos = 0;
		int varCase = 1;
		String tmp = "";
		Player tmp_player = new Player(null, 0, 0, 0);
		
		//maximal 6 spieler = 6 mal gegnerspieler auslesen
		while(pos < 6 && index < task_index){
			tmp_player = new Player(null, 0, 0, 0);
			//hier werden die einzelnen attribute durchgegangen
			while (varCase <= 4 && index < task_index) {
				index = res.indexOf(":", index + 1);
				if (index >= lastIndex && index < task_index) {
					for (int i = 1; res.charAt(index + i) != ';'; i++) {
						tmp = tmp + res.charAt(index + i);
					}							
					
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

				}
			}
						
			Gamemanager.addEnemyPlayer(tmp_player, pos);
			
			tmp = "";
			varCase = 1;
			pos++;
			lastIndex = index;
		}
	}

	private static void convTasks(int task_index, String res){
		//init variablen
		int index = task_index;
		int pos = 0;
		int varCase = 1;
		String tmp = "";
		int lastIndex = task_index;
		Task tmp_task = new Task(null, null, null, 0);
		
		//maximal 5 tasks = 5 mal auslesen
		while (index != -1 && pos < 5) {
			tmp_task = new Task(null, null, null, 0);
			//hier werden die einzelnen attribute durchgegangen
			while (varCase <= 4 && index >= lastIndex) {
				index = res.indexOf(":", index + 1);
				
				if (index != -1 && index >= lastIndex) {
					for (int i = 1; res.charAt(index + i) != ';'; i++) {
						tmp = tmp + res.charAt(index + i);
					}
					switch (varCase) {
					case 1:
						//taskName
						if (tmp != "") {
							tmp_task.setTaskName(tmp);
						} else {
							tmp_task.setTaskName("LEER");
						}
						break;
					case 2:
						//taskDesc
						if (tmp != "") {
							tmp_task.setTaskDesc(tmp);
						} else {
							tmp_task.setTaskDesc("LEER");
						}
						break;
					case 3:
						//taskInf
						if (tmp != "") {
							tmp_task.setTaskInf(Integer.valueOf(tmp));
						} else {
							tmp_task.setTaskInf(0);
						}
						break;
					case 4:
						//taskErf
						if (tmp != "") {
							tmp_task.setTaskErf(tmp);
						} else {
							tmp_task.setTaskErf(tmp);
						}
						break;
					}
					tmp = "";
					varCase++;

				}
			}
			Gamemanager.addDailyTasks(pos, tmp_task);
			
			tmp = "";
			varCase = 1;
			pos++;
			lastIndex = index;
		}
	}
}
