package view;

import java.awt.EventQueue;
import model.Author;
import model.Book;
import controller.AuthorController;
import controller.BookController;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;

public class MainFrame extends JFrame implements ActionListener {

	// Buttons for various operations
	JButton addAuthor, modifyAuthor, removeAuthor;
	JButton addBook, modifyBook, removeBook;
	JButton searchBooks;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 801, 365);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// New JButton for adding a new author, inserts the ActionListener.
		addAuthor = new JButton("New Author");
		addAuthor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		addAuthor.addActionListener(this);

		addAuthor.setBounds(132, 113, 172, 50);
		getContentPane().add(addAuthor);

		// New JButton for modifying an author, inserts the ActionListener.
		modifyAuthor = new JButton("Modify Author");
		modifyAuthor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		modifyAuthor.addActionListener(this);

		modifyAuthor.setBounds(132, 174, 172, 50);
		getContentPane().add(modifyAuthor);

		// New JButton for removing an author, inserts the ActionListener.
		removeAuthor = new JButton("Remove Author");
		removeAuthor.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		removeAuthor.addActionListener(this);

		removeAuthor.setBounds(132, 235, 172, 50);
		getContentPane().add(removeAuthor);

		// New JButton for adding a new book, inserts the ActionListener.
		addBook = new JButton("New Book");
		addBook.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		addBook.addActionListener(this);

		addBook.setBounds(357, 113, 172, 50);
		getContentPane().add(addBook);
		
		// New JButton for modifying a book, inserts the ActionListener.
		modifyBook = new JButton("Modify Book");
		modifyBook.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		modifyBook.addActionListener(this);

		modifyBook.setBounds(357, 174, 172, 50);
		getContentPane().add(modifyBook);
		
		// New JButton for removing a book, inserts the ActionListener.
		removeBook = new JButton("Remove Book");
		removeBook.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		removeBook.addActionListener(this);

		removeBook.setBounds(357, 235, 172, 50);
		getContentPane().add(removeBook);
		
		// New JButton for searching a Book by Author, Genre or both, inserts the ActionListener.
		searchBooks = new JButton("Search Books");
		searchBooks.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		searchBooks.addActionListener(this);
			
		searchBooks.setBounds(558, 174, 172, 50);
		getContentPane().add(searchBooks);

		JLabel lblNewLabel = new JLabel("Library");
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 50));
		lblNewLabel.setBounds(132, 11, 172, 61);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 785, 57);
		getContentPane().add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 94, 785, 215);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

	}

	@Override
	// Method actionPerformed, checks if a button has been pressed and then
	// compares to see which one is and what action to perform
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed_on");
		if (e.getSource() == addAuthor) {
			System.out.println("addAuthor_on");
			new NewAuthor();
		} else if (e.getSource() == modifyAuthor) {
			System.out.println("modifyAuthor_on");
			new ModifyAuthor();
		} else if (e.getSource() == removeAuthor) {
			System.out.println("removeAuthor_on");
			new RemoveAuthor();
		}else if (e.getSource() == addBook) {
			System.out.println("removeAuthor_on");
			new NewBook();
		}else if (e.getSource() == modifyBook) {
			System.out.println("removeAuthor_on");
			new ModifyBook();
		}else if (e.getSource() == removeBook) {
			System.out.println("removeAuthor_on");
			new RemoveBook();
		}else if (e.getSource() == searchBooks) {
			System.out.println("removeAuthor_on");
			new SearchBooks();
		}
	}
}
