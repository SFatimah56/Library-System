package com.library.management;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private String username;
    private List<Book> favoriteBooks;
    private String readingHabits;
    private String literaryPreferences;
    private List<UserManager> followers;
    private List<UserManager> followings;
    private int id;

    public UserManager(String username, int id) {
        this.username = username;
        this.id = id;
        this.favoriteBooks = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
    }

    public void addFavoriteBook(Book book) {
        favoriteBooks.add(book);
    }

    public void removeFavoriteBook(Book book) {
        favoriteBooks.remove(book);
    }

    public void postMessage(String message) {
        // Logic for posting message
    }

    public void commentOnPost(String postId, String comment) {
        // Logic for commenting on a post
    }

    public void likePost(String postId) {
        // Logic for liking a post
    }

    public void sharePost(String postId) {
        // Logic for sharing a post
    }

    public void joinGroup(GroupsManager.Groups group) {
        group.addMember(this);
    }

    public void leaveGroup(GroupsManager.Groups group) {
        group.removeMember(this);
    }

    public void participateInDiscussion(String discussion) {
        // Logic for participating in a discussion
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<Book> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    public String getReadingHabits() {
        return readingHabits;
    }

    public void setReadingHabits(String readingHabits) {
        this.readingHabits = readingHabits;
    }

    public String getLiteraryPreferences() {
        return literaryPreferences;
    }

    public void setLiteraryPreferences(String literaryPreferences) {
        this.literaryPreferences = literaryPreferences;
    }

    public List<UserManager> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserManager> followers) {
        this.followers = followers;
    }

    public List<UserManager> getFollowings() {
        return followings;
    }

    public void setFollowings(List<UserManager> followings) {
        this.followings = followings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
