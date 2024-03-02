package com.library.management;
import java.util.ArrayList;
import java.util.List;

import com.library.books.Book;
public class Library {
	private List<Book> books = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    // Method to add a book
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to remove a book
    public boolean removeBook(String ISBN) {
        return books.removeIf(book -> book.getISBN().equals(ISBN));
    }

    // Method to register a patron
    public void registerPatron(Patron patron) {
        patrons.add(patron);
    }
     //Checking out book
    public void checkoutBook(String ISBN, int patronID) {
        Book bookToCheckout = null;
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && book.isAvailable()) {
                bookToCheckout = book;
                break; // Stop searching once a matching book is found
            }
        }
        
        Patron patronWhoWantsToCheckout = null;
        for (Patron patron : patrons) {
            if (patron.getID() == patronID) {
                patronWhoWantsToCheckout = patron;
                break; // Stop searching once the matching patron is found
            }
        }
        
        if (bookToCheckout != null && patronWhoWantsToCheckout != null) {
            bookToCheckout.setAvailable(false); // Mark the book as checked out
            Transaction transaction = new Transaction(bookToCheckout, patronWhoWantsToCheckout);
            transactions.add(transaction);
            // Optionally, you might also want to add the transaction to the patron's borrowing history here
            patronWhoWantsToCheckout.addTransaction(transaction);
        }
    }
    
    
    //Returning book
    public void returnBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                book.setAvailable(true); // Mark the book as available again
                break; // No need to search further
            }
        }
    }
}
