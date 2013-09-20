package main.java.view;

import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import main.java.controller.ControllerInterface;
import main.java.model.Product;
import main.java.model.QuotationProduct;

public class ViewFactory {
	public ViewFactory() { }
	
//	public void setQuotProductTable(List<QuotationProduct> quotProdList,
//			List<Product> productList, JTable quotProductTable) {
//		quotProductTable.setModel( new QuotProductTableModel(quotProdList, productList) );
//		Product[] products = new Product[productList.size()];
//		JComboBox<Product> box = new JComboBox<Product>(productList.toArray(products));
//		quotProductTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
//		quotProductTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//		
//		quotProductTable.setDefaultRenderer(String.class, centerRenderer);
//		quotProductTable.setDefaultRenderer(Product.class, centerRenderer);
//		quotProductTable.setDefaultRenderer(Integer.class, centerRenderer);
//		MainWindow.initColumnSizes(quotProductTable);
//	}
//	
//	public void setAddCustomerDialog(ControllerInterface controller) {
//		JDialog dialog = new AddCustomerDialog(controller);
//		MainWindow.
//	}
}
