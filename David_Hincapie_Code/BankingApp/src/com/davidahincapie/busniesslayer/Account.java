package com.davidahincapie.busniesslayer;

import java.util.UUID;

public class Account {

	final private UUID accountId;
	private double balance = 0.00;

	private boolean isJointAccount = false;

	public Account() {
		super();
		this.accountId = UUID.randomUUID();
	}

	public Account(double balance, boolean isJointAccount) {
		super();
		this.accountId = UUID.randomUUID();
		this.balance = balance;
		this.isJointAccount = isJointAccount;
	}

	public void deposit(double deposit) {
		this.balance += deposit;
	}

	public void withdraw(double withdrawal) {
		this.balance -= withdrawal;
	}

	public void transferFunds(Account account, double transfer) {
		this.withdraw(transfer);
		account.deposit(transfer);
	}

	public UUID getAccountId() {
		return accountId;
	}

	// public void setAccountId(UUID accountId) {
	// this.accountId = accountId;
	// }

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
		return "Account [accountId=" + accountId + ", balance=" + balance + ", isJointAccount=" + isJointAccount + "]";
	}

}
