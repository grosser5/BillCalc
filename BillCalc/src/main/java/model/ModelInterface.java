package main.java.model;

import java.sql.Date;
import java.util.ArrayList;
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
	void listAllCustomers();
	void addCustomer(Customer customer);
	void removeCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void listCustomers(String searchName);
	
	//customerlocation methods
	void updateCustomerLocationList(List<CustomerLocation> loc);
	void addCustomerLocation(CustomerLocation location);
	void addCustomerLocation(Customer selected_customer, String city,
			String street, int postal);
	void removeCustomerLocation(CustomerLocation custLocation);
	void updateCustomerLocation(CustomerLocation custLocation);
	void listAllLocations(int custId);
	
	//Quotation methods
	void updateQuotationList(List<Quotation> quotations);
	void addQuotation(Quotation quotation);
	void removeQuotation(Quotation quotation);
	void listAllQuotations(int custId);
	void listAllQuotations(ArrayList<Quotation> arrayList);
	
	//QuotationProduct methods
	void listAllQuotationProducts(int quot_id);
	void updateQuotationProducts(Quotation quotation);
	void listAllQuotationProducts(Quotation selectedQuotation, List<Product> products);
	void addQuotationProduct(QuotationProduct quot_p);
	void removeQuotationProduct(QuotationProduct quot_p);
	
	//Product methods
	void addProduct(Product product);
	void removeProduct(int prodId);
	void updateProduct(Product product);
	void listAllProducts();
	int getFirstProductId();
	//view updates
	void updateQuotationProduct(QuotationProduct quotProd);
	

	
	
	
	
	
	
	
	
	

	
}
