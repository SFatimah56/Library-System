package com.library.management;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();

    //Method to add a book
    public void addBook(Book book) {
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

        if (bookToCheckout != null && patronWhoWantsToCheckout != null && bookToCheckout.isAvailable()) {
            bookToCheckout.setAvailable(false); // Mark the book as checked out
            Transaction transaction = new Transaction(patronWhoWantsToCheckout, bookToCheckout, checkoutDate);
            transactions.add(transaction);
            patronWhoWantsToCheckout.addTransaction(transaction);
        }
    }

    // Method to return book
    public void returnBook(String ISBN) {
        Book bookToReturn = findBookByISBN(ISBN);
        if (bookToReturn != null) {
            bookToReturn.setAvailable(true); // Mark the book as available again
        }
    }

    // Utility method to find a book by ISBN
    private Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null; // Book with the given ISBN not found
    }

    // Utility method to find a patron by ID
    private Patron findPatronByID(int ID) {
        for (Patron patron : patrons) {
            if (patron.getID() == ID) {
                return patron;
            }
        }
        return null; // Patron with the given ID not found
    }
}