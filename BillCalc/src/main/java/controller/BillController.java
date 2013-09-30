package main.java.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Location;
import main.java.model.ManageModel;
import main.java.model.ModelInterface;
import main.java.model.Product;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;
import main.java.view.MainWindow;
import main.java.view.ViewInterface;
import main.view.util.Log;

public class BillController implements ControllerInterface{

	private ControllerFactory factory;
	private ViewInterface view;
	private Remote remote;
	private ModelInterface model;
	public static String propertiesFileName = "billcalc.properties" ;
	public static String propertiesDBPathKey = "db_path" ;
	public static String templateDocumentPath = "BillCalcTemplate.docx";
	
	public BillController(ViewInterface view, ModelInterface model) {
		this.factory = ControllerSingleton.getControllerFactory();
		this.view = view;
		this.model = model;
		this.remote = factory.createRemote(view);
		setModel(model);
	}
	
	public BillController(ViewInterface view) {
		this.factory = ControllerSingleton.getControllerFactory();
		this.view = view;
		this.remote = factory.createRemote(view);
	}
	@Override
	public void setModel(ModelInterface model) {
		this.model = model;
		
		remote.setSlot( SearchCustomerCommand.class, 
				factory.createSearchCustomerCommand(model, view) );
		remote.setSlot( UpdateCustomerListCommand.class, 
				factory.createUpdateCustomerListCommand(view, model) );
		remote.setSlot( DeleteCustomerCommand.class,
				factory.createDeleteCustomerCommand(view, model) );
		remote.setSlot( DeleteLocationCommand.class, 
				factory.createDeleteLocationCommand(view, model) );
		remote.setSlot( DeleteQuotationCommand.class,
				factory.createDeleteQuotationCommand(view, model) );
		remote.setSlot( DeleteQuotationProductCommand.class, 
				factory.createDeleteQuotationProductCommand(view, model) );
		remote.setSlot( DeleteProductCommand.class, 
				factory.createDeleteProductCommand(view, model));
	}

	private void resetQuotationProductList() {
		model.listAllQuotationProducts(new Quotation(), new ArrayList<Product>());
	}
	
	private void resetQuotationList() {
		model.listAllQuotationProducts(new Quotation(), new ArrayList<Product>());
		model.listAllQuotations(new ArrayList<Quotation>());
	}

	@Override
	public void updateCustomerList() {
		remote.execute(UpdateCustomerListCommand.class);
	}
	@Override
	public int calcPrice(int price, int quantity, int mwst) {
		return price*quantity;
	}
	@Override
	public void searchCustomerEntered() {
		remote.execute(SearchCustomerCommand.class);
	}
	@Override
	public void customerListSelected() {
		Customer selectedCustomer = view.getSelectedCustomer();
		model.listAllLocations( selectedCustomer.getCustId() );;
		model.listAllQuotations( selectedCustomer.getCustId() );
		resetQuotationProductList();
	}
	@Override
	public void updateProductList() {
		model.listAllProducts();
		Quotation selected_quotation = view.getSelectedQuotation();
		if(view.getSelectedQuotation() != null) {
			model.listAllQuotationProducts( selected_quotation.getQuotId() );
		}
	}
	@Override
	public void quotationTableSelected() {
		Quotation selectedQuotation = view.getSelectedQuotation();
		Log.getLog(this).debug("quotationTableSelected with quotId: " 
	+ selectedQuotation.getQuotId() + " called");
		model.listAllQuotationProducts(selectedQuotation.getQuotId());
	}

	@Override
	public void addCustomer(String customerName, String customerCompType) {
		model.addCustomer(new Customer(customerName, customerCompType));
	}

	@Override
	public void cancelAddDialog() {
		view.cancelAddDialog();
	}

	@Override
	public void deleteCustomer() {
		Log.getLog(this).debug("deleteCustomer called");
		remote.execute(DeleteCustomerCommand.class);
		resetQuotationList();
		
	}

	@Override
	public void deleteSelectedLocation() {
		Log.getLog(this).debug("deleteLocation called");
		remote.execute(DeleteLocationCommand.class);
	}

	@Override
	public void addLocation(String city,
			String street, String postal, String receiver) {
		Customer selected_customer = view.getSelectedCustomer();
		Log.getLog(this).debug("addLocation called");
		try {
			int postal_number = Integer.parseInt(postal);
			model.addCustomerLocation(selected_customer, city, street,
					postal_number, receiver);
		} catch (NumberFormatException e) {
			return;
		}

	}

	@Override
	public String getYear() {
		Calendar cal = Calendar.getInstance();
		return Integer.toString( cal.get(Calendar.YEAR) );
	}

	@Override
	public String getMonth() {
		Calendar cal = Calendar.getInstance();
		return Integer.toString( cal.get(Calendar.MONTH) + 1 );
	}

	@Override
	public String getday() {
		Calendar cal = Calendar.getInstance();
		return Integer.toString( cal.get(Calendar.DAY_OF_MONTH) );
	}

	@Override
	public void addNewQuotation(String year, String month, String day, String quot_number,
			String validUntil) throws IllegalArgumentException {
		
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month)-1;
			int d = Integer.parseInt(day);
			int quot_num = Integer.parseInt(quot_number);
			Date date = new java.sql.Date(y, m, d);
			
			if(!model.isUniqueQuotNumber(quot_num))
				throw new IllegalArgumentException("Angebot Number ist nicht einzigartig!.");
				
