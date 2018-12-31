package com.revature.trms.validators;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.revature.trms.models.Event;

public class EventValidator {
	
	public static boolean validate_Event(Event event) {
		// validate event date:
		LocalDate event_date = event.getStart_date();
		if(event_date == null) {
			return false;
		}
		
		// validate event time:
		Timestamp event_time = event.getStart_time();
		if(event_time == null) {
			return false;
		}
		
		String passing_grade = event.getPassing_grade();
		if(passing_grade == null) {
			return false;
		}
		
		
		
		return true;
	}
}
