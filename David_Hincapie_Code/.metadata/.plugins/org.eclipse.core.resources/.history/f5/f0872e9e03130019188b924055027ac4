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
		case "/TRMS/html/home.do":
			System.out.println("IN HOME CONTROLLER");
			return HomeController.Home(request);
		case "/TRMS/html/Request.do":
			System.out.println("IN REQUEST CONTROLLER");
			return HomeController.Home(request);
		case "/TRMS/html/reimbursement.do":
			System.out.println("IN REIMBURSEMENT CONTROLLER");
			return ReimbursementController.ReimbursementJSON(request);
		// case "/TRMS/html/Register.do":
		// return RegisterController.Register(request);
		case "/TRMS/html/EmployeeJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH EmployeeJSON.do");
			return HomeController.EmployeeJSON(request, response);
		case "/TRMS/html/SupervisorJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH SupervisorJSON.do");
			return HomeController.SupervisorJSON(request, response);
		case "/TRMS/html/PendingRequestsJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH");
			return HomeController.PendingRequestsJSON(request, response);
		case "/TRMS/html/Details.do":
			System.out.println("IN REQUEST CONTROLLER");
			return DetailsController.Details(request);
		case "/TRMS/html/viewRequest.do":
			System.out.println("IN REQUEST CONTROLLER");
			return HomeController.ViewRequest(request);
		case "/TRMS/html/CancelRequest.do":
			System.out.println("IN REQUEST CONTROLLER");
			return DetailsController.CancelRequest(request);
		case "/TRMS/html/RequestJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH");
			return HomeController.RequestJSON(request, response);
		default:
			System.out.println("IN DEFAULT");
			return "/TRMS/html/login.html";
		}
	}

}
