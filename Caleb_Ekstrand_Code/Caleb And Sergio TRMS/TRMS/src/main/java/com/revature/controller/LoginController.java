package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.LoginDAOImpl;
import com.revature.dao.UserDAOImpl;
import com.revature.model.Login;
import com.revature.model.User;

public class LoginController {

	public static String Login(HttpServletRequest request) {
		if(request.getMethod().equals("GET")) {
			return "/html/Login.html";
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
//		System.out.println("username: " + username);
//		System.out.println("password: " + password);
		LoginDAOImpl ldi = new LoginDAOImpl();
		Login login = new Login();
		
		login = ldi.selectLoginByUsername(username);
		
//		System.out.println("username = login.username: " + username.equals(login.getUsername()));
//		System.out.println("password = login.password: " + password.equals(login.getPassword()));
//		System.out.println(login.getUsername() + " " + login.getPassword());
		if(username.equals(login.getUsername()) && password.equals(login.getPassword())) {
			System.out.println("login info correct");
			// sessions persist data beyond the request's lifetime
			UserDAOImpl udi = new UserDAOImpl();
			User user = udi.selectUserById(login.getUserId());
			request.getSession().setAttribute("User", user);
			return "/html/Home.html";
		} else {
			System.out.println("else");
			return "/html/Login.html";
		}
	}

}
