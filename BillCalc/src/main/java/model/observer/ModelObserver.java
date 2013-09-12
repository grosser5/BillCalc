package main.java.model.observer;

import java.util.List;

import main.java.model.Customer;
import main.java.model.Location;
import main.java.model.Product;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;

public interface ModelObserver {
	void updateCustomerField(List<Customer> custList);
	void updateCustomerLocationField(List<Location> locationList);
	void updateQuotationList(List<Quotation> quotList);
	void updateQuotProductList(List<QuotationProduct> quotProdList);
	void updateProductList(List<Product> productList);
}
