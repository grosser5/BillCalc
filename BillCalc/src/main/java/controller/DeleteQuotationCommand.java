package main.java.controller;

import java.util.Stack;

import main.java.model.Quotation;
import main.java.view.ViewInterface;

public class DeleteQuotationCommand extends AbstractCommand {

	private DeleteQuotation del_quotation;
	private AddNewQuotation add_quotation;
	private final Stack<Quotation> deleted_quotations = new Stack<Quotation>();
	
	DeleteQuotationCommand(ViewInterface view, DeleteQuotation del_quotation,
			AddNewQuotation add_quotation) {
		super(view);
		this.del_quotation = del_quotation;
		this.add_quotation = add_quotation;
	}
	
	@Override
	public void execute() {
		Quotation quotation = view.getSelectedQuotation();
		deleted_quotations.add(quotation);
		del_quotation.delete(quotation);
	}
	
	@Override
	public void back() {
		if(!deleted_quotations.isEmpty())
			add_quotation.add(deleted_quotations.pop());
	}

}
