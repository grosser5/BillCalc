package main.java.view;

import main.java.model.Customer;

public interface ViewInterface {
	public String getCustomerSearchText();

	public void cancleAddCustomerDialog();

	public  Customer getSelectedCustomer();
	
}
