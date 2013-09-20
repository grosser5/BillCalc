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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
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

import main.java.controller.BillController;
import main.java.controller.ControllerInterface;
import main.java.model.Customer;
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

public class MainWindow implements ViewInterface, CustomerObserver, CustomerLocationObserver,
	ProductObserver, QuotationObserver, QuotProductObserver{

	private ControllerInterface controller;
	private ModelInterface model;
	private JFrame frame;
	private JTextField customerSearch;
	private JList customerList;
	private JScrollPane customerScrollPane;
	private JLabel addProductLabel;
	private JTable quotProductTable;
	private JScrollPane quotProductscrollPane;
	private DefaultListModel<Customer> customer_list_model = 
			new DefaultListModel<Customer>();
	private QuotationTableModel q_table_model;
	private ProductTableModel p_table_model;
	private final static int QUOTTABLECOLUMNS=8;
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
		this.model = new ManageModel();
		this.controller = new BillController(this, model);
		initialize();
		loadInitData();
		disableAllButtons();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 1020, 1016);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		customerSearch = new JTextField();
		customerSearch.setText("search");
		customerSearch.setBounds(83, 87, 144, 23);
		customerSearch.addActionListener(new customerSearchActionListener());
		
		frame.getContentPane().add(customerSearch);
		
		customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(12, 118, 323, 165);
		frame.getContentPane().add(customerScrollPane);
		
		customerList = new JList(customer_list_model);
		customerList.setVisible(true);
		
		customerScrollPane.setViewportView(customerList);
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerList.setSelectedIndex(1);
		customerList.getSelectionModel().addListSelectionListener(new CustomerListSelectionListener());
	
		
		JLabel customerLabel = new JLabel("Kunde");
		customerLabel.setBounds(12, 91, 70, 15);
		customerLabel.setVisible(true);
		frame.getContentPane().add(customerLabel);
		
		addProductLabel = new JLabel("Produkte des  Angebotes:");
		addProductLabel.setBounds(395, 364, 207, 15);
		frame.getContentPane().add(addProductLabel);
		
		addQuotProduktButton = new JButton("Produkt hinzugeben");
		addQuotProduktButton.setBounds(395, 565, 178, 25);
		frame.getContentPane().add(addQuotProduktButton);
		
		quotProductscrollPane = new JScrollPane();
		quotProductscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		quotProductscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		quotProductscrollPane.setBounds(395, 388, 607, 165);
		quotProductscrollPane.setOpaque(false);
		frame.getContentPane().add(quotProductscrollPane);
		
		quotProductTable = new JTable();
		quotProductscrollPane.setViewportView(quotProductTable);		
		quotProductTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane productScrollPane = new JScrollPane();
		productScrollPane.setBounds(12, 654, 396, 152);
		frame.getContentPane().add(productScrollPane);
		
		productTable = new JTable();
		productScrollPane.setViewportView(productTable);
		
		newProductButton = new JButton("neues Produkt");
		newProductButton.setBounds(18, 815, 138, 25);
		frame.getContentPane().add(newProductButton);
		
		deleteProductButton = new JButton("entferne Produkt");
		deleteProductButton.setBounds(174, 818, 161, 25);
		frame.getContentPane().add(deleteProductButton);
		
		JLabel lblProduktTabelle = new JLabel("Zu verfuegung stehende Produkte");
		lblProduktTabelle.setBounds(12, 623, 254, 15);
		frame.getContentPane().add(lblProduktTabelle);
		
		quotationTableScrollPane = new JScrollPane();
		quotationTableScrollPane.setBounds(12, 402, 323, 152);
		frame.getContentPane().add(quotationTableScrollPane);
		
		quotationTable = new JTable();
		quotationTableScrollPane.setViewportView(quotationTable);
		quotationTable.getSelectionModel().addListSelectionListener(new QuotationTableSelectionListener());
		
		lblGespeicherteAngebote = new JLabel("gespeicherte Angebote:");
		lblGespeicherteAngebote.setBounds(12, 375, 186, 15);
		frame.getContentPane().add(lblGespeicherteAngebote);
		
		addQuotationButton = new JButton("neues Angebot");
		addQuotationButton.setBounds(12, 565, 144, 25);
		frame.getContentPane().add(addQuotationButton);
		
		deleteQuotProductButton = new JButton("Produkt löschen");
		deleteQuotProductButton.setBounds(585, 565, 161, 25);
		frame.getContentPane().add(deleteQuotProductButton);
		
		deleteQuotationButton = new JButton("lösche Angebot");
		deleteQuotationButton.setBounds(168, 565, 161, 25);
		frame.getContentPane().add(deleteQuotationButton);
		
		customerLocationTableScrollPane = new JScrollPane();
		customerLocationTableScrollPane.setBounds(395, 120, 607, 163);
		frame.getContentPane().add(customerLocationTableScrollPane);
		
		customerLocationTable = new JTable(new LocationTableModel());
		customerLocationTableScrollPane.setViewportView(customerLocationTable);
		
		
		lblRechnungsadresse = new JLabel("Rechnungsadresse:");
		lblRechnungsadresse.setBounds(395, 91, 161, 15);
		lblRechnungsadresse.setVisible(true);
		frame.getContentPane().add(lblRechnungsadresse);
		
		addLocationButton = new JButton("neue Adresse");
		addLocationButton.setBounds(395, 303, 161, 25);
		frame.getContentPane().add(addLocationButton);
		
		deleteLocationButton = new JButton("lösche Adresse");
		deleteLocationButton.setBounds(568, 303, 149, 25);
		frame.getContentPane().add(deleteLocationButton);
		
		addCustomerButton = new JButton("neuer Kunde");
		addCustomerButton.addActionListener( new AddCustomerButtonActionListener() );
		addCustomerButton.setBounds(12, 303, 131, 25);
		frame.getContentPane().add(addCustomerButton);
		
		deleteCustomerButton = new JButton("lösche Kunde");
		deleteCustomerButton.addActionListener(new DeleteCustomerButtonActionListener());
		deleteCustomerButton.setBounds(155, 303, 138, 25);
		frame.getContentPane().add(deleteCustomerButton);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		
		productTable.setFillsViewportHeight(true);
		
		//productTable.getColumnModel().getColumn(1).setPreferredWidth(150);;
		//quotProductTable.getColumnModel().getColumn(0).setPreferredWidth(200);
	}
	private void enableAllButtons() {
		addQuotationButton.setEnabled(true);
		deleteQuotProductButton.setEnabled(true);
		deleteQuotationButton.setEnabled(true);
		addLocationButton.setEnabled(true);
		deleteLocationButton.setEnabled(true);
		addCustomerButton.setEnabled(true);
		deleteCustomerButton.setEnabled(true);
		addQuotProduktButton.setEnabled(true);
		newProductButton.setEnabled(true);
		deleteProductButton.setEnabled(true);
	}
	
	private void disableAllButtons() {
		addQuotationButton.setEnabled(false);
		deleteQuotProductButton.setEnabled(false);
		deleteQuotationButton.setEnabled(false);
		addLocationButton.setEnabled(false);
		deleteLocationButton.setEnabled(false);
		addCustomerButton.setEnabled(false);
		deleteCustomerButton.setEnabled(false);
		addQuotProduktButton.setEnabled(false);
		newProductButton.setEnabled(false);
		deleteProductButton.setEnabled(false);
	}
	
	private void initColumnSizes(JTable table) {
        QuotProductTableModel model = (QuotProductTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int column_count = 0; column_count < model.getColumnCount(); column_count++) {
            column = table.getColumnModel().getColumn(column_count);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            int maxCellWidth = 0;
            for(int row_count=0; row_count < model.getRowCount(); row_count++) {
            	comp = table.getDefaultRenderer(model.getColumnClass(column_count)).
                        getTableCellRendererComponent(
                            table, model.getValueAt(row_count, column_count),
                            false, false, row_count, column_count);
            	maxCellWidth = Math.max(comp.getPreferredSize().width+5, maxCellWidth);
            }
            

            column.setPreferredWidth(Math.max(headerWidth, maxCellWidth));
            column.setMinWidth(100);
        }
        table.setFillsViewportHeight(true);
       
    }
	
	public String getCustomerSearchText() {
		return customerSearch.getText();
	}
	
	private class CustomerListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();

			if (!lsm.isSelectionEmpty() && !e.getValueIsAdjusting()) {
				// Find out which indexes are selected.
				int index = lsm.getAnchorSelectionIndex();
				Customer selectedCustomer = customer_list_model
						.elementAt(index);
				controller.customerListSelected(selectedCustomer);
			}
		}
	}
	
	public class QuotationTableSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty() && !e.getValueIsAdjusting()) {
				// Find out which indexes are selected.
				int index = lsm.getAnchorSelectionIndex();
				Quotation selectedQuotation = q_table_model.getQuotation(index);
				controller.quotationTableSelected(selectedQuotation,p_table_model.getProducts());
			}
		}
	}
	
	public class AddCustomerButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			startAddCustomerDialog();
		}
	}
	
	public class customerSearchActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.searchCustomerEntered();
		}
	}
	
	public class DeleteCustomerButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object[] options = { "Yes", "No" };
			int n = JOptionPane.showOptionDialog(frame,
					"Wirklich löschen?",
					"A Silly Question", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

			if (n == 0) {
				Customer selected_customer = (Customer) customerList
						.getSelectedValue();
				controller.deleteCustomer(selected_customer);
			}
		}

	}

	@Override
	public synchronized void updateCustomerField(List<Customer> custList) {
		customer_list_model.setSize(custList.size());
		for(int i=0; i < custList.size(); i++) {
			customer_list_model.set(i,custList.get(i));
		}
		cancleAddCustomerDialog();
	}

	@Override
	public synchronized void updateCustomerLocationField(List<Location> locationList) {
		customerLocationTable.setModel(new LocationTableModel(locationList));
		customerLocationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public synchronized void updateQuotationList(List<Quotation> quotList) {
		q_table_model = new QuotationTableModel(quotList);
		quotationTable.setModel(q_table_model);
		quotationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
	public synchronized void updateQuotProductList(List<QuotationProduct> quotProdList,
			List<Product> productList) {
		quotProductTable.setModel( new QuotProductTableModel(quotProdList, productList) );
		Product[] products = new Product[productList.size()];
		JComboBox<Product> box = new JComboBox<Product>(productList.toArray(products));
		quotProductTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(box));
		quotProductTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		quotProductTable.setDefaultRenderer(String.class, centerRenderer);
		quotProductTable.setDefaultRenderer(Product.class, centerRenderer);
		quotProductTable.setDefaultRenderer(Integer.class, centerRenderer);
		initColumnSizes(quotProductTable);
	}

	@Override
	public synchronized void updateProductList(List<Product> productList) {
		p_table_model = new ProductTableModel(productList);
		productTable.setModel(p_table_model);
		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}

	private void startAddCustomerDialog() {
		addCustomerDialog = new AddCustomerDialog(controller);
		disableAllButtons();
	}
	
	@Override
	public void cancleAddCustomerDialog() {
		if(addCustomerDialog != null) {
			addCustomerDialog.dispose();
		}
		enableAllButtons();
	}

	@Override
	public synchronized Customer getSelectedCustomer() {
		return (Customer)customerList.getSelectedValue();
	}
}
