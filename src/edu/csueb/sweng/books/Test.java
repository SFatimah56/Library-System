import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LibraryTest {

    @Test
    public void testPrintAllBooks() {
        // Arrange
        Library library = new Library();
        Book book1 = new Book("1234567890", "Charlie Puth", true, "Charlie Puth Autobioography", "Non-Fiction");
        Book book2 = new Book("0987654321", "Sally Tillers", true, "How to Sing", "General");
        library.addBook(book1);
        library.addBook(book2);

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        library.printAllBooks();

        // Assert
        String expectedOutput = "Title: Charlie Puth Autobioography, Author: Charlie Puth, ISBN: 1234567890, Genre: Non-Fiction, Available: Yes\n" +
        
                                "Title: How to Sing, Author: Sally Tillers, ISBN: 0987654321, Genre: General, Available: Yes\n";
        assertEquals(expectedOutput, outContent.toString());

        // Restore the original System.out
        System.setOut(System.out);
    }
}
