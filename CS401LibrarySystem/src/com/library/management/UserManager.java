package com.library.management;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.library.management.EventsManager.Event;

public class UserManager {
    private static ArrayList<User> users = new ArrayList<User>();  // Static list to hold all events
    
    
    void createUser(int id, String username, ArrayList<String> favoriteBookISBN, ArrayList<String> readingHabits, ArrayList<String> genres, ArrayList<String> followerIDs, ArrayList<String> followingIDs) {
        User user = new User(id, username, favoriteBookISBN, readingHabits, genres, followerIDs, followingIDs);
        UserManager.users.add(user);  // Add this event to the list
    }
    
    void createUser(String username) {
    	User user = new User(username);
    	UserManager.users.add(user);
    }
    
    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>(users);  // Return a new ArrayList containing all events
    }
    
    
    public int getHighestID() {
    	int maxID = 0;
    	for (User user : users) {
    		  if(user.id > maxID) {
    			  maxID = user.id + 1;
    		  }
    		}
    	
    	return maxID;
    }
    
    public static class User {
    	private static int nextId = 1;
        private String username;
        private ArrayList<String> favoriteBookiSBN;
        private ArrayList<String> readingHabits;
        private ArrayList<String> genres;
        private ArrayList<String> followerIDs;
        private ArrayList<String> followingIDs;
        private int id;
    	
        public User(String username) {
            this.id = nextId++;
            this.username = username;
            this.favoriteBookiSBN = new ArrayList<String>();
            this.readingHabits = new ArrayList<String>();
            this.genres = new ArrayList<String>();
            this.followerIDs = new ArrayList<String>();
            this.followingIDs = new ArrayList<String>();
        }
        
        public User(int id, String username, ArrayList<String> favoriteBookiSBN, ArrayList<String> readingHabits, ArrayList<String> genres, ArrayList<String> followerIDs, ArrayList<String> followingIDs) {
            this.id = id;
        	this.username = username;
            this.favoriteBookiSBN = favoriteBookiSBN;
            this.readingHabits = readingHabits;
            this.genres = genres;
            this.followerIDs = followerIDs;
            this.followingIDs = followingIDs;
            
        }
        
        
        public void addFavoriteBook(Book book) {
        	favoriteBookiSBN.add(book.getISBN());
        }

        public void removeFavoriteBook(Book book) {
        	favoriteBookiSBN.remove(book.getISBN());
        }

        
        public int getID() {
        	return id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public ArrayList<String> getFavoriteBookISBNs() {
            return favoriteBookiSBN;
        }
        
        public ArrayList<String> getReadingHabits() {
            return readingHabits;
        }
        
      public ArrayList<String> getGenres() {
    	  return genres;
      }
     

        public List<String> getFollowers() {
            return followerIDs;
        }
        
        
        public List<String> getFollowings() {
            return followingIDs;
        }
        
        
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        
        
        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return "Username: " + username + ", ID: " + id;
        }
        
    	
    }

	public void listAllUsers() {
		// TODO Auto-generated method stub
        if (users.isEmpty()) {
            System.out.println("No Users.");
            return;
        }
        for (User user : users) {
            System.out.println(user.toString());
        }
		
	}

   
}
