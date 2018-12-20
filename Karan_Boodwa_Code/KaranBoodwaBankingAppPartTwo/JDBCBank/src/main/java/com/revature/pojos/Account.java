package com.revature.pojos;

import java.util.List;

/**
 * Account POJO
 * @author karan
 *
 */
public class Account {
	private int accountNumber;
	private double balance;
	private List<String> accountHolders;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List<String> getAccountHolders() {
		return accountHolders;
	}
	public void setAccountHolders(List<String> accountHolders) {
		this.accountHolders = accountHolders;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountHolders == null) ? 0 : accountHolders.hashCode());
		result = prime * result + accountNumber;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (accountHolders == null) {
			if (other.accountHolders != null)
				return false;
		} else if (!accountHolders.equals(other.accountHolders))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", accountHolders=" + accountHolders
				+ "]";
	}
	public Account(int accountNumber, double balance, List<String> accountHolders) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountHolders = accountHolders;
	}
	public Account() {
		super();
	}
	
	
}
