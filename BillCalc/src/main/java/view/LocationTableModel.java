package main.java.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.java.model.CustomerLocation;
import main.java.model.Location;
import main.view.util.Log;

public class LocationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2743140788233843965L;
	List<CustomerLocation> locations;
	String[] columnNames = {"Empfänger Name", "Ort","Straße", "Postleitzahl"};
	
	LocationTableModel(List<CustomerLocation> locationList) {
		this.locations = locationList;
	}
	
	LocationTableModel() {
		this.locations = new ArrayList<CustomerLocation>();
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
		return locations.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		if(row < locations.size()) {
			CustomerLocation location = locations.get(row);
			switch(col) {
			case 0: return location.getCity();
			case 1: return location.getStreet();
			case 2: return location.getPostal();
			}
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public void setValueAt(Object obj, int row, int col) {
		if(row < locations.size() && String.class.equals(obj)) {
			CustomerLocation location = locations.get(row);
			switch(col) {
			case 0: location.setCity((String)obj);;
			case 1: location.setStreet((String)obj);
			case 2: try{
						location.setPostal( Integer.parseInt((String)obj) );
					} catch(NumberFormatException e) {
						return;
					}
			}
		}
		fireTableCellUpdated(row, col);
	}
	
	public void setLocations(List<CustomerLocation> locations) {
		this.locations = locations;
		this.fireTableDataChanged();
	}
	
	public CustomerLocation getLocation(int index) {
		Log.getLog(this).debug("location Array size: " + locations.size());
		return locations.get(index);
	}

}
