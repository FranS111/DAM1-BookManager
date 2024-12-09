package model;

public class Author {
	private int id_author;
	private String name_auth;
	private String surname_auth;
	private String country;
	
	public Author(int id_author, String name_auth, String surname_auth, String country) {
		super();
		this.id_author = id_author;
		this.name_auth = name_auth;
		this.surname_auth = surname_auth;
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Author [id_book=" + id_author + ", name=" + name_auth + ", surname=" + surname_auth + ", country=" + country + "]";
	}

	public int getId_author() {
		return id_author;
	}

	public void setId_author(int id_author) {
		this.id_author = id_author;
	}

	public String getName_auth() {
		return name_auth;
	}

	public void setName_auth(String name_auth) {
		this.name_auth = name_auth;
	}

	public String getSurname_auth() {
		return surname_auth;
	}

	public void setSurname_auth(String surname_auth) {
		this.surname_auth = surname_auth;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