			model.addQuotation(new Quotation(date, view.getSelectedCustomer().getCustId(),
					quot_num, validUntil ) );
				
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Bitte Zahlen bei den Feldern eingeben.");
		}
	}
	
	@Override
	public void copyQuotation(Quotation quotation, String year,
			String month, String day, String quot_number)  throws
	IllegalArgumentException {
		
		
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month)-1;
			int d = Integer.parseInt(day);
			int quot_num = Integer.parseInt(quot_number);
			Date date = new java.sql.Date(y, m, d);
			
			if(!model.isUniqueQuotNumber(quot_num)) 
				throw new IllegalArgumentException("Angebot Number ist nicht einzigartig!.");
			
			Quotation q = new Quotation(quotation);
			q.setQuotId(0);
			
			for(QuotationProduct quot_prod : q.getQuotProducts()) {
				quot_prod.setQuotProdId(0);
			}
			
			q.setDate(new java.sql.Date(y, m, d));
			q.setQuotNumber(quot_num);
			
			model.addQuotation(q);
			resetQuotationProductList();
				
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Bitte Zahlen bei den Feldern eingeben.");
		}		
	}

	@Override
	public void deleteSelectedQuotation() {
		remote.execute(DeleteQuotationCommand.class);
		resetQuotationProductList();
	}

	@Override
	public void addNewQuotation(Quotation quotation) {
		model.addQuotation(quotation);		
	}

	@Override
	public void addDefaultQuotationProduct() {
		Quotation quotation = view.getSelectedQuotation();
		Log.getLog(this).debug("Quotation Id: " + quotation.getQuotId() );
		QuotationProduct qp = new QuotationProduct();
		qp.setQuotId(quotation.getQuotId());
		int default_product_id = model.getFirstProductId();
		qp.setProdId(default_product_id);
		model.addQuotationProduct(qp);
	}

	@Override
	public void deleteSelectedQuotProduct() {
		remote.execute(DeleteQuotationProductCommand.class);
	}

	@Override
	public void quotProdTableValueChanged(QuotationProduct quotProd) {
		model.updateQuotationProduct(quotProd);		
	}

	@Override
	public void addProduct(String name, String costPerQuant, String unit, String text) {
		
		try{
			Product p = new Product(name, Integer.parseInt(costPerQuant), unit, text);
			model.addProduct(p);
		} catch(NumberFormatException e) {
			
		}
		
	}

	@Override
	public void updateQuotProductList() {
		Quotation q = view.getSelectedQuotation();
		if(q != null) 
			model.listAllQuotationProducts(q.getQuotId());
	}

	@Override
	public void deleteSelectedProduct() {
		remote.execute(DeleteProductCommand.class);
	}

	@Override
	public int getRecomendedQuotId() {
		return model.getLastQuotNumber()+10;
		
	}
	
	//ask user to enter a new path to the database
	@Override
	public String getNewDatabasePath() throws IOException {
		Log.getLog(this).debug("getNewDatabasePath called");
		String file_path =  "";

			Properties properties = new Properties();
			file_path = view.getDatabasePath();
			File db = new File(file_path);
			//if cancel is pressed
			if(file_path.equals(""))
				return "";
			// check if the file exists
			if(!db.exists())
				throw new IOException("Database file not found");
			FileOutputStream prop_file_stream = new FileOutputStream(
					BillController.propertiesFileName);
			properties.setProperty(BillController.propertiesDBPathKey,file_path);
			properties.store(prop_file_stream, "Billcalc properties");
			

		return file_path;
		
	}
	
	//get DatabasePath that is stored in the java .properties file
	@Override
	public String getStoredDatabasePath() {
		
		Log.getLog(this).debug("getStoredDatabasePath called");
		
			Properties properties = new Properties();
			String file_path = "";
			try {
				BufferedInputStream stream = new BufferedInputStream(
						new FileInputStream(BillController.propertiesFileName));
				properties.load(stream);
				file_path = properties.getProperty(BillController.propertiesDBPathKey);
				if(file_path == null) {
					Log.getLog(this).debug("getStoredDatabasePath: no property matched");
					return "";
				}
			} catch (IOException e) {
				return "";
			}
			File db = new File(file_path);
			if( db.exists() ) {
				Log.getLog(this).debug("getStoredDatabasePath: return stored path:" + file_path);
				return file_path;
			}
		return "";
	}

	@Override
	public void initialize() {
		Log.getLog(this).debug("initialize called");
		updateCustomerList();
		updateProductList();
		
	}

	@Override
	public ModelInterface createNewDatabase() throws IOException{
		String file_path =  "";
			Properties properties = new Properties();
			file_path = view.getDatabasePath();
			if(file_path.equals(""))
				return model;
			getDatabase(file_path);
			model.setOverrideShemeStructure(true);
			
			FileOutputStream prop_file_stream = new FileOutputStream(
					BillController.propertiesFileName);
			properties.setProperty(BillController.propertiesDBPathKey,file_path);
			properties.store(prop_file_stream, "Billcalc properties");
			
		return model;
	}

	@Override
	public ModelInterface getDatabase(String db_path) {
		this.model = new ManageModel(db_path);
		model.setOverrideShemeStructure(false);
		setModel(model);
		return model;
	}

	@Override
	public void exportToDocx() throws IllegalArgumentException  {
		Customer cust = null;
		CustomerLocation location = null;
		Quotation q = null;
		try {
			cust = view.getSelectedCustomer();
			location = view.getSelectedCustomerLocation();
			q = view.getSelectedQuotation();
		} catch (Exception e) {
			view.displayMessage("Bitte wähle einen Kunden, Ort und ein Angebot aus.");
			return;
		}
		String path = view.getExportPath();

		if(!path.equals("") && q != null && location != null && path != null) {
			model.saveDocument(q, location, path);
		}
	}


}
