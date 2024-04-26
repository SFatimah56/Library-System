
package com.library.management;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.library.management.EventsManager.Event;
import com.library.management.UserManager.User;

public class GroupManager {
    private static ArrayList<Group> groups = new ArrayList<Group>();
    
    public ArrayList<Group> getAllGroups() {
        return new ArrayList<Group>(groups);  // Return a new ArrayList containing all events
    }
    
    void createGroup(String name, ArrayList<String> discussionIDs, ArrayList<String> members) {
    	Group group = new Group(name, discussionIDs, members);
    	GroupManager.groups.add(group);
    }
    
    void createGroup(String name) {
    	Group group = new Group(name);
    	GroupManager.groups.add(group);
    }
    

    public static class Group {
        private String name;
        private ArrayList<String> members;
        private ArrayList<String> discussionIDs;
        
        public Group(String name, ArrayList<String> discussionIDs, ArrayList<String> members) {
        	this.name = name;
        	this.discussionIDs = discussionIDs;
        	this.members = members;
        }

        public Group(String name) {
            this.setName(name);
            this.members = new ArrayList<>();
            this.discussionIDs = new ArrayList<>();
        }

        public void addMember(String username) {
            members.add(username);
        }

        public void removeMember(String username) {
            members.remove(username);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addDiscussion(String discussionID) {
        	discussionIDs.add(discussionID);
        }

        public List<String> getDiscussions() {
            return discussionIDs;
        }

        public List<String> getMembers() {
            return members;
        }

        public static Group createGroup(String name) {
            Group group = getGroupByName(name);
            if (group != null) {
                System.out.println("Group " + name + " already exists.");
                return null;
            }
            group = new Group(name);
            groups.add(group);
            return group;
        }

        public static Group getGroupByName(String name) {
            for (Group group : groups) {
                if (group.getName().equals(name)) {
                    return group;
                }
            }
            return null;
        }

        public static void listAllGroup() {
            if (groups.isEmpty()) {
                System.out.println("No Group available.");
                return;
            }
            System.out.println("Available Group:");
            for (Group group : groups) {
                System.out.println(group.getName());
            }
        }

//        public static void listGroupMembers(String groupName) {
//            Group group = getGroupByName(groupName);
//            if (group == null) {
//                System.out.println("Group " + groupName + " does not exist.");
//                return;
//            }
//            System.out.println("Members of Group " + groupName + ":");
//            for (User member : group.getMembers()) {
//                System.out.println(member.getUsername());
//            }
//        }

        public static void addDiscussionToGroup(String groupName, String discussion) {
            Group group = getGroupByName(groupName);
            if (group == null) {
                System.out.println("Group " + groupName + " does not exist.");
                return;
            }
            group.addDiscussion(discussion);
            System.out.println("Discussion added to Group " + groupName + ".");
        }

        public static void listGroupDiscussions(String groupName) {
            Group group = getGroupByName(groupName);
            if (group == null) {
                System.out.println("Group " + groupName + " does not exist.");
                return;
            }
            List<String> discussions = group.getDiscussions();
            if (discussions.isEmpty()) {
                System.out.println("No discussions available in Group " + groupName + ".");
                return;
            }
            System.out.println("Discussions in Group " + groupName + ":");
            for (String discussion : discussions) {
                System.out.println(discussion);
            }
        }
        
        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return "Group Name: " + name;
        }    
    }
    
	public void listAllGroups() {
		// TODO Auto-generated method stub
        if (groups.isEmpty()) {
            System.out.println("No Groups.");
            return;
        }
        for (Group group : groups) {
            System.out.println(group.toString());
        }
	}
}
