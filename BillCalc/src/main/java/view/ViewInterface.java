package main.java.view;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Product;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;

public interface ViewInterface {
	public String getCustomerSearchText();
	public void cancelAddDialog();
	public  Customer getSelectedCustomer();
	public CustomerLocation getSelectedCustomerLocation();
	public Quotation getSelectedQuotation();
	public QuotationProduct getSelectedQuotProduct();
	public Product getSelectedProduct();
	public String getDatabasePath();
	
}
