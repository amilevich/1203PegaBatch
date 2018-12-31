package com.revature.trms.dao;

import com.revature.trms.models.Event;

public interface EventDAO {

	//CRUDE methods
		//Create
		public boolean insertEvent(Event event);
		
		//Read
		public Event getEvent(int id);
		
		//Update
		public boolean updateEvent(Event event);
		
		//Delete
		public boolean deleteEvent(int id);
}
