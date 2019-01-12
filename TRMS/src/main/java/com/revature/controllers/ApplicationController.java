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
import com.revature.daoimpl.DepartmentDaoImpl;
import com.revature.daoimpl.EmployeeDaoImpl;
import com.revature.daoimpl.EventDaoImpl;
import com.revature.daoimpl.ManagementDaoImpl;

public class ApplicationController {
	
	public static String ReimForm(HttpServletRequest request) {
		
		ApplicationDaoImpl empDaoImpl = new ApplicationDaoImpl();
		EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
		Employee employee = (Employee)request.getSession().getAttribute("Employee");
		DepartmentDaoImpl departDaoImpl = new DepartmentDaoImpl();
		ManagementDaoImpl managementDaoImpl = new ManagementDaoImpl();
		EventDaoImpl eventDaoImpl = new EventDaoImpl();
		Application application = new Application();
		Department department = new Department();
		Management management = (Management)request.getSession().getAttribute("Management");
		Event event = new Event();
		int TotalReimbursement = 1000;
		
		System.out.println(employee.getUserId());
		
		application.setFirstName(request.getParameter("firstname"));
	
		application.setLastName(request.getParameter("lastname"));
		
		application.setEmail(request.getParameter("email"));
		
		department.setDepartmentName(request.getParameter("department"));
		try {
			departDaoImpl.selectDepartment(department);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		application.setDepartmentId(department.getDepartmentId());
		
		application.setManagerId(management.getManagementId());
		
		event.setEvenType(request.getParameter("eventtype"));
		try {
			eventDaoImpl.selectEvent(event);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		application.setEventId(event.getEventId());
		
		application.setEventLocation(request.getParameter("eventlocation"));
		application.setGradeFormat(request.getParameter("gradeformat"));
		application.setEventCost(Double.parseDouble(request.getParameter("eventcost")));
		
		//DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
		application.setEventDate((String)request.getParameter("eventdate"));
		
		employee.setPendingReimbursement(application.getEventCost()*event.getConverage());
		//employee.setAvaiReimbursement(TotalReimbursement - employee.getPendingReimbursement() - employee.getAwaredReimbursement()); 
		
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
			empDaoImpl.insertEmployInfo(application);
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
		
		// ObjectMapper converts an object to a string
		Sessions sessions = new Sessions(management, employee, login);
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
