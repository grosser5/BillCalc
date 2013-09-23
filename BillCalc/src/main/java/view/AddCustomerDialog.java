package main.java.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.java.controller.ControllerInterface;
import main.view.util.Log;
import javax.swing.BoxLayout;
import java.awt.Component;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

public class AddCustomerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField customerNameField;
	private JTextField customerCompTypeField;
	private ControllerInterface controller;

	public AddCustomerDialog(ControllerInterface controller) {
		setTitle("neuer Kunde");
		
		this.controller = controller;
		
		setBounds(100, 100, 384, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[457px]", "[296px]"));
		
		JPanel namePanel = new JPanel();
		contentPanel.add(namePanel, "cell 0 0,alignx left,growy");
		namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel compTypePanel = new JPanel();
		namePanel.add(compTypePanel);
		compTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblFirmaType = new JLabel("Firma Type:     ");
		compTypePanel.add(lblFirmaType);
		
		customerCompTypeField = new JTextField();
		compTypePanel.add(customerCompTypeField);
		customerCompTypeField.setColumns(10);
		
		JPanel panel = new JPanel();
		namePanel.add(panel);
		
		JLabel lblEnterCustomerName = new JLabel("Kunden Name:");
		panel.add(lblEnterCustomerName);
		lblEnterCustomerName.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		customerNameField = new JTextField();
		panel.add(customerNameField);
		customerNameField.setHorizontalAlignment(SwingConstants.LEFT);
		customerNameField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener());
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener( new CancelButtonActionListener() );
				buttonPane.add(cancelButton);
			}
		}
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.addWindowListener(new closeWindow());
	}
	
	public class closeWindow extends WindowAdapter {
		@Override
        public void windowClosing(WindowEvent e) {
			controller.cancelAddDialog();
        }
	}
	
	public class OkButtonActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Log.getLog(this).debug("OkButtonActionListener called");
			controller.addCustomer(customerNameField.getText(),customerCompTypeField.getText());
		}
	}
	
	public class CancelButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.cancelAddDialog();
		}
		
	}
}
