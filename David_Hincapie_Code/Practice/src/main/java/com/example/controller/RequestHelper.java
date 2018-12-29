package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BEGINING OF PROCESS");
		switch (request.getRequestURI()) {
		case "/Practice/html/Login.do":
			System.out.println("GOING TO LOGIN CONTROLLER");
			return LoginController.Login(request);
		case "/Practice/html/Home.do":
			System.out.println("IN HOME CONTROLLER");
			return HomeController.Home(request);
		case "/Practice/html/Register.do":
			return RegisterController.Register(request);
		case "/Practice/html/PetJSON.do":
			System.out.println("REQUEST HELPER HOMECONTROLLER SWITCH");
			return HomeController.PetJSON(request, response);
		default:
			System.out.println("IN DEFAULT");
			return "/html/Login.html";
		}
	}

}
