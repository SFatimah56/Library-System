package com.library.management;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class EventsManager {
    private static ArrayList<Event> events = new ArrayList<>();  // Static list to hold all events

    // Static nested class Event
    public static class Event {
        private static int nextId = 1;
        private int eventId;
        private String title;
        private String description;
        private Date eventDate;
        private String eventType;

        // Event constructor that automatically adds the event to the manager's list
        public Event(String title, String description, Date eventDate, String eventType) {
            this.eventId = nextId++;
            this.title = title;
            this.description = description;
            this.eventDate = eventDate;
            this.eventType = eventType;
            EventsManager.events.add(this);  // Add this event to the list
            System.out.println("Event created: " + title);
        }

        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return "Event ID: " + eventId + ", Title: " + title + ", Description: " + description +
                   ", Date: " + dateFormat.format(eventDate) + ", Type: " + eventType;
        }
        // Getters for Event properties
        public int getEventId() {
            return eventId;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public Date getEventDate() {
            return eventDate;
        }

        public String getEventType() {
            return eventType;
        }
    }

    // Method to list all events
    public static void listAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }
        for (Event event : events) {
            System.out.println(event.toString());
        }
    }
    
    public static ArrayList<Event> getAllEvents() {
        return new ArrayList<>(events);  // Return a new ArrayList containing all events
    }
}