package com.library.management;
import java.util.Date;

public class Transaction {
    private static int transactionCount = 0; // Static variable to generate unique transaction IDs
    private final String transactionID;
    private final Patron patron;
    private final Book book;
    private final Date checkoutDate;
    private Date returnDate;

    //Constructor
    public Transaction(Patron patron, Book book, Date checkoutDate) {
        if (patron == null || book == null || checkoutDate == null) {
            throw new IllegalArgumentException("Patron, book, and checkout date cannot be null");
        }
        this.transactionID = generateTransactionID();
        this.patron = patron;
        this.book = book;
        this.checkoutDate = new Date(checkoutDate.getTime()); // Create a defensive copy of the date
        this.returnDate = null; // Set to null until the book is returned
    }

    // Getter for transaction ID
    public String getTransactionID() {
        return transactionID;
    }

    // Getter for patron
    public Patron getPatron() {
        return patron;
    }

    // Getter for book
    public Book getBook() {
        return book;
    }

    // Getter for checkout date
    public Date getCheckoutDate() {
        return new Date(checkoutDate.getTime()); // Return a defensive copy of the date
    }

    // Getter for return date
    public Date getReturnDate() {
        return returnDate != null ? new Date(returnDate.getTime()) : null; // Return a defensive copy of the date if not null
    }

    // Method to set return date
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate != null ? new Date(returnDate.getTime()) : null; // Set a defensive copy of the date if not null
    }

    // Method to generate unique transaction ID
    private synchronized String generateTransactionID() {
        return "Transaction" + (++transactionCount);
    }

    // Method to get transaction details
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Transaction ID: ").append(transactionID).append("\n");
        details.append("Patron: ").append(patron.getName()).append("\n");
        details.append("Book: ").append(book.getTitle()).append("\n");
        details.append("Checkout Date: ").append(checkoutDate.toString()).append("\n");
        if (returnDate != null) {
            details.append("Return Date: ").append(returnDate.toString());
        } else {
            details.append("Return Date: Not returned yet");
        }
        return details.toString();
    }
}