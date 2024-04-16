package com.library.management;

public class User {
    private String username;
    private int followers;    //Who follows the user
    private int followings;   //Who the user follows
    private int id;
    private Followers manageFollowers;
    private Following manageFollowings;

    public User(String username, int followers, int followings, int id) {
        this.username = username;
        this.followers = followers;
        this.followings = followings;
        this.id = id;
        this.manageFollowers = new Followers();
        this.manageFollowings = new Following();
    }

    public void follow(User followUser) {
        manageFollowings.addFollowing(followUser);
        followUser.manageFollowers.addFollower(this);
    }
}