package com.assignment.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.bean.Employee;
import com.assignment.bean.Grade;
import com.assignment.bean.Reimbursement;
import com.assignment.daoimpl.AddInfoImpl;
import com.assignment.daoimpl.GradeImpl;
import com.assignment.daoimpl.ReimbursementImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReviewController {

	public static String Form(HttpServletRequest request, HttpServletResponse response) {
		Employee e = (Employee) request.getSession().getAttribute("User");

		String tempID = request.getParameter("name");
		int rei_id = Integer.parseInt(tempID);
		Reimbursement r = null;
		ReimbursementImpl rimpl = new ReimbursementImpl();
		try {
			r = rimpl.readReimbursement(rei_id);
		} catch (SQLException e1) {
			System.out.println("review.do didn't work");
			e1.printStackTrace();
		}
		request.getSession().setAttribute("Reimbursement", r);
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(r));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("heyheyhey");
		return "/html/Review.html";
	}

	public static String Action(HttpServletRequest request) {
		Reimbursement r = (Reimbursement) request.getSession().getAttribute("Reimbursement");
		Employee e = (Employee) request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) {// returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		int reviewerID = e.getId();
		int rei_id = r.getRei_id();
		String requestInfo = "";
		String action = request.getParameter("eventTimeframe");
		int actVal = Integer.parseInt(action);
		ReimbursementImpl rimpl = new ReimbursementImpl();
		if (actVal == 0) {
			AddInfoImpl aimpl = new AddInfoImpl();
			requestInfo = request.getParameter("comment");
			switch (e.getEmpType()) {
			case "Direct Supervisor":
				try {
					aimpl.createAddInfo(rei_id, reviewerID, r.getEmp_id(), requestInfo);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			case "Department Head":
				try {
					if (reviewerID == r.getDsID() && reviewerID == r.getDhID()) {
						aimpl.createAddInfo(rei_id, reviewerID, r.getEmp_id(), requestInfo);
						rimpl.updateReiState(rei_id, 6);// 6 is waiting on response
					} else {
						aimpl.createAddInfo(rei_id, reviewerID, r.getDsID(), requestInfo);
						rimpl.updateReiState(rei_id, 6);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			case "Benefits Coordinator":
				try {
					aimpl.createAddInfo(rei_id, reviewerID, r.getDhID(), requestInfo);
					rimpl.updateReiState(rei_id, 6);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		} else if (actVal != 0 && reviewerID == r.getDsID() && reviewerID == r.getDhID()) {
			try {
				rimpl.updateDSAppr(rei_id, actVal);
				rimpl.updateDHAppr(rei_id, actVal);
				switch (actVal) {
				case 1:
					rimpl.updateReiState(rei_id, 3);
				case 2:
					rimpl.updateReiState(rei_id, 2);
				}
			} catch (SQLException e1) {
				System.out.println("double decision failed action.do");
				e1.printStackTrace();
			}
		} else if (actVal == 1) {

			switch (e.getEmpType()) {
			case "Direct Supervisor":
				try {
					rimpl.updateDSAppr(rei_id, actVal);
					rimpl.updateReiState(rei_id, 3);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			case "Department Head":
				try {
					rimpl.updateDHAppr(rei_id, actVal);
					rimpl.updateReiState(rei_id, 3);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			case "Benefits Coordinator":
				try {
					rimpl.updateBCAppr(rei_id, actVal);
					rimpl.updateReiState(rei_id, 3);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else {
			switch (e.getEmpType()) {
			case "Direct Supervisor":
				try {
					rimpl.updateDSAppr(rei_id, actVal);
					rimpl.updateReiState(rei_id, 2);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			case "Department Head":
				try {
					rimpl.updateDHAppr(rei_id, actVal);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			case "Benefits Coordinator":
				try {
					rimpl.updateBCAppr(rei_id, actVal);
					rimpl.updateReiState(rei_id, 8);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return "/html/ApproversHome.html";
	}

	public static String GoToFinalApproval(HttpServletRequest request, HttpServletResponse response) {
		Employee e = (Employee) request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) {// returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		String tempID = request.getParameter("name");
		int rei_id = Integer.parseInt(tempID);
		GradeImpl gimpl = new GradeImpl();
		Grade g = null;
		try {
			g = gimpl.readGrade(rei_id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getSession().setAttribute("Grade", g);
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(g));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "/html/FinalApproval.html";
	}

	public static String FinalApproval(HttpServletRequest request) {
		Employee e = (Employee) request.getSession().getAttribute("User");
		Grade g = (Grade)request.getSession().getAttribute("Grade");
		int rei_id = g.getReiID();
		String decision = request.getParameter("finalApproval");
		int decisionID = Integer.parseInt(decision);
		GradeImpl gimpl = new GradeImpl();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		Reimbursement r = null;
		try {
			r = rimpl.readReimbursement(rei_id);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			gimpl.updateGrade(g.getReiID(), decisionID, e.getId());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch (decisionID) {
		case 0:
			try {
				rimpl.updateReiState(rei_id, 3);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		case 1:
			try {
				rimpl.updateReiState(rei_id, 2);
				rimpl.updateAwardGranted(rei_id, r.getAwardRequested());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
	
}
