package main.java.controller;

import main.java.model.ModelInterface;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;

public class AddQuotationProduct {
	private ModelInterface model;

	public AddQuotationProduct(ModelInterface model) {
		
		this.model = model;
	}
	
	public void add(QuotationProduct p) {
		model.addQuotationProduct(p);
	}

}
