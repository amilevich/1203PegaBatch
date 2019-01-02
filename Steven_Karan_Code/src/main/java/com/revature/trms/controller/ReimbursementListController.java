package com.revature.trms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.trms.daoimpls.ReimbursementDAOImpl;
import com.revature.trms.models.Employee;
import com.revature.trms.models.Reimbursement;

public class ReimbursementListController {

	public static String ReimbursementList(HttpServletRequest req) {

		return "/html/reimbursement-list.html";
	}

	public static String ReimbursementListJSON(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("Processing Reimbursement List");
		//Ensure that user is authenticated...
		Employee emp = (Employee) req.getSession().getAttribute("Employee");
		//System.out.println(emp);
		if(emp == null) {
			return "/html/login.html";
		}
		try {
			ArrayList<Reimbursement> reimb_list = new ReimbursementDAOImpl().getAllReimbursementByEmployee(emp.getEmp_id());
			//System.out.println(new ObjectMapper().writeValueAsString(reimb_list));
			resp.getWriter().write(new ObjectMapper().writeValueAsString(reimb_list));
		}catch(JsonProcessingException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
