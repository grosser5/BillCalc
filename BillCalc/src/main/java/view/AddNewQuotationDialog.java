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

public class AddNewQuotationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField yearField;
	private ControllerInterface controller;
	private ViewInterface mainFrame;
	private JFormattedTextField dayField;
	private JFormattedTextField monthField;
	
	public AddNewQuotationDialog(ControllerInterface controller, ViewInterface mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		
		setBounds(100, 100, 217, 161);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblDate = new JLabel("Datum:");
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(lblDate, BorderLayout.NORTH);
		{
			JPanel datePannel = new JPanel();
			contentPanel.add(datePannel, BorderLayout.CENTER);
			{
				dayField = new JFormattedTextField();
				dayField.setText(controller.getday());
			}
			{
				monthField = new JFormattedTextField();
				monthField.setText(controller.getMonth());
			}
			
			yearField = new JTextField();
			yearField.setText(controller.getYear());
			yearField.setHorizontalAlignment(SwingConstants.LEFT);
			yearField.setColumns(10);
			GroupLayout gl_datePannel = new GroupLayout(datePannel);
			gl_datePannel.setHorizontalGroup(
				gl_datePannel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_datePannel.createSequentialGroup()
						.addContainerGap()
						.addComponent(dayField, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(monthField, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(200, Short.MAX_VALUE))
			);
			gl_datePannel.setVerticalGroup(
				gl_datePannel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_datePannel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_datePannel.createParallelGroup(Alignment.BASELINE)
							.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(103, Short.MAX_VALUE))
			);
			datePannel.setLayout(gl_datePannel);
		}
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
			controller.addNewQuotation(yearField.getText(),monthField.getText(),
					dayField.getText());
		}
	}
	
	public class CancelButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.cancelAddDialog();
		}
		
	}
}
