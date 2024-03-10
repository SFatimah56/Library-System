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
        System.out.println("5. Quit");
        System.out.print("Enter your choice (1-5): ");
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        Book book = new Book();
        book.setTitle(title);
        book.setISBN(isbn);
        book.setGenre(genre);

        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    private static void registerPatron() {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patron contact information: ");
        String contact = scanner.nextLine();


        Patron patron = new Patron(0, name, contact);

        library.registerPatron(patron);
        System.out.println("Patron registered successfully.");
    }

    private static void checkOutBook() {
        System.out.print("Enter patron ID: ");
        Integer patronId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter ISBN of the book to check out: ");
        String isbn = scanner.nextLine();
        library.checkoutBook(isbn, patronId, new Date());
        System.out.println("Book checked out successfully.");
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        library.returnBook(isbn);
        System.out.println("Book returned successfully.");
    }

}