package com.assignment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHelper {
	public static String process (HttpServletRequest request, HttpServletResponse response) {
		switch(request.getRequestURI()) {
		//names are all tentative, depending on js call
		case "/TRMSProject/html/Login.do"://performs a check on login request.
			return LoginController.Login(request);
			
		case "/TRMSProject/html/ReimbursementForm.do"://submit will create a new reimbursement in the DB, and redirect to home
			System.out.println("hit something!");
			return ReimbursementController.Form(request);
						
		case "/TRMSProject/html/CompletionForm.do"://will put completed information into DB, redirects to home page...
			System.out.println("Completion GO");
			return CompletionController.Form(request);
			
		case "/TRMSProject/html/AdditionalForm.do"://will put completed information into DB, redirects to home page...
			return AdditionalController.EmpReadAddInfo(request, response);
		
		case "/TRMSProject/html/History.do"://Will take user to history of their reimbursements
			return HistoryController.Table(request, response);
		
		case "/TRMSProject/html/Reviewing.do"://gets specific reimbursement into session, 			
			return ReviewController.Form(request, response);
			
		case "/TRMSProject/html/Review.do":
			return ReviewController.Action(request);
			
		case "/TRMSProject/html/CancelRei.do"://upon pressing cancel, this will cancel a reimbursement request
			System.out.println("your touching this...");
			return ActionController.CancelRei(request, response);
		
		case "/TRMSProject/html/Additional.do"://to provide clarification--IMPORTANT on 
			return AdditionalController.Form(request);
			
		case "/TRMSProject/html/AcceptChange.do"://if user accepts changes to their reward amount
			return ActionController.AcceptChange(request,response);
		
		case "/TRMSProject/html/GetFinalApproval.do"://to view reimbursements that submitted a  presentation, for DS or DH
			return ReviewController.GoToFinalApproval(request,response);
		case "/TRMSProject/html/FinalApproval.do":
			return ReviewController.FinalApproval(request);
			
		case "/TRMSProject/html/AdditionalComplete.do":
			return AdditionalController.AddInfoComplete(request);
			
		case "/TRMSProject/html/EmpJSON.do":
			return HomeController.EmpJSON(request, response);
			
		case "/TRMSProject/html/Notification.do":
			return AdditionalController.Notification(request,response);
			
		case"/TRMSProject/html/NotificationForm.do":
			return AdditionalController.NotificationForm(request,response);
			
		case "/TRMSProject/html/Logout.do":
			try {
				LogoutController.Logout(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return "";
			
        case "/TRMSProject/html/NavJSON.do":
			System.out.println("your touching this...2");
			return HomeController.NavJSON(request, response);
                
			
		default:
			return "/html/Login.html";		
		}
	}
}
