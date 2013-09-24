package main.java.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.CellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.DefaultSingleSelectionModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

import main.java.controller.BillController;
import main.java.controller.ControllerInterface;
import main.java.model.Customer;
import main.java.model.CustomerLocation;
import main.java.model.Location;
import main.java.model.ManageModel;
import main.java.model.ModelInterface;
import main.java.model.Product;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;
import main.java.model.observer.*;
import main.view.util.Log;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.LayoutStyle.ComponentPlacement;

public class MainWindow implements ViewInterface, CustomerObserver, CustomerLocationObserver,
	ProductObserver, QuotationObserver, QuotProductObserver{

	private final MainWindow windowInstance;
	private ControllerInterface controller;
	private ModelInterface model;
	private JFrame frmBillcalc;
	private JTextField customerSearch;
	private JList customerList;
	private JScrollPane customerScrollPane;
	private JLabel quotProdLabel;
	private JTable quotProductTable;
	private JScrollPane quotProductscrollPane;
	private DefaultListModel<Customer> customer_list_model = 
			new DefaultListModel<Customer>();
	private LocationTableModel location_table_model = new LocationTableModel();
	private QuotationTableModel q_table_model = 
			new QuotationTableModel();
	private QuotProductTableModel q_p_table_model;
	private ProductTableModel p_table_model = new ProductTableModel();
	private JTable productTable;
	private JTable quotationTable;
	private JScrollPane quotationTableScrollPane;
	private JLabel lblGespeicherteAngebote;
	private JButton addQuotationButton;
	private JButton deleteQuotProductButton;
	private JButton deleteQuotationButton;
	private JTable customerLocationTable;
	private JScrollPane customerLocationTableScrollPane;
	private JLabel lblRechnungsadresse;
	private JButton addLocationButton;
	private JButton deleteLocationButton;
	private JButton addCustomerButton;
	private JButton deleteCustomerButton;
	private JButton addQuotProduktButton;
	private JButton newProductButton;
	private JButton deleteProductButton;
	private ViewFactory viewFactory;
	private JDialog addCustomerDialog;
	private JDialog addLocationDialog;
	private JDialog addNewQuotationDialog;
	private JPanel customerBelowPanel;
	private JPanel locationPanel;
	private JPanel locationBelowPanel;
	private JPanel quotationPanel;
	private JPanel quotationBelowPanel;
	private JButton copyQuotationButton;
	
	List<JButton> buttonGroupAllButtons;
	List<JButton> buttonGroupSelectedCustomer;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Log.getMainWindowLogger().info("prog start\n");
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
//		Log.getMainWindowLogger().info("prog end\n");
//	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		this.windowInstance = this;
		this.model = new ManageModel();
		this.controller = new BillController(this, model);
		q_p_table_model = new QuotProductTableModel(controller);
		buttonGroupAllButtons = new ArrayList<JButton>();
		buttonGroupSelectedCustomer = new ArrayList<JButton>();
		initialize();
		loadInitData();
		setAllButtons(false);
	}

	private void loadInitData() {
		model.registerCustomerObserver(this);
		model.registerCustomerLocationObserver(this);
		model.registerQuotationObserver(this);
		model.registerQuotProductObserver(this);
		model.registerProductObserver(this);
		controller.updateCustomerList();
		controller.updateProductList();
		this.viewFactory = new ViewFactory();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		Log.getLog(this).debug("initialize called");
		frmBillcalc = new JFrame();
		frmBillcalc.setTitle("BillCalc");
		frmBillcalc.setBounds(100, 100, 1268, 1015);
		frmBillcalc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBillcalc.setVisible(true);
		frmBillcalc.getContentPane().setLayout(
				new MigLayout("", "[356px][43px][22px][10px][559px]",
						"[338px][255px][28px][330px]"));

		JPanel customerPanel = new JPanel();
		frmBillcalc.getContentPane().add(customerPanel, "cell 0 0 2 1,grow");
		customerPanel.setLayout(new BorderLayout(0, 0));

		JPanel customerAbovePanel = new JPanel();
		customerPanel.add(customerAbovePanel, BorderLayout.NORTH);

		JLabel customerLabel = new JLabel("Kunden: ");

		customerSearch = new JTextField();
		// customerSearch.setMinimumSize(new Dimension(20, 19));
		customerSearch.setText("search...");
		customerSearch.addActionListener(new CustomerSearchActionListener());
		GroupLayout gl_customerAbovePanel = new GroupLayout(customerAbovePanel);
		gl_customerAbovePanel
				.setHorizontalGroup(gl_customerAbovePanel.createParallelGroup(
						Alignment.LEADING)
						.addGroup(
								gl_customerAbovePanel
										.createSequentialGroup()
										.addComponent(customerLabel)
										.addGap(24)
										.addComponent(customerSearch,
												GroupLayout.PREFERRED_SIZE, 97,
												GroupLayout.PREFERRED_SIZE)
										.addGap(252)));
		gl_customerAbovePanel
				.setVerticalGroup(gl_customerAbovePanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_customerAbovePanel
										.createSequentialGroup()
										.addGroup(
												gl_customerAbovePanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																customerSearch,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																customerLabel))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		customerAbovePanel.setLayout(gl_customerAbovePanel);
		customerLabel.setVisible(true);

		customerScrollPane = new JScrollPane();
		customerPanel.add(customerScrollPane, BorderLayout.CENTER);

		customerList = new JList(customer_list_model);
		customerList.setVisible(true);

		customerScrollPane.setViewportView(customerList);
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerList.setSelectedIndex(1);

		customerBelowPanel = new JPanel();
		customerPanel.add(customerBelowPanel, BorderLayout.SOUTH);

		addCustomerButton = new JButton("neuer Kunde");
		customerBelowPanel.add(addCustomerButton);

		deleteCustomerButton = new JButton("lösche Kunde");
		customerBelowPanel.add(deleteCustomerButton);
		deleteCustomerButton
				.addActionListener(new DeleteCustomerButtonActionListener());
		addCustomerButton
				.addActionListener(new AddCustomerButtonActionListener());
		customerList.getSelectionModel().addListSelectionListener(
				new CustomerListSelectionListener());

		locationPanel = new JPanel();
		frmBillcalc.getContentPane().add(locationPanel, "cell 4 0,grow");
		locationPanel.setLayout(new BorderLayout(0, 0));

		lblRechnungsadresse = new JLabel("Rechnungsadresse:");
		locationPanel.add(lblRechnungsadresse, BorderLayout.NORTH);
		lblRechnungsadresse.setVisible(true);

		customerLocationTableScrollPane = new JScrollPane();
		locationPanel.add(customerLocationTableScrollPane, BorderLayout.CENTER);

		customerLocationTable = new JTable(location_table_model);
		customerLocationTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		customerLocationTable.setFillsViewportHeight(true);
		customerLocationTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerLocationTableScrollPane.setViewportView(customerLocationTable);

		locationBelowPanel = new JPanel();
		locationPanel.add(locationBelowPanel, BorderLayout.SOUTH);

		addLocationButton = new JButton("neue Adresse");
		addLocationButton
				.addActionListener(new AddLocationButtonActionListener());
		locationBelowPanel.add(addLocationButton);

		deleteLocationButton = new JButton("lösche Adresse");
		deleteLocationButton
				.addActionListener(new DeleteLocationButtonActionListener());
		locationBelowPanel.add(deleteLocationButton);

		quotationPanel = new JPanel();
		frmBillcalc.getContentPane().add(quotationPanel, "cell 0 1 2 2,grow");
		quotationPanel.setLayout(new BorderLayout(0, 0));

		lblGespeicherteAngebote = new JLabel("gespeicherte Angebote:");
		quotationPanel.add(lblGespeicherteAngebote, BorderLayout.NORTH);

		quotationTableScrollPane = new JScrollPane();
		quotationPanel.add(quotationTableScrollPane);

		quotationTable = new JTable(q_table_model);
		quotationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		quotationTableScrollPane.setViewportView(quotationTable);
		quotationTable.getSelectionModel().addListSelectionListener(
				new QuotationTableSelectionListener());

		quotationBelowPanel = new JPanel();
		quotationPanel.add(quotationBelowPanel, BorderLayout.SOUTH);

		copyQuotationButton = new JButton("kopiere Angebot");
		copyQuotationButton.setEnabled(false);
		copyQuotationButton
				.addActionListener(new CopyQuotationButtonActionListener());
		quotationBelowPanel.add(copyQuotationButton);

		addQuotationButton = new JButton("neues Angebot");
		addQuotationButton
				.addActionListener(new AddQuotationButtonActionListener());
		quotationBelowPanel.add(addQuotationButton);

		deleteQuotationButton = new JButton("lösche Angebot");
		deleteQuotationButton
				.addActionListener(new DeleteQuotationButtonActionListener());
		quotationBelowPanel.add(deleteQuotationButton);

		JPanel quotProdPanel = new JPanel();
		frmBillcalc.getContentPane().add(quotProdPanel,
				"cell 4 1 1 2,growx,aligny top");
		quotProdPanel.setLayout(new BorderLayout(0, 0));

		quotProductscrollPane = new JScrollPane();
		quotProdPanel.add(quotProductscrollPane, BorderLayout.CENTER);
		quotProductscrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		quotProductscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		quotProductscrollPane.setOpaque(false);

		quotProductTable = new JTable(q_p_table_model);
		quotProductTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		quotProductTable.setFillsViewportHeight(true);
		quotProductTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		quotProductscrollPane.setViewportView(quotProductTable);

		quotProdLabel = new JLabel("Produkte des  Angebotes:");
		quotProdPanel.add(quotProdLabel, BorderLayout.NORTH);

		JPanel quotProdBelwPanel = new JPanel();
		quotProdPanel.add(quotProdBelwPanel, BorderLayout.SOUTH);

		addQuotProduktButton = new JButton("Produkt hinzugeben");
		addQuotProduktButton.addActionListener(
				new addQuotProduktButtonActionListener() );
		quotProdBelwPanel.add(addQuotProduktButton);
		
		deleteQuotProductButton = new JButton("Produkt löschen");
		deleteQuotProductButton.addActionListener(new DeleteQuotProductButtonActionListener());
		quotProdBelwPanel.add(deleteQuotProductButton);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		JPanel productPanel = new JPanel();
		frmBillcalc.getContentPane().add(productPanel, "cell 0 3 3 1,grow");
		productPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel productBelowPanel = new JPanel();
		productPanel.add(productBelowPanel, BorderLayout.SOUTH);
		
		newProductButton = new JButton("neues Produkt");
		productBelowPanel.add(newProductButton);
		
		deleteProductButton = new JButton("entferne Produkt");
		productBelowPanel.add(deleteProductButton);
		
		JLabel lblProduktTabelle = new JLabel("Zu verfuegung stehende Produkte");
		productPanel.add(lblProduktTabelle, BorderLayout.NORTH);
		
		JScrollPane productScrollPane = new JScrollPane();
		productPanel.add(productScrollPane);
		
		productTable = new JTable();
		productScrollPane.setViewportView(productTable);
		
		
		productTable.setFillsViewportHeight(true);
		
		initButtons();
		//productTable.getColumnModel().getColumn(1).setPreferredWidth(150);;
		//quotProductTable.getColumnModel().getColumn(0).setPreferredWidth(200);
	}
	
	public void initButtons() {
		buttonGroupAllButtons.add(addQuotationButton);
		buttonGroupAllButtons.add(deleteQuotProductButton);
		buttonGroupAllButtons.add(deleteQuotationButton);
		buttonGroupAllButtons.add(addLocationButton);
		buttonGroupAllButtons.add(deleteLocationButton);
		buttonGroupAllButtons.add(addCustomerButton);
		buttonGroupAllButtons.add(deleteCustomerButton);
		buttonGroupAllButtons.add(addQuotProduktButton);
		buttonGroupAllButtons.add(newProductButton);
		buttonGroupAllButtons.add(deleteProductButton);
		buttonGroupAllButtons.add(copyQuotationButton);
		
		buttonGroupSelectedCustomer.add(addQuotationButton);
		buttonGroupSelectedCustomer.add(deleteQuotProductButton);
		buttonGroupSelectedCustomer.add(deleteQuotationButton);
		buttonGroupSelectedCustomer.add(addLocationButton);
		buttonGroupSelectedCustomer.add(deleteLocationButton);
		buttonGroupSelectedCustomer.add(addQuotProduktButton);
		buttonGroupSelectedCustomer.add(copyQuotationButton);
		
	}
	public void setAllButtons(boolean state) {
		for(JButton button : buttonGroupAllButtons) {
			button.setEnabled(state);
		}
	}
	public void setSelectedCustomerButtons(boolean state) {
		for(JButton button : buttonGroupSelectedCustomer) {
			button.setEnabled(state);
		}
	}
	
