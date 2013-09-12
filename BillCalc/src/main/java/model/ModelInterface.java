package main.java.model;

import java.sql.Date;

import main.java.model.observer.ModelObserver;

public interface ModelInterface {

	void registerModelObserver(ModelObserver observer);
	
	void addCustomer(Customer customer);
	void removeCustomer(int custId);
	void updateCustomer(Customer customer);
	
	
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
