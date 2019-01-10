package com.revature.trms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.daoimpls.EventTypeDAOImpl;
import com.revature.trms.models.EventStats;

public class StatController {

	public static String StatJSON(HttpServletRequest req, HttpServletResponse resp) {
		EventTypeDAOImpl etdi = new EventTypeDAOImpl();
		
		ArrayList<EventStats> event_stats = new ArrayList<>();
		event_stats.add( etdi.getEventTypeStats("Course"));
		event_stats.add( etdi.getEventTypeStats("Seminar"));
		event_stats.add( etdi.getEventTypeStats("Certification"));
		event_stats.add( etdi.getEventTypeStats("Technical-Training"));
		
		try {
			resp.getWriter().write(new ObjectMapper().writeValueAsString(event_stats));
			
			
		}catch(JsonProcessingException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
		return null;
	}

	public static String Stat(HttpServletRequest req) {
		
		return "/html/stats.html";
	}
	
	

}
