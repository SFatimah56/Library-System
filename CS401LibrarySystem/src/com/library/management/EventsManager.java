package com.library.management;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class EventsManager {
    private static List<Event> events = new ArrayList<>();  // Static events list

    // Static method to create a new event
    public static void createEvent(String title, String description, Date eventDate, String eventType) {
        Event newEvent = new Event(title, description, eventDate, eventType);
        events.add(newEvent);
        System.out.println("Event created: " + title);
    }

    // Static method to list all events
    public static void listAllEvents() {
        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }
        for (Event event : events) {
            System.out.println(event.toString());
        }
    }

    // Static nested class for Event
    public static class Event {
        private static int nextId = 1;  // Static counter
        private int eventId;
        private String title;
        private String description;
        private Date eventDate;
        private String eventType;

        public Event(String title, String description, Date eventDate, String eventType) {
            this.eventId = nextId++;
            this.title = title;
            this.description = description;
            this.eventDate = eventDate;
            this.eventType = eventType;
        }

        @Override
        public String toString() {
        	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date format without time
             return "Event ID: " + eventId + ", Title: " + title + ", Description: " + description +
                    ", Date: " + dateFormat.format(eventDate) + ", Type: " + eventType;
        }
    }
}