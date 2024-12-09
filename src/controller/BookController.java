package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Dao;
import model.Author;
import model.Book;

public class BookController {
	
	// List of books
	private ArrayList<Book> books;
	// Connection to the database
	private Connection connectionSQL;
	
	public BookController() {
		// Establishes a database connection
		connectionSQL = Dao.getConnection();
		books = new ArrayList<>();
	}
	
	public void addBook(Book b) {
		System.out.println("Inserting the book: " + b);
		// Perform the operation in SQL
		try {
			System.out.println("INSERTING AN ELEMENT TO THE BOOKS TABLE...");
			// Insert a new book into the books table
			System.out.println("INSERT INTO books(isbn,title,genre,pages,id_Author) VALUES ('"+b.getIsbn()+"','"+b.getTitle()+"','"+b.getGenre()+"','"+b.getPages()+"','"+b.getId_Author()+"')");
			Statement st = connectionSQL.createStatement();
			st.executeUpdate("INSERT INTO books(isbn,title,genre,pages,id_Author) VALUES ('"+b.getIsbn()+"','"+b.getTitle()+"','"+b.getGenre()+"','"+b.getPages()+"','"+b.getId_Author()+"')");
			System.out.println("BOOK INSERTED!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		books.add(b);
	}
	public Book getBookByName(String selectedBookName) {
		try {
			// Prepare the SQL statement with a parameterized query
			PreparedStatement ps = connectionSQL.prepareStatement("SELECT * FROM books WHERE title = ?");
			// Set the value of the parameter in the query
			ps.setString(1, selectedBookName);
			// Execute the query and retrieve the result set
			ResultSet rs = ps.executeQuery();

			// Check if there is a result in the result set
			if (rs.next()) {

				// Retrieve the author's information from the result set
				int id_Book = rs.getInt("id_Book");
				long isbn = rs.getLong("isbn");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				int pages = rs.getInt("pages");
				int idAuthor = rs.getInt("id_Author");



				// Create an Author object with the retrieved information
				Book book = new Book(id_Book, isbn, title, genre, pages, idAuthor);
				
				// Set the ID of the author
				book.setId_Book(id_Book);
				
				// Return the author object
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean modifyExistentBook(long newIsbn, String newTitle, String newGenre, int newPages, int newid_Author, String old_title) {
		try {
			// Establish a connection to the database
			// Create the SQL query to update the author's data
	        String sql = "UPDATE books SET isbn = ?, title = ?, genre = ?, pages = ?, id_Author = ? WHERE title = ?";

			// Create the PreparedStatement with the SQL query
	        PreparedStatement statement = connectionSQL.prepareStatement(sql);
	        statement.setLong(1, newIsbn);
	        statement.setString(2, newTitle);
	        statement.setString(3, newGenre);
	        statement.setInt(4, newPages);
	        statement.setInt(5, newid_Author);
	        statement.setString(6, old_title);



			// Execute the query
			int rowsAffected = statement.executeUpdate();

			// Check if at least one row was updated in the database
			if (rowsAffected > 0) {
				// Indicate that the modification was successful
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Indicate that the modification failed
		return false;
	}
	public void removeBook(String bookName) {
		
		try (Connection connectionSQL = Dao.getConnection();) {
			//Delete query on the database
			String query = "DELETE FROM books WHERE title = ?";
			try (PreparedStatement statement = connectionSQL.prepareStatement(query)) {
				statement.setString(1, bookName);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getGenres() {
	    ArrayList<String> genres = new ArrayList<>();
	    
	    try {
	        Connection connectionSQL = Dao.getConnection();
	        String query = "SELECT DISTINCT genre FROM books";
	        PreparedStatement statement = connectionSQL.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String genre = resultSet.getString("genre");
	            genres.add(genre);
	        }

	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return genres;
	}
	/**
	 * Seleccionar todas las personas de la BBDD y guardarlas en un ArrayList
	 * */
	public ArrayList<Book> getBook() {
		books = new ArrayList<>();
		try {
			Statement st = connectionSQL.createStatement();
			// Selects all books from the database
			ResultSet rs = st.executeQuery("SELECT * FROM books");
			while(rs.next()) {
				
				int idBook = rs.getInt("id_Book");
				long isbn = rs.getLong("isbn");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				int pages = rs.getInt("pages");
				int id_Author = rs.getInt("id_Author");
				
				Book b = new Book(idBook, isbn, title, genre, pages, id_Author);
				b.setId_Book(idBook);
				// Add the book object to the array
				books.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}
	
	public Book getBook(int index) {
		return books.get(index);
	}

	public void setAuthors(ArrayList<Book> books) {
		this.books = books;
	}
	

}
