package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.AuthorController;
import controller.BookController;
import model.Author;
import model.Book;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Author;
import controller.AuthorController;

public class RemoveAuthor extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	// JDialog window to show the insert error
	JDialog insertBookWindow;
	// okButton declaration
	JButton okButton;

	// cancelButton declaration
	JButton cancelButton;
	// combo box String declaration
	JComboBox<String> comboBox;

	private BookController bookController;
	private AuthorController authorController;
	private Book selectedBook;
	private Author selectedAuthor;

	/**
	 * Create the dialog.
	 */
	public RemoveAuthor() {
		// Initialize the author controller
	    authorController = new AuthorController();
		// Retrieve authors from the controller
	    ArrayList<Author> authors = authorController.getAuthor();

	    setVisible(true);
	    setTitle("RemoveAuthor");
	    setBounds(100, 100, 533, 165);
	    getContentPane().setLayout(new BorderLayout());
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    getContentPane().add(contentPanel, BorderLayout.CENTER);
	    contentPanel.setLayout(null);
	    {
	        JLabel lblAuthorsRegistered_1 = new JLabel("Authors Registered:");
	        lblAuthorsRegistered_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	        lblAuthorsRegistered_1.setBounds(10, 11, 189, 30);
	        contentPanel.add(lblAuthorsRegistered_1);
	    }

	    // ComboBox to display the authors
	    comboBox = new JComboBox<>();
	    comboBox.setToolTipText("Books");
	    comboBox.setBounds(209, 15, 292, 22);
	    contentPanel.add(comboBox);

	    for (Author author : authors) {
			// Add each author's name to the combo box
	        comboBox.addItem(author.getName_auth());
	    }

		// Create the "Delete Author" and "Cancel" buttons
	    okButton = new JButton("Delete Author");
	    cancelButton = new JButton("Cancel");
	    
		// Add action listeners to the buttons
	    okButton.addActionListener(this);
	    cancelButton.addActionListener(this);
	    
		// Set action commands for the buttons
	    okButton.setActionCommand("OK");
	    cancelButton.setActionCommand("Cancel");

	    {
	        JPanel buttonPane = new JPanel();
	        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        getContentPane().add(buttonPane, BorderLayout.SOUTH);
	        {
	            buttonPane.add(okButton);
				// Set the default button when Enter is pressed
	            getRootPane().setDefaultButton(okButton);
	        }
	        {
	            buttonPane.add(cancelButton);
				// Set the default button when Enter is pressed
	            getRootPane().setDefaultButton(cancelButton);
	        }
	    }
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			// Get the selected author's name from the combo box
			String selectedAuthorName = (String) comboBox.getSelectedItem();

			// Remove the author from the database or perform the corresponding action
			authorController.removeAuthor(selectedAuthorName);

			// Remove the author from the combo box
			comboBox.removeItem(selectedAuthorName);

			// Show a confirmation message
			JOptionPane.showMessageDialog(this, "Author removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			// Dispose the dialog
			dispose();
		} else if (e.getSource() == cancelButton) {
			// Action for the Cancel button
			dispose();
			
		} else if (e.getSource() == comboBox) {
		}
	}

}
