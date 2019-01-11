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
			System.out.println("Event Type Invalid");
			return false;
		}
		
		// validate event date:
		// null checks, and also checks that the date is in the future
		LocalDate event_date = event.getStart_date();
		if(event_date == null || event_date.isBefore(LocalDate.now())) {
			System.out.println("Event Date Invalid");
			return false;
		}
		
		// check that event is at least one week in the future:
		if( event_date.isBefore(LocalDate.now().plusWeeks(1))) {
			System.out.println("Event Date Invalid.\n LESS THAN 1 WEEK AWAY.");
			return false;
		}
		
		// validate grading format:
		String grading_format = event.getFormat_name();
		GradingFormatDAOImpl gfdi = new GradingFormatDAOImpl();
		if(gfdi.getGradingFormat(grading_format) == null) {
			System.out.println("Event Grading Format Invalid");
			return false;
		}
		
		// validate event time:
		Timestamp event_time = event.getStart_time();
		if(event_time == null) {
			System.out.println("Event Start Time Invalid");
			return false;
		}
		
		// validate passing grade:
		String passing_grade = event.getPassing_grade();
		if(passing_grade == null || passing_grade.length() >10) {
			System.out.println("Event passing grade invalid");
			return false;
		}
		
		// validate description:
		String description = event.getDescription();
		if(description == null || description.length() > 1000) {
			System.out.println("Event description invalid");
			return false;
		}
		
		
		// validate cost:
		// NOTE: Max cost manually set to $10,000 here
		if(event.getCost() < 0 || event.getCost() > 10_000) {
			System.out.println("Event cost invalid");
			return false;
		}
		
		return true;
	}
}
