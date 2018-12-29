package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.models.Employee;

public class LoginController {

	public static String Login(HttpServletRequest req) {
		//System.out.println("IN LOGIN CONTROLLER");
		
		if (req.getMethod().equals("GET")) {
			return "/html/login.html";
		}
		String username = req.getParameter("username");
		//System.out.println("username: " + username);
		String password = req.getParameter("password");
		//System.out.println("password: " + password);

		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		Employee emp = new Employee();

		emp = edi.getEmployeeByUsername(username);
		//System.out.println("emp: " + emp);
		//System.out.println(BCrypt.checkpw(password, emp.getPassword()));
		if (validCredentials(emp,username,password)) {
			if (username.equals(emp.getUsername()) && BCrypt.checkpw(password, emp.getPassword())) {
				//System.out.println("username " + username);
				req.getSession().setAttribute("Employee", emp);
				return "/html/index.html";
			}
		}

		return "/html/login.html";
	}
	
	public static boolean validCredentials(Employee emp, String username, String password) {
		//First avoid nullpointerexception
		if(username == null || password==null || emp==null) {
			return false;
		}
		
		//Check that the request send has both the username and the password.
		if(username=="" || password=="") {
			System.out.println("Request must have a username and password.");
			return false;
		}
		return true;
	}
}
