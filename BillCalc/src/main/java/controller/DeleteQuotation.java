package main.java.controller;

import main.java.model.ModelInterface;
import main.java.model.Quotation;

public class DeleteQuotation {
	private ModelInterface model;
	
	DeleteQuotation(ModelInterface model) {
		this.model = model;
	}
	
	public void delete(Quotation quotation) {
		model.removeQuotation(quotation);;
	}
}
