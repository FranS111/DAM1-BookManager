package model;

public class Book {
	private int id_Book;
	private long isbn;
	private String title;
	private String genre;
	private int pages;
	private int id_Author;

	
	public Book(int id_Book, long isbn, String title, String genre, int pages, int id_Author) {
		super();
		this.id_Book = id_Book;
		this.isbn = isbn;
		this.title = title;
		this.genre = genre;
		this.pages = pages;
		this.id_Author = id_Author;
	}
	
	@Override
	public String toString() {
		return "Book [id_book=" + id_Book + ", isbn=" + isbn + ", title=" + title + ", genre=" + genre + ", pages=" + pages + "]";
	}


	public int getId_Book() {
		return id_Book;
	}


	public void setId_Book(int id_book) {
		this.id_Book = id_book;
	}


	public long getIsbn() {
		return isbn;
	}


	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getPages() {
		return pages;
	}


	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public int getId_Author() {
		return id_Author;
	}

	public void setId_Author(int id_Author) {
		this.id_Author = id_Author;
	}

	


}