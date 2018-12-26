package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		switch(request.getRequestURI()) {
		
		case "/PetsExample/html/Login.do":
			System.out.println("in Login.do case");
			return LoginController.Login(request);
			
		case "/PetsExample/html/Home.do":
			System.out.println("in Home.do case");
			return HomeController.Home(request);
			
		case "/PetsExample/html/Register.do":
			System.out.println("in Register.do case");
			return RegisterController.Register(request);
			
		default:
			return "/html/Login.html";
		}
	}

}
