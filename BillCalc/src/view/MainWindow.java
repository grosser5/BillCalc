package view;

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

public class MainWindow {

	private JFrame frame;
	private JTextField txtSearch;
	private JList list;
	private JScrollPane scrollPane_1;
	private JLabel lblProdukte;
	private JTable table_1;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1020, 737);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		txtSearch = new JTextField();
		txtSearch.setText("search");
		txtSearch.setBounds(12, 416, 144, 23);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		DefaultListModel<String> list_model = new DefaultListModel<String>();
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 250, 323, 165);
		frame.getContentPane().add(scrollPane_1);
		list = new JList(list_model);
		scrollPane_1.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectedIndex(1);
		list.setVisibleRowCount(3);
		
		JLabel lblKudnde = new JLabel("Kunde");
		lblKudnde.setBounds(12, 223, 70, 15);
		frame.getContentPane().add(lblKudnde);
		
		lblProdukte = new JLabel("Produkt hinzufügen");
		lblProdukte.setBounds(350, 223, 156, 15);
		frame.getContentPane().add(lblProdukte);
		
		JButton btnNeuesProdukt = new JButton("neues Produkt");
		btnNeuesProdukt.setBounds(370, 429, 144, 25);
		frame.getContentPane().add(btnNeuesProdukt);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(360, 252, 607, 165);
		scrollPane.setOpaque(false);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		

		
		Object[][] data = {
		        {false, "Verlegearbeiten",
		         "TrainingsPlatz", 7000, "m2", 2.0, 17500, 20}
		        };
		
		table_1.setModel( new ProduktTableModel(data) );
		
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		table_1.setDefaultRenderer(Integer.class, centerRenderer);
		table_1.setDefaultRenderer(String.class, centerRenderer);
		table_1.setDefaultRenderer(Double.class, centerRenderer);
		table_1.getColumnModel().getColumn(1).setMinWidth(200);
		
		
		list_model.addElement("Kunde1");
		list_model.addElement("Kunde2");
		list_model.addElement("kunde3");
		list_model.addElement("kunde4");
		list_model.addElement("Kunde2");
		list_model.addElement("kunde3");
		list_model.addElement("kunde4");
	}
	

}
