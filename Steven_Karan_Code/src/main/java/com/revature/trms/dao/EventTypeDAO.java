package com.revature.trms.dao;

import java.util.ArrayList;

import com.revature.trms.models.EventStats;
import com.revature.trms.models.EventType;

public interface EventTypeDAO {
	//CRUDE methods
		//Create
		public boolean insertEventType(EventType type);
		
		//Read
		public EventType getEventType(String type);
		public ArrayList<EventType> getAllEventTypes();
		
		//Update
		public boolean updateEventType(EventType event, String searchType);
		
		//Delete
		public boolean deleteEventType(String type);

		public EventStats getEventTypeStats(String type);
		
}
