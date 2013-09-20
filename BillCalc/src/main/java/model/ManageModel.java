package main.java.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

import main.java.model.observer.CustomerLocationObserver;
import main.java.model.observer.CustomerObserver;
import main.java.model.observer.ProductObserver;
import main.java.model.observer.QuotProductObserver;
import main.java.model.observer.QuotationObserver;
import main.view.util.Log;

public class ManageModel  implements ModelInterface{

	private List<CustomerObserver> customer_observer = new ArrayList<CustomerObserver>();
	private List<CustomerLocationObserver> cust_loc_observer = new ArrayList<CustomerLocationObserver>();
	private List<ProductObserver> product_observer = new ArrayList<ProductObserver>();
	private List<QuotationObserver> quotation_observer = new ArrayList<QuotationObserver>();
	private List<QuotProductObserver> quot_prod_observer = new ArrayList<QuotProductObserver>();
	
	
	public ManageModel() {
		
	}
	
	
	@Override
	public void registerCustomerObserver(CustomerObserver observer) {
		customer_observer.add(observer);
		
	}

	@Override
	public void registerCustomerLocationObserver(
			CustomerLocationObserver observer) {
		cust_loc_observer.add(observer);
		
	}

	@Override
	public void registerProductObserver(ProductObserver observer) {
		product_observer.add(observer);
		
	}

	@Override
	public void registerQuotationObserver(QuotationObserver observer) {
		quotation_observer.add(observer);
		
	}

	@Override
	public void registerQuotProductObserver(QuotProductObserver observer) {
		quot_prod_observer.add(observer);
		
	}

	@Override
	public void addCustomer(final Customer customer) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("addCustomer called");
				ModelFactory factory = ModelSingleton.getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				
				manage_db.saveObj(customer);
				listAllCustomers();
			}
		});
		t.start();
	}

	@Override
	public void removeCustomer(final Customer customer) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.deleteObj(customer);;
				listAllCustomers();
			}
		});
		t.start();
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listCustomers(final String searchName) {
		Log.getLog(this).debug("listAllCustomers(String searchName) called");
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				List<Customer> cust_list = manage_db.getCustomer(searchName);
				
				for (CustomerObserver observer : customer_observer) {
					observer.updateCustomerField(cust_list);
				}
			}
		});
		t.start();
	}
	
	@Override
	public void listAllCustomers() {
		Log.getLog(this).debug("listAllCustomers() called");
		
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				List<Customer> cust_list = manage_db.getClasses(Customer.class);
				
				for (CustomerObserver observer : customer_observer) {
					observer.updateCustomerField(cust_list);
				}
			}
		});
		t.start();
		
		
	}

	@Override
	public void addCustomerLocation(CustomerLocation custLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeCustomerLocation(int custLocId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomerLocation(CustomerLocation custLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addQuotation(Quotation quotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeQuotation(int quotId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQuotation(Quotation quotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProduct(int prodId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateCustomerLocationList(List<Location> loc) {
		for(CustomerLocationObserver observer : cust_loc_observer) {
			observer.updateCustomerLocationField(loc);
		}
		
	}


	@Override
	public void updateQuotationList(List<Quotation> quotations) {
		for(QuotationObserver observer : quotation_observer) {
			observer.updateQuotationList(quotations);
		}
	}


	@Override
	public void listAllProducts() {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("listAllProducts called");
				ModelFactory factory = ModelSingleton.getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				List<Product> products = manage_db.getClasses(Product.class);
				for(ProductObserver observer : product_observer) {
					Log.getLog(this).debug("iterate through ProductObservers");
					observer.updateProductList(products);			
				}
			}
		});
		t.start();
	}


	@Override
	public void listAllQuotatoinProducts(final int quot_id) {

		Thread t = new Thread(new Runnable() {
			public void run() {

				ModelFactory factory = ModelSingleton.getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();

				List<Product> products = manage_db.getClasses(Product.class);

				List<QuotationProduct> quot_products = manage_db
						.getQuotationProducts(quot_id);
				for (QuotProductObserver observer : quot_prod_observer) {
					observer.updateQuotProductList(quot_products, products);
				}
			}
		});
		t.start();
	}

	@Override
	public void listAllQuotatoinProducts(Quotation selectedQuotation,
			List<Product> products) {
		for(QuotProductObserver observer : quot_prod_observer){
			observer.updateQuotProductList(selectedQuotation.getQuotProducts(),
					products);
		}
		
	}

}
