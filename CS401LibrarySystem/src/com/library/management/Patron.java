package com.library.management;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    //attributes
    private int ID;
    private String name;
    private String contactInfo;
    private List<Transaction> borrowingHistory;
    
    //constructors
    //constructor for existing patron
    public Patron(int ID, String name, String contactInfo, List<Transaction> borrowingHistory) {
        this.ID = ID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowingHistory = new ArrayList<>(borrowingHistory);
    }
    //constructor for new patron
    public Patron(int ID, String name, String contactInfo) {
        this.ID = ID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowingHistory = new ArrayList<>();
    }

    //setters & getters
    public void setID(int d) {
        this.ID = d;
    }
    public int getID() {
        return ID;
    }
    
    public void setName(String n) {
        this.name = n;
    }
    public String getName() {
        return name;
    }
    public void setContactInfo(String c) {
        this.contactInfo = c;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    
    public List<Transaction> getBorrowingHistory() {
        return borrowingHistory;
    }

    public void setBorrowingHistory(List<Transaction> bH) {
        this.borrowingHistory = bH;
    }

    //methods
    public void updateDetails(String name, String contactInfo) {
        // Update the name and contact information by overriding old details
        if (name != null) {
            this.name = name;
        }
        else if (contactInfo != null) {
            this.contactInfo = contactInfo;
        }
    }
    
    public void addTransaction(Transaction transaction) {
        borrowingHistory.add(transaction);
    }
    
    // StudentPatron inherits from Patron
    public static class StudentPatron extends Patron {
        private String studentID;
        
        //constructor
        public StudentPatron(int ID, String name, String contactInfo, String studentID) {
            super(ID, name, contactInfo);
            this.studentID = studentID;
        }
        
        //getters & setters
        public void setStudentID(String s) {
            this.studentID = s;
        }
        
        public String getStudentID() {
            return studentID;
        }
    }
    
 // FacultyPatron inherits from Patron
    public static class FacultyPatron extends Patron {
        private String department;
        
        //constructor
        public FacultyPatron(int ID, String name, String contactInfo, String department) {
            super(ID, name, contactInfo);
            this.department = department;
        }
        
        //getters & setters
        public void setDepartment(String d) {
            this.department = d;
        }
        
        public String getDepartment() {
            return department;
        }
    }

}