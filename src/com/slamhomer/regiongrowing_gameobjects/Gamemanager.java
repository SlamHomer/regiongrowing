package com.slamhomer.regiongrowing_gameobjects;

public class Gamemanager {
	private static LocalPlayer LocalPlayer;
	private static Player[] EnemyPlayerArray = 
		{new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0)}; //maximal 6 Spieler
	
	public static LocalPlayer getLocalPlayer() {
		return LocalPlayer;
	}
	public static void setLocalPlayer(LocalPlayer localPlayer) {
		LocalPlayer = localPlayer;
	}
	public static Player[] getEnemyPlayerArray() {
		return EnemyPlayerArray;
	}
	public static void setEnemyPlayerArray(Player[] enemyPlayerArray) {
		EnemyPlayerArray = enemyPlayerArray;
	}
	
	public static void addEnemyPlayer(Player enemy, int pos){
		EnemyPlayerArray[pos] = enemy;
	}
	
	public static Player getEnemyPlayer(int pos){
		return EnemyPlayerArray[pos];
	}
	
	public static void printAllPlayer(){
		System.out.println("##########################################");
		System.out.println("LOCAL NAME: "+Gamemanager.getLocalPlayer().getName());
		System.out.println("LOCAL INF: "+Gamemanager.getLocalPlayer().getInfluence());
		System.out.println("LOCAL LAT: "+Gamemanager.getLocalPlayer().getpLatitude());
		System.out.println("LOCAL LONG: "+Gamemanager.getLocalPlayer().getpLongitude());
		System.out.println("LOCAL INGAME: "+Gamemanager.getLocalPlayer().isInGame());
		System.out.println("##########################################");
		
		for (int pos = 0; pos < EnemyPlayerArray.length; pos++) {
			System.out.println("##########################################");
			System.out.println("ENEMY " + pos + " NAME: "
					+ Gamemanager.getEnemyPlayer(pos).getName());
			System.out.println("ENEMY " + pos + " INF: "
					+ Gamemanager.getEnemyPlayer(pos).getInfluence());
			System.out.println("ENEMY " + pos + " LAT: "
					+ Gamemanager.getEnemyPlayer(pos).getpLatitude());
			System.out.println("ENEMY " + pos + " LONG: "
					+ Gamemanager.getEnemyPlayer(pos).getpLongitude());
			System.out.println("##########################################");
		}
	}
}
