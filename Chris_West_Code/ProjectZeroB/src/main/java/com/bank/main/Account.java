package com.bank.main;

import java.util.ArrayList;
import java.util.Random;

public class Account {
	int accountID;
	double balance;
	String accountType;
	int accountOpen;
	ArrayList<Integer> accounts;
public Account() {
	
}
	public Account(int accountID, double balance, String accountType, int accountOpen, ArrayList<Integer> accounts) {
		super();
		this.accountID = accountID;
		this.balance = balance;
		this.accountType = accountType;
		this.accountOpen = accountOpen;
		this.accounts = accounts;
	}

	public double withdrawMoney(double amount) {
		double bal = Bank.account.getBalance();
		double withdraw = bal - amount;
		return withdraw;
	}

	public double depositMoney(double amount) {
		double bal = Bank.account.getBalance();
		double deposit = bal + amount;
		return deposit;

	}

	public double transferFunds(double amount) {
		double bal = Bank.account.getBalance();
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

	public int getAccountSize() {
		return accounts.size();
	}

	public int createAccountNumber() {
		Random rm = new Random();
		int number = 0;
		do {
			System.out.println(number);
			number = rm.nextInt(900000000) + 100000000;
		} while (!Bank.accDao1.accountExist2(number));

		return number;
	}

	public void whichAccount() {
		int count = 0;
		int accountNumber = 0;
		System.out.println("\n\n<---- BEGIN ---- ACCOUNT LIST ---- BEGIN ---->\n\n");
		if (getAccountSize() > 1) {
			for (Integer x : accounts) {
				System.out.println("Account # " + x + "\n ID number: " + count + "\n\n");
				count++;
			}
			System.out.println("\n\n<---- END ---- ACCOUNT LIST ---- END ---->\n\n");
			do {
			System.out.print("Enter the account ID number that you want to access: ");
			Bank.input = UserInputValidation.isInt(Bank.input);
			accountNumber = Bank.input.nextInt();
			}while(accountNumber < 0 && accountNumber > getAccountSize());
			Bank.account = Bank.accDao1.getAccountByNumber(accounts.get(accountNumber));
		}
	}
	public void whichAccount2() {
		int count = accounts.size()-1;
		int accountNumber = 0;
		System.out.println("\n\n<---- BEGIN ---- ACCOUNT LIST ---- BEGIN ---->\n\n");
		if (getAccountSize() > 1) {
			for (Integer x : accounts) {
				System.out.println("Account # " + x + "\n ID number: " + count + "\n\n");
				count--;
			}
			System.out.println("\n\n<---- END ---- ACCOUNT LIST ---- END ---->\n\n");
			do {
			System.out.print("Enter the account ID number that you want to access: ");
			Bank.input = UserInputValidation.isInt(Bank.input);
			accountNumber = Bank.input.nextInt();
			}while(accountNumber < 0 && accountNumber > getAccountSize());
			Bank.account2 = Bank.accDao1.getAccountByNumber(accounts.get(accountNumber));
		}
	}
	public void transferTo() {
		int accountNumber = 0;
		do {
		UserInputValidation.isInt(Bank.input);
		accountNumber = Bank.input.nextInt();
		//System.out.println("Test");
		}while(!(Bank.accDao1.accountExistTransfer(accountNumber)));
		
		Bank.account2 = Bank.accDao1.getAccountTransfer(accountNumber);
	}
	

}
