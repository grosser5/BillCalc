package main.java.model;

import java.sql.Date;
import java.util.List;

public class ModelFactory {

	public ModelFactory() {}
	
	public ManageDatabase createManageDatabase() {
		return new ManageDatabase();
	}
	
	public Customer createCustomer(String name, String compType,
			List<CustomerLocation> locations) {
		return new Customer(name,compType,locations);
	}
	
	public Location createLocation(String city, String street, int postal) {
		return new Location(city,street,postal);
	}
	
	public CustomerLocation createCustomerLocation(String city, String street, int postal) {
		return new CustomerLocation(city,street,postal);
	}
	
	public CustomerLocation createCustomerLocation(int custId,Location location) {
		return new CustomerLocation( custId, location );
	}
	
	public CustomerLocation createCustomerLocation(int custId,String city, String street, int postal, String receiver) {
		Location loc = new Location(city,street,postal);
		loc.setReceiver(receiver);
		return new CustomerLocation( custId, loc );
	}
	
	public Quotation createQuotation(Date date, List<QuotationProduct> quotProducts) {
		return new Quotation(date,quotProducts);
	}
	public QuotationProduct createquotProducts(int prodId, int costPerQuantity,
			int quantity, int mwst, String place) {
		return new QuotationProduct(prodId,costPerQuantity,quantity,mwst,place);
	}
	
}
