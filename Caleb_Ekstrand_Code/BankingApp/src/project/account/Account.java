package project.account;

import java.util.ArrayList;

public class Account {
	private int accountNumber;
	private double balance = 0;
	private String type;
	private boolean activated = false;
	private static ArrayList<Account> accounts = new ArrayList<>();
	
	public Account(){
		if (accounts.size() > 0) {
			this.accountNumber = accounts.get(accounts.size()-1).getAccountNumber()+1;
		} else {
			this.accountNumber = 1;
		}
		accounts.add(this);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		this.balance += amount;
	}
	public void withdraw(double amount) {
		this.balance -= amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public static ArrayList<Account> getAccounts() {
		return accounts;
	}

	public static void setAccounts(ArrayList<Account> accounts) {
		Account.accounts = accounts;
	}
	public static boolean accountExists(int num) {
		for (Account i : accounts) {
			if (i.getAccountNumber() == num) {
				return true;
			}
		}
		return false;
	}
	public static Account getAccount(int num) {
		for (Account i : accounts) {
			if (i.getAccountNumber() == num) {
				return i;
			}
		}
		return null;
	}
	public static void cancelAccount(int account) {
		for (Account i : accounts) {
			if (i.getAccountNumber() == account) {
				accounts.remove(i);
			}
		}
	}
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", type=" + type + ", activated="
				+ activated + "]";
	}
	
}
