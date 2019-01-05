package com.revature.bean;

import java.sql.Date;

public class Employee {
	
	int employeeId;
	String firstName;
	String lastName;
	String email;
	int departmentId;
	int managerId;
	int eventId;
	String eventLocation;
	double eventCost;
	double availableReimbursement;
	String appStatus;
	Date eventDate;
	int userId;
	String justification;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeId, String firstName, String lastName, String email, int departmentId, int managerId,
			int eventId, String eventLocation, double eventCost, double availableReimbursement, String appStatus,
			Date eventDate, int userId, String justification) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.departmentId = departmentId;
		this.managerId = managerId;
		this.eventId = eventId;
		this.eventLocation = eventLocation;
		this.eventCost = eventCost;
		this.availableReimbursement = availableReimbursement;
		this.appStatus = appStatus;
		this.eventDate = eventDate;
		this.userId = userId;
		this.justification = justification;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public double getEventCost() {
		return eventCost;
	}

	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
	}

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", departmentId=" + departmentId + ", managerId=" + managerId + ", eventId=" + eventId
				+ ", eventLocation=" + eventLocation + ", eventCost=" + eventCost + ", availableReimbursement="
				+ availableReimbursement + ", appStatus=" + appStatus + ", eventDate=" + eventDate + ", userId="
				+ userId + ", justification=" + justification + "]";
	}
}
