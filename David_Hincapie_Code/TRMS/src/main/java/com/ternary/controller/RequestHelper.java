package com.ternary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BEGINING OF PROCESS");
System.out.println("URI " + request.getRequestURI());
		switch (request.getRequestURI()) {
		case "/TRMS/html/login.do":
			System.out.println("GOING TO LOGIN CONTROLLER");
			return LoginController.Login(request);
		case "/TRMS/html/Home.do":
			System.out.println("IN HOME CONTROLLER");
			return HomeController.Home(request);
//		case "/TRMS/html/Register.do":
//			return RegisterController.Register(request);
		case "/TRMS/html/EmployeeJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH EmployeeJSON.do");
			return HomeController.EmployeeJSON(request, response);
		case "/TRMS/html/SupervisorJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH SupervisorJSON.do");
			return HomeController.SupervisorJSON(request, response);
		default:
			System.out.println("IN DEFAULT");
			return "/TRMS/html/login.html";
		}
	}

}
