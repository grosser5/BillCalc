package main.java.model.observer;

import java.util.List;

import main.java.model.Product;
import main.java.model.QuotationProduct;

public interface QuotProductObserver {
	void updateQuotProductList(List<QuotationProduct> quotProdList, List<Product> productList);
}
