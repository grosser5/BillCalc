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
	private String db_path;
	
	public ManageModel(String db_path) {
		this.db_path = db_path;
		//ModelSingleton.getInstance().resetSessionFactory(db_path);
	}
	
	//registe Observers
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

	//Customer methods
	@Override
	public void addCustomer(final Customer customer) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("addCustomer called");
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
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
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.deleteObj(customer);;
				listAllCustomers();
			}
		});
		t.start();
	}

	@Override
	public void updateCustomer(final Customer customer) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.updateObj(customer);
				listAllCustomers();
			}
		});
		t.start();
	}

	@Override
	public void listCustomers(final String searchName) {
		Log.getLog(this).debug("listAllCustomers(String searchName) called");
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
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
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				List<Customer> cust_list = manage_db.getClasses(Customer.class);
				
				for (CustomerObserver observer : customer_observer) {
					observer.updateCustomerField(cust_list);
				}
			}
		});
		t.start();
		
		
	}
	
	//Customer Locations methods
	
	@Override
	public void addCustomerLocation(final CustomerLocation location) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.saveObj(location);
				listAllLocations(location.getCustId());
			}
		});
		t.start();
	}
	
	@Override
	public void addCustomerLocation(Customer customer, String city,
			String street, int postal) {
		
		ModelFactory factory = ModelSingleton.getInstance().getModelFactory();				
		CustomerLocation location = factory.createCustomerLocation(customer.getCustId(),
				city, street, postal);
		addCustomerLocation(location);
	}

	@Override
	public void removeCustomerLocation(final CustomerLocation custLocation) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("removeCustomerLocation called");
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				
				manage_db.deleteObj(custLocation);
				listAllLocations(custLocation.getCustId());
			}
		});
		t.start();
				
	}
	
	@Override
	public void updateCustomerLocationList(List<CustomerLocation> loc) {
		for(CustomerLocationObserver observer : cust_loc_observer) {
			observer.updateCustomerLocationField(loc);
		}
		
	}
	
	@Override
	public void listAllLocations(final int custId) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("listAllLocations called");
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				Customer cust = manage_db.getCustomer(custId);
				if(cust == null) {
					Log.getLog(this).debug("customer is null, custId: " + custId);
				}
				Log.getLog(this).debug("get customerLocations");
				List<CustomerLocation> locations = cust.getLocations();
				Log.getLog(this).debug("listed Locations: ");
				for(CustomerLocationObserver observer : cust_loc_observer) {
					Log.getLog(this).debug("iterate through LocationObservers");
					observer.updateCustomerLocationField(locations);;			
				}
			}
		});
		t.start();
	}

	@Override
	public void updateCustomerLocation(CustomerLocation custLocation) {
		// TODO Auto-generated method stub
		
	}
	
	//Quotation methods
	
	@Override
	public void listAllQuotations(ArrayList<Quotation> quotList) {
		for(QuotationObserver observer : quotation_observer) {
			observer.updateQuotationList(quotList);			
		}
	}
	
	@Override
	public void addQuotation(final Quotation quotation) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				int id = quotation.getCustId();
				manage_db.saveObj(quotation);
				listAllQuotations(id);
			}
		});
		t.start();
	}
	
	@Override
	public void listAllQuotations(final int custId) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("listAllQuotations called");
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				Customer cust = manage_db.getCustomer(custId);
				if(cust == null) {
					Log.getLog(this).debug("customer is null, custId: " + custId);
				}
				List<Quotation> quotList = cust.getQuotations();
				Log.getLog(this).debug("listed Locations: ");
				for(QuotationObserver observer : quotation_observer) {
					observer.updateQuotationList(quotList);			
				}
			}
		});
		t.start();
	}

	@Override
	public void removeQuotation(final Quotation quotation) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("removeCustomerLocation called");
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				
				manage_db.deleteObj(quotation);
				listAllQuotations(quotation.getCustId());
			}
		});
		t.start();
	}
	
	@Override
	public void updateQuotationList(List<Quotation> quotations) {
		for(QuotationObserver observer : quotation_observer) {
			observer.updateQuotationList(quotations);
		}
	}
	
	@Override
	public int getLastQuotNumber() {
		ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
		ManageDatabase manage_db = factory.createManageDatabase();
		
		List<Quotation> quot_list = (List<Quotation>)manage_db.getLastObj(Quotation.class,
				"quotNumber");
		
		if(quot_list.iterator().hasNext()) {
			Quotation last = quot_list.iterator().next();
			Log.getLog(this).debug("last QuotNumber: " + last.getQuotId());
			return last.getQuotNumber();
		}
		return 0;
	}
	
	@Override
	public boolean isUniqueQuotNumber(int quot_number) {
		ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
		ManageDatabase manage_db = factory.createManageDatabase();
		
		List<Quotation> quots = manage_db.getQuotations(quot_number);
		if(quots.isEmpty())
			return true;
		return false;
	}
	
	// QuotationProduct methods
	
	@Override
	public void updateQuotationProducts(final Quotation quotation) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("updateQuotationProducts called, quotId: " + quotation.getQuotId());
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				
				manage_db.updateObj(quotation);
				listAllQuotationProducts( quotation.getQuotId() );
			}
		});
		t.start();
	}
	
	@Override
	public void listAllQuotationProducts(final int quot_id) {

		Thread t = new Thread(new Runnable() {
			public void run() {
				Log.getLog(this).debug("listAllQuotationProducts called with"
						+ "quotId: " + quot_id);

				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();

				List<Product> products = manage_db.getClasses(Product.class);

				List<QuotationProduct> quot_products = manage_db
						.getQuotationProducts(quot_id);
				Log.getLog(this).debug("QuotationProducts: " + quot_products.toString());
				for (QuotProductObserver observer : quot_prod_observer){
					observer.updateQuotProductList(quot_products, products);
				}
			}
		});
		t.start();
	}

	@Override
	public void listAllQuotationProducts(Quotation selectedQuotation,
			List<Product> products) {
		for (QuotProductObserver observer : quot_prod_observer) {
			observer.updateQuotProductList(selectedQuotation.getQuotProducts(),
					products);
		}

	}
	
	@Override
	public void addQuotationProduct(final QuotationProduct quot_p) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				int id = quot_p.getQuotId();
				manage_db.saveObj(quot_p);
				listAllQuotationProducts(id);
			}
		});
		t.start();
	}
	
	@Override
	public void removeQuotationProduct(final QuotationProduct quot_p) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				int id = quot_p.getQuotId();
				manage_db.deleteObj(quot_p);
				listAllQuotationProducts(id);
			}
		});
		t.start();
	}

	// Product Methods
	@Override
	public void addProduct(final Product product) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.saveObj(product);
				listAllProducts();
			}
		});
		t.start();
	}

	@Override
	public void removeProduct(final Product p) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.deleteObj(p);
				listAllProducts();
			}
		});
		t.start();
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listAllProducts() {
		Thread t = new Thread(new Runnable(){
			public void run() {
				Log.getLog(this).debug("listAllProducts called");
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
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
	public void updateQuotationProduct(final QuotationProduct quotProd) {
		Thread t = new Thread(new Runnable(){
			public void run() {
				ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
				ManageDatabase manage_db = factory.createManageDatabase();
				manage_db.updateObj(quotProd);
			}
		});
		t.start();
	}

	@Override
	public int getFirstProductId() {

		ModelFactory factory = ModelSingleton.getInstance().getModelFactory();
		ManageDatabase manage_db = factory.createManageDatabase();
		List<Object> prod_list = manage_db.getFirstObject(Product.class);
		if(prod_list.size() == 0)
			return 0;
		Product p = (Product)prod_list.iterator().next();
		return p.getProdId();
	}

	@Override
	public void setOverrideShemeStructure(Boolean state) {
		ModelSingleton.getInstance().setOverrideSheme(state);
		ModelSingleton.getInstance().resetSessionFactory(db_path);
	}

	@Override
	public String getDBPath() {
		
		return db_path;
	}

	








}
