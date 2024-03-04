package edu.csueb.sweng.books;
import java.util.ArrayList;
import java.util.List;
public class Author  {
	private String firstName;
	private String  lastName;
	private String  nickname;
	private List<Book> books;
	
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
