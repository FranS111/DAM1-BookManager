package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.AuthorController;
import controller.BookController;
import model.Author;
import model.Book;
import javax.swing.JComboBox;

import javax.swing.JTextField;

public class NewBook extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private JComboBox<String> authorComboBox;

	// JDialog window to show the insert error
	JDialog insertBookWindow;
	// okButton declaration
	JButton okButton;
	// cancelButton declaration
	JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public NewBook() {
		setVisible(true);
		setTitle("NewBook");
		setBounds(100, 100, 612, 301);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("NewBook");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JLabel lblIsbn = new JLabel("ISBN:");
			lblIsbn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblIsbn.setBounds(10, 11, 85, 39);
			contentPanel.add(lblIsbn);
		}
		{
			JLabel lblTitle = new JLabel("Title:");
			lblTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblTitle.setBounds(10, 52, 135, 39);
			contentPanel.add(lblTitle);
		}
		{
			JLabel lblAuthor = new JLabel("Genre:");
			lblAuthor.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblAuthor.setBounds(10, 93, 164, 39);
			contentPanel.add(lblAuthor);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Pages:");
			lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblNewLabel_1_1.setBounds(10, 134, 85, 39);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Author:");
			lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
			lblNewLabel_1_1.setBounds(10, 174, 85, 39);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(105, 15, 453, 30);
			contentPanel.add(textField_3);
		}
		{
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(105, 56, 453, 30);
			contentPanel.add(textField_2);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(105, 97, 453, 30);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(105, 138, 453, 30);
			contentPanel.add(textField_1);
		}
		{
			authorComboBox = new JComboBox<>();
			authorComboBox.setBounds(105, 178, 453, 30);
			contentPanel.add(authorComboBox);
		}

		// Retrieve authors from the database using AuthorController
		AuthorController authorController = new AuthorController();
		ArrayList<Author> authors = authorController.getAuthor();

		// Populate the combo box with author names
		for (Author author : authors) {
			authorComboBox.addItem(author.getName_auth());
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
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
			long isbn = Long.parseLong(textField_3.getText());
			String title = textField_2.getText();
			String genre = textField.getText();
			int pages = Integer.parseInt(textField_1.getText());

			String selectedAuthorName = (String) authorComboBox.getSelectedItem();
			// Default value if no author is found
			int id_Author = 0;
			AuthorController authorController = new AuthorController();
			Author selectedAuthor = authorController.getAuthorByName(selectedAuthorName);
			if (selectedAuthor != null) {
				id_Author = selectedAuthor.getId_author();
			}

			// If the inserts have no values or the values are too short then we show an
			// error message
			if (title == "" || genre == "" || title.length() < 2 || genre.length() < 2) {
				JOptionPane.showMessageDialog(insertBookWindow, "ERROR: WRONG INPUTS, PLEASE TRY AGAIN",
						"Insertion Error", JOptionPane.ERROR_MESSAGE);

			} else {
                // Create an instance of BookController
				BookController bookController = new BookController();

                // Create a Book object with the provided data
				Book book = new Book(1, isbn, title, genre, pages, id_Author);

                // Add the book to the database
				bookController.addBook(book);

				// Close the window when finished
				dispose();

			}
		} else if (e.getSource() == cancelButton) {

			// Close the window when cancelButton is pressed
			dispose();
		}

	}

}
