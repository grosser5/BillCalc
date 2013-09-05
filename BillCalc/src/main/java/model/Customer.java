package main.java.model;

import java.util.ArrayList;

public class Customer {

	private Integer custId;
	private String name;
	private String compType;
	private Location location;
	private ArrayList<Quotation> quotations;
	
	public Customer() {		
	}

	public Customer(String name, String compType,
			Location location) {
		this.name = name;
		this.compType = compType;
		this.location = location;
		//this.quotations = quotations;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompType() {
		return compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(ArrayList<Quotation> quotations) {
		this.quotations = quotations;
	}


	
}
