package com.library.management;
import java.util.ArrayList;

public class Post {
	private String title; 
	private ArrayList<Post> comments;
	
	public Post(String title, ArrayList<Post> comments) {
		this.title = title;
		this.comments = comments;
	}
}
