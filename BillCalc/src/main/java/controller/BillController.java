package main.java.controller;

import main.java.view.MainWindow;
import main.java.view.ViewInterface;

public class BillController implements ControllerInterface{

	private ViewInterface view;
	private Remote remote;
	public BillController() {
		this.view = new MainWindow(this);
		this.remote = new Remote(view);
		
		SearchCustomer search = new SearchCustomer();
		AbstractCommand searchCommand = new SearchCustomerCommand(view,search);
		remote.setSlot("searchCustomer", searchCommand);
		
	}
	@Override
	public void newProductButtonPressed() {
		remote.execute("searchCustomer");
		
	}

	@Override
	public void updateCustomerList() {
		
	}

}
