package com.example.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.err.println("in doGet method, handling a GET request");
		
		String favoriteColor = "GreenRedPurpleh";
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("<h1>Your favorite color is: " + favoriteColor + "</h1>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("in doPost method, handling a POST request");
		
		//resp.sendRedirect("https://media.giphy.com/media/26FPCXdkvDbKBbgOI/giphy.gif");
		req.getRequestDispatcher("home.html").forward(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}
	
}
