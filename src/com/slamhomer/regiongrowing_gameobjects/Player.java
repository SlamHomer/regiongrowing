package com.slamhomer.regiongrowing_gameobjects;

public class Player {
	private String name = null;
	private String pLatitude = null;
	private String pLongitude = null;
	private int influence = -1;
	

	public Player(String name, String lat, String lon, int influence){
		this.name = name;
		this.influence = influence;
		this.pLatitude = lat;
		this.pLongitude = lon;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getpLatitude() {
		return pLatitude;
	}


	public void setpLatitude(String pLatitude) {
		this.pLatitude = pLatitude;
	}


	public String getpLongitude() {
		return pLongitude;
	}


	public void setpLongitude(String pLongitude) {
		this.pLongitude = pLongitude;
	}


	public int getInfluence() {
		return influence;
	}


	public void setInfluence(int influence) {
		this.influence = influence;
	}
}