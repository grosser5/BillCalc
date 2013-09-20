package main.java.model.observer;

import java.util.List;

import main.java.model.Customer;

public interface CustomerObserver {
	void updateCustomerField(List<Customer> custList);
}
