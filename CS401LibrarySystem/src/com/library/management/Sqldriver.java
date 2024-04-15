 
package com.library.management;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.library.management.EventsManager.Event;

public class Sqldriver
    {
      public ArrayList<User> getUsers()
      {
    	  
    ArrayList<User> users = new ArrayList<>();

        // NOTE: Connection and Statement are AutoClosable.
        //       Don't forget to close them both in order to avoid leaks.
        try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
          Statement statement = connection.createStatement();
        )
        {
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          ResultSet rs = statement.executeQuery("select id, username, followers from Users");
          while(rs.next())
          {
            // read the result set
        	User user = new User(
        	rs.getString("username"), 
        	rs.getInt("id"),
        	rs.getInt("followers")
        	);
        	
        	users.add(user);

          }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
        
        return users;
      }
      
      public ArrayList<Event> getEvents()
      {
    	  
    	  ArrayList<Event> events = new ArrayList<Event>();

        // NOTE: Connection and Statement are AutoClosable.
        //       Don't forget to close them both in order to avoid leaks.
        try
        (
          // create a database connection
          Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
          Statement statement = connection.createStatement();
        )
        {
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          ResultSet rs = statement.executeQuery("select id, title, description, eventdate, eventtype from Users");
          while(rs.next())
          {
            // read the result set
        	Event event = new Event(
        			rs.getString("title"),
        			rs.getString("description"),
        			rs.getDate("eventdate"),
        			rs.getString("eventtype")
        	);
        	
        	events.add(event);

          }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          e.printStackTrace(System.err);
        }
        
        return events;
      }
      
      
    }