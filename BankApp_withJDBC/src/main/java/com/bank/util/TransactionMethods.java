package com.bank.util;

public interface TransactionMethods {
	public void viewAccount();
	public int withdraw(int w);
	public void deposit(int d);
	public int transfer(int accountid, int transferamount);
}

