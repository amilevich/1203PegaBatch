package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.models.Alert;
import com.revature.trms.models.Employee;

public class LoginController {

	public static String Login(HttpServletRequest req) {
		//System.out.println("Login Controller");
		
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		
		if(emp!=null) {
			return "/html/reimburse-list.html";
		}
		if (req.getMethod().equals("GET")) {
			return "/html/login.html";
		}
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		emp = null;
		emp = edi.getEmployeeByUsername(username);
		//System.out.println("Got Emp:" + emp);
		if (validCredentials(emp,username,password)) {
			if (username.equals(emp.getUsername()) && BCrypt.checkpw(password, emp.getPassword())) {
				req.getSession().setAttribute("Employee", emp);
				//System.out.println(req.getSession().getAttribute("Employee"));
				return "/html/reimburse-list.html";
			}
		}
		
		// Set alert message telling user that they entered in an incorrect username/password
		Alert alert = new Alert("danger","Incorrect Username or Password");
		req.getSession().setAttribute("Alert", alert);
		return "/html/login.html";
	}
	
	public static boolean validCredentials(Employee emp, String username, String password) {
		//First avoid nullpointerexception
		if(username == null || password==null || emp==null) {
			return false;
		}
		
		//Check that the request send has both the username and the password.
		if(username=="" || password=="") {
			return false;
		}
		return true;
	}

	public static String Logout(HttpServletRequest req) {
		//System.out.println("Logging out...");
		//System.out.println(req.getSession().getAttribute("Employee"));
		req.getSession().setAttribute("Employee",null);
		req.getSession().setAttribute("Alert", new Alert("success","Successfully logged out!"));
		//System.out.println(req.getSession().getAttribute("Employee"));
		return null;
	}
}
