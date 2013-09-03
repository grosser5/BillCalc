package main.java.controller;




import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import main.java.view.ViewInterface;

public class SearchCustomerCommand extends AbstractCommand{

	List<String> last_search = null;
	ListIterator iter =  last_search.listIterator();
	SearchCustomer search;
	
	SearchCustomerCommand(ViewInterface view, SearchCustomer search) {
		super(view);
		this.search = search;
	}

	@Override
	public void execute() {
		String search = view.getCustomerSearchText();
		last_search.add(search);
		iter.next();
		
	}
	@Override
	public void back() {
		if(iter.hasPrevious()) {
			iter.previous();
		}
	}
}
