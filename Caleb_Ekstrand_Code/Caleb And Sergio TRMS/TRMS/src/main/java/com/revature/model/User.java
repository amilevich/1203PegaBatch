package com.revature.model;

import java.util.ArrayList;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private int dsId;
	private int depId;
	private ArrayList<Ticket> tickets;
	private ArrayList<Ticket> ticketsToApprove;
	public User(int userId, String firstName, String lastName, int dsId, int depId, ArrayList<Ticket> tickets,
			ArrayList<Ticket> ticketsToApprove) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dsId = dsId;
		this.depId = depId;
		this.tickets = tickets;
		this.ticketsToApprove = ticketsToApprove;
	}
	

	public ArrayList<Ticket> getTicketsToApprove() {
		return ticketsToApprove;
	}

	public void setTicketsToApprove(ArrayList<Ticket> ticketsToApprove) {
		this.ticketsToApprove = ticketsToApprove;
	}
	public ArrayList<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getDsId() {
		return dsId;
	}
	public void setDsId(int dsId) {
		this.dsId = dsId;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dsId=" + dsId
				+ ", depId=" + depId + ", tickets=" + tickets + ", ticketsToApprove=" + ticketsToApprove + "]";
	}
	public User(int userId, String firstName, String lastName, int dsId, int depId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dsId = dsId;
		this.depId = depId;
	}
	
}
