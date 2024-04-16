package com.library.management;
import java.util.ArrayList;

public class Post {
	private String title; 
	private ArrayList<Post> comments;
	private ArrayList<User> likes;
	
	public Post(String title, ArrayList<Post> comments) {
		this.title = title;
		this.comments = comments;
        this.likes = new ArrayList<>();
	}
}
