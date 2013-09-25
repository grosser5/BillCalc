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
import main.java.model.Quotation;
import main.view.util.Log;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;

public class CopyQuotationDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControllerInterface controller;
	private ViewInterface mainFrame;
	private JFormattedTextField dayField;
	private JFormattedTextField monthField;
	private JFormattedTextField yearField;
	private JFormattedTextField quotNumberField;
	private JTextPane errorTextPane;
	private JPanel datePannel;
	
	public CopyQuotationDialog(ControllerInterface controller, ViewInterface mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		
		setBounds(100, 100, 320, 215);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblDate = new JLabel("Datum:");
		{
			datePannel = new JPanel();
			{
				dayField = new JFormattedTextField();
				dayField.setText(controller.getday());
			}
			{
				monthField = new JFormattedTextField();
				monthField.setText(controller.getMonth());
			}
		}
		
		JLabel lblAngebot = new JLabel("AngebotsNummer:");
		
		quotNumberField = new JFormattedTextField();
		quotNumberField.setText( Integer.toString( controller.getRecomendedQuotId() ) );
		
		errorTextPane = new JTextPane();
		errorTextPane.setEditable(false);
		errorTextPane.setVisible(false);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAngebot)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(quotNumberField, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addComponent(datePannel, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(errorTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblDate)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(datePannel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAngebot)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(quotNumberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
					.addComponent(errorTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		yearField = new JFormattedTextField();
		yearField.setText(controller.getYear());
		GroupLayout gl_datePannel = new GroupLayout(datePannel);
		gl_datePannel.setHorizontalGroup(
			gl_datePannel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_datePannel.createSequentialGroup()
					.addContainerGap()
					.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		gl_datePannel.setVerticalGroup(
			gl_datePannel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_datePannel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_datePannel.createParallelGroup(Alignment.BASELINE)
						.addComponent(dayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		datePannel.setLayout(gl_datePannel);
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
			try {
				
				Quotation selected_quot = mainFrame.getSelectedQuotation();
				controller.copyQuotation(selected_quot, yearField.getText(),
						monthField.getText(), dayField.getText(), quotNumberField.getText());
				
			} catch(IllegalArgumentException ex) {
				errorTextPane.setText(ex.getMessage());
				errorTextPane.setVisible(true);
				Log.getLog(this).error(ex.getMessage());
			}
		}
	}
	
	public class CancelButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.cancelAddDialog();
		}
		
	}
}
