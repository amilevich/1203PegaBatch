package com.assignment.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.assignment.bean.Employee;
import com.assignment.bean.Grade;
import com.assignment.bean.Reimbursement;
import com.assignment.daoimpl.GradeImpl;
import com.assignment.daoimpl.ReimbursementImpl;

public class CompletionController {//UPLOAD NEEDS TO BE DONE!!!!!!!
	//objective of this:
	//create grade entry and tie to reimbursement
	//add finish date to reimbursements
	//change reimbursement status to Waiting on Final Approval
	public static String Form(HttpServletRequest request) {
		Employee e = (Employee)request.getSession().getAttribute("User");
		Reimbursement r = (Reimbursement)request.getSession().getAttribute("Reimbursement");
		int tempID =r.getRei_id();
		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		String eventFinishDate = request.getParameter("eventStart");//idk, ask West why its called start
		String gradeFormat = request.getParameter("eventType");//should be grade format
		GradeImpl gimpl = new GradeImpl();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		Grade g = null;
		try {//create grade id and info and retrieves info
			gimpl.createGrade(tempID, gradeFormat);
			g = gimpl.readGrade(tempID);
		} catch (SQLException e1) {
			System.out.println("Your grade impl sucks # comp Controller");
			e1.printStackTrace();
		}
		try {//stores grade id into reimbursement table
			rimpl.updateGradeID(tempID, g.getGradeID());
			rimpl.updateEventFinishDate(tempID, eventFinishDate);
			rimpl.updateReiState(tempID, 7);
		} catch (SQLException e1) {
			System.out.println("Comp Controller, rimpl sucks");
			e1.printStackTrace();
		}
		
		
		
		
		if (e.getEmpType().equals("Employee"))
			return "/html/EmpHome.html";
		else
			return "/html/ApproversHome.html";
	}
}
