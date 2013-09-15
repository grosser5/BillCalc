package main.java.controller;

import java.util.HashMap;
import java.util.Map;

import main.java.view.ViewInterface;

public class Remote {

	private ViewInterface view;
	private Map<Class,AbstractCommand> slot = new HashMap<Class,AbstractCommand>();
	
	Remote(ViewInterface view) {
		this.view = view;
		
	}
	
	
	
	public void setSlot(Class c, AbstractCommand command) {
		slot.put(c, command);
	}
	
	public void execute(Class c) {
		slot.get(c).execute();
	}
	
	
}
