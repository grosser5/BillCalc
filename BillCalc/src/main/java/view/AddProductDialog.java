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
import main.java.model.Product;
import main.view.util.Log;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;

public class AddProductDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ControllerInterface controller;
	private ViewInterface mainFrame;
	private JTextField costPerQuantTextField;
	private JTextField nameTextField;
	private JTextField unitTextField;
	
	public AddProductDialog(ControllerInterface controller, ViewInterface mainFrame) {
		this.mainFrame = mainFrame;
		this.controller = controller;
		
		setBounds(100, 100, 320, 263);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblPreismenge = new JLabel("Kosten/Menge:");
		
		costPerQuantTextField = new JTextField();
		costPerQuantTextField.setColumns(10);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		
		JLabel lblEinheig = new JLabel("Einheit:");
		
		unitTextField = new JTextField();
		unitTextField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPreismenge)
						.addComponent(lblName)
						.addComponent(lblEinheig))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(unitTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(costPerQuantTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPreismenge)
						.addComponent(costPerQuantTextField, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEinheig)
						.addComponent(unitTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(92, Short.MAX_VALUE))
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
			controller.addProduct(nameTextField.getText(),
					costPerQuantTextField.getText(),
					unitTextField.getText());
			controller.cancelAddDialog();
		}
	}
	
	public class CancelButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			controller.cancelAddDialog();
		}
		
	}
}
