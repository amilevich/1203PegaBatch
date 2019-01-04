package com.revature.trms.validators;

import java.sql.Timestamp;
import java.time.LocalDate;

import com.revature.trms.daoimpls.EventTypeDAOImpl;
import com.revature.trms.daoimpls.GradingFormatDAOImpl;
import com.revature.trms.models.Event;

public class EventValidator {
	
	public static boolean validate_Event(Event event) {
		// initial null check to avoid nullptr exceptions later
		if(event == null) {
			return false;
		}
		
		
		// Valid event type:
		EventTypeDAOImpl etdi = new EventTypeDAOImpl();
		if(etdi.getEventType(event.getType_name()) == null) {
			return false;
		}
		
		// validate event date:
		// null checks, and also checks that the date is in the future
		LocalDate event_date = event.getStart_date();
		if(event_date == null || event_date.isBefore(LocalDate.now())) {
			return false;
		}
		
		// check that event is at least one week in the future:
		if( event_date.isBefore(LocalDate.now().plusWeeks(1))) {
			return false;
		}
		
		// validate grading format:
		String grading_format = event.getFormat_name();
		GradingFormatDAOImpl gfdi = new GradingFormatDAOImpl();
		if(gfdi.getGradingFormat(grading_format) == null) {
			return false;
		}
		
		// validate event time:
		Timestamp event_time = event.getStart_time();
		if(event_time == null) {
			return false;
		}
		
		// validate passing grade:
		String passing_grade = event.getPassing_grade();
		if(passing_grade == null || passing_grade.length() >10) {
			return false;
		}
		
		// validate description:
		String description = event.getDescription();
		if(description == null || description.length() > 500) {
			return false;
		}
		
		
		// validate cost:
		// NOTE: Max cost manually set to $10,000 here
		if(event.getCost() < 0 || event.getCost() > 10_000) {
			return false;
		}
		
		return true;
	}
}
