package com.trms.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		
		switch(request.getRequestURI()) {
		case "/TRMS/html/login.do":
			System.out.println("in login.do");
			return LoginController.Login(request);
			
		case "/TRMS/html/index.do":
			System.out.println("in index.do");
			return IndexController.Index(request);
			
		case "/TRMS/html/register.do":
			System.out.println("in register.do");
			return RegisterController.Register(request);
			
		case "/TRMS/html/Employee2JSON.do":
			System.out.println("in index.do");
			return IndexController.Employee2JSON(request, response);
			
		default:
			System.out.println("default");
			return "/html/login.html";
		}	
	}	
}
