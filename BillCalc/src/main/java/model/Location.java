package main.java.model;

public class Location {
	
	
	private int custId;
	private String city;
	private String street;
	private int postal;
	
	public Location(){}
	
	public Location(String city, String street, int postal) {
		this.city = city;
		this.street = street;
		this.postal = postal;
	}



	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
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
	
	

}
