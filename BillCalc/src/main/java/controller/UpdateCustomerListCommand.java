package main.java.controller;

import main.java.view.ViewInterface;

public class UpdateCustomerListCommand extends AbstractCommand {

	private UpdateCustomerList update_cust_list;
	UpdateCustomerListCommand(ViewInterface view, UpdateCustomerList update_cust_list) {
		super(view);
		this.update_cust_list = update_cust_list;
	}

	@Override
	public void execute() {
		update_cust_list.update();
	}
	
}
