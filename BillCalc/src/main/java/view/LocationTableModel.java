package main.java.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import main.java.model.Location;

public class LocationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -2743140788233843965L;
	List<Location> locations;
	String[] columnNames = {"Ort","Straße", "Postleitzahl"};
	
	LocationTableModel(List<Location> locationList) {
		this.locations = locationList;
	}
	
	LocationTableModel() {
		this.locations = new ArrayList<Location>();
	}
	
	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
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
			Location location = locations.get(row);
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
			Location location = locations.get(row);
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
	
	public void setLocations(List<Location> locations) {
		this.locations = locations;
		this.fireTableDataChanged();
	}

}
