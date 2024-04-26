
package com.library.management;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Date;

import com.library.management.EventsManager.Event;
import com.library.management.GroupManager.Group;
import com.library.management.UserManager.User;
public class SocialNetwork {
	private static SocialNetwork instance = null;  // Single private instance
  EventsManager eventmanager;
	UserManager usermanager;
	GroupManager groupmanager;
	Scanner scanner;
	
  private SocialNetwork() {
    setupdatabase(); 
    this.eventmanager = new EventsManager();
    this.usermanager = new UserManager();
    this.groupmanager = new GroupManager();
    getEvents();  
    getUsers();   
    getGroups(); 
    this.scanner = new Scanner(System.in); 
}

// Public method to access the instance
public static SocialNetwork getInstance() {
    if (instance == null) {
        instance = new SocialNetwork();
    }
    return instance;
}

	
	private void setupdatabase() {
		  try
	        (
	          // create a database connection
	          Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
	          Statement statement = connection.createStatement();
	        )
	        {
	        	String setUp = "CREATE TABLE IF NOT EXISTS Events (\n"
	                    + "  id integer,\n"
	                    + "  title text NOT NULL,\n"
	                    + "  description text NOT NULL,\n"
	                    + "  eventdate date NOT NULL,\n"
	                    + "  eventtype text NOT NULL\n"
	                    + ");";
	            
	            statement.execute(setUp);

	        	String setUp2 = "CREATE TABLE IF NOT EXISTS Users (\n"
	        			+ " id integer,\n"
	        			+ " username text NOT NULL,\n"
	        			+ " favoriteBookISBN text,\n"
	        			+ " readingHabits text,\n"
	        			+ " genres text,\n"
	        			+ " followerIDs text,\n"
	        			+ " followingIDs text\n"
	        			+ ");";
	            
	            statement.execute(setUp2);
	            
	            
	        	String setUp3 = "CREATE TABLE IF NOT EXISTS Groups (\n"
	                    + "  name text,\n"
	                    + "  discussionIDs text,\n"
	                    + "  members text\n"
	                    + ");";
	            
	            statement.execute(setUp3);
	            
	            // Table for Messages
	            String setUpMessages = "CREATE TABLE IF NOT EXISTS Messages (\n"
	                    + "  message_id integer PRIMARY KEY AUTOINCREMENT,\n"
	                    + "  sender_id integer NOT NULL,\n"
	                    + "  receiver_id integer NOT NULL,\n"
	                    + "  content text NOT NULL,\n"
	                    + "  timestamp datetime DEFAULT CURRENT_TIMESTAMP,\n"
	                    + "  FOREIGN KEY (sender_id) REFERENCES Users(id),\n"
	                    + "  FOREIGN KEY (receiver_id) REFERENCES Users(id)\n"
	                    + ");";
	            statement.execute(setUpMessages);

	            // Table for Discussions
	            String setUpDiscussions = "CREATE TABLE IF NOT EXISTS Discussions (\n"
	                    + "  discussion_id integer PRIMARY KEY AUTOINCREMENT,\n"
	                    + "  group_name text NOT NULL,\n"
	                    + "  title text NOT NULL,\n"
	                    + "  content text NOT NULL,\n"
	                    + "  timestamp datetime DEFAULT CURRENT_TIMESTAMP,\n"
	                    + "  FOREIGN KEY (group_name) REFERENCES Groups(name)\n"
	                    + ");";
	            statement.execute(setUpDiscussions);
	            
	            // Table for Event Comments
	            String setUpEventComments = "CREATE TABLE IF NOT EXISTS EventComments (\n"
	                + "  comment_id integer PRIMARY KEY AUTOINCREMENT,\n"
	                + "  event_id integer NOT NULL,\n"
	                + "  user_id integer NOT NULL,\n"
	                + "  comment text NOT NULL,\n"
	                + "  timestamp datetime DEFAULT CURRENT_TIMESTAMP,\n"
	                + "  FOREIGN KEY (event_id) REFERENCES Events(id),\n"
	                + "  FOREIGN KEY (user_id) REFERENCES Users(id)\n"
	                + ");";
	            statement.execute(setUpEventComments);

	            // Table for Event RSVPs
	            String setUpEventRSVPs = "CREATE TABLE IF NOT EXISTS EventRSVPs (\n"
	                + "  rsvp_id integer PRIMARY KEY AUTOINCREMENT,\n"
	                + "  event_id integer NOT NULL,\n"
	                + "  user_id integer NOT NULL,\n"
	                + "  status text NOT NULL,\n"
	                + "  timestamp datetime DEFAULT CURRENT_TIMESTAMP,\n"
	                + "  FOREIGN KEY (event_id) REFERENCES Events(id),\n"
	                + "  FOREIGN KEY (user_id) REFERENCES Users(id)\n"
	                + ");";
	            statement.execute(setUpEventRSVPs);
	            
	            //Table for Follows and Followers
	            String setUpUserFollows = "CREATE TABLE IF NOT EXISTS UserFollows (\n"
	            	    + "  follower_id integer,\n"
	            	    + "  followed_id integer,\n"
	            	    + "  PRIMARY KEY (follower_id, followed_id),\n"
	            	    + "  FOREIGN KEY (follower_id) REFERENCES Users(id),\n"
	            	    + "  FOREIGN KEY (followed_id) REFERENCES Users(id)\n"
	            	    + ");";
	            statement.execute(setUpUserFollows);      
	        }
	        catch(SQLException e)
	        {
	          e.printStackTrace(System.err);
	        }
	}
	
