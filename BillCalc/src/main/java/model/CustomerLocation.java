package main.java.model;

import main.view.util.Log;

public class CustomerLocation {

	private Location location;
	private int id = 0;
	private int custId = 0;
	
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
		Log.getLog(this).info("id = " + id + "\n");
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
	
	public String toString() {
		String l = "null";
		if(location != null) {
			l = location.toString();
		}
		return "CustomerId: " + custId + " Location: "  + l;
	}
}
