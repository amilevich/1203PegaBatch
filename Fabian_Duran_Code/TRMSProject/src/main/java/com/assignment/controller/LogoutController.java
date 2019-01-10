package com.assignment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController {

	public static void Logout(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.getSession().invalidate();
		RequestDispatcher view = request.getRequestDispatcher("../html/Login.html");
		view.forward(request, resp);
	
	}

}
