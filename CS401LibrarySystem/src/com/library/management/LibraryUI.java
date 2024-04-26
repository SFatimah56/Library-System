package com.library.management;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;


public class LibraryUI {
    private static Library library = Library.getInstance();  //
    private static Scanner scanner = new Scanner(System.in);
    private static SocialNetwork socialnetwork = SocialNetwork.getInstance();

    public static void main(String[] args) {
        while(true) {
            System.out.println("\nEnter 1 for Library management and 2 for social network and 3 to quit the program:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            if(choice == 1) {
            	libraryManagement();
            }
            else if (choice == 2){
            	socialNetwork();
            }
            else if (choice == 3) {
            	break;
            }
        }      
       System.out.print("Program Ending...");
    }
    
    private static void socialNetwork() {
        	boolean running = true;
        	int choice;
            while (running) {
            	socialNetworkMenu();
            	try {
                choice = scanner.nextInt();
            	}catch(InputMismatchException ex){
            	choice = 10; 
            	}
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    addGroup();
                    break;
                case 3:
                    addEvents();
                    break;
                case 4:
                	printAllUsers();
                    break;
                case 5:
                	printAllGroups();
                    break;
                case 6:    
                	printAllEvents();
                    break;
                case 7:
                	socialInteractions();
                	break;
                case 8:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        
    private static void socialInteractions() {
        boolean running = true;
        while (running) {
            socialInteractionsMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after numeric input

            switch (choice) {
            case 1:
            	System.out.print("Enter user ID: ");
                int currentUserId = scanner.nextInt();
                System.out.print("Enter receiver ID: ");
                int receiverId = scanner.nextInt();
                scanner.nextLine(); // Clean up the newline
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                socialnetwork.sendMessage(currentUserId, receiverId, message); // Assumes `currentUserId` is defined
                break;
            case 2:
            	System.out.print("Enter user ID: ");
                currentUserId = scanner.nextInt();
                socialnetwork.viewMessages(currentUserId);
                break;
            case 3:
                System.out.print("Enter Group Name: ");
                String groupName = scanner.nextLine();
                System.out.print("Enter Discussion Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Discussion Content: ");
                String content = scanner.nextLine();
                socialnetwork.addDiscussionToGroup(groupName, title, content);
            	break;
            case 4: 
            	System.out.print("Enter Group Name: ");
                groupName = scanner.nextLine();
                socialnetwork.listDiscussionsInGroup(groupName);
            	break;
            case 5:
                System.out.print("Enter Event ID: ");
                int eventId = scanner.nextInt();
                System.out.print("Enter user ID: ");
                currentUserId = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                System.out.print("Enter your comment: ");
                String comment = scanner.nextLine();
                socialnetwork.addEventComment(eventId, currentUserId, comment);
            	break;
            case 6: 
                System.out.print("Enter Event ID: ");
                eventId = scanner.nextInt();
                System.out.print("Enter user ID: ");
                currentUserId = scanner.nextInt();
                System.out.print("Enter RSVP status (Attending/Interested/Declined): ");
                scanner.nextLine(); // Clear buffer
                String status = scanner.nextLine();
                socialnetwork.addRSVP(eventId, currentUserId, status);
            	break;
            case 7:
                System.out.print("Enter your user ID: ");
                int followerId = scanner.nextInt();
                System.out.print("Enter the user ID of the user you want to follow: ");
                int followedId = scanner.nextInt();
                socialnetwork.followUser(followerId, followedId);
                System.out.println("You are now following user ID: " + followedId);
            case 8:
                running = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
            }
        }
    }

	private static void printAllEvents() {
		socialnetwork.listAllEvents();	
	}

	private static void printAllGroups() {
		socialnetwork.listAllGroups();
	}

	private static void printAllUsers() {
		socialnetwork.listAllUsers();
	}

	private static void addEvents() {
		socialnetwork.createEvent();
	}

	private static void addGroup() {
		socialnetwork.createGroup();		
	}

	private static void addUser() {
		socialnetwork.createUser();
	}

	private static void libraryManagement() {
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
      
    private static void socialNetworkMenu(){
        System.out.println("\nSocial Network System");
        System.out.println("1. Add User");
        System.out.println("2. Add Group");
        System.out.println("3. Add Events");
        System.out.println("4. Print All User");
        System.out.println("5. Print All Groups");
        System.out.println("6. Print All Events");
        System.out.println("7. Social Interaction");
        System.out.println("8. Quit");
        System.out.print("Enter your choice (1-7): ");
    }
    private static void socialInteractionsMenu() {
        System.out.println("\nSocial Interactions Menu");
        System.out.println("1. Send Message");
        System.out.println("2. View Messages");
        System.out.println("3. Add Discussion to Group");
        System.out.println("4. View Discussions in Group");
        System.out.println("5. Comment on Event");
        System.out.println("6. RSVP to Event");
        System.out.println("7. Follow a User");
        System.out.println("8. Return to Main Menu");
        System.out.print("Enter your choice (1-8): ");
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