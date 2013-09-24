package main.java.controller;

import java.sql.Date;
import java.util.List;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Product;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;

public interface ControllerInterface {
	public void updateCustomerList();
	public void searchCustomerEntered();
	public int calcPrice(int price, int quantity, int mwst);
	public void customerListSelected();
	public void updateProductList();
	public void quotationTableSelected();
	public void addCustomer(String customerName, String customerCompType);
	public void addLocation(String city, String street,
			String postal);
	public void cancelAddDialog();
	public void deleteCustomer();
	public void deleteSelectedLocation();
	public String getYear();
	public String getMonth();
	public String getday();
	public void addNewQuotation(String year, String month, String day);
	public void copyQuotation();
	public void deleteSelectedQuotation();
	public void addNewQuotation(Quotation quotation);
	public void addDefaultQuotationProduct();
	public void deleteSelectedQuotProduct();
	public void quotProdTableValueChanged(QuotationProduct quotProd);
	
}
