package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse resp) {
		
		switch (req.getRequestURI()) {
		case "/ReimbursementSystem/html/home.do":
			return HomeController.Home(req);
		
		default:
			break;
		}
		
		
		return null;
	}

}
