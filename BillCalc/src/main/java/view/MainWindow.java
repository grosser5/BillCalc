package main.java.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JTree;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

import main.java.controller.ControllerInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow implements ViewInterface{

	private JFrame frame;
	private JTextField customerSearch;
	private JList customerList;
	private JScrollPane customerScrollPane;
	private JLabel addProductLabel;
	private JTable productTable;
	private JScrollPane addProductscrollPane;
	private ControllerInterface controller;
			
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainWindow window = new MainWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainWindow(ControllerInterface controller) {
					  
		initialize();
		this.controller = controller;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1020, 737);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		customerSearch = new JTextField();
		customerSearch.setText("search");
		customerSearch.setBounds(12, 416, 144, 23);
		customerSearch.addActionListener(new customerSearchActionListener());
		frame.getContentPane().add(customerSearch);
		//customerSearch.setColumns(10);
		
		DefaultListModel<String> list_model = new DefaultListModel<String>();
		
		customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(12, 250, 323, 165);
		frame.getContentPane().add(customerScrollPane);
		customerList = new JList(list_model);
		customerScrollPane.setViewportView(customerList);
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerList.setSelectedIndex(1);
		customerList.setVisibleRowCount(3);
		
		JLabel customerLabel = new JLabel("Kunde");
		customerLabel.setBounds(12, 223, 70, 15);
		frame.getContentPane().add(customerLabel);
		
		addProductLabel = new JLabel("Produkt hinzufügen");
		addProductLabel.setBounds(350, 223, 156, 15);
		frame.getContentPane().add(addProductLabel);
		
		JButton newProduktButton = new JButton("neues Produkt");
		newProduktButton.addActionListener(new newProductButtonActionListener());
		newProduktButton.setBounds(370, 429, 144, 25);
		frame.getContentPane().add(newProduktButton);
		
		addProductscrollPane = new JScrollPane();
		addProductscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		addProductscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		addProductscrollPane.setBounds(360, 252, 607, 165);
		addProductscrollPane.setOpaque(false);
		frame.getContentPane().add(addProductscrollPane);
		
		productTable = new JTable();
		addProductscrollPane.setViewportView(productTable);
		
		

		
		Object[][] data = {
		        {false, "Verlegearbeiten",
		         "TrainingsPlatz", 7000, "m2", 2.0, 17500, 20}
		        };
		
		productTable.setModel( new ProductTableModel(data) );
		
		productTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		productTable.setDefaultRenderer(Integer.class, centerRenderer);
		productTable.setDefaultRenderer(String.class, centerRenderer);
		productTable.setDefaultRenderer(Double.class, centerRenderer);
		productTable.getColumnModel().getColumn(1).setMinWidth(200);
		
		
		list_model.addElement("Kunde1");
		list_model.addElement("Kunde2");
		list_model.addElement("kunde3");
		list_model.addElement("kunde4");
		list_model.addElement("Kunde2");
		list_model.addElement("kunde3");
		list_model.addElement("kunde4");
	}
	
	public String getCustomerSearchText() {
		return customerSearch.getText();
	}
	
	
	class newProductButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.newProductButtonPressed();
		}
	}
	
	class customerSearchActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.updateCustomerList();
		}
	}
	
	

}
