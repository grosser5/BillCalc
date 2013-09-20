package main.java.controller;

import main.java.model.ModelInterface;

public class UpdateCustomerList {

	ModelInterface model;
	
	UpdateCustomerList(ModelInterface model) {
		this.model = model;
	}
	public void update() {
		model.listAllCustomers();
	}
}
