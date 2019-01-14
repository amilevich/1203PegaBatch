package com.ternary.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ternary.daoimpl.RequestDaoImpl;
import com.ternary.model.Employee;

public class DetailsController {

	public static String Details(HttpServletRequest request) {
		
		Employee employee = (Employee) request.getSession().getAttribute("Employee");

		if (request.getMethod().equals("GET")) {
			return "/html/login.html";
		} else {
			if (!(employee.getJobTitle().contains("Staff"))) {
                return "/html/managerhome.html";
            } else {
                return "/html/home.html";
            }
		}

	}

	public static String CancelRequest(HttpServletRequest request) {
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		RequestDaoImpl requestDaoImpl = new RequestDaoImpl();
		requestDaoImpl.cancelRequest(requestId);

		return "/html/home.do";
	}

	public static String ApprovalJSON(HttpServletRequest request, HttpServletResponse response) {
		
		Employee employee = (Employee) request.getSession().getAttribute("Employee");
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		
		RequestDaoImpl requestDao = new RequestDaoImpl();
		int dbStatus = requestDao.updateApproval(requestId, employee);
		
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(dbStatus));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return null;
	}

	public static String DenyJSON(HttpServletRequest request, HttpServletResponse response) {
		
		Employee employee = (Employee) request.getSession().getAttribute("Employee");
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		
		RequestDaoImpl requestDao = new RequestDaoImpl();
		int dbStatus = requestDao.updateDeny(requestId, employee);
		
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(dbStatus));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return null;
	}

}