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

		login.setUsername(request.getParameter("name"));
		login.setPasswd(request.getParameter("type"));

		if (loginDI.isInLogin(login)) {
			// sessions persist data beyond the request's lifetime
			request.getSession().setAttribute("Login", login);
			return "/html/Home.html";
		}
		
		else
			return "/html/Login.html";
	}

}
