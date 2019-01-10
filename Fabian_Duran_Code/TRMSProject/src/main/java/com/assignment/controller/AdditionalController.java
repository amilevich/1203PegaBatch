package com.assignment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.bean.AdditionalInformation;
import com.assignment.bean.Employee;
import com.assignment.bean.Reimbursement;
import com.assignment.daoimpl.AddInfoImpl;
import com.assignment.daoimpl.ReimbursementImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdditionalController {
	//objective: take information written and store recipient and sender
	//will store this in sql table and return will hopefully appear in recipient's notification...
	//this also implies a response.do that will change reimbursement state back to pending
	public static String Form(HttpServletRequest request) {
		Employee e = (Employee)request.getSession().getAttribute("User");
		Reimbursement r = (Reimbursement)request.getSession().getAttribute("Reimbursement");
		int recipientID = 0;
		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		if (r.getDsAppr()==0)
			recipientID = r.getDsID();//this stores the requestor's id
		else if (r.getDhAppr()==0)
			recipientID = r.getDhID();//this stores the ds's id
		else
			recipientID = r.getDhID();//will store dh's id
		
		String requestMessage = request.getParameter("comment");
		AddInfoImpl aimpl = new AddInfoImpl();
		try {
			aimpl.createAddInfo(r.getRei_id(), e.getId(), recipientID, requestMessage);
		} catch (SQLException e1) {
			System.out.println("your notification failed");
			e1.printStackTrace();
		}
		
		ReimbursementImpl rimpl = new ReimbursementImpl();
		try {
			rimpl.updateReiState(r.getRei_id(), 6);
		} catch (SQLException e1) {
			System.out.println("updating rei state on additional controller failed");
			e1.printStackTrace();
		}
		
		if (e.getEmpType().equals("Employee"))
			return "/html/EmpHome.html";
		else
			return "/html/ApproversHome.html";
	}
	public static String EmpReadAddInfo(HttpServletRequest request, HttpServletResponse response) {
		Employee e = (Employee)request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		String getReiID = request.getParameter("name");
		int rei_id = Integer.parseInt(getReiID);
		ReimbursementImpl rimpl = new ReimbursementImpl();
		Reimbursement r = null;
		try {
			r = rimpl.readReimbursement(rei_id);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		AddInfoImpl aimpl = new AddInfoImpl();
		AdditionalInformation a = null;
		try {
			a  = aimpl.readAddInfo(rei_id, e.getId(),0);
			response.getWriter().write(new ObjectMapper().writeValueAsString(a));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getSession().setAttribute("Reimbursement", r);
		request.getSession().setAttribute("AdditionalInformation", a);
		
		return "/html/AddtionalInformation.html";
	}

	public static String AddInfoComplete(HttpServletRequest request) {
		Employee e = (Employee)request.getSession().getAttribute("User");
		Reimbursement r = (Reimbursement)request.getSession().getAttribute("Reimbursement");
		AdditionalInformation a = (AdditionalInformation)request.getSession().getAttribute("AdditionalInformation");
		
		String responseInfo = request.getParameter("comment");
		AddInfoImpl aimpl = new AddInfoImpl();
		try {
			aimpl.updateResponse(a.getAi_id(), responseInfo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (e.getEmpType().equals("Employee"))
			return "/html/EmpHome.html";
		else
			return "/html/ApproversHome.html";
	}
	public static String Notification(HttpServletRequest request, HttpServletResponse response) {
		Employee e = (Employee)request.getSession().getAttribute("User");
//		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
//			if (e.getEmpType().equals("Employee"))
//				return "/html/EmpHome.html";
//			else
//				return "/html/ApproversHome.html";
//		}
		AddInfoImpl aimpl = new AddInfoImpl();
		List <AdditionalInformation>addInfoList = new ArrayList<AdditionalInformation>();
		
		try {
			addInfoList = aimpl.getNotificationList(e.getId());
			response.getWriter().write(new ObjectMapper().writeValueAsString(addInfoList));	
			System.out.println(addInfoList.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "/html/Notification.html";
	}
	public static String NotificationForm(HttpServletRequest request, HttpServletResponse response) {
		Employee e = (Employee)request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) //returns user to home page if something happens
				return "/html/Notification.html";

		String getaiID = request.getParameter("name");
		int ai_id = Integer.parseInt(getaiID);

		AddInfoImpl aimpl = new AddInfoImpl();
		AdditionalInformation a = null;
		try {
			a  = aimpl.readAddInfo(ai_id);
			response.getWriter().write(new ObjectMapper().writeValueAsString(a));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int rei_id = a.getFrom_id();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		Reimbursement r = null;
		try {
			r = rimpl.readReimbursement(rei_id);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		request.getSession().setAttribute("Reimbursement", r);
		request.getSession().setAttribute("AdditionalInformation", a);
		
		return "/html/AddtionalInformation.html";
	}
}
