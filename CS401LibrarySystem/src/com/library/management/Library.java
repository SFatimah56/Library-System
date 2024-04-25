package com.library.management;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private static Library instance; // Single private instance

    // Private constructor to prevent external instantiation
    private Library() {
    }

    // Public method to provide access to the instance
    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    // Method to add a book
    public void addBook(Book book) {
        // Check if a book with the same ISBN already exists in the library
        for (Book existingBook : books) {
            if (existingBook.getISBN().equals(book.getISBN())) {
                System.out.println("A book with ISBN " + book.getISBN() + " already exists. Not adding the book.");
                return; // Exit the method without adding the new book
            }
        }

        // If no book with the same ISBN exists, add the new book to the library
        books.add(book);
    }

    // Method to remove a book
    public boolean removeBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return books.remove(book);
            }
        }
        return false; // Book with the given ISBN not found
    }

    // Method to register a patron
    public void registerPatron(Patron patron) {
        patrons.add(patron);
    }

    // Method to checkout book
    public void checkoutBook(String ISBN, int patronID, Date checkoutDate) {
        Book bookToCheckout = findBookByISBN(ISBN);
        Patron patronWhoWantsToCheckout = findPatronByID(patronID);

        if (bookToCheckout == null && patronWhoWantsToCheckout == null) {
            System.out.println("Error: Book not found and Patron not found.");
            return;
        }
        if (bookToCheckout == null) {
            System.out.println("Error: Book not found.");
            return;
        }

        if (patronWhoWantsToCheckout == null) {
            System.out.println("Error: Patron not found.");
            return;
        }

        if (!bookToCheckout.isAvailable()) {
            System.out.println("Error: Book is currently unavailable.");
            return;
        }

        bookToCheckout.setAvailable(false); // Mark the book as checked out
        Transaction transaction = new Transaction(patronWhoWantsToCheckout, bookToCheckout, checkoutDate);
        transactions.add(transaction); //add this checkout to transaction list
        patronWhoWantsToCheckout.addTransaction(transaction); //add this transaction to patrons checkout history
        System.out.println("Book checked out successfully.");
    }
 

    // Method to return book
    public void returnBook(String ISBN) {
        Book bookToReturn = findBookByISBN(ISBN);
        if (bookToReturn != null) {
            bookToReturn.setAvailable(true); // Mark the book as available again
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Error: Book not found.");
        }
    }


    // Utility method to find a book by ISBN
    public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null; // Book with the given ISBN not found
    }

    // Utility method to find a patron by ID
    public Patron findPatronByID(int ID) {
        for (Patron patron : patrons) {
            if (patron.getID() == ID) {
                return patron;
            }
        }
        return null; // Patron with the given ID not found
    }
   
    // Method to print all books in the library
    public void printAllBooks() {
    	if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book book : books) {
            System.out.println(book.getDetails()); // Uses polymorphism to get the correct details
        }
    }
    
    //Method to print all Patrons
    public void printAllPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("No patrons registered.");
        } else {
            System.out.println("\nList of all patrons:");
            for (Patron patron : patrons) {
                String patronType = "General Patron"; // Default type
                if (patron instanceof Patron.StudentPatron) {
                    patronType = "Student";
                } else if (patron instanceof Patron.FacultyPatron) {
                    patronType = "Faculty";
                }

                System.out.println("ID: " + patron.getID() + ", Name: " + patron.getName() + ", Email: " + patron.getEmail() + ", Phone #: " + patron.getPhoneNumber() + ", Type: " + patronType);
            }
        }
    }
    
    // Method to print all Transactions
    public void printAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions have been recorded.");
        } else {
            System.out.println("\nList of all transactions:");
            for (Transaction transaction : transactions) {
                String bookTitle = transaction.getBook().getTitle();
                String patronName = transaction.getPatron().getName();
                String checkoutDate = transaction.getCheckoutDate().toString(); // Format date as needed
                String transactionID = transaction.getTransactionID(); // Assuming you have this method in Transaction

                System.out.println("Transaction ID: " + transactionID + ", Book: " + bookTitle + ", Patron: " + patronName + ", Checkout Date: " + checkoutDate);
            }
        }
    }
    
    public List<Book> getBooks(){
    	return this.books;
    }
    
    public List<Patron> getPatrons() {
        return this.patrons;
    }

}