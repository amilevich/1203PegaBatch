package com.ternary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternary.daoimpl.RequestDaoImpl;
import com.ternary.model.Employee;
import com.ternary.model.Request;

public class HomeController {

	public static String Home(HttpServletRequest request) {
		Employee employee = (Employee) request.getSession().getAttribute("Employee");

		RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
		List<Request> requests = requestDaoImpl.getRequests(employee.getEmployeeId());
		request.getSession().setAttribute("Requests", requests);
		
		if (!(employee.getJobTitle().contains("Staff"))) {
            return "/html/managerhome.html";
        } else {
            return "/html/home.html";
        }

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

		@SuppressWarnings("unchecked")
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

	public static String ViewRequest(HttpServletRequest request) {

		int requestId = Integer.parseInt(request.getParameter("reqId"));

		RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
		Request req = new Request();

		req = requestDaoImpl.getRequest(requestId);
		request.getSession().setAttribute("Request", req);

		return "/html/details.html";
	}

	
	public static String FileJSON(HttpServletRequest request, HttpServletResponse response) {
		Request req = (Request) request.getSession().getAttribute("Request");
		
		
		
		
		return null;
	}
	
	public static String RequestJSON(HttpServletRequest request, HttpServletResponse response) {
		Request req = (Request) request.getSession().getAttribute("Request");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(req));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String MgrRequestJSON(HttpServletRequest request, HttpServletResponse response) {
		
		Employee employee = (Employee) request.getSession().getAttribute("Employee");
		
		RequestDaoImpl requestDao = new RequestDaoImpl();
		List<Request> mgrRequests = requestDao.getMgrRequests(employee);
		
		System.out.println("mgrReqs=" + mgrRequests);
		
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(mgrRequests));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return null;
		}

}