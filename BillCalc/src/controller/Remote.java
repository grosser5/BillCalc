package controller;

import java.util.Map;

import view.ViewInterface;

public class Remote {

	private ViewInterface view;
	
	Remote(ViewInterface view) {
		this.view = view;
	}
	
	Map<String,AbstractCommand> slot;
	
	public void setSlot(String name, AbstractCommand command) {
		slot.put(name, command);
	}
	
	public void execute(String name) {
		slot.get(name).execute();
	}
	
	
}
