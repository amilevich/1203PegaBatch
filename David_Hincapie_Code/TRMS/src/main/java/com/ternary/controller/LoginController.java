package com.ternary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ternary.daoimpl.EmployeeDaoImpl;
import com.ternary.daoimpl.RequestDaoImpl;
import com.ternary.model.Employee;
import com.ternary.model.Request;

public class LoginController {

	public static String Login(HttpServletRequest request) {

		if (request.getMethod().equals("GET")) {
			return "/html/login.html";
		}

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		EmployeeDaoImpl employeeDaoImpl = new EmployeeDaoImpl();
		Employee employee = new Employee();
		Employee supervisor = new Employee();

		employee = employeeDaoImpl.selectByEmployeeEmail(email);
		supervisor = employeeDaoImpl.selectByEmployeeId(employee.getReportTo());

		if (email.equals(employee.getEmail()) && password.equals(employee.getPassword())) {
			// sessions persist data beyond the request's lifetime
			request.getSession().setAttribute("Employee", employee);
			request.getSession().setAttribute("Supervisor", supervisor);
			System.out.println("LOGIN METHOD IN LOGIN CONTROLLER");

			RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
			List<Request> requests = requestDaoImpl.getRequests(employee.getEmployeeId());
			request.getSession().setAttribute("Requests", requests);

			return "/html/home.html";
		} else {
			System.out.println("Email or password not found");
			return "/html/login.html";
		}
	}

}
