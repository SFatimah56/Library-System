package com.library.management;
import java.util.ArrayList;
import java.util.List;

public class Book {
	private String ISBN;
	private boolean available;
	private String title;
	private String genre;
	private String author;
	private List<Author> authors;

	

	public Book(String iSBN, String author, boolean available, String title, String genre) {
		ISBN = iSBN;
		this.author = author;
		this.available = available;
		this.title = title;
		this.genre = genre;
	}


	public Book() {
		// TODO Auto-generated constructor stub
	}


	public String getDetails() {
        return "\nTitle: " + getTitle() +
               ", Author: " + getAuthor() +
               ", ISBN: " + getISBN() +
               ", Genre: " + getGenre() +
               ", Available: " + (isAvailable() ? "Yes" : "No");
    }
    
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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
	public static class FictionBook extends Book{
		private String theme;
		private String audience;
		private List<String> characters;
		
	    public FictionBook(String ISBN, String author, boolean available, String title, String genre, String theme, String audience) {
	        super(ISBN, author, available, title, genre);
	        this.theme = theme;
	        this.audience = audience;
	    }
		public FictionBook(String theme, List<String> characters) {
			super();
			this.theme = theme;
			this.characters = characters;
		}
		
	    @Override
	    public String getDetails() {
	        return super.getDetails() + ", Type: Fiction" + ", Theme: " + theme +", Audience: " + audience;             
	    }

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

		public List<String> getCharacters() {
			return characters;
		}

		public void setCharacters(List<String> characters) {
			this.characters = characters;
		}
			
	}
	
	// NonfictionBook inherits from Book
	public static class NonfictionBook extends Book{
		private String subjectArea;
		private String audience;
		
	    public NonfictionBook(String ISBN, String author, boolean available, String title, String genre, String subjectArea, String audience) {
	        super(ISBN, author, available, title, genre);
	        this.subjectArea = subjectArea;
	        this.audience = audience;
	    }
	    
	    @Override
	    public String getDetails() {
	        return super.getDetails() + ", Type: Non-Fiction" + ", Subject Area: " + subjectArea + ", Audience: " + audience;
	    }
		public String getSubjectArea() {
			return subjectArea;
		}

		public void setSubjectArea(String subjectArea) {
			this.subjectArea = subjectArea;
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
