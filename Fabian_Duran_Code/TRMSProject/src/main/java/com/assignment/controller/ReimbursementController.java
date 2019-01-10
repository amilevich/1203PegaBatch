package com.assignment.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.assignment.bean.Employee;
import com.assignment.daoimpl.EmployeeImpl;
import com.assignment.daoimpl.ReimbursementImpl;

public class ReimbursementController {
	
	private static double adjustedAward(String type, double amount) {
	switch(type) {
	case "University Course":
		amount = amount *0.8;
		break;
	case "Seminar":
		amount = amount *0.6;
		break;
	case "Certification Preparation Class":
		amount = amount *0.75;
		break;
	case "Certification":
		amount = amount *1;
		break;
	case "Technical Training Class":
		amount = amount *0.9;
		break;
	case "Other":
		amount = amount *0.3;
		break;
	}	
		return amount;
	}
	
	public static String Form(HttpServletRequest request) {
		int urgent = 0;
		System.out.println("hit something2!");
		Employee e = (Employee)request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		//meats and potatoes
		//employee info

		int empID = e.getId();
		System.out.println(empID);
		String firstname = request.getParameter("firstName");
		String middlename = request.getParameter("middleName");
		String lastname = request.getParameter("lastName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String tempZip = request.getParameter("zip");
		int zip = Integer.parseInt(tempZip);
		String tempPN = request.getParameter("phoneNumber");
		long phonenumber = Long.parseLong(tempPN);
		//reimbursement info
		String eventType = request.getParameter("eventType");
		String eventTitle = request.getParameter("eventTitle");
		String eventLocation = request.getParameter("eventLocation");
		//date stuff, needs testing
		String date = request.getParameter("eventStart");
		String time = request.getParameter("eventTime");
		String award = request.getParameter("eventAward");

		double awardRequest = Double.parseDouble(award);
		awardRequest = adjustedAward(eventType,awardRequest);//adjust award automatically
		//for the eventstart date
		String dateTime = date+" "+time;
		String description = request.getParameter("comment");
		String submitDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		EmployeeImpl eimpl = new EmployeeImpl();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		Date eventT = null;
		Date submit = null;
		try {
			eventT = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			submit =new SimpleDateFormat("yyyy-MM-dd").parse(submitDate);
		} catch (ParseException e2) {
			e2.printStackTrace();
		} 
		
		long i = eventT.getTime();
		long b = submit.getTime();
		if (i-b < 1209600000)
			urgent = 1;
		try {
			eimpl.updateAddress(empID, address, city, state, zip);
		} catch (SQLException e1) {
			System.out.println("this failed");
			e1.printStackTrace();
		}
		try {
			rimpl.createReimbursement(empID, eventType, awardRequest, submitDate, dateTime, e.getDsID(), e.getDhID(), "Submitted", urgent, description, eventTitle);
		} catch (SQLException e1) {
			System.out.println("this also failed");
			e1.printStackTrace();
		}


		if (e.getEmpType().equals("Employee"))
			return "/html/EmpHome.html";
		else
			return "/html/ApproversHome.html";
		
	}

}
