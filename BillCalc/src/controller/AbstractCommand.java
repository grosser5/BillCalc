package controller;

import view.ViewInterface;

public abstract class AbstractCommand {
	protected ViewInterface view;
	
	AbstractCommand(ViewInterface view) {
		this.view = view;
	}
	public void execute() {
		//do nothing
	}
	public void back() {
		//do nothing
	}
}
