package com.revature.model;

import java.util.ArrayList;


public class Ticket {
	private int ticketId;
	private String date;
	private String time;
	private String location;
	private String description;
	private String event;
	private int cost;
	private int gfId;
	private String gfPassing;
	private String justification;
	private int userId;
	private String status;
	private String stage;
	private ArrayList<Attachment> pictures;
	
	public Ticket(int ticketId, String date, String time, String location, String description, String event, int cost,
			int gfId, String gfPassing, String justification, int userId, String status, String stage,
			ArrayList<Attachment> pictures) {
		super();
		this.ticketId = ticketId;
		this.date = date;
		this.time = time;
		this.location = location;
		this.description = description;
		this.event = event;
		this.cost = cost;
		this.gfId = gfId;
		this.gfPassing = gfPassing;
		this.justification = justification;
		this.userId = userId;
		this.status = status;
		this.stage = stage;
		this.pictures = pictures;
	}
	
	public ArrayList<Attachment> getPictures() {
		return pictures;
	}

	public void setPictures(ArrayList<Attachment> pictures) {
		this.pictures = pictures;
	}

	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getGfId() {
		return gfId;
	}
	public void setGfId(int gfId) {
		this.gfId = gfId;
	}
	public String getGfPassing() {
		return gfPassing;
	}
	public void setGfPassing(String gfPassing) {
		this.gfPassing = gfPassing;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", date=" + date + ", time=" + time + ", location=" + location
				+ ", description=" + description + ", event=" + event + ", cost=" + cost + ", gfId=" + gfId
				+ ", gfPassing=" + gfPassing + ", justification=" + justification + ", userId=" + userId + ", status="
				+ status + ", stage=" + stage + "]";
	}
	public Ticket(int ticketId, String date, String timeStamp, String location, String description, String event, int cost,
			int gfId, String gfPassing, String justification, int userId, String status, String stage) {
		super();
		this.ticketId = ticketId;
		this.date = date;
		this.time = timeStamp;
		this.location = location;
		this.description = description;
		this.event = event;
		this.cost = cost;
		this.gfId = gfId;
		this.gfPassing = gfPassing;
		this.justification = justification;
		this.userId = userId;
		this.status = status;
		this.stage = stage;
	}
	public Ticket() {
		// TODO Auto-generated constructor stub
	}
}
