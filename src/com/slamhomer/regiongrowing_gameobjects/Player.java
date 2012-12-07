package com.slamhomer.regiongrowing_gameobjects;

public class Player {
	private String name = null;
	private double pLatitude = 0.0;
	private double pLongitude = 0.0;
	private int influence = -1;
	

	public Player(String name, double lat, double lon, int influence){
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


	public double getpLatitude() {
		return pLatitude;
	}


	public void setpLatitude(double pLatitude) {
		this.pLatitude = pLatitude;
	}


	public double getpLongitude() {
		return pLongitude;
	}


	public void setpLongitude(double pLongitude) {
		this.pLongitude = pLongitude;
	}


	public int getInfluence() {
		return influence;
	}


	public void setInfluence(int influence) {
		this.influence = influence;
	}
}