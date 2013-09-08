package main.java.model;

public class CustomerLocation {

	private Location location;
	private int id;
	private int custId;
	
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
	
}
