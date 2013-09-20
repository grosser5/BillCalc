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

public class AddLocationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField customerNameField;
	private JTextField customerCompTypeField;
	private ControllerInterface controller;

	public AddLocationDialog(ControllerInterface controller) {
		
		this.controller = controller;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblEnterCustomerName = new JLabel("Kunden Name:");
		lblEnterCustomerName.setBounds(12, 26, 179, 15);
		contentPanel.add(lblEnterCustomerName);
		
		customerNameField = new JTextField();
		customerNameField.setHorizontalAlignment(SwingConstants.LEFT);
		customerNameField.setBounds(197, 24, 235, 19);
		contentPanel.add(customerNameField);
		customerNameField.setColumns(10);
		
		JLabel lblFirmaType = new JLabel("Firma Type: ");
		lblFirmaType.setBounds(12, 73, 100, 15);
		contentPanel.add(lblFirmaType);
		
		customerCompTypeField = new JTextField();
		customerCompTypeField.setBounds(197, 71, 235, 19);
		contentPanel.add(customerCompTypeField);
		customerCompTypeField.setColumns(10);
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
				cancelButton.setActionCommand("Cancel");
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
			controller.cancelAddCustomerDialog();
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
			controller.cancelAddCustomerDialog();
		}
		
	}
}
