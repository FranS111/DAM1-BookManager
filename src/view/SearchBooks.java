package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import controller.AuthorController;
import controller.BookController;
import dao.Dao;
import model.Author;
import model.Book;

public class SearchBooks extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> authorComboBox;
	private JComboBox<String> genreComboBox;

	private BookController bookController;
	private AuthorController authorController;
	private Book selectedBook;
	private Author selectedAuthor;

	// okButton declaration
	JButton okButton;

	// cancelButton declaration
	JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public SearchBooks() {
		authorController = new AuthorController();
		ArrayList<Author> authors = authorController.getAuthor();

		setBounds(100, 100, 450, 502);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Label for author selection
		JLabel lblAuthorsRegistered_2 = new JLabel("Author Name");
		lblAuthorsRegistered_2.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblAuthorsRegistered_2.setBounds(45, 11, 126, 30);
		contentPanel.add(lblAuthorsRegistered_2);

		// Label for genre selection
		JLabel lblGenres = new JLabel("Book Genre");
		lblGenres.setFont(new Font("Bahnschrift", Font.PLAIN, 20));
		lblGenres.setBounds(271, 11, 114, 30);
		contentPanel.add(lblGenres);

		// ComboBox to display the authors
		authorComboBox = new JComboBox<>();
		authorComboBox.setToolTipText("Authors");
		authorComboBox.setBounds(10, 41, 193, 22);
		contentPanel.add(authorComboBox);

		// Add authors to the ComboBox
		for (Author author : authors) {
			authorComboBox.addItem(author.getName_auth());
		}

		// Disable automatic selection
		authorComboBox.setSelectedIndex(-1); 

		// ComboBox to display the genres
		genreComboBox = new JComboBox<>();
		genreComboBox.setToolTipText("Genres");
		genreComboBox.setBounds(231, 41, 193, 22);
		contentPanel.add(genreComboBox);

		// Retrieve genres from the controller
		bookController = new BookController();
		ArrayList<String> genres = bookController.getGenres();

		// Add genres to the ComboBox
		for (String genre : genres) {
			genreComboBox.addItem(genre);
		}

		genreComboBox.setSelectedIndex(-1); // Disable automatic selection

		// Button to initiate the search
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnSearch.setBounds(146, 389, 148, 30);
		contentPanel.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchBooks();
			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// OK button
		 okButton = new JButton("OK");
         okButton.setActionCommand("OK");
         buttonPane.add(okButton);
         getRootPane().setDefaultButton(okButton);
         okButton.addActionListener(this);

		// Cancel button
		cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
        getRootPane().setDefaultButton(cancelButton);
        cancelButton.addActionListener(this);

		setVisible(true);
	}
	/**
	 * Performs the search for books based on the selected author and genre.
	 */
	private void searchBooks() {
	    authorController = new AuthorController();

		String author = (authorComboBox.getSelectedItem() != null) ? authorComboBox.getSelectedItem().toString() : "";
		String genre = (genreComboBox.getSelectedItem() != null) ? genreComboBox.getSelectedItem().toString() : "";

		try {
			Connection connectionSQL = Dao.getConnection();
			String query = "SELECT * FROM books";
			
			// Check if author or genre are selected
			if (!author.isEmpty() || !genre.isEmpty()) {
				query += " WHERE ";
				boolean whereAdded = false;

				if (!author.isEmpty()) {
					query += "id_Author IN (SELECT id_Author FROM authors WHERE name_auth = ?)";
					whereAdded = true;
				}

				if (!genre.isEmpty()) {
					if (whereAdded) {
						query += " AND ";
					}
					query += "genre = ?";
				}
			} else {
				// Both ComboBoxes are empty, select all books
				query += " ORDER BY id_Book";
			}

			PreparedStatement statement = connectionSQL.prepareStatement(query);

			int parameterIndex = 1;
			if (!author.isEmpty()) {
				statement.setString(parameterIndex, author);
				parameterIndex++;
			}

			if (!genre.isEmpty()) {
				statement.setString(parameterIndex, genre);
			}

			ResultSet resultSet = statement.executeQuery();

			// Show the results in a popup window
			DefaultListModel<String> resultListModel = new DefaultListModel<>();
			resultListModel.addElement(" ");
			while (resultSet.next()) {
			    int idBook = resultSet.getInt("id_Book");
			    String title = resultSet.getString("title");
			    String isbn = resultSet.getString("isbn");
			    String genre2 = resultSet.getString("genre");
			    int pages = resultSet.getInt("pages");

			    // Obtener el nombre del autor
			    int authorId = resultSet.getInt("id_Author");
			    Author author2 = authorController.getAuthor(authorId);
			    String authorName = (author2 != null) ? author2.getName_auth() : "Unknown Author";

			    resultListModel.addElement("  - Book ID: " + idBook + ",   >Title: " + title + ",   >ISBN: " + isbn
			            + ",   >Genre: " + genre2 + ",   >Pages: " + pages + ",   >Author: " + authorName);
			}

			if (resultListModel.getSize() > 0) {
				// If there are results, show them in a popup window with a vertical list
				JList<String> resultList = new JList<>(resultListModel);
				resultList.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				JScrollPane scrollPane = new JScrollPane(resultList);
				scrollPane.setPreferredSize(new Dimension(500, 200));

				JDialog resultDialog = new JDialog();
				resultDialog.setTitle("Search Results");
				resultDialog.getContentPane().add(scrollPane);
				resultDialog.pack();
				resultDialog.setLocationRelativeTo(null);
				resultDialog.setVisible(true);
			} else {
				// If there are no results, show a message that no books were found
				JLabel noResultLabel = new JLabel("No books found.");
				noResultLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
				JDialog noResultDialog = new JDialog();
				noResultDialog.setTitle("Search Results");
				noResultDialog.getContentPane().add(noResultLabel);
				noResultDialog.setSize(200, 100);
				noResultDialog.setLocationRelativeTo(null);
				noResultDialog.setVisible(true);
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			dispose();
		} else if (e.getSource() == cancelButton) {
			dispose();
		}
	}
}
