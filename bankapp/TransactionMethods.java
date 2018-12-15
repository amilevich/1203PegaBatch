package com.projectz.bankapp;

public interface TransactionMethods {
	public void viewAccount();
	public double withdraw(double w);
	public void deposit(double d);
	public double transfer(int accountid, double transferamount);
}
