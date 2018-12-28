package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.models.Employee;

public class LoginController {
	
	public static String Login(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return "/hmtl/login.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		Employee emp = new Employee();
		
		emp = edi.getEmployeeByUsername(username);
		System.out.println("emp: " + emp);
		System.out.println(BCrypt.checkpw(password, emp.getPassword()));
		if(emp!=null)
			if(username.equals(emp.getUsername()) && BCrypt.checkpw(password, emp.getPassword())) {
				System.out.println("username" + username);
				req.getSession().setAttribute("Employee", emp);
				return "/html/index.html";
			}	
		
		return "/hmtl/login.html";
	}
}
