package test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import com.library.management.GroupManager;
import com.library.management.GroupManager.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupManagerTest {
    private GroupManager group;

    @Before
    public void setup() {
        group = new GroupManager();
    }

    @After
    public void teardown() {
        group = null;
        
    }
    


    @Test
    public void testAddMember() {
        // Arrange
        ArrayList<String> initialMember = new ArrayList<>();
        initialMember.add("User1");
        group.createGroup("Group1", new ArrayList<>(), initialMember);
        GroupManager.Group groups=GroupManager.Group.getGroupByName("Group1");
        if(groups!=null) {
        groups.addMember("User2");
        }
        List<String> updatedMembers = group.getAllGroups().get(0).getMembers();
        assertTrue(updatedMembers.contains("User2"));
    }

    @Test
    public void testRemoveMember() {
    	GroupManager group=new GroupManager();
    	ArrayList<String> intial=new ArrayList<>();
    	intial.add("User1");
    	intial.add("User2");
    	group.createGroup("Group1", new ArrayList<>(), intial);
    	GroupManager.Group groups=GroupManager.Group.getGroupByName("Group1");
    	if(groups!=null) {
    		groups.removeMember("User1");
    	}
    	else {
    		fail("Group not found");
    	}
    	List<String> updatedMembers = group.getAllGroups().get(0).getMembers();
        assertFalse(updatedMembers.contains("User1"));	
    }
    @Test
    public void testGetDiscussion() {
    	GroupManager group=new GroupManager();
    	ArrayList<String> intial=new ArrayList<>();
    	intial.add("Discussion1");
    	group.createGroup("Group1", intial, new ArrayList<>());
    	List<String> discussions = group.getAllGroups().get(0).getDiscussions();
        assertIterableEquals(intial, discussions);  
    }
    @Test
    public void testGetDiscuss() {
    	GroupManager group=new GroupManager();
    	ArrayList<String> intial=new ArrayList<>();
    	intial.add("Discussion1");
    	group.createGroup("Group1", intial, new ArrayList<>());
        List<String> discussions = group.getAllGroups().get(0).getDiscussions();
        assertEquals(1, discussions.size());
        assertTrue(discussions.contains("Discussion1"));
    }
    @Test
    public void testGetMembers() {
        Group group = new Group("Test Group");
        group.addMember("User1");
        group.addMember("User2");
        List<String> members = group.getMembers();
        assertEquals(2, members.size());
        assertTrue(members.contains("User1"));
        assertTrue(members.contains("User2"));
    }
    
    @Test 
    public void testCreateGroup() {
    	GroupManager group=new GroupManager();
    	String groupName="Group1";
    	group.createGroup(groupName);
    	List<GroupManager.Group> allgroups=group.getAllGroups();
    	 assertEquals("One group should be created", 2, allgroups.size());
    	 GroupManager.Group createdGroup = allgroups.get(0);
         assertEquals("Group name should match", groupName, createdGroup.getName());	
    }
    
    @Test 
    public void testExistingGroup() {
        GroupManager group = new GroupManager();
        String groupName = "ExistingGroup";
        group.createGroup(groupName);
        List<GroupManager.Group> allGroups = group.getAllGroups();
        // Print the number of groups
        System.out.println("Number of groups after creating '" + groupName + "': " + allGroups.size());
        assertEquals("Only existing groups should exist", 5, allGroups.size());
    }
    
   
    
    
    
     

    

}  