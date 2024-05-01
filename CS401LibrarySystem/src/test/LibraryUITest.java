package test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.library.management.LibraryUI;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class LibraryUITest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final InputStream originalInputStream = System.in;
    private final PrintStream originalOutputStream = System.out;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void restoreStreams() {
        System.setIn(originalInputStream);
        System.setOut(originalOutputStream);
    }

    @Test
    public void testLibraryManagementChoice() {
        // Simulate user input for choosing library management
        String simulatedUserInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that library management was chosen
        assertTrue(outputStream.toString().contains("Library Management System"));
    }

    @Test
    public void testSocialNetworkChoice() {
        // Simulate user input for choosing social network
        String simulatedUserInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that social network was chosen
        assertTrue(outputStream.toString().contains("Social Network System"));
    }
    
    @Test
    public void testQuitProgram() {
        // Simulate user input for quitting the program
        String simulatedUserInput = "3\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that program ending message is displayed
        assertTrue(outputStream.toString().contains("Program Ending..."));
    }

    @Test
    public void testAddBookIndirectly() {
        // Simulate user input for choosing library management
        String simulatedUserInput = "1\n1\nTest Title\nTest Author\nTest ISBN\nTest Genre\nfiction\nTest Theme\nTest Audience\n9\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that the book was added successfully
        assertTrue(outputStream.toString().contains("Fiction book added successfully."));
    }
    
    @Test
    public void testRegisterPatronIndirectly() {
        // Simulate user input for choosing library management
        String simulatedUserInput = "1\n2\nTest Name\nTest Email\nTest Phone Number\nstudent\nTest Student ID\n9\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that the patron was registered successfully
        assertTrue(outputStream.toString().contains("Student Patron registered successfully."));
    }
    @Test
    public void testCheckOutBookIndirectly() {
        // Simulate user input for choosing library management
        String simulatedUserInput = "1\n3\nTest ISBN\n1\n9\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that the book was checked out successfully
        assertTrue(outputStream.toString().contains("Book checked out successfully."));
    }
    public void testReturnBookIndirectly() {
        // Simulate user input for choosing library management
        String simulatedUserInput = "1\n4\nTest ISBN\n1\n9\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        
        // Call the main method
        LibraryUI.main(null);
        
        // Verify that the book was returned successfully
        assertTrue(outputStream.toString().contains("Book returned successfully."));
    }
}
