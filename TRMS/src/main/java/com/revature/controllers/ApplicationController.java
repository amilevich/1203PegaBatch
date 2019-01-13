package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.Application;
import com.revature.bean.Department;
import com.revature.bean.Employee;
import com.revature.bean.Event;
import com.revature.bean.Login;
import com.revature.bean.Management;
import com.revature.bean.Sessions;
import com.revature.daoimpl.ApplicationDaoImpl;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.EventDaoImpl;

public class ApplicationController {
	
	public static String ReimForm(HttpServletRequest request) {
		
		ApplicationDaoImpl appDaoImpl = new ApplicationDaoImpl();
		EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
		Employee employee = (Employee)request.getSession().getAttribute("Employee");
		EventDaoImpl eventDaoImpl = new EventDaoImpl();
		Application application = new Application();
		Department department = (Department)request.getSession().getAttribute("Department");
		Management management = (Management)request.getSession().getAttribute("Management");
		Event event = new Event();
				
		application.setFirstName(request.getParameter("firstname"));
			
		application.setLastName(request.getParameter("lastname"));
		
		application.setEmail(request.getParameter("email"));
		
		application.setDepartmentId(department.getDepartmentId());
		
		application.setManagerId(management.getManagementId());
		
		event.setEvenType(request.getParameter("eventtype"));
		try {
			eventDaoImpl.selectEvent(event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("Event", event);
		
		application.setEventId(event.getEventId());
		
		application.setEventLocation(request.getParameter("eventlocation"));
		
		application.setGradeFormat(request.getParameter("gradeformat"));
		
		application.setEventCost(Double.parseDouble(request.getParameter("eventcost")));
	
		application.setEventDate((String)request.getParameter("eventdate"));
		
		employee.setPendingReimbursement(application.getEventCost()*event.getConverage());
		
		try {
			employeeDaoImpl.updateReimbursementAmounts(employee);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		application.setAppStatus("Pending");
		
		application.setJustification(request.getParameter("certjustify"));
		
		application.setHoursMissed(Integer.parseInt(request.getParameter("workhours")));
		
		application.setSummary(request.getParameter("summary"));
		
		application.setBencoApproved(0);
		application.setDepartmentheadApproved(0);
		application.setBencoApproved(0);

		application.setUserId(employee.getUserId());
		
		application.setAttachment(null);
		application.setApprovedAttahment(null);
		
		try {
			appDaoImpl.insertEmployInfo(application);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/html/EmployeeHome.html";
	}
	
	public static String ApplicationForm(HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			return "/html/Login.html";
		}
			
		return "/html/EmployeeApplication.html";
	}
	
	public static String ApplicationJSON(HttpServletRequest request, HttpServletResponse response) {
		Employee employee = (Employee)request.getSession().getAttribute("Employee");
		Management management = (Management)request.getSession().getAttribute("Management");
		Login login = (Login)request.getSession().getAttribute("Login");
		Department department = (Department)request.getSession().getAttribute("Department");
		
		// ObjectMapper converts an object to a string
		Sessions sessions = new Sessions(management, employee, login, department);
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(sessions));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
