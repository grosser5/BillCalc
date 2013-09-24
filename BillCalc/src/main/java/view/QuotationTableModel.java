package main.java.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.java.model.Quotation;

public class QuotationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -6860977587028156604L;
	List<Quotation> quotations;
	String[] columnNames = {"Angebotnummer.", "Datum"};
	
	QuotationTableModel(List<Quotation> quotations) {
		this.quotations = quotations;
	}
	
	QuotationTableModel() {
		this.quotations = new ArrayList<Quotation>();
	}
	
	public void setLocations(ArrayList<Quotation> quotations) {
		this.quotations = quotations;
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
		return quotations.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		if(row < quotations.size()) {
			Quotation quotation = quotations.get(row);
			switch(col) {
			case 0: return quotation.getQuotId();
			case 1: return ""
					+ quotation.getDate().getDay() + "-"
					+ quotation.getDate().getMonth() + "-"
					+ quotation.getDate().getYear();
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void setQuotations(List<Quotation> quotations) {
		this.quotations = quotations;
		this.fireTableDataChanged();
	}
	
	public Quotation getQuotation(int index) {
		return quotations.get(index);
	}

}
