package main.java.model;

import java.sql.Date;
import java.util.List;

import main.java.model.observer.*;

public interface ModelInterface {

	//observers
	void registerCustomerObserver(CustomerObserver observer);
	void registerCustomerLocationObserver(CustomerLocationObserver observer);
	void registerProductObserver(ProductObserver observer);
	void registerQuotationObserver(QuotationObserver observer);
	void registerQuotProductObserver(QuotProductObserver observer);
	
	//customer methods
	void addCustomer(Customer customer);
	void removeCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void listCustomers(String searchName);
	
	//customerlocation methods
	void addCustomerLocation(CustomerLocation custLocation);
	void removeCustomerLocation(int custLocId);
	void updateCustomerLocation(CustomerLocation custLocation);
	
	//Quotation methods
	void addQuotation(Quotation quotation);
	void removeQuotation(int quotId);
	void updateQuotation(Quotation quotation);
	
	//Product methods
	void addProduct(Product product);
	void removeProduct(int prodId);
	void updateProduct(Product product);
	
	//view updates
	void updateCustomerLocationList(List<Location> loc);
	void listAllCustomers();
	void updateQuotationList(List<Quotation> quotations);
	void listAllProducts();
	void listAllQuotatoinProducts(int quot_id);
	void listAllQuotatoinProducts(Quotation selectedQuotation, List<Product> products);
}
