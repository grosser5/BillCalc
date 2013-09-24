package main.java.controller;

import java.util.Stack;

import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Location;
import main.java.model.Product;
import main.java.view.ViewInterface;

public class DeleteProductCommand  extends AbstractCommand{

	private DeleteProduct del_product;
	private AddProduct add_product;
	private final Stack<Product> deleted_products =
			new Stack<Product>();
	
	DeleteProductCommand(ViewInterface view, DeleteProduct del_prod, AddProduct add) {
		super(view);
		this.del_product = del_prod;
		this.add_product = add;
	}
	
	@Override
	public void execute() {
		Product p = view.getSelectedProduct();
		deleted_products.add(p);
		del_product.delete(p);
	}
	
	@Override
	public void back() {
		if(!deleted_products.isEmpty()) {
			Product p = deleted_products.pop();
			add_product.addProduct(p);
		}
	}
}
