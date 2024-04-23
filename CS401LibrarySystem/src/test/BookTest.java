package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.management.Book;

class BookTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void TestGetDetails() {
		Book book = new Book("1234", "author", true, "hello", "fiction");
		String details = book.getDetails();
		assert(details.equals("\nTitle: hello, Author: author, ISBN: 1234, Genre: fiction, Available: Yes"));
	}
	@Test
	void TestGetAuthor() {
		Book book = new Book("1234", "author", true, "hello", "fiction");
		Book.Author author = book.new Author("Jane", "Doe", "JD");
        book.addAuthor(author);
        List<Book.Author> authors = book.getAuthors();
        assertNotEquals(null, book.getAuthors());
        assertEquals(1, authors.size());
        assertEquals("Jane", authors.get(0).getFirstName());
       }
	
	@Test
    public void testFictionBookGetDetails() {
        Book.FictionBook fictionBook = new Book.FictionBook("1234567890", "John Doe", true, "Fiction Test Book", "Fiction", "Adventure", "Adult");
        String expectedDetails = "\nTitle: Fiction Test Book, Author: John Doe, ISBN: 1234567890, Genre: Fiction, Available: Yes, Type: Fiction, Theme: Adventure, Audience: Adult";
        assertEquals(expectedDetails, fictionBook.getDetails());
    }
	
	@Test 
	public void testNonFictionBookGetDetails() {
		Book.NonfictionBook nonfictionbook=new Book.NonfictionBook("1001001011", "Alice Walkers", true, "Test-Nonfiction", "Non-Fiction", "Math", "General");
		String detail= "\nTitle: Test-Nonfiction, Author: Alice Walkers, ISBN: 1001001011, Genre: Non-Fiction, Available: Yes, Type: Non-Fiction, Subject Area: Math, Audience: General";
		assertEquals(detail,nonfictionbook.getDetails());
		
	}
	

	@Test
    public void testAddMultipleAuthors() {
        Book book = new Book("1234567890", "John Doe", true, "Test Book", "Fiction");
        Book.Author author1 = book.new Author("Jane", "Doe", "JD");
        Book.Author author2 = book.new Author("Alice", "Smith", "AS");
        book.addAuthor(author1);
        book.addAuthor(author2);
        List<Book.Author> authors = book.getAuthors();
        assertEquals(2, authors.size());
        assertEquals("Jane", authors.get(0).getFirstName());
        assertEquals("Alice", authors.get(1).getFirstName());
    }
	


}