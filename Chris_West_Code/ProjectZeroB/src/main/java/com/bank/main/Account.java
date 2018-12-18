package com.bank.main;

import java.util.ArrayList;

public class Account {
	int accountID;
	double balance;
	String accountType;
	int accountOpen;
	ArrayList<Integer> accounts;
	
	public Account(int accountID, double balance, String accountType, int accountOpen, ArrayList<Integer> accounts) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.accountType = accountType;
		this.accountOpen = accountOpen;
		this.accounts = accounts;
	}
	public double withdrawMoney(double amount) {
		double bal = getBalance();
		double withdraw = bal - amount;
		return withdraw;
	}
	public double depositMoney(double amount) {
		double bal = getBalance();
		double deposit = bal + amount;
		return deposit;
		
	}
	public double transferFunds(double amount) {
		double bal = getBalance();
		double transfer = bal - amount;
		return transfer;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountOpen() {
		return accountOpen;
	}
	public void setAccountOpen(int accountOpen) {
		this.accountOpen = accountOpen;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	
	
}
