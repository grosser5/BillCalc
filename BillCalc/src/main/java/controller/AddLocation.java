package main.java.controller;

import main.java.model.CustomerLocation;
import main.java.model.ModelInterface;

public class AddLocation {
	
		private ModelInterface model;

		public AddLocation(ModelInterface model) {
			
			this.model = model;
		}
		
		public void addLocation(CustomerLocation location) {
			model.addCustomerLocation(location);
		}

	
}
