package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.HomeController;
import com.revature.controller.LoginController;

public class RequestHelper {

	public static String process(HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		case "/TRMS/html/Login.do":
			return LoginController.Login(request);
		case "/TRMS/html/Home.do":
			//System.out.println("hi");
			return HomeController.Home(request, response);
		case "/TRMS/html/Form.do":
			return FormController.Form(request);
		case "/TRMS/html/Approve.do":
			return HomeController.Approve(request);
		case "/TRMS/html/Deny.do":
			return HomeController.Deny(request);
		case "/TRMS/html/Cancel.do":
			return HomeController.Cancel(request);
		case "/TRMS/html/Upload.do":
			return UploadController.downloadFile(request);
		default:
			return "/html/Login.html";
		}
	}

}
