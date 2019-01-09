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
		case "/ReimbursementSystem/html/personal-listJSON.do":
			return ReimbursementListController.PersonalReimbursementListJSON(req, resp);
		case "/ReimbursementSystem/html/assignment-listJSON.do":
			return ReimbursementListController.AssignReimbursementListJSON(req, resp);
		case "/ReimbursementSystem/html/update.do":
			return ReimbursementListController.SelectAction(req);
		case "/ReimbursementSystem/html/fileJSON.do":
			return AttachmentController.DownloadFile(req, resp);
		case "/ReimbursementSystem/html/profile.do":
			return ProfileController.Profile(req, resp);
		case "/ReimbursementSystem/html/update_profile.do":
			return ProfileController.UpdateProfile(req, resp);
		default:
			return LoginController.Login(req);
		}
		
		
		
		
	}

}
