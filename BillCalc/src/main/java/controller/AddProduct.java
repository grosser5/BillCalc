package main.java.controller;

import main.java.model.ModelInterface;
import main.java.model.Product;

public class AddProduct {
	private ModelInterface model;

	public AddProduct(ModelInterface model) {
		
		this.model = model;
	}
	
	public void addProduct(Product p) {
		model.addProduct(p);
	}
}
