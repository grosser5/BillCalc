package main.java.controller;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

import main.java.view.ViewInterface;
import main.view.util.Log;

public class SearchCustomerCommand extends AbstractCommand{

	private final Stack<String> next_search = new Stack<String>();
	private final Stack<String> prev_search = new Stack<String>();
	private String current_search = "";
	private SearchCustomer search;
	
	SearchCustomerCommand(ViewInterface view, SearchCustomer search) {
		super(view);
		Log.getLog(this).debug("constructor called");
		this.search = search;
	}

	@Override
	public void execute() {
		prev_search.add(current_search);
		current_search = view.getCustomerSearchText();
		next_search.clear();
		
		search.search(current_search);
		
	}
	@Override
	public void back() {
		next_search.add(current_search);
		current_search = prev_search.pop();
		
		search.search(current_search);
	}
	
	
}
