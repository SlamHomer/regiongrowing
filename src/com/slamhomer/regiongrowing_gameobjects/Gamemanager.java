package com.slamhomer.regiongrowing_gameobjects;

import java.util.Date;

public class Gamemanager {
	
	private static LocalPlayer LocalPlayer = 
			new LocalPlayer(null, -1, -1, -1, null, false);
	private static Player[] EnemyPlayerArray = 
		{new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0),
		new Player(null, 0, 0, 0)}; //maximal 6 Spieler
	private static Task[] DailyTasks =
		{new Task(null, null, null, 0),
		new Task(null, null, null, 0),
		new Task(null, null, null, 0),
		new Task(null, null, null, 0),
		new Task(null, null, null, 0),
		};
	private static String winner = null;
	private static Date serverDate = null;
	private static Date gameEndDate = null;
	
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
	
	public static Task[] getDailyTasks() {
		return DailyTasks;
	}
	public static Task getTask(int pos){
		return DailyTasks[pos];
	}
	
	public static void setDailyTasks(Task[] dailyTasks) {
		Gamemanager.DailyTasks = dailyTasks;
	}
	public static void addDailyTasks(int pos, Task task){
		DailyTasks[pos] = task;
	}
	public static void rmDailyTasks(int pos){
		DailyTasks[pos] = null;
	}
	public static void rmAllDailyTasks(){
		for(int i = 0; i < 6; i++){
			DailyTasks[i] = null;
		}
	}
	
	public static String getWinner() {
		return winner;
	}
	public static void setWinner(String winner) {
		Gamemanager.winner = winner;
	}
	public static Date getServerDate() {
		return serverDate;
	}
	public static void setServerDate(Date serverDate) {
		Gamemanager.serverDate = serverDate;
	}
	public static Date getGameEndDate() {
		return gameEndDate;
	}
	public static void setGameEndDate(Date gameEndDate) {
		Gamemanager.gameEndDate = gameEndDate;
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
	
	public static void printAllTasks(){
		for (int pos = 0; pos < DailyTasks.length; pos++) {
			System.out.println("##########################################");
			System.out.println("Task " + pos + " NAME: "
					+ Gamemanager.getTask(pos).getTaskName());
			System.out.println("Task " + pos + " DESC: "
					+ Gamemanager.getTask(pos).getTaskDesc());
			System.out.println("Task " + pos + " INF: "
					+ Gamemanager.getTask(pos).getTaskInf());
			System.out.println("Task " + pos + " ERFÜLLT: "
					+ Gamemanager.getTask(pos).getTaskErf());
			System.out.println("##########################################");
		}
	}
	
	private static void printAllGameData() {
		System.out.println("##########################################");
		System.out.println("SERVER Date: "+Gamemanager.getServerDate());
		System.out.println("GAME END Date: "+Gamemanager.getGameEndDate());
		System.out.println("WINNER: "+Gamemanager.getWinner());
		System.out.println("##########################################");
	}
	
	public static void printAll(){
		printAllPlayer();
		printAllTasks();
		printAllGameData();
	}

}
