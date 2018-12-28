package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.trms.daoimpls.EmployeeDAOImpl;
import com.revature.trms.models.Employee;

public class LoginController {
	
	public static String Login(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return "/hmtl/Login.html";
		}
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		
		EmployeeDAOImpl edi = new EmployeeDAOImpl();
		Employee emp = new Employee();
		
		return null;
	}

}
