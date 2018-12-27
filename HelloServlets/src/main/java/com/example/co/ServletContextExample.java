package com.example.co;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletContextExample extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println(getServletName() + " - got the servlet name");
		
		//two ways of getting context params
		
		String firstValue = getServletContext().getInitParameter("databaseUrl");
		System.out.println("\tfirst value: " + firstValue);
		
//		String secondValue = getServletConfig().getServletContext().getInitParameter("databaseUrl");
	}
}
