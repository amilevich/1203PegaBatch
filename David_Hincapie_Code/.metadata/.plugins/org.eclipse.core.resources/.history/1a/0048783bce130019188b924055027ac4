package com.ternary.controller;

import javax.servlet.http.HttpServletRequest;

import com.ternary.daoimpl.RequestDaoImpl;

public class DetailsController {

	public static String Details(HttpServletRequest request) {

		if (request.getMethod().equals("GET")) {
			return "/html/login.html";
		} else {
			return "/html/home.html";
		}

	}

	public static String CancelRequest(HttpServletRequest request) {
		System.out.println("ttttttttttttttttttttttttt         "+request.getParameter("requestId"));
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
		requestDaoImpl.cancelRequest(requestId);

		return "/html/home.do";
	}

}