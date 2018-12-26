package com.bank.beans;

import com.bank.util.TransactionMethods;

public class Account implements TransactionMethods {
	private int accountId = 0;
	private String createdDate = "";
	private String accountType = "";
	private int activated = 0;
	private int balance = 0; // placeholder for the code that holds the balance in a .txt, .csv, or cookie,
								// or log4j

	public Account(int accID) {
		this.accountId = accID;
	}

	public Account(int accID, String createdDate, String accountType, int activated, int bal) {
		this.accountId = accID;
		this.createdDate = createdDate;
		this.accountType = accountType;
		this.activated = activated;
		this.balance = bal;
	}

	public void viewAccount() {
		System.out.println("The account balance for Account #" + this.accountId + " is $" + this.balance);
	}

	public int getAccountID() {
		return this.accountId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int isActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public String getStringAccountID() {
		String thing = "00" + this.accountId;
		return thing;
	}

	public int withdraw(int w) {
		// this.balance -= w;
		System.out.println(w + "88888888888888");
		if (this.balance <= 0) {

			System.out.println("You've overdrafted your account. Your balance is: $" + this.balance);
		} else {

			System.out.println("You've withdrawn $" + this.balance + "." + "Your balance is: $" + this.balance);
		}

		return this.balance; // this will return the result to the calling method where ever
	}

	public void deposit(int d) { // this will only update the amount in the bank
		if (this.balance <= 0) {
			System.out
					.println("Deposit completed. Due to previous overdraft activity on your account, your balance is: $"
							+ this.balance);
			this.balance += d;
		} else {
			System.out.println("You've deposited $" + this.balance + "." + "Your balance is: $" + this.balance);
			this.balance += d;
		}
	}

	public int transfer(int accountid, int transferamount) { // a regular expression for input here would be nice
		this.balance -= transferamount;
		Account acc = new Account(accountid);
		acc.deposit(transferamount);
		return this.balance;
	}

	public void cancel() {
		System.out.println(
				"Your account will be processed for cancellation. Please complete our brief 10 question survey so we can better serve our customers.");
	}
}