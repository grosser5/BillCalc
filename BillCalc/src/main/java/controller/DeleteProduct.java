package main.java.controller;

import main.java.model.ModelInterface;
import main.java.model.Product;

public class DeleteProduct {
	ModelInterface model;
	DeleteProduct(ModelInterface model) {
		this.model = model;
	}
	public void delete(Product p) {
		model.removeProduct(p);;
	}
}
