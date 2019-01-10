package com.assignment.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.bean.Employee;
import com.assignment.bean.Reimbursement;
import com.assignment.daoimpl.ReimbursementImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HomeController {
	public static String Home(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String EmpJSON(HttpServletRequest request, HttpServletResponse response) {
		Employee emp = (Employee) request.getSession().getAttribute("User");// returns object
		int emp_id = emp.getId();
		String empType = emp.getEmpType();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		List<Reimbursement> homePageList = new ArrayList<Reimbursement>();
		try {
			switch (empType) {
			case "Employee":
				System.out.println("read this");
				homePageList = rimpl.getPendingEmpList(emp_id);
				break;
			case "Direct Supervisor":
				homePageList = rimpl.getSubmittedReviewList(emp_id);
				break;
			case "Department Head":
				homePageList = rimpl.getDHPendingReviewList(emp_id, "Department Head");
				List<Reimbursement> tempList = rimpl.getSubmittedReviewList(emp_id);
				homePageList.addAll(tempList);
				break;
			case "Benefits Coordinator":
				homePageList = rimpl.getDHPendingReviewList(emp_id, "Benefits Coordinator");
				break;
			}
			System.out.println(empType + " this");
			response.getWriter().write(new ObjectMapper().writeValueAsString(homePageList));
			// System.out.println("{\"Reimbursement\":"+jsonHomePageList+"}");
			// object mapper converts object into string to be understood by Javascript
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String HistoryJSON(HttpServletRequest request, HttpServletResponse response) {// to get list of all
																								// past reimbursements
		Employee emp = (Employee) request.getSession().getAttribute("User");// returns object
		int emp_id = emp.getId();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		// convert your list to json

		List<Reimbursement> historyPageList = new ArrayList<Reimbursement>();

		try {
			historyPageList = rimpl.getPendingHistoryList(emp_id);

			response.getWriter().write(new ObjectMapper().writeValueAsString(historyPageList));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static String NavJSON(HttpServletRequest request, HttpServletResponse response) {
		Employee emp = (Employee)request.getSession().getAttribute("User");//returns object
		System.out.println("employee");
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(emp));
			System.out.println(response);
			//object mapper converts object into string to be understood by Javascript
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
