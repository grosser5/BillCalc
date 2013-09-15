package main.java.model;

import java.sql.Date;

import main.java.model.observer.*;

public interface ModelInterface {

	void registerCustomerObserver(CustomerObserver observer);
	void registerCustomerLocationObserver(CustomerLocationObserver observer);
	void registerProductObserver(ProductObserver observer);
	void registerQuotationObserver(QuotationObserver observer);
	void registerQuotProductObserver(QuotProductObserver observer);
	
	void addCustomer(Customer customer);
	void removeCustomer(int custId);
	void updateCustomer(Customer customer);
	void listCustomers(String searchName);
	void listAllCustomers();
	
	void addCustomerLocation(CustomerLocation custLocation);
	void removeCustomerLocation(int custLocId);
	void updateCustomerLocation(CustomerLocation custLocation);
	
	void addQuotation(Quotation quotation);
	void removeQuotation(int quotId);
	void updateQuotation(Quotation quotation);
	
	void addProduct(Product product);
	void removeProduct(int prodId);
	void updateProduct(Product product);
}
