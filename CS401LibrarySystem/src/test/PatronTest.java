package test;

package test;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    //attributes
	private static int lastId = 0;
    private int ID;
    private String name;
    private String email;
    private String phoneNumber;
    protected boolean isStudent;
    protected boolean isFaculty;
    private List<Transaction> borrowingHistory;
    
    //constructor for existing patron
    public Patron(int ID, String name, String email, String phoneNumber, List<Transaction> borrowingHistory) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.borrowingHistory = new ArrayList<>(borrowingHistory);
    }
    //constructor for new patron
    public Patron(String name, String email, String phoneNumber) {
        this.ID = ++lastId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
     public List<Transaction> getBorrowingHistory() {
        return borrowingHistory;
    }
    public void setBorrowingHistory(List<Transaction> bH) {
        this.borrowingHistory = bH;
    }
    

    //methods
    // Update the name and contact information by overriding old details
    public void updateDetails(String name, String email, String phoneNumber) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
        if (email != null && !email.isEmpty()) {
            this.email = email;
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            this.phoneNumber = phoneNumber;
        }
    }
    
    public void addTransaction(Transaction transaction) {
        borrowingHistory.add(transaction);
    }
    
    //added to print patrons borrowing history
    public void printBorrowingHistory() {
        System.out.println("Borrowing history for " + getName() + ":");
        if (borrowingHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            borrowingHistory.forEach(transaction -> System.out.println("Transaction ID: " + transaction.getTransactionID() + ", Book: " + transaction.getBook().getTitle() + ", Date: " + transaction.getCheckoutDate()));
        }
    }

	// StudentPatron inherits from Patron
    public static class StudentPatron extends Patron {
        private String studentID;
        
        //constructor
        public StudentPatron(String name,  String email, String phoneNumber, String studentID) {
            super(name, email, phoneNumber);
            this.studentID = studentID;
            this.isStudent = true;
        }
        
        //getters & setters
        public void setStudentID(String s) {
            this.studentID = s;
        }
        
        public String getStudentID() {
            return studentID;
        }
        
        public boolean isFaculty() {
    		return isFaculty;
    	}
        
    }
    
 // FacultyPatron inherits from Patron
    public static class FacultyPatron extends Patron {
        private String department;
        
        //constructor
        public FacultyPatron(String name,  String email, String phoneNumber, String department) {
            super(name, email, phoneNumber);
            this.department = department;
            this.isFaculty = true;
            
        }
        
        //getters & setters
        public void setDepartment(String d) {
            this.department = d;
        }
        
        public String getDepartment() {
            return department;
        }
        
        public boolean isFaculty() {
    		return isFaculty;
    	}
    }
    
  

}
