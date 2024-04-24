package test;
import static org.junit.Assert.*;
import org.junit.Test;

import com.library.management.EventsManager;

import java.sql.Timestamp;
import java.util.ArrayList;

public class EventsManagerTest {
    
    @Test
    public void testCreateEvent() {
        EventsManager eventsManager = new EventsManager();
        Timestamp eventDate = Timestamp.valueOf("2024-04-23 10:00:00"); 
        eventsManager.createEvent("Conference", "Annual conference on technology", eventDate, "Conference");
        ArrayList<EventsManager.Event> events = eventsManager.getAllEvents();
        assertEquals(1, events.size());
        EventsManager.Event event = events.get(0);
        assertEquals("Conference", event.getTitle());
        assertEquals("Annual conference on technology", event.getDescription());
        assertEquals("Conference", event.getEventType());
        assertEquals(eventDate, event.getEventDate());
    }
    
    @Test
    public void testListAllEvents() {
        EventsManager eventsManager = new EventsManager();
        Timestamp eventDate1 = Timestamp.valueOf("2024-04-23 10:00:00"); 
        eventsManager.createEvent("Meeting", "Monthly team meeting", eventDate1, "Meeting");
        Timestamp eventDate2 = Timestamp.valueOf("2024-04-25 14:00:00"); 
        eventsManager.createEvent("Training", "Training session on new software", eventDate2, "Training");
        assertEquals(3, eventsManager.getAllEvents().size());
    }
    
    @Test
    public void testEmptyEventList() {
        EventsManager eventsManager = new EventsManager();
        Timestamp eventDate = Timestamp.valueOf("2024-04-23 10:00:00"); // Example date and time
        eventsManager.createEvent("Workshop", "Workshop on project management", eventDate, "Workshop");
        eventsManager.getAllEvents().clear(); // Clear all events
        assertEquals(4, eventsManager.getAllEvents().size());
    }
  
}
