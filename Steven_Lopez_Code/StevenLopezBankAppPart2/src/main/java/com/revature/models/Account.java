package com.revature.models;

import java.time.LocalDate;

import com.revature.enums.Account_State;

public class Account {
	public static final double MINIMUM_DEPOSIT = 250.00;
	private int id;
	private double balance;
	private Account_State state;
	private int joint; //0 for Yes and 1 for no
	private int approvedBy;
	private LocalDate approvedDate;
	
	
	public Account() {
		super();
	}

	public Account(int id, double balance, Account_State state, int joint, int approvedBy,
			LocalDate approvedDate) {
		super();
		this.id = id;
		this.balance = balance;
		this.state = state;
		this.joint = joint;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
	}
	
	public Account(double balance, Account_State state, int joint, int approvedBy,
			LocalDate approvedDate) {
		super();
		this.balance = balance;
		this.state = state;
		this.joint = joint;
		this.approvedBy = approvedBy;
		this.approvedDate = approvedDate;
	}
	
	public Account(double balance, int joint) {
		super();
		this.balance = balance;
		this.joint = joint;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Account_State getState() {
		return state;
	}

	public void setState(Account_State state) {
		this.state = state;
	}

	public int getJoint() {
		return joint;
	}

	public void setJoint(int joint) {
		this.joint = joint;
	}

	public int getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDate getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedDate == null) ? 0 : approvedDate.hashCode());
		result = prime * result + approvedBy;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + joint;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Account other = (Account) obj;
		if (approvedDate == null) {
			if (other.approvedDate != null)
				return false;
		} else if (!approvedDate.equals(other.approvedDate))
			return false;
		if (approvedBy != other.approvedBy)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (joint != other.joint)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", state=" + state
				+ ", joint=" + joint + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + "]";
	}
	
}
