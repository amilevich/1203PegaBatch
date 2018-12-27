package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {

		switch (request.getRequestURI()) {
		case "/Practice/html/Login.do":
			return LoginController.Login(request);
		case "/Practice/html/Home.do":
			return HomeController.Home(request);
		case "/Practice/html/Register.do":
			return RegisterController.Register(request);
		case "/Practice/html/PetJSON.do":
			return HomeController.PetJSON(request, response);
		default:
			return "/html/Login.html";
		}
	}

}
