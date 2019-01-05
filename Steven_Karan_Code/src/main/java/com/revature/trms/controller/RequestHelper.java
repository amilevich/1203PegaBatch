package com.revature.trms.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
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
		case "/ReimbursementSystem/html/emp-listJSON.do":
			return ReimbursementListController.ReimbursementListJSON(req, resp);
		case "/ReimbursementSystem/html/upload.do":
			return AttachmentController.UploadFile(req);
		default:
			return LoginController.Login(req);
		}
	}

}
