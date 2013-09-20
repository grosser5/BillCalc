package main.java.controller;

import java.util.List;

import main.java.model.Customer;
import main.java.model.Product;
import main.java.model.Quotation;

public interface ControllerInterface {
	public void updateCustomerList();
	public void searchCustomerEntered();
	public int calcPrice(int price, int quantity, int mwst);
	public void customerListSelected(Customer selectedCustomer);
	public void updateProductList();
	public void quotationTableSelected(Quotation selectedQuotation, List<Product> products);
	public void addCustomer(String customerName, String customerCompType);
	public void cancelAddCustomerDialog();
	public void deleteCustomer(Customer selected_customer);
}
