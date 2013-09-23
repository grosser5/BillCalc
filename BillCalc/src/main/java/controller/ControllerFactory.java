package main.java.controller;

import main.java.model.ModelInterface;
import main.java.view.ViewInterface;

public class ControllerFactory {

	public ControllerFactory() { }
	
	public SearchCustomerCommand createSearchCustomerCommand(ModelInterface model,
			ViewInterface view) {
		SearchCustomer search = new SearchCustomer(model);
		return new SearchCustomerCommand(view,search);
	}
	
	public Remote createRemote(ViewInterface view) {
		return new Remote(view);
	}
	
	public UpdateCustomerListCommand createUpdateCustomerListCommand(ViewInterface view,
			ModelInterface model) {
		UpdateCustomerList update_list = new UpdateCustomerList(model);
		return new UpdateCustomerListCommand(view,update_list);
	}
	
	public DeleteCustomerCommand createDeleteCustomerCommand(ViewInterface view,
			ModelInterface model) {
		DeleteCustomer del_cust = new DeleteCustomer(model);
		AddCustomer  add_cust = new AddCustomer(model);
		return new DeleteCustomerCommand(view,del_cust,add_cust);
	}
	
	public DeleteLocationCommand createDeleteLocationCommand(ViewInterface view,
			ModelInterface model) {
		DeleteLocation del_loc = new DeleteLocation(model);
		AddLocation add_loc = new AddLocation(model);
		return new DeleteLocationCommand(view,del_loc,add_loc);
	}
	
	public DeleteQuotationCommand createDeleteQuotationCommand(ViewInterface view,
			ModelInterface model) {
		DeleteQuotation del_quot = new DeleteQuotation(model);
		AddNewQuotation add_quot = new AddNewQuotation(model);
		return new DeleteQuotationCommand(view,del_quot,add_quot);
	}
	
	public DeleteQuotationProductCommand createDeleteQuotationProductCommand(ViewInterface view,
			ModelInterface model) {
		DeleteQuotationProduct del_quot_prod = new DeleteQuotationProduct(model);
		AddQuotationProduct add_quot_prod = new AddQuotationProduct(model);
		return new DeleteQuotationProductCommand(view,del_quot_prod,add_quot_prod);
	}
}
