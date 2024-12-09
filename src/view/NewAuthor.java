package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthorController;
import model.Author;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class NewAuthor extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	// JDialog window to show the insert error
	JDialog insertBookWindow;
	// okButton declaration
	JButton okButton;
	// cancelButton declaration
	JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public NewAuthor() {
		
		setVisible(true);
		setTitle("NewAuthor");
		setBounds(100, 100, 612, 302);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Surname:");
			lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblNewLabel.setBounds(10, 61, 135, 39);
			contentPanel.add(lblNewLabel);
		}

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 11, 85, 39);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel("Country:");
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 161, 85, 39);
		contentPanel.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.setBounds(181, 16, 380, 30);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(181, 70, 380, 30);
		contentPanel.add(textField_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(181, 170, 380, 30);
		contentPanel.add(textField_3);

		// END OF GRAPHICAL ELEMENTS

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				// Ok Button to submit all inserted data to the database
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				// Cancel Button to close the JDialog window
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				getRootPane().setDefaultButton(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == okButton) {
			// When okButton is pressed then we declare variables that store all the values
			// that the user inserts on the text fields
			String name_auth = textField.getText();
			String surname_auth = textField_1.getText();
			String country = textField_3.getText();

			// If the inserts have no values or the values are too short then we show an
			// error message
			if (name_auth == "" || surname_auth == "" || country == "" || country.length() < 2
					|| name_auth.length() < 2) {
				JOptionPane.showMessageDialog(insertBookWindow, "ERROR: WRONG INPUTS, PLEASE TRY AGAIN",
						"Insertion Error", JOptionPane.ERROR_MESSAGE);

			} else {
				// Create AuthorController Instance
				AuthorController authorController = new AuthorController();

				// Create Author instance with all the data collected
				Author author = new Author(1, name_auth, surname_auth, country);

				// Add the Author to the database
				authorController.addAuthor(author);
				
				//Close the window when finished
				dispose();

			}
		} else if (e.getSource() == cancelButton) {
			
			//Close the window when cancelButton is pressed
			dispose();
		}

	}

}
