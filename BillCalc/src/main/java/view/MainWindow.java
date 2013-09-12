package main.java.view;

import java.awt.Component;
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

import main.java.controller.ControllerInterface;
import main.java.model.Customer;
import main.java.model.Location;
import main.java.model.Product;
import main.java.model.Quotation;
import main.java.model.QuotationProduct;
import main.java.model.observer.ModelObserver;
import main.java.view.TableRenderDemo.MyTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow implements ViewInterface, ModelObserver{

	private JFrame frame;
	private JTextField customerSearch;
	private JList customerList;
	private JScrollPane customerScrollPane;
	private JLabel addProductLabel;
	private JTable quotProductTable;
	private JScrollPane quotProductscrollPane;
	private ControllerInterface controller;
	private DefaultListModel<String> customer_list_model = new DefaultListModel<String>();
	private Object[][] QuotationTableData = {{}};
	private final static int QUOTTABLECOLUMNS=8;
	private JTable productTable;
	private JTable quotationTable;
	private JScrollPane quotationTableScrollPane;
	private JLabel lblGespeicherteAngebote;
	private JButton addQuotationButton;
	private JButton deleteQuotProductButton_1;
	private JButton deleteQuotationButton;
	private JTable customerLocationTable;
	private JScrollPane customerLocationTableScrollPane;
	private JLabel lblRechnungsadresse;
	private JButton addLocationButton;
	private JButton deleteLocationButton;
	private JButton addCustomerButton;
	private JButton btnLscheKunde;
	
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1020, 1016);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		customerSearch = new JTextField();
		customerSearch.setText("search");
		customerSearch.setBounds(83, 87, 144, 23);
		customerSearch.addActionListener(new customerSearchActionListener());
		customerSearch.setVisible(true);
		frame.getContentPane().add(customerSearch);
		//customerSearch.setColumns(10);
		
		
		
		customerScrollPane = new JScrollPane();
		customerScrollPane.setBounds(12, 118, 323, 165);
		frame.getContentPane().add(customerScrollPane);
		customerList = new JList(customer_list_model);
		
		customer_list_model.add(0, "test");
		
		customerScrollPane.setViewportView(customerList);
		customerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		customerList.setLayoutOrientation(JList.VERTICAL);
		customerList.setSelectedIndex(1);
		customerList.setVisibleRowCount(3);
		
		JLabel customerLabel = new JLabel("Kunde");
		customerLabel.setBounds(12, 91, 70, 15);
		frame.getContentPane().add(customerLabel);
		
		addProductLabel = new JLabel("Produkte des  Angebotes:");
		addProductLabel.setBounds(395, 364, 207, 15);
		frame.getContentPane().add(addProductLabel);
		
		JButton addQuotProduktButton = new JButton("Produkt hinzugeben");
		addQuotProduktButton.addActionListener(new newProductButtonActionListener());
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
		
		

		
		Object[][] data = {
		        {false, "Verlegearbeiten",
		         "TrainingsPlatz", 7000, "m2", 2.0, 17500, 20}
		        };
		
		List<Product> products = new ArrayList<Product>(); products.add(new Product("prod 1", 15, "m2"));
		List<QuotationProduct> quot_prod = new ArrayList<QuotationProduct>(); 
		quot_prod.add(new QuotationProduct(1, 0, 200, 20, "Spielplatz"));
		ProductTableModel table_model = new ProductTableModel(quot_prod,products);
		quotProductTable.setModel( table_model );
		
		quotProductTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane productScrollPane = new JScrollPane();
		productScrollPane.setBounds(12, 654, 396, 152);
		frame.getContentPane().add(productScrollPane);
		
		productTable = new JTable();
		productScrollPane.setViewportView(productTable);
		
		JButton newProductButton = new JButton("neues Produkt");
		newProductButton.setBounds(18, 815, 138, 25);
		frame.getContentPane().add(newProductButton);
		
		JButton deleteProductButton = new JButton("entferne Produkt");
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
		
		lblGespeicherteAngebote = new JLabel("gespeicherte Angebote:");
		lblGespeicherteAngebote.setBounds(12, 375, 186, 15);
		frame.getContentPane().add(lblGespeicherteAngebote);
		
		addQuotationButton = new JButton("neues Angebot");
		addQuotationButton.setBounds(12, 565, 144, 25);
		frame.getContentPane().add(addQuotationButton);
		
		deleteQuotProductButton_1 = new JButton("Produkt löschen");
		deleteQuotProductButton_1.setBounds(585, 565, 161, 25);
		frame.getContentPane().add(deleteQuotProductButton_1);
		
		deleteQuotationButton = new JButton("lösche Angebot");
		deleteQuotationButton.setBounds(168, 565, 161, 25);
		frame.getContentPane().add(deleteQuotationButton);
		
		customerLocationTableScrollPane = new JScrollPane();
		customerLocationTableScrollPane.setBounds(395, 120, 607, 163);
		frame.getContentPane().add(customerLocationTableScrollPane);
		
		customerLocationTable = new JTable();
		customerLocationTableScrollPane.setViewportView(customerLocationTable);
		
		lblRechnungsadresse = new JLabel("Rechnungsadresse:");
		lblRechnungsadresse.setBounds(395, 91, 161, 15);
		frame.getContentPane().add(lblRechnungsadresse);
		
		addLocationButton = new JButton("neue Adresse");
		addLocationButton.setBounds(395, 303, 161, 25);
		frame.getContentPane().add(addLocationButton);
		
		deleteLocationButton = new JButton("lösche Adresse");
		deleteLocationButton.setBounds(568, 303, 149, 25);
		frame.getContentPane().add(deleteLocationButton);
		
		addCustomerButton = new JButton("neuer Kunde");
		addCustomerButton.setBounds(12, 303, 131, 25);
		frame.getContentPane().add(addCustomerButton);
		
		btnLscheKunde = new JButton("lösche Kunde");
		btnLscheKunde.setBounds(155, 303, 138, 25);
		frame.getContentPane().add(btnLscheKunde);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(493, 741, 32, 24);
		
		
		frame.getContentPane().add(comboBox);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		//centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		
		quotProductTable.setDefaultRenderer(Integer.class, centerRenderer);
		quotProductTable.setDefaultRenderer(String.class, centerRenderer);
		quotProductTable.setDefaultRenderer(Double.class, centerRenderer);
		//productTable.setFillsViewportHeight(true);
		//initColumnSizes(productTable);
		//productTable.getColumnModel().getColumn(3).setPreferredWidth(200);;
	}
	
	private void initColumnSizes(JTable table) {
        ProductTableModel model = (ProductTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] rowData = model.getRows();
        TableCellRenderer headerRenderer =
            table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < QUOTTABLECOLUMNS; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                                 null, column.getHeaderValue(),
                                 false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                             getTableCellRendererComponent(
                                 table, rowData[i],
                                 false, false, 0, i);
  table.getDefaultRenderer(String.class).
            cellWidth = comp.getPreferredSize().width;

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
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

	@Override
	public void updateCustomerField(List<Customer> custList) {
		list_model.setSize(custList.size());
		for(int i=0; i < custList.size(); i++) {
			list_model.set(i,custList.get(i).getName() );
		}
	}

	@Override
	public void updateCustomerLocationField(List<Location> locationList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQuotationList(List<Quotation> quotList) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void updateQuotProductList(List<QuotationProduct> quotProdList) {
		QuotationTableData = new Object[quotProdList.size()][QUOTTABLECOLUMNS];
		
		for(int i=0; i<quotProdList.size(); i++) {
			//QuotationTableData[i] = {false, quotProdList.get(i).ge}
		}
		
	}

	@Override
	public void updateProductList(List<Product> productList) {
		// TODO Auto-generated method stub
		
	}
}
