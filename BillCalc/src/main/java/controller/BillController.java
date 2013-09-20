package main.java.controller;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Location;
import main.java.model.ModelInterface;
import main.java.model.Product;
import main.java.model.Quotation;
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
	public void customerListSelected(Customer selectedCustomer) {
		List<CustomerLocation> locations = selectedCustomer.getLocations();
		List<Location> loc = new ArrayList<Location>();
		for(CustomerLocation l : locations) {
			loc.add(l.getLocation());
		}
		model.updateCustomerLocationList(loc);
		model.updateQuotationList(selectedCustomer.getQuotations());
	}
	@Override
	public void updateProductList() {
		model.listAllProducts();
	}
	@Override
	public void quotationTableSelected(Quotation selectedQuotation,
			List<Product> products) {
		model.listAllQuotatoinProducts(selectedQuotation, products);
	}

	@Override
	public void addCustomer(String customerName, String customerCompType) {
		model.addCustomer(new Customer(customerName, customerCompType));
	}

	@Override
	public void cancelAddCustomerDialog() {
		view.cancleAddCustomerDialog();
	}

	@Override
	public void deleteCustomer(Customer selected_customer) {
		Log.getLog(this).debug("deleteCustomer called");
		remote.execute(DeleteCustomerCommand.class);
	}


}
