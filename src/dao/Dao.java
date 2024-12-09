package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	private Connection conn;
	private final static String HOST = "localhost";
	private final static Integer PORT = 3307;
	private final static String DBNAME = "practicaswing";
	private final static String DBUSER = "root";
	private final static String DBPWD = null;

	public static Connection getConnection() {	
		try {
			System.out.println("Establishing Connection...");
			Connection c = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT + "/" + DBNAME, DBUSER, DBPWD);
			System.out.println("Connection Established...");
			return c;
		} catch (SQLException e) {
			System.err.println("[ERROR] Couldn't connect to the database");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}