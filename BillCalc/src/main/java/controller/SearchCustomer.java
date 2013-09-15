package main.java.controller;

import main.java.model.ModelInterface;

public class SearchCustomer {
	ModelInterface model;
	public SearchCustomer(ModelInterface model) {
		this.model = model;
	}
	
	public void search(String s) {
		model.listCustomers(s);
	}

}
