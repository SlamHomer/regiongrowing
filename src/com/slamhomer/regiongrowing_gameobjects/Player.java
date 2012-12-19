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
	
	public int convLatitude(){
		Double latE6 = this.getpLatitude() * 1E6;
		int lat = latE6.intValue();
		
		System.out.println("########################");
		System.out.println("GEO LAT: "+lat);
		System.out.println("########################");
		return lat;
	}
	
	public int convLongitude(){
		Double lngE6 = this.getpLongitude() * 1E6;
		int lng = lngE6.intValue();
	    
		
		System.out.println("########################");
		System.out.println("GEO LONG: "+lng);
		System.out.println("########################");
		return lng;
	}
}