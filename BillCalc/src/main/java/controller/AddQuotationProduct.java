package main.java.controller;

import main.java.model.Customer;
import main.java.model.ModelInterface;

public class AddQuotationProduct {
	private ModelInterface model;

	public AddQuotationProduct(ModelInterface model) {
		
		this.model = model;
	}
	
	public void add(Customer cust) {
		model.addCustomer(cust);
	}

}
