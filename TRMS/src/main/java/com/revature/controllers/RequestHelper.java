package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch (request.getRequestURI()) {
		
		case "/TRMS/html/Login.do":
				return LoginController.Login(request);
			
		case "/TRMS/html/ManagementHome.do":
			return HomeController.Home(request);
			
		case "/TRMS/html/EmployeeHome.do":
			return HomeController.Home(request);
			
		case "/TRMS/html/LoginJSON.do":
			return HomeController.LoginJSON(request, response);
			
		case "/TRMS/html/EmployeeApplication.do":
			return ApplicationController.ReimForm(request);
		
		case "/TRMS/html/ApplicationForm.do":
			return ApplicationController.ApplicationForm(request, response);
		
		case "/TRMS/html/ApplicationJSON.do":
			return ApplicationController.ApplicationJSON(request, response);	
		default:
			return "/html/Login.html";
		}
	}

}
