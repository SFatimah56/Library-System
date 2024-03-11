package com.library.management;
import java.util.Scanner;

import java.util.Date;


public class LibraryUI {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                registerPatron();
                break;
            case 3:
                checkOutBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                library.printAllBooks();
                break;
            case 6:
                library.printAllPatrons();
                break;
            case 7:
                printPatronsBorrowingHistory();
                break;
            case 8:    
                library.printAllTransactions();
                break;
            case 9:
                running = false;
                System.out.println("Exiting the system. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. Add Book");
        System.out.println("2. Register Patron");
        System.out.println("3. Check Out Book");
        System.out.println("4. Return Book");
        System.out.println("5. Print All Books");
        System.out.println("6. Print All Patrons");
        System.out.println("7. Print Patron's Borrowing History");
        System.out.println("8. Print All Library Transactions");
        System.out.println("9. Quit");
        System.out.print("Enter your choice (1-8): ");
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        
        System.out.print("Is the book fiction or non-fiction? (Enter 'fiction', 'non-fiction'): ");
        String type = scanner.nextLine().toLowerCase();
        
        Book book;
        
        if ("fiction".equals(type)) {
            // Collect additional information specific to FictionBook
            System.out.print("Enter the theme for the fiction book: ");
            String theme = scanner.nextLine();
            System.out.print("Enter the audience for the fiction book: ");
            String audience = scanner.nextLine();
            
            book = new Book.FictionBook(isbn, author, true, title, genre, theme, audience); 
            System.out.println("Fiction book added successfully.");
        } else if ("non-fiction".equals(type)) {
            // Collect additional information specific to NonfictionBook
            System.out.print("Enter the subject area for the non-fiction book: ");
            String subjectArea = scanner.nextLine();
            System.out.print("Enter the audience for the non-fiction book: ");
            String audience = scanner.nextLine();
            
            book = new Book.NonfictionBook(isbn, author, true, title, genre, subjectArea, audience);
            System.out.println("Non-fiction book added successfully.");
        } else {
            book = new Book(isbn, author, true, title, genre); // Fallback for general Book
            System.out.println("Book added successfully.");
        }
        library.addBook(book);
    }

    private static void registerPatron() {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patron email: ");
        String email = scanner.nextLine();
        System.out.print("Enter patron phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Is the patron a student or faculty? (Enter 'student' or 'faculty'): ");
        String type = scanner.nextLine().toLowerCase();
        Patron patron;

        if ("student".equals(type)) {
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();
            patron = new Patron.StudentPatron(name, email, phoneNumber, studentId);
        } else if ("faculty".equals(type)) {
            System.out.print("Enter department: ");
            String department = scanner.nextLine();
            patron = new Patron.FacultyPatron(name, email, phoneNumber, department);
        } else {
            // Default to a general Patron if not a student or faculty
            System.out.println("Registering as a general patron.");
            patron = new Patron(name, email, phoneNumber);
        }
     
        library.registerPatron(patron);
        if (patron instanceof Patron.StudentPatron) {
        	System.out.println("Studen Patron registered successfully.");
            System.out.println("Student ID: " + ((Patron.StudentPatron) patron).getStudentID());
        } 
        else if (patron instanceof Patron.FacultyPatron) {
        	System.out.println("Faculty Patron registered successfully.");
            System.out.println("Department: " + ((Patron.FacultyPatron) patron).getDepartment());
        }
        else {
        	System.out.println("Patron registered successfully.");
        }
        System.out.println("Name: " + patron.getName());
        System.out.println("Library ID #: " + patron.getID());
    }

    private static void checkOutBook() {
        System.out.print("Enter patron ID: ");
        Integer patronId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter ISBN of the book to check out: ");
        String isbn = scanner.nextLine();
        library.checkoutBook(isbn, patronId, new Date());
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn);
    }
    
    public static void printPatronsBorrowingHistory() {
        System.out.print("Enter patron ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Patron patron = library.findPatronByID(id);
        if (patron != null) {
            patron.printBorrowingHistory();
        } else {
            System.out.println("Patron not found.");
        }
    }
    

}