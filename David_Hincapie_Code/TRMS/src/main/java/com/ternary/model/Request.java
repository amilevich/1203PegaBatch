package com.ternary.model;

import java.sql.Date;

public class Request {

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int requestId;
	private int empId;
	private String eventName;
	private int eventId;
	private int urgent;
	private String status;
	private Date dateCompleted;
	private String justification;
	private int approvalSupervisor;
	private int approvalDeptHead;
	private int approvalBenco;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getUrgent() {
		return urgent;
	}

	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getApprovalSupervisor() {
		return approvalSupervisor;
	}

	public void setApprovalSupervisor(int approvalSupervisor) {
		this.approvalSupervisor = approvalSupervisor;
	}

	public int getApprovalDeptHead() {
		return approvalDeptHead;
	}

	public void setApprovalDeptHead(int approvalDeptHead) {
		this.approvalDeptHead = approvalDeptHead;
	}

	public int getApprovalBenco() {
		return approvalBenco;
	}

	public void setApprovalBenco(int approvalBenco) {
		this.approvalBenco = approvalBenco;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", empId=" + empId + ", eventId=" + eventId + ", urgent=" + urgent
				+ ", status=" + status + ", dateCompleted=" + dateCompleted + ", justification=" + justification
				+ ", approvalSupervisor=" + approvalSupervisor + ", approvalDeptHead=" + approvalDeptHead
				+ ", approvalBenco=" + approvalBenco + "]";
	}
}
