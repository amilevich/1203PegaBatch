package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.RequestHelper;

public class MasterServlet extends HttpServlet {

	/**
	 *  
	 */
	public static final long serialVersionUID = 5489847281029103929L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestHelper.process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String targetURL = RequestHelper.process(request, response);
		request.getRequestDispatcher(targetURL).forward(request, response);
	}
}