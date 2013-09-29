package main.java.controller;

import main.java.view.ViewInterface;
import main.view.util.Log;

public class UpdateCustomerListCommand extends AbstractCommand {

	private UpdateCustomerList update_cust_list;
	UpdateCustomerListCommand(ViewInterface view, UpdateCustomerList update_cust_list) {
		super(view);
		this.update_cust_list = update_cust_list;
	}

	@Override
	public void execute() {
		Log.getLog(this).debug("exetue() called");
		update_cust_list.update();
	}
	
}
