package com.slamhomer.regiongrowing_gameobjects;

public class Gamemanager {
	private static LocalPlayer LocalPlayer;
	private static Player[] EnemyPlayerArray;
	
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
	

}
