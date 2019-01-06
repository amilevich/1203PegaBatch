package com.revature.trms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.models.Alert;

public class AlertController {

	public static String AlertJSON(HttpServletRequest req, HttpServletResponse resp) {
		Alert alert = (Alert)req.getSession().getAttribute("Alert");
		try {
			resp.getWriter().write(new ObjectMapper().writeValueAsString(alert));
			req.getSession().setAttribute("Alert", null);
			
		}catch(JsonProcessingException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
		
		
	}

}
