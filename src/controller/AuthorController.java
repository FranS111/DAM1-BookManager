package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.Driver;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

import dao.Dao;
import model.Author;

public class AuthorController {

	// Declare an ArrayList to store Author objects
	private ArrayList<Author> authors;
	// Declare a Connection object for database connection
	private Connection connectionSQL;

	public AuthorController() {
		// Establish a database connection
		// Get a database connection using Dao class
		connectionSQL = Dao.getConnection();
		// Initialize the ArrayList to store authors
		authors = new ArrayList<>();
	}

	public void addAuthor(Author a) {
		System.out.println("Inserting the author: " + a);
		// Perform the SQL operation
		try {
			System.out.println("INSERTING AN ELEMENT TO THE AUTHORS TABLE...");
			System.out.println("INSERT INTO authors(name_auth,surname_auth,country) VALUES ('" + a.getName_auth()
					+ "','" + a.getSurname_auth() + "','" + a.getCountry() + "')");
			Statement st = connectionSQL.createStatement();
			st.executeUpdate("INSERT INTO authors(name_auth,surname_auth,country) VALUES ('" + a.getName_auth() + "','"
					+ a.getSurname_auth() + "','" + a.getCountry() + "')");
			System.out.println("AUTHOR INSERTED!!!");
		} catch (SQLException e) {
			// Print stack trace for any SQL errors
			e.printStackTrace();
		}
		// Add the Author object to the ArrayList
		authors.add(a);
	}

	public Author getAuthorByName(String selectedAuthorName) {
		try {
			// Prepare the SQL statement with a parameterized query
			PreparedStatement ps = connectionSQL.prepareStatement("SELECT * FROM authors WHERE name_auth = ?");
			// Set the value of the parameter in the query
			ps.setString(1, selectedAuthorName);
			// Execute the query and retrieve the result set
			ResultSet rs = ps.executeQuery();

			// Check if there is a result in the result set
			if (rs.next()) {

				// Retrieve the author's information from the result set
				int idAuthor = rs.getInt("id_Author");
				String name_auth = rs.getString("name_auth");
				String surname_auth = rs.getString("surname_auth");
				String country = rs.getString("country");

				// Create an Author object with the retrieved information
				Author author = new Author(idAuthor, name_auth, surname_auth, country);

				// Set the ID of the author
				author.setId_author(idAuthor);

				// Return the author object
				return author;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean modifyExistentAuthor(String authorName, String newName, String newSurname, String newCountry) {
		try {
			// Establish a connection to the database
			Connection connectionSQL = Dao.getConnection();

			// Create the SQL query to update the author's data
			String sql = "UPDATE authors SET name_auth = ?, surname_auth = ?, country = ? WHERE name_auth = ?";

			// Create the PreparedStatement with the SQL query
			PreparedStatement statement = connectionSQL.prepareStatement(sql);
			statement.setString(1, newName);
			statement.setString(2, newSurname);
			statement.setString(3, newCountry);
			statement.setString(4, authorName);

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

	public void removeAuthor(String authorName) {

		try (Connection connectionSQL = Dao.getConnection();) {
			String query = "DELETE FROM authors WHERE name_auth = ?";
			try (PreparedStatement statement = connectionSQL.prepareStatement(query)) {
				statement.setString(1, authorName);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Select all authors from the database and store them in an ArrayList
	 */
	public ArrayList<Author> getAuthor() {
		authors = new ArrayList<>();
		try {
			Statement st = connectionSQL.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM authors");
			while (rs.next()) {
				int idAuthor = rs.getInt("id_Author");
				String name_auth = rs.getString("name_auth");
				String surname_auth = rs.getString("surname_auth");
				String country = rs.getString("country");
				Author a = new Author(idAuthor, name_auth, surname_auth, country);
				a.setId_author(idAuthor);
				// Add the object to the array once we have it
				authors.add(a);
			}
		} catch (SQLException e) {
			// Print stack trace for any SQL errors
			e.printStackTrace();
		}
		return authors;
	}

	public Author getAuthor(int index) {
	    if (index >= 0 && index < authors.size()) {
	        return authors.get(index);
	    }
	    return null;
	}

	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
}
