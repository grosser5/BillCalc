package main.java.controller;

public interface ControllerInterface {
	public void newProductButtonPressed();
	public void updateCustomerList();
	public int calcPrice(int price, int quantity, int mwst);
}
