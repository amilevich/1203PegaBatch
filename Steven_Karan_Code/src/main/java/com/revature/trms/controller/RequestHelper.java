package com.revature.trms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse resp) {
		
		switch (req.getRequestURI()) {
		case "/ReimbursementSystem/html/home.do":
			return HomeController.Home(req);
		case "/ReimbursementSystem/html/register.do":
			return RegisterController.Register(req);
		case "/ReimbursementSystem/html/login.do":
			return LoginController.Login(req);
		case "/ReimbursementSystem/html/empJSON.do":
			return HomeController.EmpJSON(req, resp);
		case "/ReimbursementSystem/html/reimburse.do":
			return ReimbursementController.Reimburse(req);
		case "/ReimbursementSystem/html/alertJSON.do":
			return AlertController.AlertJSON(req,resp);
		case "/ReimbursementSystem/html/reimburse-list.do":
			return ReimbursementListController.ReimbursementList(req);
		case "/ReimbursementSystem/html/reimburse-listJSON.do":
			return ReimbursementListController.ReimbursementListJSON(req, resp);
		default:
			return LoginController.Login(req);
		}
	}

}
