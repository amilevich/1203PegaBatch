package com.revature.businesslayer;

import java.util.UUID;

public class Account {

	private int id;
	private String accountid = UUID.randomUUID().toString();
	private double balance = 0.00;
	private boolean isJointAccount = false;

	public Account() {
		super();
	}

	public Account(String accountid, double balance, boolean isJointAccount) {
		super();
		this.accountid = accountid;
		this.balance = balance;
		this.isJointAccount = isJointAccount;
	}

	public Account(int id, String accountid, double balance, boolean isJointAccount) {
		super();
		this.id = id;
		this.accountid = accountid;
		this.balance = balance;
		this.isJointAccount = isJointAccount;
	}

	public void deposit(double deposit) {
		if (deposit < 0) {
			System.out.println("You must deposit a positive amount.");
		} else {
			this.balance += deposit;
		}
	}

	public boolean withdraw(double withdrawal) {
		boolean withdrew = false;
		if (getBalance() - withdrawal < 0) {
			System.out.println("Funds are not availbe to withdraw $" + withdrawal);
			return withdrew = false;
		} else if (withdrawal < 0) {
			System.out.println("You must withdraw a positive amount.");
			return withdrew = false;
		} else {
			this.balance -= withdrawal;
			return withdrew = true;
		}
	}

	public void transferFunds(Account account, double transfer) {
		this.withdraw(transfer);
		account.deposit(transfer);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isJointAccount() {
		return isJointAccount;
	}

	public void setJointAccount(boolean isJointAccount) {
		this.isJointAccount = isJointAccount;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountid=" + accountid + ", balance=" + balance + ", isJointAccount="
				+ isJointAccount + "]";
	}

}
