package com.library.management;

import java.util.ArrayList;
import java.util.List;

import com.library.management.UserManager.User;

public class GroupsManager {
    private static final List<Groups> groups = new ArrayList<>();

    public static class Groups {
        private String name;
        private List<User> members;
        private List<String> discussions;

        public Groups(String name) {
            this.setName(name);
            this.members = new ArrayList<>();
            this.discussions = new ArrayList<>();
        }

        public void addMember(User user) {
            members.add(user);
        }

        public void removeMember(User user) {
            members.remove(user);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addDiscussion(String discussion) {
            discussions.add(discussion);
        }

        public List<String> getDiscussions() {
            return discussions;
        }

        public List<User> getMembers() {
            return members;
        }

        public static Groups createGroup(String name) {
            Groups group = getGroupByName(name);
            if (group != null) {
                System.out.println("Group " + name + " already exists.");
                return null;
            }
            group = new Groups(name);
            groups.add(group);
            return group;
        }

        public static Groups getGroupByName(String name) {
            for (Groups group : groups) {
                if (group.getName().equals(name)) {
                    return group;
                }
            }
            return null;
        }

        public static void listAllGroups() {
            if (groups.isEmpty()) {
                System.out.println("No groups available.");
                return;
            }
            System.out.println("Available Groups:");
            for (Groups group : groups) {
                System.out.println(group.getName());
            }
        }

        public static void listGroupMembers(String groupName) {
            Groups group = getGroupByName(groupName);
            if (group == null) {
                System.out.println("Group " + groupName + " does not exist.");
                return;
            }
            System.out.println("Members of Group " + groupName + ":");
            for (User member : group.getMembers()) {
                System.out.println(member.getUsername());
            }
        }

        public static void addDiscussionToGroup(String groupName, String discussion) {
            Groups group = getGroupByName(groupName);
            if (group == null) {
                System.out.println("Group " + groupName + " does not exist.");
                return;
            }
            group.addDiscussion(discussion);
            System.out.println("Discussion added to Group " + groupName + ".");
        }

        public static void listGroupDiscussions(String groupName) {
            Groups group = getGroupByName(groupName);
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
    }
}