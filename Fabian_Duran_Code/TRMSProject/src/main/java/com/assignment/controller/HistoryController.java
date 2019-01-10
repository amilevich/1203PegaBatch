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

public class HistoryController {

	public static String Table(HttpServletRequest request, HttpServletResponse response) {
		Employee e = (Employee)request.getSession().getAttribute("User");
		if (request.getMethod().equals("GET")) {//returns user to home page if something happens
			if (e.getEmpType().equals("Employee"))
				return "/html/EmpHome.html";
			else
				return "/html/ApproversHome.html";
		}
		List <Reimbursement>reiList = new ArrayList<Reimbursement>();
		ReimbursementImpl rimpl = new ReimbursementImpl();
		try {
			reiList = rimpl.getPendingHistoryList(e.getId());
			response.getWriter().write(new ObjectMapper().writeValueAsString(reiList));
		} catch (SQLException e1) {
			System.out.println("History.do messed");
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			System.out.println("History.do messed");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("History.do messed");
			e1.printStackTrace();
		}
		return "/html/History.html";
	}

}
