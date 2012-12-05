package com.slamhomer.regiongrowing_gameobjects;

public class LocalPlayer extends Player{
	private boolean isInGame;
	
	public LocalPlayer(String name, String lat, String lon, int influence, 
			String email, boolean isInGame) {
		super(name, lat, lon, influence);
		this.isInGame = isInGame;
	}

	public boolean isInGame() {
		return isInGame;
	}

	public void setInGame(boolean isInGame) {
		this.isInGame = isInGame;
	}
	

}
