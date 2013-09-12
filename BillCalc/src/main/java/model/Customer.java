package main.java.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private Integer custId;
	private String name = "";
	private String compType = "";
	private List<CustomerLocation> locations = new ArrayList();
	private List<Quotation> quotations = new ArrayList();
	
	public Customer() {		
	}

	public Customer(String name, String compType,
			List<CustomerLocation> locations) {
		this.name = name;
		this.compType = compType;
		this.locations = locations;		
	}
	
	

	public Customer(String name, String compType,
			List<CustomerLocation> locations, List<Quotation> quotations) {
		super();
		this.name = name;
		this.compType = compType;
		this.locations = locations;
		this.quotations = quotations;
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

	public List<CustomerLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<CustomerLocation> locations) {
		this.locations = locations;
	}

	public List<Quotation> getQuotations() {
		return quotations;
	}

	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
	}

	public String toString() {
		return name + " " + compType;
	}

	
}
