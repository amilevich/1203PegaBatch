package com.revature.trms.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.models.Employee;

public class HomeController {

	public static String Home(HttpServletRequest req) {

		return "/html/index.html";
	}
	
	public static String EmpJSON(HttpServletRequest req, HttpServletResponse resp) {
		
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		//System.out.println(emp);
		try {
			//System.out.println(new ObjectMapper().writeValueAsString(emp));
			resp.getWriter().write(new ObjectMapper().writeValueAsString(emp));
			
		}catch(JsonProcessingException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
