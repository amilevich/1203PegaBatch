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

public class ActionController {

	public static String CancelRei(HttpServletRequest request, HttpServletResponse response) {
		
		Employee e = (Employee)request.getSession().getAttribute("User");
//		System.out.println("WHY" + e);
//		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
//			if (e.getEmpType().equals("Employee"))
//				return "/html/EmpHome.html";
//			else
//				return "/html/ApproversHome.html";
//		}
		
		String ID = request.getParameter("name");
		int rei_id = Integer.parseInt(ID);
		ReimbursementImpl rimpl = new ReimbursementImpl();
		List <Reimbursement>reiList = new ArrayList<Reimbursement>();
		
		
		try {
			rimpl.updateReiState(rei_id, 5);
			reiList = rimpl.getPendingEmpList(e.getId());
			response.getWriter().write(new ObjectMapper().writeValueAsString(reiList));
		} catch (SQLException e1) {
			System.out.println("Cancelled Failed");
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (e.getEmpType().equals("Employee"))
			return "/html/EmpHome.html";
		else
			return "/html/ApproversHome.html";
	}

	public static String AcceptChange(HttpServletRequest request, HttpServletResponse response) {//accepting the change simply changes reimbursement state to waiting event completion
		Employee e = (Employee)request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		
		
		String ID = request.getParameter("name");
		int rei_id = Integer.parseInt(ID);
		ReimbursementImpl rimpl = new ReimbursementImpl();
		List <Reimbursement>reiList = new ArrayList<Reimbursement>();
		
		try {
			rimpl.updateReiState(rei_id, 8);
			reiList = rimpl.getPendingEmpList(e.getId());
			response.getWriter().write(new ObjectMapper().writeValueAsString(reiList));
		} catch (SQLException e1) {
			System.out.println("Cancelled Failed");
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (e.getEmpType().equals("Employee"))
			return "/html/EmpHome.html";
		else
			return "/html/ApproversHome.html";
	}



	

}
