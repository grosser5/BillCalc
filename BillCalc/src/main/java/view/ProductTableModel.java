package main.java.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.model.Product;
import main.java.model.QuotationProduct;


public class ProductTableModel extends AbstractTableModel {

	
	
	private static final long serialVersionUID = 544322;
	private String[] columnNames = new String[] { "Produkt", "Ort", "Menge",
    		"Einheit", "Euro/Eh", "Betrag", "MWSt" };
	private List<RowData> data = new ArrayList();
	private Product[] products = null;
    
    public ProductTableModel(){ }
    
    public ProductTableModel(List<QuotationProduct> qot_products, List<Product> products) {
    	
    	
    	this.products = new Product[products.size()];
    	for(int i = 0; i < products.size(); i++) {
    		this.products[i] = products.get(i);
    	}
    	
    	for(QuotationProduct p : qot_products) {
    		JComboBox<Product> box = new JComboBox<Product>( this.products );
    		box.setEditable(true);
    		this.data.add( new RowData( p, box ) );
    	}
    }
    
    
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
    		return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	
    	
    	RowData cd = data.get(row);
    	
    	switch(col) {
    	case 0: return cd.products;
    	case 1: return cd.quotation_product.getPlace();
    	case 2: return cd.quotation_product.getQuantity();
    	case 3: {
    		int sel_item = cd.products.getSelectedIndex();
    		if( sel_item == -1)
    			return "";
    		else
    			return cd.products.getItemAt(sel_item).getQuantityUnit();
    	}
    	case 4: return cd.quotation_product.getCostPerQuantity();
    	case 5: return cd.quotation_product.getPrice();
    	case 6: return cd.quotation_product.getMwst();
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
        if (col == 1 || col == 4) {
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
    	
        //data[row][col] = value;
    	
    	RowData cd = data.get(row);
    	
    	switch(col) {
    	case 1:  cd.quotation_product.getPlace(); break;
    	case 2:  cd.quotation_product.getQuantity();break;
    	case 3: {
    		int sel_item = cd.products.getSelectedIndex();
    		if( sel_item == -1)
    			return;
    		else
    			cd.products.getItemAt(sel_item).getQuantityUnit();
    		
    		break;
    	}
    	case 4: cd.quotation_product.getCostPerQuantity();break;
    	case 5: cd.quotation_product.getPrice();break;
    	case 6: cd.quotation_product.getMwst();break;
    	default: return;
    	}
    	
        fireTableCellUpdated(row, col);
    }
    
    public void setRows(List<QuotationProduct> qot_products, List<Product> products) {
    	this.products = new Product[products.size()];
    	for(int i = 0; i < products.size(); i++) {
    		this.products[i] = products.get(i);
    	}
    	
    	for(QuotationProduct p : qot_products) {
    		JComboBox<Product> box = new JComboBox<Product>( this.products );
    		box.setEditable(true);
    		this.data.add( new RowData( p, box ) );
    	}
    }
    
    public List<RowData> getRowData() {
    	return data;
    }
    
    public void addQuotProduct(QuotationProduct qot_products) {
    	JComboBox<Product> box = new JComboBox<Product>( this.products );
    	box.setEditable(true);
    	data.add(new RowData(qot_products,box ) );
    }
    
    public void setProducts(List<Product> products) {
    	this.products = new Product[products.size()];
    	for(int i = 0; i < products.size(); i++) {
    		this.products[i] = products.get(i);
    	}
    }
    
    public class RowData {
    	public QuotationProduct quotation_product;
    	public JComboBox<Product> products;
		public RowData(QuotationProduct quotation_product, JComboBox<Product> products) {
			super();
			this.quotation_product = quotation_product;
			this.products = products;
		}
    	
    }
}