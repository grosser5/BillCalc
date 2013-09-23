package main.java.controller;

import main.java.model.ModelInterface;
import main.java.model.Quotation;

public class AddNewQuotation {
	private ModelInterface model;
	
	AddNewQuotation(ModelInterface model) {
		this.model = model;
	}
	
	public void add(Quotation quotation) {
		model.addQuotation(quotation);
	}
}
