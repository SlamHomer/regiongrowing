package com.slamhomer.regiongrowing_gameobjects;

public class Player {
	private String name = null;
	private String email = null;
	private String password = null;
	private String homeLocation = null;
	private int influence = -1;
	

	public Player(String name, String email, String password, String home, int influence){
		this.email = email;
		this.name = name;
		this.password = password;
		this.homeLocation = home;
		this.influence = influence;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getHomeLocation() {
		return homeLocation;
	}


	public void setHomeLocation(String homeLocation) {
		this.homeLocation = homeLocation;
	}


	public int getInfluence() {
		return influence;
	}


	public void setInfluence(int influence) {
		this.influence = influence;
	}
}