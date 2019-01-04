package com.revature.trms.models;

import java.time.LocalDate;

public class Reimbursement {

	private int reimb_id;
	private LocalDate request_date;
	
	private String justification;
	private int work_time_missed;
	private double fund_awarded;
	
	
	// Reimbursement status information
	private int status_id;
	private String status_name;
	private boolean urgent;
	private boolean sup_flag;
	private boolean dept_flag;
	private boolean benco_flag;
	private int next_id;

	// Employee Information
	private int emp_id;

	// Event details
	private Event event;

	public Reimbursement() {

	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public LocalDate getRequest_date() {
		return request_date;
	}

	public void setRequest_date(LocalDate request_date) {
		this.request_date = request_date;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public int getWork_time_missed() {
		return work_time_missed;
	}

	public void setWork_time_missed(int work_time_missed) {
		this.work_time_missed = work_time_missed;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

	public boolean isUrgent() {
		return urgent;
	}

	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}

	public boolean isSup_flag() {
		return sup_flag;
	}

	public void setSup_flag(boolean sup_flag) {
		this.sup_flag = sup_flag;
	}

	public boolean isDept_flag() {
		return dept_flag;
	}

	public void setDept_flag(boolean dept_flag) {
		this.dept_flag = dept_flag;
	}

	public boolean isBenco_flag() {
		return benco_flag;
	}

	public void setBenco_flag(boolean benco_flag) {
		this.benco_flag = benco_flag;
	}

	public double getFund_awarded() {
		return fund_awarded;
	}

	public void setFund_awarded(double fund_awarded) {
		this.fund_awarded = fund_awarded;
	}

	public int getNext_id() {
		return next_id;
	}

	public void setNext_id(int next_id) {
		this.next_id = next_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", request_date=" + request_date + ", justification="
				+ justification + ", work_time_missed=" + work_time_missed + ", status_id=" + status_id
				+ ", status_name=" + status_name + ", urgent=" + urgent + ", sup_flag=" + sup_flag + ", dept_flag="
				+ dept_flag + ", benco_flag=" + benco_flag + ", fund_awarded=" + fund_awarded + ", next_id=" + next_id
				+ ", emp_id=" + emp_id + ", event=" + event + "]";
	}

	public Reimbursement(int reimb_id, LocalDate request_date, String justification, int work_time_missed,
			int status_id, String status_name, boolean urgent, boolean sup_flag, boolean dept_flag, boolean benco_flag,
			double fund_awarded, int next_id, int emp_id, Event event) {
		super();
		this.reimb_id = reimb_id;
		this.request_date = request_date;
		this.justification = justification;
		this.work_time_missed = work_time_missed;
		this.status_id = status_id;
		this.status_name = status_name;
		this.urgent = urgent;
		this.sup_flag = sup_flag;
		this.dept_flag = dept_flag;
		this.benco_flag = benco_flag;
		this.fund_awarded = fund_awarded;
		this.next_id = next_id;
		this.emp_id = emp_id;
		this.event = event;
	}
	

	

}
