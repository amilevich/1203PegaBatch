package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.bean.Login;
import com.revature.daoimpl.LoginDaoImpl;

public class LoginController {

	public static String Login(HttpServletRequest request) {

		if (request.getMethod().equals("GET")) {
			return "/html/Login.html";
		}

		LoginDaoImpl loginDI = new LoginDaoImpl();
		Login login = new Login();
		
		login.setUsername(request.getParameter("username"));
		login.setPasswd(request.getParameter("passwd"));
		
		System.out.println(login.getUsername() + " " + login.getPasswd());
		
		if (loginDI.isInLogin(login)) { //employee portal
		//if (true) {
			// sessions persist data beyond the request's lifetime
			System.out.println("EMPLOYEE PORTAL");
			System.out.println(request.getSession().getAttribute("Login"));
			System.out.println((request.getSession().getAttributeNames().toString()));
			request.getSession().setAttribute("Login", login);
			return "/html/employeehome.html";
		}
			
		/*if (loginDI.isInLogin(login)) { //management portal
			// sessions persist data beyond the request's lifetime
			System.out.println("MANAGEMENT PORTAL");
			System.out.println(request.getSession().getAttribute("Login"));
			System.out.println((request.getSession().getAttributeNames().toString()));
			request.getSession().setAttribute("Login", login);
			return "/html/managementhome.html";
		}*/
		else
			System.out.println("LOGIN PORTAL");
			return "/html/Login.html";
	}

}
