package com.library.management;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.sql.Date;

import com.library.management.EventsManager.Event;
import com.library.management.GroupManager.Group;
import com.library.management.UserManager.User;
public class SocialNetwork {
	EventsManager eventmanager;
	UserManager usermanager;
	GroupManager groupmanager;
	Scanner scanner;
	
	
	public SocialNetwork(){
		setupdatabase();
		this.eventmanager = new EventsManager();
		this.usermanager = new UserManager();
		this.groupmanager = new GroupManager();
		getEvents();
		getUsers();
		getGroups();
	    scanner = new Scanner(System.in);

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
		// TODO Auto-generated method stub
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


	public void listAllEvents() {
		// TODO Auto-generated method stub
		eventmanager.listAllEvents();
	}


	public void listAllUsers() {
		usermanager.listAllUsers();
		// TODO Auto-generated method stub
		
	}


	public void listAllGroups() {
		groupmanager.listAllGroups();
	}


}
