package main.java.controller;

import main.java.model.ModelInterface;
import main.java.view.MainWindow;
import main.java.view.ViewInterface;

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
		
	}
	@Override
	public void newProductButtonPressed() {
		//remote.execute("newProductCommand");
		
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

}
