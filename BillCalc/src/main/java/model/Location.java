package main.java.model;

public class Location {
	
	private int locId;
	private String city;
	private String street;
	private int postal;
	
	public Location(){}
	
	public Location(String city, String street, int postal) {
		
		this.city = city;
		this.street = street;
		this.postal = postal;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostal() {
		return postal;
	}

	public void setPostal(int postal) {
		this.postal = postal;
	}

	public int getLocId() {
		return locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}
	
}