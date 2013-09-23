package main.java.controller;

import java.util.Stack;

import main.java.model.QuotationProduct;
import main.java.view.ViewInterface;

public class DeleteQuotationProductCommand extends AbstractCommand {

	private DeleteQuotationProduct del_quot_prod;
	private AddQuotationProduct add_quot_prod;
	private final Stack<QuotationProduct> deleted_quotations = new Stack<QuotationProduct>();
	
	DeleteQuotationProductCommand(ViewInterface view, DeleteQuotationProduct del_quotation,
			AddQuotationProduct add_quotation) {
		super(view);
		this.del_quot_prod = del_quotation;
		this.add_quot_prod = add_quotation;
	}
	
	@Override
	public void execute() {
		QuotationProduct quot_prod = view.getSelectedQuotProduct();
		deleted_quotations.add( quot_prod );
		del_quot_prod.delete( quot_prod );
	}
	
	@Override
	public void back() {
		if(!deleted_quotations.isEmpty())
			add_quot_prod.add(deleted_quotations.pop());
	}

}