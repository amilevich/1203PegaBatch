package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.Ticket;

public interface TicketDAO {
	public void insertTicket(Ticket ticket);
	public ArrayList<Ticket> selectTicketsByUserId(int userId);
	public ArrayList<Ticket> selectTicketsForApprovalByUserId(int userId);
	public void approveTicket(int ticketId, String stage);
	public void denyTicket(int ticketId);
	public void cancelTicket(int ticketId);
}
