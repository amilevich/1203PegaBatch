package com.revature.beans;

public class Accounts {
	private int accountID;
	private String accounType;
	private double balance;
	private String status;
	private int userID;
	
	public Accounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accounts(int accountID, String accounType, double balance, String status, int userID) {
		super();
		this.accountID = accountID;
		this.accounType = accounType;
		this.balance = balance;
		this.status = status;
		this.userID = userID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccounType() {
		return accounType;
	}

	public void setAccounType(String accounType) {
		this.accounType = accounType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Accounts [accountID = " + accountID + ", accounType = " + accounType + ", balance = " + balance + ", status = "
				+ status + ", userID = " + userID + "]";
	}
	
	
}
