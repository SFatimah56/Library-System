package edu.csueb.sweng.books;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private String ISBN;

	private String genre;
	private boolean available;
	private String title;
	private List<Author> authors;

	public Book() {
		super();
	}

	public Book(String iSBN, String genre, boolean available, String title) {
		super();
		ISBN = iSBN;
		this.genre = genre;
		this.available = available;
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		if (this.authors == null) {
			this.authors = new ArrayList<Author>();
		}
		this.authors.add(author);
	}
		
}



