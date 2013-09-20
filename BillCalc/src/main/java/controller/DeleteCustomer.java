package main.java.controller;

import main.java.model.Customer;
import main.java.model.ModelInterface;
import main.view.util.Log;

public class DeleteCustomer {
	ModelInterface model;
	DeleteCustomer(ModelInterface model) {
		this.model = model;
	}
	public void delete(Customer cust) {
		Log.getLog(this).debug("delete cust with Id: " + cust.getCustId());
		model.removeCustomer(cust);
	}
}
