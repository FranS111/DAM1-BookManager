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

public class RemoveBook extends JDialog implements ActionListener {

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
	public RemoveBook() {
		// Initialize the author controller
	    authorController = new AuthorController();

	    setVisible(true);
	    setTitle("RemoveBook");
	    setBounds(100, 100, 533, 165);
	    getContentPane().setLayout(new BorderLayout());
	    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    getContentPane().add(contentPanel, BorderLayout.CENTER);
	    contentPanel.setLayout(null);
	    {
	        JLabel lblAuthorsRegistered_1 = new JLabel("Books Registered:");
	        lblAuthorsRegistered_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
	        lblAuthorsRegistered_1.setBounds(10, 11, 189, 30);
	        contentPanel.add(lblAuthorsRegistered_1);
	    }

	    // Retrieve books from the controller
	    bookController = new BookController();
	    ArrayList<Book> books = bookController.getBook();
	    // ComboBox to display the authors
	    comboBox = new JComboBox<>();
	    comboBox.setToolTipText("Books");
	    comboBox.setBounds(209, 15, 292, 22);
	    contentPanel.add(comboBox);

	    for (Book book : books) {
			// Add each book's title to the combo box
	        comboBox.addItem(book.getTitle());
	    }

		// Create the "Delete Book" and "Cancel" buttons
	    okButton = new JButton("Delete Book");
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
			// Get the selected book's name from the combo box
			String selectedBookName = (String) comboBox.getSelectedItem();

			// Remove the book from the database or perform the corresponding action
			bookController.removeBook(selectedBookName);

			// Remove the book from the combo box
			comboBox.removeItem(selectedBookName);

			// Show a confirmation message
			JOptionPane.showMessageDialog(this, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			// Dispose the dialog
			dispose();
		} else if (e.getSource() == cancelButton) {
			// Action for the Cancel button
			dispose();
		} else if (e.getSource() == comboBox) {
		}
	}

}
