package com.ternary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternary.model.Employee;
import com.ternary.model.Request;

public class HomeController {

	public static String Home(HttpServletRequest request) {
		return null;
	}

	public static String EmployeeJSON(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = (Employee) request.getSession().getAttribute("Employee");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(employee));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String SupervisorJSON(HttpServletRequest request, HttpServletResponse response) {
		Employee supervisor = (Employee) request.getSession().getAttribute("Supervisor");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(supervisor));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String PendingRequestsJSON(HttpServletRequest request, HttpServletResponse response) {

		List<Request> requests = (List<Request>) request.getSession().getAttribute("Requests");

		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(requests));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
