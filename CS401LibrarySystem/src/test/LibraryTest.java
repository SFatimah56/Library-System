package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.library.management.Book;
import com.library.management.Library;
import com.library.management.Patron;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Patron patron1;
    private Patron patron2;

    @Before
    public void setUp() {
        library = Library.getInstance();
        book1 = new Book("1234567890", "Book Title 1", true, "Author 1", "fiction");
        book2 = new Book("0987654321", "Book Title 2", false, "Author 2", "non-fiction");
        patron1 = new Patron("John Doe", "john@example.com", "1234567890");
        patron2 = new Patron("Jane Smith", "jane@example.com", "0987654321");
    }

    @Test
    public void testAddBook() {
        library.addBook(book1);
        List<Book> books = library.getBooks();
        
        boolean found = false;
        for (Book book : books) {
            if (book.getISBN().equals(book1.getISBN()) &&
                book.getTitle().equals(book1.getTitle()) &&
                book.getAuthor().equals(book1.getAuthor())) {
                found = true;
                break;
            }
        }
        
        assertTrue("Book should have been added", found);
    }


    @Test
    public void testRemoveBook() {
        library.addBook(book1);
        assertTrue(library.removeBook(book1.getISBN()));
        assertFalse(library.removeBook("nonexistentISBN"));
    }

    @Test
    public void testRegisterPatron() {
        library.registerPatron(patron1);
        List<Patron> patrons = library.getPatrons();
        assertTrue(patrons.contains(patron1));
    }

    @Test
    public void testCheckoutBook() {
        library.addBook(book1);
        library.registerPatron(patron1);
        Date checkoutDate = new Date();
        library.checkoutBook(book1.getISBN(), patron1.getID(), checkoutDate);
        assertFalse(book1.isAvailable());
    }

    @Test
    public void testReturnBook() {
        library.addBook(book1);
        Date checkoutDate = new Date();
        library.checkoutBook(book1.getISBN(), patron1.getID(), checkoutDate);
        library.returnBook(book1.getISBN());
        assertTrue(book1.isAvailable());
    }

    @Test
    public void testPrintAllBooks() {
        library.addBook(book1);
        library.addBook(book2);
        // Redirect System.out to check printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        library.printAllBooks();
        
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains(book1.getISBN()));
        assertTrue(printedOutput.contains(book1.getTitle()));
        assertTrue(printedOutput.contains(book1.getAuthor()));
        assertTrue(printedOutput.contains(book2.getISBN()));
        assertTrue(printedOutput.contains(book2.getTitle()));
        assertTrue(printedOutput.contains(book2.getAuthor()));

        // Reset System.out
        System.setOut(originalOut);
    }

    @Test
    public void testPrintAllPatrons() {
        library.registerPatron(patron1);
        library.registerPatron(patron2);
        // Redirect System.out to check printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        library.printAllPatrons();
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains(patron1.getName()));
        assertTrue(printedOutput.contains(patron2.getName()));
    }

    @Test
    public void testPrintAllTransactions() {
        library.addBook(book1);
        library.registerPatron(patron1);
        Date checkoutDate = new Date();
        library.checkoutBook(book1.getISBN(), patron1.getID(), checkoutDate);
        // Redirect System.out to check printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        library.printAllTransactions();
        String printedOutput = outputStream.toString();
        assertTrue(printedOutput.contains(book1.getTitle()));
        assertTrue(printedOutput.contains(patron1.getName()));
        assertTrue(printedOutput.contains(checkoutDate.toString()));
    }

    @Test
    public void testFindBookByISBN() {
        library.addBook(book1);
        Book foundBook = library.findBookByISBN(book1.getISBN());
        assertNotNull(foundBook); // Ensure found book is not null
        assertEquals(book1.getISBN(), foundBook.getISBN());
        assertEquals(book1.getTitle(), foundBook.getTitle());
        assertEquals(book1.getAuthor(), foundBook.getAuthor());
    }

    @Test
    public void testFindPatronByID() {
        library.registerPatron(patron1);
        Patron foundPatron = library.findPatronByID(patron1.getID());
        assertEquals(patron1, foundPatron);
    }

}
