package com.revature.trms.models;

import java.time.LocalDate;

public class Reimbursement {

	private int reimb_id;
	private LocalDate request_date;
	private double cost;
	private String justification;
	private int work_time_missed;

	// Reimbursement status information
	private int status_id;
	private String status_name;
	private boolean urgent;
	private boolean sup_flag;
	private boolean dept_flag;
	private boolean benco_flag;
	private double fund_awarded;// better fit will be in reimbursement, not in status(?)
	private int next_id;

	// Employee Information
	private int emp_id;

	// Event details
	private int event_id;

	public Reimbursement() {

	}

	public Reimbursement(int reimb_id, LocalDate request_date, double cost, String justification, int work_time_missed,
			int status_id, String status_name, boolean urgent, boolean sup_flag, boolean dept_flag, boolean benco_flag,
			double fund_awarded, int next_id, int emp_id, int event_id) {
		super();
		this.reimb_id = reimb_id;
		this.request_date = request_date;
		this.cost = cost;
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
		this.event_id = event_id;
	}

	public Reimbursement(int status_id, int emp_id, int event_id, LocalDate request_date, double cost,
			String justification, int work_time_missed) {
		super();
		this.request_date = request_date;
		this.cost = cost;
		this.justification = justification;
		this.work_time_missed = work_time_missed;
		this.status_id = status_id;
		this.emp_id = emp_id;
		this.event_id = event_id;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (benco_flag ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (dept_flag ? 1231 : 1237);
		result = prime * result + emp_id;
		result = prime * result + event_id;
		temp = Double.doubleToLongBits(fund_awarded);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + next_id;
		result = prime * result + reimb_id;
		result = prime * result + ((request_date == null) ? 0 : request_date.hashCode());
		result = prime * result + status_id;
		result = prime * result + ((status_name == null) ? 0 : status_name.hashCode());
		result = prime * result + (sup_flag ? 1231 : 1237);
		result = prime * result + (urgent ? 1231 : 1237);
		result = prime * result + work_time_missed;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (benco_flag != other.benco_flag)
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (dept_flag != other.dept_flag)
			return false;
		if (emp_id != other.emp_id)
			return false;
		if (event_id != other.event_id)
			return false;
		if (Double.doubleToLongBits(fund_awarded) != Double.doubleToLongBits(other.fund_awarded))
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (next_id != other.next_id)
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (request_date == null) {
			if (other.request_date != null)
				return false;
		} else if (!request_date.equals(other.request_date))
			return false;
		if (status_id != other.status_id)
			return false;
		if (status_name == null) {
			if (other.status_name != null)
				return false;
		} else if (!status_name.equals(other.status_name))
			return false;
		if (sup_flag != other.sup_flag)
			return false;
		if (urgent != other.urgent)
			return false;
		if (work_time_missed != other.work_time_missed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", request_date=" + request_date + ", cost=" + cost
				+ ", justification=" + justification + ", work_time_missed=" + work_time_missed + ", status_id="
				+ status_id + ", status_name=" + status_name + ", urgent=" + urgent + ", sup_flag=" + sup_flag
				+ ", dept_flag=" + dept_flag + ", benco_flag=" + benco_flag + ", fund_awarded=" + fund_awarded
				+ ", next_id=" + next_id + ", emp_id=" + emp_id + ", event_id=" + event_id + "]";
	}

}
