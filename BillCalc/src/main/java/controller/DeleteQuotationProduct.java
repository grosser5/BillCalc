package main.java.controller;

import main.java.model.ModelInterface;
import main.java.model.QuotationProduct;

public class DeleteQuotationProduct {
	private ModelInterface model;

	public DeleteQuotationProduct(ModelInterface model) {
		this.model = model;
	}
	
	public void delete(QuotationProduct p) {
		model.removeQuotationProduct(p);
	}
}
