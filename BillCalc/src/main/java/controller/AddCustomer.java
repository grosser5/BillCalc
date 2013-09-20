package main.java.controller;

import main.java.model.Customer;
import main.java.model.ModelInterface;

public class AddCustomer {
	private ModelInterface model;

	public AddCustomer(ModelInterface model) {
		
		this.model = model;
	}
	
	public void addCustomer(Customer cust) {
		model.addCustomer(cust);
	}

}