	private void getGroups() {
	      // NOTE: Connection and Statement are AutoClosable.
	      //       Don't forget to close them both in order to avoid leaks.
	      try
	      (
	        // create a database connection
	        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
	        Statement statement = connection.createStatement();
	      )
	      {
	        statement.setQueryTimeout(30);  // set timeout to 30 sec.
	        ResultSet resultSet = statement.executeQuery("select * from Groups");
	        while(resultSet.next())
	        {
	          // read the result set        
	        String name = resultSet.getString("name");
	        boolean dup = false;
	        
	        for(Group i : groupmanager.getAllGroups()) {
	        	if(i.getName() == name) {
	        		dup = true;
	        	}
	        }
	        	
	        if(!dup) {
	        groupmanager.createGroup(
	      			name,
	                new ArrayList<String>(Arrays.asList(resultSet.getString("discussionIDs").split(","))),
	                new ArrayList<String>(Arrays.asList(resultSet.getString("members").split(",")))
	        		);
	        }
	      	
	        }
	      }
	      catch(SQLException e)
	      {
	        // if the error message is "out of memory",
	        // it probably means no database file is found
	        e.printStackTrace(System.err);
	      }
	}

	public void updateEvents() {
		ArrayList<Event> events = eventmanager.getAllEvents();

        try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
          Statement statement = connection.createStatement();
        )
        {
        	String setUp = "CREATE TABLE IF NOT EXISTS Events (\n"
                    + "  id integer,\n"
                    + "  title text NOT NULL,\n"
                    + "  description text NOT NULL,\n"
                    + "  eventdate date NOT NULL,\n"
                    + "  eventtype text NOT NULL\n"
                    + ");";
            
            statement.execute(setUp);

            
            String sql2 = "INSERT INTO Events(id, title, description, eventdate, eventtype) VALUES(?, ?, ?, ?, ?) ";

            for(Event i : events) {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, i.getEventId());
                pstmt.setString(2, i.getTitle());
                pstmt.setString(3, i.getDescription());
                pstmt.setTimestamp(4, i.getEventDate());
                pstmt.setString(5, i.getEventType());            
                pstmt.execute();
            }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
	}
	
	public void updateUsers() {
		ArrayList<User> users = usermanager.getAllUsers();
        try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
          Statement statement = connection.createStatement();
        )
        {
        	String setUp = "CREATE TABLE IF NOT EXISTS Users (\n"
        			+ " id integer,\n"
        			+ " username text NOT NULL,\n"
        			+ " favoriteBookISBN text,\n"
        			+ " readingHabits text,\n"
        			+ " genres text,\n"
        			+ " followerIDs text,\n"
        			+ " followingIDs text\n"
        			+ ");";
            
            statement.execute(setUp);
            
            String sql2 = "INSERT INTO Users(id, username, favoriteBookISBN, readingHabits, genres, followerIDs, followingIDs) VALUES(?, ?, ?, ?, ?, ?, ?) ";

            for(User i : users) {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setInt(1, i.getID());
                pstmt.setString(2, i.getUsername());
                pstmt.setString(3, 
                		String.join(",",i.getFavoriteBookISBNs())
                		);
                pstmt.setString(3, 
                		String.join(",",i.getFavoriteBookISBNs())
                		);
                pstmt.setString(4, 
                		String.join(",",i.getReadingHabits())
                		);
                pstmt.setString(5, 
                		String.join(",",i.getGenres())
                		);
                pstmt.setString(6, 
                		String.join(",",i.getFollowers())
                		);
                pstmt.setString(7, 
                		String.join(",",i.getFollowings())
                		);
               
                pstmt.execute();
            }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
	}
	
	public void createEvent()
	{	
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter desc: ");
        String description = scanner.nextLine();
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        
        eventmanager.createEvent(title, description, date, type);
        updateEvents();
	}
	
	public void createUser()
	{		
        System.out.print("Enter username: ");
        String username = scanner.nextLine();   
        usermanager.createUser(username);
        updateUsers();
	}

	public void createGroup() {
        System.out.print("Enter Group name: ");
        String name = scanner.nextLine();
        
        groupmanager.createGroup(name);
        
        updateGroups();        
	}
	
    private void updateGroups() {
    	ArrayList<Group> groups = groupmanager.getAllGroups();

        try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
          Statement statement = connection.createStatement();
        )
        {
        	String setUp = "CREATE TABLE IF NOT EXISTS Groups (\n"
                    + "  name text,\n"
                    + "  discussionIDs text,\n"
                    + "  members text\n"
                    + ");";
            
            statement.execute(setUp);
            
            String sql2 = "INSERT INTO Groups(name, discussionIDs, members) VALUES(?, ?, ?) ";

            for(Group i : groups) {
                PreparedStatement pstmt = connection.prepareStatement(sql2);
                pstmt.setString(1, i.getName());
                pstmt.setString(2, 
                		String.join(",",i.getDiscussions())
                		);  
                pstmt.setString(3, 
                		String.join(",",i.getMembers())
                		);                 
                pstmt.execute();
            }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }	
	}

	public void getEvents()
    { 	  
      // NOTE: Connection and Statement are AutoClosable.
      //       Don't forget to close them both in order to avoid leaks.
      try
      (
        // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement statement = connection.createStatement();
      )
      {
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        ResultSet rs = statement.executeQuery("select id, title, description, eventdate, eventtype from Events");
        while(rs.next())
        {
          // read the result set      
        String title = rs.getString("title");
        boolean dup = false;
        
        for(Event i : eventmanager.getAllEvents()) {
        	if(i.getTitle() == title) {
        		dup = true;
        	}
        }
        	
        if(!dup) {
        eventmanager.createEvent(
      			rs.getString("title"),
      			rs.getString("description"),
      			rs.getTimestamp("eventdate"),
      			rs.getString("eventtype")
      	);
        }   	
        }
      }
      catch(SQLException e)
      {
        // if the error message is "out of memory",
        // it probably means no database file is found
        e.printStackTrace(System.err);
      }      
    }
       
    public void getUsers()
    {
  	  
      // NOTE: Connection and Statement are AutoClosable.
      //       Don't forget to close them both in order to avoid leaks.
      try
      (
        // create a database connection
        Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement statement = connection.createStatement();
      )
      {
        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        ResultSet resultSet = statement.executeQuery("select * from Users");
        while(resultSet.next())
        {
          // read the result set
        
        int id = resultSet.getInt("id");
        boolean dup = false;
        
        for(User i : usermanager.getAllUsers()) {
        	if(i.getID() == id) {
        		dup = true;
        	}
        }
        	
        if(!dup) {
        usermanager.createUser(
        		id,
        		resultSet.getString("username"),
                new ArrayList<String>(Arrays.asList(resultSet.getString("favoriteBookISBN").split(","))),
                new ArrayList<String>(Arrays.asList(resultSet.getString("readingHabits").split(","))),
                new ArrayList<String>(Arrays.asList(resultSet.getString("genres").split(","))),
                new ArrayList<String>(Arrays.asList(resultSet.getString("followerIDs").split(","))),
                new ArrayList<String>(Arrays.asList(resultSet.getString("followingIDs").split(",")))
      	);
        }
      	
        }
      }
      catch(SQLException e)
      {
        // if the error message is "out of memory",
        // it probably means no database file is found
        e.printStackTrace(System.err);
      }
      
    }
    public void sendMessage(int senderId, int receiverId, String content) {
        String sql = "INSERT INTO Messages(sender_id, receiver_id, content) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, senderId);
            pstmt.setInt(2, receiverId);
            pstmt.setString(3, content);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }
    public void viewMessages(int userId) {
        String sql = "SELECT M.content, U.username as sender, M.timestamp FROM Messages M JOIN Users U ON M.sender_id = U.id WHERE M.receiver_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("From: " + rs.getString("sender") + " - Message: " + rs.getString("content") + " [" + rs.getString("timestamp") + "]");
            }
        } catch (SQLException e) {
            System.out.println("Error viewing messages: " + e.getMessage());
        }
    }
    public void addDiscussionToGroup(String groupName, String title, String content) {
        String sql = "INSERT INTO Discussions(group_name, title, content) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, groupName);
            pstmt.setString(2, title);
            pstmt.setString(3, content);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding discussion: " + e.getMessage());
        }
    }
    public void listDiscussionsInGroup(String groupName) {
        String sql = "SELECT title, content, timestamp FROM Discussions WHERE group_name = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, groupName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Title: " + rs.getString("title") + " - Content: " + rs.getString("content") + " [" + rs.getString("timestamp") + "]");
            }
        } catch (SQLException e) {
            System.out.println("Error listing discussions: " + e.getMessage());
        }
    }
    public void addRSVP(int eventId, int userId, String status) {
        String sql = "INSERT INTO EventRSVPs(event_id, user_id, status) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, status);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding RSVP: " + e.getMessage());
        }
    }
    public void addEventComment(int eventId, int userId, String comment) {
        String sql = "INSERT INTO EventComments(event_id, user_id, comment) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, eventId);
            pstmt.setInt(2, userId);
            pstmt.setString(3, comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding comment: " + e.getMessage());
        }
    }

	public void listAllEvents() {
		//eventmanager.listAllEvents();
	    // SQL query to fetch events along with comments and RSVPs
	    String query = "SELECT E.id, E.title, E.description, E.eventdate, E.eventtype, "
	                   + "U.username AS comment_user, C.comment, "
	                   + "R.status, V.username AS rsvp_user "
	                   + "FROM Events E "
	                   + "LEFT JOIN EventComments C ON E.id = C.event_id "
	                   + "LEFT JOIN Users U ON C.user_id = U.id "
	                   + "LEFT JOIN EventRSVPs R ON E.id = R.event_id "
	                   + "LEFT JOIN Users V ON R.user_id = V.id "
	                   + "ORDER BY E.id, C.timestamp, R.timestamp;";

	    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        System.out.println("All Events with Comments and RSVPs:");
	        while (rs.next()) {
	            // Print each event along with associated comments and RSVPs
	            System.out.println("Event ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") +
	                               ", Description: " + rs.getString("description") +
	                               ", Date: " + rs.getString("eventdate") + ", Type: " + rs.getString("eventtype"));
	            String commenter = rs.getString("comment_user");
	            if (commenter != null) {
	                System.out.println("\tComment by " + commenter + ": " + rs.getString("comment"));
	            }
	            String rsvpUser = rs.getString("rsvp_user");
	            if (rsvpUser != null) {
	                System.out.println("\tRSVP by " + rsvpUser + ": " + rs.getString("status"));
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error listing events: " + e.getMessage());
	    }
	}
	public void followUser(int followerId, int followedId) {
	    String sql = "INSERT INTO UserFollows (follower_id, followed_id) VALUES (?, ?)";
	    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, followerId);
	        pstmt.setInt(2, followedId);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("Error following user: " + e.getMessage());
	    }
	}

	public void listAllUsers() {
		//usermanager.listAllUsers();
	    String sqlUsers = "SELECT id, username FROM Users";
	    String sqlFollowers = "SELECT U.username AS follower FROM UserFollows UF JOIN Users U ON UF.follower_id = U.id WHERE UF.followed_id = ?";
	    String sqlFollowing = "SELECT U.username AS following FROM UserFollows UF JOIN Users U ON UF.followed_id = U.id WHERE UF.follower_id = ?";

	    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
	         Statement stmtUsers = conn.createStatement();
	         ResultSet rsUsers = stmtUsers.executeQuery(sqlUsers)) {
	        
	        while (rsUsers.next()) {
	            int userId = rsUsers.getInt("id");
	            String username = rsUsers.getString("username");
	            List<String> followers = new ArrayList<>();
	            List<String> followings = new ArrayList<>();

	            // Fetch followers
	            try (PreparedStatement pstmtFollowers = conn.prepareStatement(sqlFollowers)) {
	                pstmtFollowers.setInt(1, userId);
	                ResultSet rsFollowers = pstmtFollowers.executeQuery();
	                while (rsFollowers.next()) {
	                    followers.add(rsFollowers.getString("follower"));
	                }
	            }

	            // Fetch following
	            try (PreparedStatement pstmtFollowing = conn.prepareStatement(sqlFollowing)) {
	                pstmtFollowing.setInt(1, userId);
	                ResultSet rsFollowing = pstmtFollowing.executeQuery();
	                while (rsFollowing.next()) {
	                    followings.add(rsFollowing.getString("following"));
	                }
	            }

	            // Print user details
	            System.out.println("User: " + username + " (ID: " + userId + ")");
	            System.out.println("\tFollowers: " + (followers.isEmpty() ? "None" : String.join(", ", followers)));
	            System.out.println("\tFollowing: " + (followings.isEmpty() ? "None" : String.join(", ", followings)));
	        }
	    } catch (SQLException e) {
	        System.out.println("Error listing users: " + e.getMessage());
	    }
	}

	public void listAllGroups() {
		groupmanager.listAllGroups();
	}
}
