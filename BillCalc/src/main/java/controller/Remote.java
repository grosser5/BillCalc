package main.java.controller;

import java.util.HashMap;
import java.util.Map;

import main.java.view.ViewInterface;

public class Remote {

	private ViewInterface view;
	private Map<String,AbstractCommand> slot = new HashMap<String,AbstractCommand>();
	
	Remote(ViewInterface view) {
		this.view = view;
		
	}
	
	
	
	public void setSlot(String name, AbstractCommand command) {
		slot.put(name, command);
	}
	
	public void execute(String name) {
		slot.get(name).execute();
	}
	
	
}
