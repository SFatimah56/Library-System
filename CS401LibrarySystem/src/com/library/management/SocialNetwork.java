package com.library.management;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.library.management.EventsManager.Event;
public class SocialNetwork {
	EventsManager eventmanager;
	public SocialNetwork(){
		this.eventmanager = new EventsManager();

	}
}
