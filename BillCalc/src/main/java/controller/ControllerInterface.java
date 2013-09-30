package main.java.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.ModelInterface;
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
			String postal, String receiver);
	public void cancelAddDialog();
	public void deleteCustomer();
	public void deleteSelectedLocation();
	public String getYear();
	public String getMonth();
	public String getday();
	public void addNewQuotation(String year, String month, String day, String quot_number,
			String validUntil) 
			throws IllegalArgumentException ;
	public void copyQuotation(Quotation quotation, String year,
			String month, String day, String quot_number)  throws IllegalArgumentException ;
	public void deleteSelectedQuotation();
	public void addNewQuotation(Quotation quotation);
	public void addDefaultQuotationProduct();
	public void deleteSelectedQuotProduct();
	public void quotProdTableValueChanged(QuotationProduct quotProd);
	public void addProduct(String name, String costPerQuant, String unit, String text);
	public void updateQuotProductList();
	public void deleteSelectedProduct();
	public int getRecomendedQuotId();
	public void setModel(ModelInterface model);
	public String getStoredDatabasePath();
	String getNewDatabasePath()  throws IOException;
	public void initialize();
	public ModelInterface createNewDatabase() throws IOException;
	public ModelInterface getDatabase(String db_path);
	public void exportToDocx();
}
