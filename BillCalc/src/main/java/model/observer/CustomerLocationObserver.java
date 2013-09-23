package main.java.model.observer;

import java.util.List;

import main.java.model.CustomerLocation;

public interface CustomerLocationObserver {
	public void updateCustomerLocationField(List<CustomerLocation> locationList);
	
}
