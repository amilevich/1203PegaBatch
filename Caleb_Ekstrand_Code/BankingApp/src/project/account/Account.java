package project.account;

import java.sql.SQLException;
import java.util.ArrayList;

import project.daoimpl.AccountDAOImpl;

public class Account {
	private int accountNumber;
	private double balance = 0;
	private String type;
	private boolean activated = false;
	private static ArrayList<Account> accounts = new ArrayList<>();
	private static AccountDAOImpl adi = new AccountDAOImpl();
	
	public Account(String type){
		if (accounts.size() > 0) {
			this.accountNumber = accounts.get(accounts.size()-1).getAccountNumber()+1;
		} else {
			this.accountNumber = 1;
		}
		this.type = type;
		accounts.add(this);
		try {
			adi.createAccount(this.accountNumber, this.type);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Account(int accountNumber, double balance, String type, boolean activated) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.type = type;
		this.activated = activated;
	}
	public static void loadAccounts() {
		try {
			accounts = adi.getAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Account i : accounts) {
			System.out.println(i.toString());
		}
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
		try {
			adi.setBalance(this.accountNumber, this.balance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void withdraw(double amount) {
		this.balance -= amount;
		try {
			adi.setBalance(this.accountNumber, this.balance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			adi.activatedAcc(this.accountNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
