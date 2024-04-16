package com.library.management;
import java.util.ArrayList;
import java.util.List;

/* Group class represents a group within the library system*/
public class Groups {
    private String name;
    private List<User> members;

    public Groups(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }
}