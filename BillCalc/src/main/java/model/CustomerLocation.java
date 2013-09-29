package main.java.model;

import java.util.HashMap;
import java.util.Map;

import main.view.util.Log;

public class CustomerLocation {

	private Location location;
	private int id = 0;
	private int custId = 0;
	
	public final static String templateFieldCity = "CITY";
	public final static String templateFieldStreet = "STREET";
	public final static String templateFieldPostal = "POSTAL";
	
	
	public CustomerLocation() {}
	
	public CustomerLocation(String city, String street, int postal) {	
		this.location = new Location(city,street,postal);
	}

	public CustomerLocation(int custId,Location location) {
		this.location = location;
		this.custId = custId;
	}


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location locations) {
		this.location = locations;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}
	
	public String getCity() {
		return location.getCity();
	}

	public void setCity(String city) {
		location.setCity(city);
	}
	
	public String getStreet() {
		return location.getStreet();
	}

	public void setStreet(String street) {
		location.setStreet(street);
	}
	
	public int getPostal() {
		return location.getPostal();
	}
	
	public void setPostal(int postal) {
		location.setPostal(postal);
	}

	public String toString() {
		String l = "null";
		if(location != null) {
			l = location.toString();
		}
		return "CustomerId: " + custId + " Location: "  + l;
	}
	
	public Map<String, String> getTemplateReplacement() {
		Map<String,String> temp_replacement= new HashMap<String,String>();
		temp_replacement.put(templateFieldCity, getCity() );
		temp_replacement.put(templateFieldStreet, getStreet() );
		temp_replacement.put(templateFieldPostal, Integer.toString( getPostal() ) );
		return temp_replacement;
	}
}
