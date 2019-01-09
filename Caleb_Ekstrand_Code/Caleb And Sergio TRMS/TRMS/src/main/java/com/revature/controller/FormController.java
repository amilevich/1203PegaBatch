package com.revature.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.TicketDAOImpl;
import com.revature.model.Ticket;
import com.revature.model.User;

public class FormController {
	
	public static String Form(HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("User");
		Ticket ticket = new Ticket(1,request.getParameter("date"), request.getParameter("time"), request.getParameter("location"),
				request.getParameter("description"), request.getParameter("event"), Integer.parseInt(request.getParameter("cost")), 
				Integer.parseInt(request.getParameter("gf")), request.getParameter("pass-grade"), request.getParameter("just"), 
				user.getUserId(), "status", "stage");
		System.out.println(ticket.getDate());
		TicketDAOImpl tdi = new TicketDAOImpl();
		tdi.insertTicket(ticket);
		user.setTickets(tdi.selectTicketsByUserId(user.getUserId()));
		request.getSession().setAttribute("User", user);
		return "/html/Home.html";
	}

}
