package com.example.co;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletConfigExample extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("ServletConfigExample");
		
		String firstVal = getInitParameter("name1");
		System.out.println("\tfirst value: " + firstVal);
		
		String secondVal = getInitParameter("favoriteColor");
		System.out.println("\tsecond value: " + secondVal);
	}
	
}
