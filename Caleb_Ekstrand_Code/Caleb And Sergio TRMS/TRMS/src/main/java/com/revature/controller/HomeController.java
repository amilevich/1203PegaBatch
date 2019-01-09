package com.revature.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.TicketDAOImpl;
import com.revature.model.User;

public class HomeController {

	public static String Home(HttpServletRequest request, HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("User");
		System.out.println(user.toString());
		try {
			response.getWriter().write(new ObjectMapper().writeValueAsString(user));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/html/Home.html";
	}

	public static String Approve(HttpServletRequest request) {
		System.out.println("in Approve");
//		System.out.println(request.getParameterValues("id"));           
//		System.out.println(request.getParameterValues("stage"));           
		TicketDAOImpl tdi = new TicketDAOImpl();
		tdi.approveTicket(Integer.parseInt(request.getParameter("id")), request.getParameter("stage"));
		User user = (User)request.getSession().getAttribute("User");
		user.setTickets(tdi.selectTicketsByUserId(user.getUserId()));
		request.getSession().setAttribute("User", user);
		return "/html/Home.html";
	}

	public static String Deny(HttpServletRequest request) {
		System.out.println("in Deny");
//		System.out.println(request.getParameterValues("id"));           
//		System.out.println(request.getParameterValues("stage"));           
		TicketDAOImpl tdi = new TicketDAOImpl();
		tdi.denyTicket(Integer.parseInt(request.getParameter("id")));
		User user = (User)request.getSession().getAttribute("User");
		user.setTickets(tdi.selectTicketsByUserId(user.getUserId()));
		request.getSession().setAttribute("User", user);
		return "/html/Home.html";
	}

	public static String Cancel(HttpServletRequest request) {
		System.out.println("in Cancel");
//		System.out.println(request.getParameterValues("id"));           
//		System.out.println(request.getParameterValues("stage"));           
		TicketDAOImpl tdi = new TicketDAOImpl();
		tdi.cancelTicket(Integer.parseInt(request.getParameter("id")));
		User user = (User)request.getSession().getAttribute("User");
		user.setTickets(tdi.selectTicketsByUserId(user.getUserId()));
		request.getSession().setAttribute("User", user);
		return "/html/Home.html";
	}

}
