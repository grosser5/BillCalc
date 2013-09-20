package main.java.controller;

import java.util.Stack;

import main.java.model.Customer;
import main.java.view.ViewInterface;

public class DeleteCustomerCommand  extends AbstractCommand{

	private DeleteCustomer delete;
	private AddCustomer add;
	private final Stack<Customer> deleted_customers = new Stack<Customer>();
	
	DeleteCustomerCommand(ViewInterface view, DeleteCustomer delete, AddCustomer add) {
		super(view);
		this.delete = delete;
		this.add = add;
	}
	
	@Override
	public void execute() {
		Customer cust = view.getSelectedCustomer();
		deleted_customers.add(cust);
		delete.delete(cust);
	}
	@Override
	public void back() {
		if(!deleted_customers.isEmpty()) {
			Customer cust = deleted_customers.pop();
			add.addCustomer(cust);
		}
	}
}
