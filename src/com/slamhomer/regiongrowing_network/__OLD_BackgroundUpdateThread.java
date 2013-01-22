package com.slamhomer.regiongrowing_network;

import com.slamhomer.regiongrowing_gameobjects.Gamemanager;
import com.slamhomer.regiongrowing_maps.UpdateMap;

public class __OLD_BackgroundUpdateThread extends Thread{
	private static long WAITTIME_MS =  40*1000; //update alle 40 sekunden
	private volatile static boolean update = false;
	
	private UpdateMap uMap;
	private Thread updateThread;
	
	public __OLD_BackgroundUpdateThread(UpdateMap uMap){
		this.uMap = uMap;
	}
	
	public static boolean isUpdate() {
		return update;
	}

	public static void setUpdate(boolean update) {
		__OLD_BackgroundUpdateThread.update = update;
	}
	
	public void run(){
		System.out.println("BACKGROUND_UPDATE_THREAD_START!!!!!");
		
		while (true) {
			System.out.println("WHILE SCHLEIFE !!!!");
			if(update == true) {
				try {
					sleep(WAITTIME_MS);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("InterruptedException");
				}
				if (Gamemanager.getLocalPlayer() != null) {
					System.out.println("BACKGROUND_UPDATE_THREAD!!!!!");

					updateThread = new UpdateThread(Gamemanager
							.getLocalPlayer().getName());
					//Update Stats
					try {
						if (updateThread.isAlive() == false) {
							System.out.println("THREAD IS DEAD AND START NEW");
							updateThread.start();
						} else {
							System.out.println("THREAD IS STILL ALIVE");
						}
					} catch (Exception e) {
						System.out
								.println("WTF!! updateThread ist kaputt " + e);
					}
					try {
						updateThread.join(WAITTIME_MS * 0, 7);

						//Update Map
						uMap.update();
						System.out.println("JOIN CHECK UPDATE CHECK");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
