package com.library.management;

import java.util.ArrayList;
import java.util.List;

import com.library.management.UserManager.User;
	
	//Followers class
	class Followers {
		private static ArrayList<User> followers = new ArrayList<>();
		public Followers() {
			Followers.followers = new ArrayList<>();
		}
		public void addFollower(User follower) {
			followers.add(follower);
		}
	}
	//Following class
	class Following {
		private static ArrayList<User> followings = new ArrayList<>();	

	    public Following() {
	        Following.followings = new ArrayList<>();
	    }

	    public void addFollowing(User following) {
	        followings.add(following);
	    }
	}