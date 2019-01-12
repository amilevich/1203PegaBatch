package com.revature.bean;

import java.sql.Blob;

public class Application {
	
	int appId;
	String firstName;
	String lastName;
	String email;
	int departmentId;
	int managerId;
	int eventId;
	String eventLocation;
	double eventCost;
	String appStatus;
	String eventDate;
	int userId;
	String gradeFormat;
	String justification;
	String summary;
	int hoursMissed;
	int superviserApproved;
	int departmentheadApproved;
	int bencoApproved;
	Blob attachment;
	Blob approvedAttahment;

	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Application(int appId, String firstName, String lastName, String email, int departmentId, int managerId,
			int eventId, String eventLocation, double eventCost, String appStatus,
			String eventDate, int userId, String gradeFormat, String justification, String summary, int hoursMissed,
			int superviserApproved, int departmentheadApproved, int bencoApproved, Blob attachment,
			Blob approvedAttahment) {
		super();
		this.appId = appId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.departmentId = departmentId;
		this.managerId = managerId;
		this.eventId = eventId;
		this.eventLocation = eventLocation;
		this.eventCost = eventCost;
		this.appStatus = appStatus;
		this.eventDate = eventDate;
		this.userId = userId;
		this.gradeFormat = gradeFormat;
		this.justification = justification;
		this.summary = summary;
		this.hoursMissed = hoursMissed;
		this.superviserApproved = superviserApproved;
		this.departmentheadApproved = departmentheadApproved;
		this.bencoApproved = bencoApproved;
		this.attachment = attachment;
		this.approvedAttahment = approvedAttahment;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
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

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(int hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public int getSuperviserApproved() {
		return superviserApproved;
	}

	public void setSuperviserApproved(int superviserApproved) {
		this.superviserApproved = superviserApproved;
	}

	public int getDepartmentheadApproved() {
		return departmentheadApproved;
	}

	public void setDepartmentheadApproved(int departmentheadApproved) {
		this.departmentheadApproved = departmentheadApproved;
	}

	public int getBencoApproved() {
		return bencoApproved;
	}

	public void setBencoApproved(int bencoApproved) {
		this.bencoApproved = bencoApproved;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

	public Blob getApprovedAttahment() {
		return approvedAttahment;
	}

	public void setApprovedAttahment(Blob approvedAttahment) {
		this.approvedAttahment = approvedAttahment;
	}

	@Override
	public String toString() {
		return "Application [appId=" + appId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", departmentId=" + departmentId + ", managerId=" + managerId + ", eventId=" + eventId
				+ ", eventLocation=" + eventLocation + ", eventCost=" + eventCost + ", appStatus=" + appStatus
				+ ", eventDate=" + eventDate + ", userId=" + userId + ", gradeFormat=" + gradeFormat
				+ ", justification=" + justification + ", summary=" + summary + ", hoursMissed=" + hoursMissed
				+ ", superviserApproved=" + superviserApproved + ", departmentheadApproved=" + departmentheadApproved
				+ ", bencoApproved=" + bencoApproved + ", attachment=" + attachment + ", approvedAttahment="
				+ approvedAttahment + "]";
	}

	
}
