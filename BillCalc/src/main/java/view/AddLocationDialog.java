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
import main.java.model.Customer;
import main.view.util.Log;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;

public class AddLocationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField cityField;
	private JTextField streetField;
	private JTextField postalField;
	private JTextField receiverField;
	private ControllerInterface controller;
	private ViewInterface mainFrame;
	
	public AddLocationDialog(ControllerInterface controller, ViewInterface mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		
		setBounds(100, 100, 584, 249);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCity = new JLabel("Ort:");
		
		cityField = new JTextField();
		cityField.setHorizontalAlignment(SwingConstants.LEFT);
		cityField.setColumns(10);
		
		JLabel lblStreet = new JLabel("Straße mit Hausnummer: ");
		
		streetField = new JTextField();
		streetField.setColumns(10);
		
		JLabel lblPostalNumber = new JLabel("Postleitzahl:");
		
		postalField = new JFormattedTextField();
		
		JLabel lblEmpfnger = new JLabel("Empfänger:");
		
		receiverField = new JFormattedTextField();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStreet, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPostalNumber))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEmpfnger)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(cityField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(streetField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(postalField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
						.addComponent(receiverField, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCity)
						.addComponent(cityField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStreet)
						.addComponent(streetField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostalNumber)
						.addComponent(postalField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmpfnger)
						.addComponent(receiverField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38))
		);
		contentPanel.setLayout(gl_contentPanel);
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
				cancelButton.addActionListener(new CancelButtonActionListener());
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
			controller.addLocation(	cityField.getText(), streetField.getText(),
					postalField.getText(), receiverField.getText());
		}
	}
	
	public class CancelButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Log.getLog(this).debug("cancel button pressed");
			controller.cancelAddDialog();
		}
		
	}
}
