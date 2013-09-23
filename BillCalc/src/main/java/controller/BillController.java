package main.java.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Location;
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
	
	public BillController(ViewInterface view, ModelInterface model) {
		this.factory = ControllerSingleton.getControllerFactory();
		this.view = view;
		this.model = model;
		this.remote = factory.createRemote(view);
		
		remote.setSlot(SearchCustomerCommand.class, 
				factory.createSearchCustomerCommand(model, view) );
		remote.setSlot(UpdateCustomerListCommand.class, 
				factory.createUpdateCustomerListCommand(view, model) );
		remote.setSlot(DeleteCustomerCommand.class,
				factory.createDeleteCustomerCommand(view, model));
		remote.setSlot(DeleteLocationCommand.class, 
				factory.createDeleteLocationCommand(view, model));
		remote.setSlot(DeleteQuotationCommand.class,
				factory.createDeleteQuotationCommand(view, model));
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
		return price*quantity + price*quantity*mwst/100;
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
			String street, String postal) {
		Customer selected_customer = view.getSelectedCustomer();
		Log.getLog(this).debug("addLocation called");
		try {
			int postal_number = Integer.parseInt(postal);
			model.addCustomerLocation(selected_customer, city, street,
					postal_number);
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
	public void addNewQuotation(String year, String month, String day) {
		try {
			int y = Integer.parseInt(year);
			int m = Integer.parseInt(month);
			int d = Integer.parseInt(day);
			Date date = new java.sql.Date(y, m, d);
			model.addQuotation(new Quotation(date, view.getSelectedCustomer().getCustId()));
		} catch(NumberFormatException e) {
			return;
		}
	}
	
	@Override
	public void copyQuotation() {
		Quotation quotation = view.getSelectedQuotation();
		Quotation q = new Quotation(quotation);
		q.setQuotId(0);
		for(QuotationProduct quot_prod : q.getQuotProducts()) {
			quot_prod.setQuotProdId(0);
		}
		
		model.addQuotation(q);
		resetQuotationProductList();
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
		quotation.getQuotProducts().add(qp);
		model.updateQuotationProducts(quotation);
	}

	@Override
	public void deleteSelectedQuotProduct() {
				
	}


}