//	private void initColumnSizes(JTable table) {
//		
//        TableModel model = table.getModel();
//        TableColumn column = null;
//        Component comp = null;
//        int headerWidth = 0;
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        TableCellRenderer headerRenderer =
//            table.getTableHeader().getDefaultRenderer();
//
//        int data_width = 0;
//        
//        for (int column_count = 0; column_count < model.getColumnCount(); column_count++) {
//            column = table.getColumnModel().getColumn(column_count);
//
//            comp = headerRenderer.getTableCellRendererComponent(
//                                 null, column.getHeaderValue(),
//                                 false, false, 0, 0);
//            headerWidth = comp.getPreferredSize().width;
//
//            int maxCellWidth = 0;
//            for(int row_count=0; row_count < model.getRowCount(); row_count++) {
//            	comp = table.getDefaultRenderer(model.getColumnClass(column_count)).
//                        getTableCellRendererComponent(
//                            table, model.getValueAt(row_count, column_count),
//                            false, false, row_count, column_count);
//            	maxCellWidth = Math.max(comp.getPreferredSize().width+5, maxCellWidth);
//            }
//            
//            int pref_width = Math.max(headerWidth, maxCellWidth);
//            data_width += pref_width;
//            column.setPreferredWidth(pref_width);            
//        }
//        
//        
//        
//        
//        try {
//        	JPanel grand_parent = (JPanel) table.getParent().getParent().getParent();
//        	if( data_width <= grand_parent.getWidth() )
//        		table.setPreferredSize(grand_parent.getSize());
//        } catch( ClassCastException e ) { Log.getLog(this).error(e); }
//        //table.setFillsViewportHeight(true);
//       
//    }
	
	public String getCustomerSearchText() {
		return customerSearch.getText();
	}
	
	//table listSelectionListener
	private class CustomerListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();

			if (!lsm.isSelectionEmpty() && !e.getValueIsAdjusting()) {
				// Find out which indexes are selected.
				controller.customerListSelected();
				
			}
		}
	}
	
	public class QuotationTableSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty() && !e.getValueIsAdjusting()) {
				controller.quotationTableSelected();
			}
		}
	}
	
	//customer ActionListener
	public class AddCustomerButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			addCustomerDialog = viewFactory.createAddCustomerDialog(windowInstance, controller);
		}
	}
	
	public class CustomerSearchActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.searchCustomerEntered();
		}
	}
	
	public class DeleteCustomerButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Customer selected_customer = (Customer) customerList
					.getSelectedValue();
			if(selected_customer == null)
				return;
			
			int n = viewFactory.createDeleteDialog(frmBillcalc);
			if (n == 0) {
				controller.deleteCustomer();
			}
		}
	}
	
	//Location actionListener
	public class AddLocationButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer selected_customer = (Customer) customerList
					.getSelectedValue();
			if(selected_customer == null)
				return;
			addLocationDialog = viewFactory.createAddLocationDialog(windowInstance, controller);
		}
	}
	
	public class DeleteLocationButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = customerLocationTable.getSelectedRow();
			if(selected == -1)
				return;
			int n = viewFactory.createDeleteDialog(frmBillcalc);
			if (n == 0) {
				controller.deleteSelectedLocation();
			}
		}
	}
	
	//Quotation ActionListeners
	public class AddQuotationButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer selected_customer = (Customer) customerList
					.getSelectedValue();
			if(selected_customer == null)
				return;
			addNewQuotationDialog = viewFactory
					.createAddNewQuotationDialog(windowInstance, controller);
		}
	}
	
	public class DeleteQuotationButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer selected_customer = (Customer) customerList
					.getSelectedValue();
			if(selected_customer == null)
				return;
			int n = viewFactory.createDeleteDialog(frmBillcalc);
			if (n == 0) {
				controller.deleteSelectedQuotation();
			}
		}
	}
	
	public class CopyQuotationButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected_row = quotationTable.getSelectedRow();
			if(selected_row == -1)
				return;
			controller.copyQuotation();
		}
	}
	
	//QuotationProduct ActionListener
	
	public class addQuotProduktButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Log.getLog(this).debug("add Quotation Product pressed");
			int selected_quot_row = quotationTable.getSelectedRow();
			if(selected_quot_row == -1)
				return;
			Log.getLog(this).debug("selected QuotationId: "
					+ q_table_model.getQuotation(selected_quot_row).getQuotId() );
			controller.addDefaultQuotationProduct();
		}
	}
	
	public class DeleteQuotProductButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected_quot_row = quotProductTable.getSelectedRow();
			if(selected_quot_row == -1)
				return;
			int n = viewFactory.createDeleteDialog(frmBillcalc);
			if (n == 0) {
				controller.deleteSelectedQuotProduct();
			}
		}
	}
	
	//update Lists and Tables
	@Override
	public synchronized void updateCustomerField(List<Customer> custList) {
		customer_list_model.setSize(custList.size());
		for(int i=0; i < custList.size(); i++) {
			customer_list_model.set(i,custList.get(i));
		}
		cancelAddDialog();
		setSelectedCustomerButtons(false);
	}	
	
	@Override
	public synchronized void updateCustomerLocationField(List<CustomerLocation> locationList) {
		Log.getLog(this).debug("updateCustomerLocationField() called");
		location_table_model.setLocations(locationList);
		//initColumnSizes(customerLocationTable);
		cancelAddDialog();
	}

	@Override
	public synchronized void updateQuotationList(List<Quotation> quotList) {
		
		q_table_model.setQuotations(quotList);
//		q_table_model = new QuotationTableModel(quotList);
//		quotationTable.setModel(q_table_model);
		cancelAddDialog();
	}

	@Override
	public synchronized void updateQuotProductList(
			final List<QuotationProduct> quotProdList,
			final List<Product> productList) {

		Log.getLog(this).debug(
				"updateQuotProductList called with " + quotProdList.size()
						+ " prods");

		q_p_table_model.setQuotProducts(quotProdList);
		q_p_table_model.setProducts(productList);
		Product[] products = new Product[productList.size()];

		for (int i = 0; i < q_p_table_model.getRowCount(); i++) {
			Log.getLog(this).debug(
					"Tabledata: " + q_p_table_model.getQuotProd(i));
		}

		for (int i = 0; i < q_p_table_model.getRowCount(); i++) {
			for (int j = 0; j < q_p_table_model.getColumnCount(); j++) {
				Log.getLog(this).debug(
						"data: " + q_p_table_model.getValueAt(i, j));
			}
		}

		JComboBox<Product> box = new JComboBox<Product>(
				productList.toArray(products));

		Log.getLog(this).debug("setCellEditor");

		quotProductTable.getColumnModel().getColumn(0)
				.setCellEditor(new DefaultCellEditor(box));
		quotProductTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		Log.getLog(this).debug("set Renderers");
		quotProductTable.setDefaultRenderer(String.class, centerRenderer);
		quotProductTable.setDefaultRenderer(Product.class, centerRenderer);
		quotProductTable.setDefaultRenderer(Integer.class, centerRenderer);
		// initColumnSizes(quotProductTable);
	}

	@Override
	public synchronized void updateProductList(List<Product> productList) {
		p_table_model = new ProductTableModel(productList);
		productTable.setModel(p_table_model);
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	// get selected methods
	@Override
	public CustomerLocation getSelectedCustomerLocation() {
		int selected = customerLocationTable.getSelectedRow();
		if(selected == -1) {
			Log.getLog(this).debug("no location selected");
		}
		Log.getLog(this).debug("selected Location id: " + selected);
		return location_table_model.getLocation(selected);
	}
	
	@Override
	public synchronized Customer getSelectedCustomer() {
		return (Customer)customerList.getSelectedValue();
	}

	@Override
	public Quotation getSelectedQuotation() {
		
		int selected = quotationTable.getSelectedRow();
		if(selected == -1)
			return null;
		return q_table_model.getQuotation(selected);
	}
	
	@Override
	public void cancelAddDialog() {
		if(addCustomerDialog != null) 
			addCustomerDialog.dispose();
		if(addLocationDialog != null) 
			addLocationDialog.dispose();
		if(addNewQuotationDialog != null) 
			addNewQuotationDialog.dispose();
		setAllButtons(true);
	}

	@Override
	public QuotationProduct getSelectedQuotProduct() {
		int selected = quotProductTable.getSelectedRow();
		if(selected == -1)
			return null;
		return q_p_table_model.getQuotProd(selected);
	}
}
