package com.slamhomer.regiongrowing_network;

import com.slamhomer.regiongrowing.ErrorMsg;
import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_maps.UpdateMap;

public class BackgroundUpdateThread extends Thread{
	private static long WAITTIME_MS =  30*1000; //update alle 30 sekunden
	
	private UpdateMap uMap;
	private UpdateThread updateThread;
	
	public BackgroundUpdateThread(UpdateMap uMap){
		this.uMap = uMap;
	}
	
	public void run(){
		System.out.println("BACKGROUND_UPDATE_THREAD_START!!!!!");
		
		while(1==1){
			try {
				sleep(WAITTIME_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("InterruptedException");
			}
				if(Gamemanager.getLocalPlayer() != null){
					System.out.println("BACKGROUND_UPDATE_THREAD!!!!!");
					
					updateThread = new UpdateThread(
							Gamemanager.getLocalPlayer().getName());
					//Update Stats
					try{
						if (updateThread.isAlive() == false) {
							System.out.println("THREAD IS DEAD AND START NEW");
							updateThread.start();
						}else{
							System.out.println("THREAD IS STILL ALIVE");
						}
					}catch(Exception e){
						System.out.println("WTF!! updateThread ist kaputt "+e);
					}
					try {
						updateThread.join(WAITTIME_MS*0,7);		
						
						//Update Map
						uMap.update();
						System.out.println("JOIN CHECK UPDATE CHECK");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
	}
}
