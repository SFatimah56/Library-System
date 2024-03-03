package com.library.management;
import java.util.ArrayList;
import java.util.List;

public class Book {
	private String ISBN;
	private String genre;
	private boolean available;
	private String title;
	private List<Author> authors;

	public Book() {}

	public Book(String iSBN, String genre, boolean available, String title) {
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
	
	//FictionBook inherits from Book
	public class FictionBook extends Book{
		private String theme;
		private List<String> characters;
		
		public FictionBook(String theme, List<String> characters) {
			super();
			this.theme = theme;
			this.characters = characters;
		}

		public String getTheme() {
			return theme;
		}

		public void setTheme(String theme) {
			this.theme = theme;
		}

		public List<String> getCharacters() {
			return characters;
		}

		public void setCharacters(List<String> characters) {
			this.characters = characters;
		}
			
	}
	
	// NonfictionBook inherits from Book
	public class NonfictionBook extends Book{
		private String theme;
		private String audience;
		
		
		public String getTheme() {
			return theme;
		}
		public void setTheme(String theme) {
			this.theme = theme;
		}
		public String getAudience() {
			return audience;
		}
		public void setAudience(String audience) {
			this.audience = audience;
		}
		
	}
	
	//composition class Author
	public class Author  {
		private String firstName;
		private String  lastName;
		private String  nickname;
		private List<Book> books;
		
		public Author() {
	        this.books = new ArrayList<>();
	    }
		
		public Author(String firstName, String lastName, String nickname) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.nickname = nickname;
	        this.books = new ArrayList<>();
	    }
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public List<Book> getBooks() {
			return books;
		}
		public void setBooks(List<Book> books) {
			this.books = books;
		}
		public void addBook(Book book) {
			if (this.books == null) {
				this.books = new ArrayList<Book>();
			}
		  this.books.add(book);
		}
	}
	
	//composition class Genre
	public class Genre {
	    private String name;

	    public Genre(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }
	}
}