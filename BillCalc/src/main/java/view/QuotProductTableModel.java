package main.java.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.controller.ControllerInterface;
import main.java.model.Product;
import main.java.model.QuotationProduct;
import main.view.util.Log;


public class QuotProductTableModel extends AbstractTableModel {

	
	
	private static final long serialVersionUID = 544322;
	public static final String[] columnNames = new String[] { "Produkt", "Ort", "Menge",
    		"Einheit", "€/Einheit", "Betrag", "MWSt" };
	private List<QuotationProduct> quot_products = new ArrayList<QuotationProduct>();
	private List<Product> products = new ArrayList();
    private ControllerInterface controller;
	
    public QuotProductTableModel(ControllerInterface controller){
    	this.controller = controller;
    }
    
    
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
    		return quot_products.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	//Log.getLog(this).debug("getValueAt called");
    	QuotationProduct cd = quot_products.get(row);
    	
    	Product selected_product = null;
    	for(Product p : products) {
    		if(p.getProdId() == cd.getProdId()) {
    			selected_product = p;
    			break;
    		}
    	}
    	if(selected_product == null)
    		return "";
    	
    	switch(col) {
    	case 0: return selected_product;
    	case 1: return cd.getPlace();
    	case 2: return cd.getQuantity();
    	case 3: return selected_product.getQuantityUnit();
    	case 4: return cd.getCostPerQuantity();
    	case 5: return controller.calcPrice(cd.getCostPerQuantity(),
    			cd.getQuantity(), cd.getMwst());
    	case 6: return cd.getMwst();
    	default: return "";
    	}
    	
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col == 3 || col == 5) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
    	if(row >= quot_products.size() || col >= columnNames.length)
    		return;
    	
    	QuotationProduct cd = quot_products.get(row);
    	Log.getLog(this).debug("set value at " + row + " " + col);
    	try {
    		switch(col) {
    		case 0: {
    			Product sel_prod = (Product)value;
    			cd.setProdId(sel_prod.getProdId());
    			cd.setCostPerQuantity(sel_prod.getDefaultCostPerQuantity());
    			break;
    		}
    		case 1: cd.setPlace((String) value); break;
    		case 2: cd.setQuantity((Integer) value);break;
    		case 4: cd.setCostPerQuantity((Integer)value);break;
    		case 5: cd.setPrice((Integer)value);break;
    		case 6: cd.setMwst((Integer)value);break;
    		default: return;
    		}
    	} catch(ClassCastException | IndexOutOfBoundsException e) {
    		Log.getLog(this).error(e);
    		return;
    	}
    	controller.quotProdTableValueChanged(cd);
        this.fireTableRowsUpdated(row, row);;
    }
    
    public void setQuotProducts(List<QuotationProduct> quot_products) {
    	Log.getLog(this).debug("setQuotProducts: " + quot_products);
    	this.quot_products = quot_products;
    	this.fireTableDataChanged();
    }
    
    public void addQuotProduct(QuotationProduct quot_product) {
    	quot_products.add(quot_product);
    	this.fireTableDataChanged();
    }
    
    public void setProducts(List<Product> products) {
    	this.products = products;
    	this.fireTableDataChanged();
    }

	public QuotationProduct getQuotProd(int selected) {
		return quot_products.get(selected);
	}
}