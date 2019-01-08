package com.ternary.controller;

import java.util.ArrayList;
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
		List<Employee> employeeList = new ArrayList<>();
		Employee employee = new Employee();
		Employee supervisor = new Employee();

		employee = employeeDaoImpl.selectByEmployeeEmail(email);
		if (employee == null) {
			System.out.println("Email or password not found");
			return "/html/login.html";
		}
		supervisor = employeeDaoImpl.selectByEmployeeId(employee.getReportTo());
		employeeList = employeeDaoImpl.getEmployeeList();

		if (email.equals(employee.getEmail()) && password.equals(employee.getPassword())) {
			// sessions persist data beyond the request's lifetime
			request.getSession().setAttribute("Employee", employee);
			request.getSession().setAttribute("Supervisor", supervisor);
			System.out.println("LOGIN METHOD IN LOGIN CONTROLLER");

			RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
			List<Request> requests = requestDaoImpl.getRequests(employee.getEmployeeId());
			request.getSession().setAttribute("Requests", requests);
			// return "/html/home.html";

			for (int i = 0; i < employeeList.size(); i++) {
				if (!(employee.getJobTitle().contains("Staff"))) {
					return "/html/managerhome.html";
				} else {
					if (i == employeeList.size() - 1) {
						return "/html/home.html";
					}
				}
			} // end of for loop checks if employee is a supervisor

		} // end of email and password verification
		return "/html/login.html";
	}

}