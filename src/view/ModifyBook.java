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

	
	
	
	public class ModifyBook extends JDialog implements ActionListener {
	
		private final JPanel contentPanel = new JPanel();
		private JTextField textField_isbn;
		private JTextField textField_title;
		private JTextField textField_genre;
		private JTextField textField_pages;
	
		// JDialog window to show the insert error
		JDialog insertBookWindow;
		// okButton declaration
		JButton okButton;
		// cancelButton declaration
		JButton cancelButton;
		// combo box String declaration
		JComboBox<String> comboBox;

		JComboBox<String> comboBox2;
	
	
		// Controller for handling author-related operations
		private BookController bookController;
		private AuthorController authorController;
		private Book selectedBook;
		private Author selectedAuthor;
		
	
	
		/**
		 * Create the dialog.
		 */
		public ModifyBook() {
			bookController = new BookController();
			authorController = new AuthorController();
	
			ArrayList<Book> books = bookController.getBook();
			ArrayList<Author> authors = authorController.getAuthor();
	
	
			setVisible(true);
			setTitle("ModifyBook");
			setBounds(100, 100, 612, 341);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JLabel lblBooksRegistered = new JLabel("Books Registered:");
				lblBooksRegistered.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
				lblBooksRegistered.setBounds(10, 11, 189, 30);
				contentPanel.add(lblBooksRegistered);
			}
			{
	
			}
			{
				JLabel lblIsbn = new JLabel("ISBN:");
				lblIsbn.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
				lblIsbn.setBounds(20, 48, 85, 39);
				contentPanel.add(lblIsbn);
			}
			{
				textField_isbn = new JTextField();
				textField_isbn.setColumns(10);
				textField_isbn.setBounds(115, 52, 453, 30);
				contentPanel.add(textField_isbn);
			}
			{
				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
				lblTitle.setBounds(20, 89, 135, 39);
				contentPanel.add(lblTitle);
			}
			{
				textField_title = new JTextField();
				textField_title.setColumns(10);
				textField_title.setBounds(115, 93, 453, 30);
				contentPanel.add(textField_title);
			}
			{
				JLabel lblAuthor = new JLabel("Author:");
				lblAuthor.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
				lblAuthor.setBounds(20, 130, 164, 39);
				contentPanel.add(lblAuthor);
			}

			{
				JLabel lblNewLabel_1_1 = new JLabel("Genre:");
				lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
				lblNewLabel_1_1.setBounds(20, 171, 85, 39);
				contentPanel.add(lblNewLabel_1_1);
			}
			{
				textField_genre = new JTextField();
				textField_genre.setColumns(10);
				textField_genre.setBounds(115, 175, 453, 30);
				contentPanel.add(textField_genre);
			}
			{
				JLabel lblNewLabel_1_1 = new JLabel("Pages:");
				lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
				lblNewLabel_1_1.setBounds(20, 211, 85, 39);
				contentPanel.add(lblNewLabel_1_1);
			}
			{
				textField_pages = new JTextField();
				textField_pages.setColumns(10);
				textField_pages.setBounds(115, 215, 453, 30);
				contentPanel.add(textField_pages);
			}
			JList<String> list = new JList<>();
			list.setBounds(133, 41, 1, 1);
			contentPanel.add(list);
	
			// Retrieve books from the controller
			bookController = new BookController();
	
			// ComboBox to display the books
			comboBox = new JComboBox<>();
			comboBox.setToolTipText("Books");
			comboBox.setBounds(209, 15, 352, 22);
			contentPanel.add(comboBox);
			
			comboBox2 = new JComboBox<>();
			comboBox2.setToolTipText("Authors");
			comboBox2.setBounds(115, 134, 453, 30);
			contentPanel.add(comboBox2);
			
			// Add book names to the combo box
			
			for (Book book : books) {
			    comboBox.addItem(book.getTitle());
			}
			
			for (Author author : authors) {
			    comboBox2.addItem(author.getName_auth());
			}
			
	
	
			comboBox.addActionListener(this);
			comboBox2.addActionListener(this);
	
	
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
		// Handle action events
	
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == comboBox) {
	
				String selectedBookName = (String) comboBox.getSelectedItem();
				selectedBook = bookController.getBookByName(selectedBookName);
				// Get the selected book
	
				// Retrieve book data from the controller
				selectedBook = bookController.getBookByName(selectedBookName);
	
				// Set the book's data in the corresponding text fields
				if (this.selectedBook != null) {
					String selectedAuthorName = (String) comboBox2.getSelectedItem();
			        selectedAuthor = authorController.getAuthorByName(selectedAuthorName);
		            int authorId = this.selectedAuthor.getId_author();
					String bookName = selectedBook.getTitle();
					textField_isbn.setText(String.valueOf(selectedBook.getIsbn()));
					textField_title.setText(selectedBook.getTitle());
					textField_genre.setText(selectedBook.getGenre());
					textField_pages.setText(String.valueOf(selectedBook.getPages()));
					 
				} else {
					textField_isbn.setText("");
					textField_genre.setText("");
					textField_title.setText("");
					JOptionPane.showMessageDialog(null, "Book not found", "Error", JOptionPane.ERROR_MESSAGE);
				}
	
			} else if (e.getSource() == okButton) {
				String bookName = selectedBook.getTitle();

				String selectedAuthorName = (String) comboBox2.getSelectedItem();
				Author selectedAuthor = authorController.getAuthorByName(selectedAuthorName);
				int authorId = selectedAuthor.getId_author();
				// When OK button is pressed, retrieve user input from text fields
				long isbn = Long.parseLong(textField_isbn.getText());
		        String title = textField_title.getText();
		        String genre = textField_genre.getText();
		        int pages = Integer.parseInt(textField_pages.getText());
	
				// Validate the inputs
				if (title == "" || genre == "" || genre.length() < 2 || title.length() < 2) {
					// Show an error message if inputs are invalid
					JOptionPane.showMessageDialog(insertBookWindow, "ERROR: WRONG INPUTS, PLEASE TRY AGAIN",
							"Insertion Error", JOptionPane.ERROR_MESSAGE);
	
				} else {
					// Modify the book's data using the BookController
		            boolean success = bookController.modifyExistentBook(isbn, title, genre, pages, authorId, bookName);
					if (success) {
						JOptionPane.showMessageDialog(null, "Book data updated successfully.");
					} else {
						JOptionPane.showMessageDialog(null, "Failed to update book data.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
	
					// Close the window when finished
					dispose();
	
				}
			} else if (e.getSource() == cancelButton) {
	
				// Close the window when Cancel button is pressed
				dispose();
			}
		}
	
	}
