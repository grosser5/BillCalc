package main.java.controller;

import java.util.Stack;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Location;
import main.java.view.ViewInterface;

public class DeleteLocationCommand  extends AbstractCommand{

	private DeleteLocation del_location;
	private AddLocation add_loc;
	private final Stack<CustomerLocation> deleted_locations =
			new Stack<CustomerLocation>();
	
	DeleteLocationCommand(ViewInterface view, DeleteLocation del_loc, AddLocation add) {
		super(view);
		this.del_location = del_loc;
		this.add_loc = add;
	}
	
	@Override
	public void execute() {
		CustomerLocation cust_loc = view.getSelectedCustomerLocation();
		deleted_locations.add(cust_loc);
		del_location.delete(cust_loc);
	}
	
	@Override
	public void back() {
		if(!deleted_locations.isEmpty()) {
			CustomerLocation location = deleted_locations.pop();
			add_loc.addLocation(location);
		}
	}
}
