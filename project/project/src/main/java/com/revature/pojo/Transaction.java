package com.revature.pojo;

import java.sql.Date;

public class Transaction {
	private int transactionID;
	private int accountID;
	private Date date;
	private double change;
	
	public Transaction(int transactionID, int accountID, Date date, double change) {
		super();
		this.transactionID = transactionID;
		this.accountID = accountID;
		this.date = date;
		this.change = change;
	}
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
}