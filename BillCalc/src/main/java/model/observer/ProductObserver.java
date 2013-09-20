package main.java.model.observer;

import java.util.List;

import main.java.model.Product;

public interface ProductObserver {
	void updateProductList(List<Product> productList);
}
