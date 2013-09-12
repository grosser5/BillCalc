package main.java.controller;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import main.java.view.ViewInterface;
import main.view.util.Log;

public class SearchCustomerCommand extends AbstractCommand{

	List<String> last_search;
	ListIterator iter;
	SearchCustomer search;
	
	SearchCustomerCommand(ViewInterface view, SearchCustomer search) {
		super(view);
		//Log.getSearchCustomerCommandLogger().info("super(view) called");
		this.search = search;
		
		//last_search = new ArrayList();
		//iter =  last_search.listIterator();
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
