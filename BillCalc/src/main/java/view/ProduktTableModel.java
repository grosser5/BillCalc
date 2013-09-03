package main.java.view;

import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

class ProduktTableModel extends AbstractTableModel {

	
	
	private static final long serialVersionUID = 544322;
	private String[] columnNames = new String[] { "sel", "Produkt", "Ort", "Menge",
    		"Einheit", "Euro/Eh", "Betrag", "MWSt" };
    private Object[][] data = null;

    public ProduktTableModel(){ }
    
    public ProduktTableModel(Object[][] data) {
    	this.data = data;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
    	if(data != null)
    		return data.length;
    	else 
    		return 0;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
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
    	
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
    public void setRows(Object[][] data) {
    	this.data = data;
    	
    }
    
}