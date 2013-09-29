package main.java.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.java.model.Location;
import main.java.model.Product;

public class ProductTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2743140788233843965L;
	List<Product> products;
	String[] columnNames = {"Name","Default Kosten/Einheit", "Einheit", "Text"};
	
	ProductTableModel(List<Product> productList) {
		this.products = productList;
	}
	
	ProductTableModel() {
		this.products = new ArrayList<Product>();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int pos) {
		if(pos < columnNames.length)
			return columnNames[pos];
		return null;
	}

	@Override
	public int getRowCount() {
		return products.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		if(row < products.size()) {
			Product product = products.get(row);
			switch(col) {
			case 0: return product.getName();
			case 1: return product.getDefaultCostPerQuantity();
			case 2: return product.getQuantityUnit();
			case 3: return product.getText();
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	
	public void setProducts(List<Product> products) {
		this.products = products;
		this.fireTableDataChanged();
	}
	
	public List<Product> getProducts()  {
		return products;
	}

	public Product getProduct(int selected) {
		if(selected < products.size()) {
			return products.get(selected);
		}
		return null;
	}

}
