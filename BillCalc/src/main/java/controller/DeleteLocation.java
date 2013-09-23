package main.java.controller;

import main.java.model.CustomerLocation;
import main.java.model.ModelInterface;
import main.view.util.Log;

public class DeleteLocation {
	ModelInterface model;
	DeleteLocation(ModelInterface model) {
		this.model = model;
	}
	public void delete(CustomerLocation location) {
		Log.getLog(this).debug("delete location : " + location);
		model.removeCustomerLocation(location);
	}
}
