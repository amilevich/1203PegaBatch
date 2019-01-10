package com.assignment.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.assignment.bean.Employee;
import com.assignment.daoimpl.EmployeeImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {//this is what happens when you press the "login button"!

	public static String Login(HttpServletRequest request) {
		if (request.getMethod().equals("GET")) {
			return "/html/Login.html";
		}
		
		String tempID = request.getParameter("employeeID");
		String password = request.getParameter("password");
		int id = Integer.parseInt(tempID);
		EmployeeImpl eimpl = new EmployeeImpl();
		boolean validcredentials = false;
		try {
			validcredentials = eimpl.readPassword(id, password);
			
		} catch (SQLException e) {
			System.out.println("You don' goofed with the impl!");
			e.printStackTrace();
		}
		
		if (validcredentials) {//if successful, this will take user to their home page
			try {
				Employee e = eimpl.readEmployee(id);
				request.getSession().setAttribute("User", e);
				if (e.getEmpType().equals("Employee"))
					return "/html/EmpHome.html";
				else
					return "/html/ApproversHome.html";
			} catch (SQLException e) {
				System.out.println("it didn't work");
				e.printStackTrace();
			}
		}
		return "/html/Login.html";//returns login page on fail
			
		
	}
}
