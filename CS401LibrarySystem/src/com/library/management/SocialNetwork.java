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
import com.library.management.UserManager.User;
public class SocialNetwork {
	EventsManager eventmanager;
	UserManager usermanager;
	Scanner scanner;
	
	
	public SocialNetwork(){
		this.eventmanager = new EventsManager();
		this.usermanager = new UserManager();
		getEvents();
		getUsers();
	    scanner = new Scanner(System.in);

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
                    + "  id integer PRIMARY KEY,\n"
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
        			+ " id integer PRIMARY KEY,\n"
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
        
        int id = rs.getInt("id");
        boolean dup = false;
        
        for(Event i : eventmanager.getAllEvents()) {
        	if(i.getEventId() == id) {
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


}
