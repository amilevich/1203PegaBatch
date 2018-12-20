package com.assignment.utilities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.daoimpl.AccountsDAOImpl;

public class Account {	
	static boolean loaded = false;//I'm not sure if I'll need to keep or get rid of this
	int ID;
	int open;
	int approved;
	Double balance;
	static List<Account>accountList = new ArrayList<Account>();
	public Account() {};//default constructor

	//the true bean!
	public Account (int ID, double balance, int approved, int open) {//this will replace old object
		super();
		this.ID = ID;
		this.balance = balance;
		this.approved = approved;
		this.open = open;
	}
	
	@Override
	public String toString() {//reads account information
		return "Account [ID: " + ID + ", balance: " + balance + "]";
	}

	public static boolean getLoaded() {
		return loaded;
	}
	public int getID() {
		return ID;
	}
	public Double getBalance() {
		return balance;
	}
	public static void logout() {//eventually, the hope is that this method will be used to write all data to text file/database prior to exiting, but for now, its this
		System.exit(0);
	}
	public int getOpen() {//will return 0 or 1, 0->cancelled, 1->open
		return open;
	}
	public int getApproved() {//will return 0 or 1, 0->not approved, 1->approved
		return approved;
	}
	public static void loadAccountList() {//this will probably replace the load method from before
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			accountList = aimpl.getAccountList();//loads all the accounts onto a java list, easier access after this
		} catch (SQLException e) {
			System.out.println("loadAccountList! Database Connection Error!");
			e.printStackTrace();
		}
		if (loaded==false)
			loaded = true;
	}
	public static boolean checkAccount (int accountID) {//did I not have a way to check if accounts existed?
		int tempI;										//I probably checked in customers...^$#$#$^^
		for (int i = 0; i < accountList.size(); i++) {
			tempI=accountList.get(i).getID();
			if (accountID == tempI)//this might get moved to customer class to sanity's sake
				return true;
		}
		return false;
	}
	//view pending accounts will go in employee class->god help us all
	//method below will replace "addtoHash"
	//will create new account in database, no args need since sql takes of it all...
	public static void addToAccountDB() {
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.createAccount();
			Account.loadAccountList();//this updates the java lists
		} catch (SQLException e) {
			System.out.println("addToAccountDB! Database Connection Error!");
			e.printStackTrace();
		}
	}
	public static void deposit(int accountID, double amount) {
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.updateBalance(1, accountID, amount);
			Account.loadAccountList();//to update java list after every transaction
		} catch (SQLException e) {
			System.out.println("deposit! Database Connection Error!");
			e.printStackTrace();
		}
	}
	public static void withdraw(int accountID, double amount) {
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.updateBalance(2, accountID, amount);
			Account.loadAccountList();//to update java list after every transaction
		} catch (SQLException e) {
			System.out.println("withdraw! Database Connection Error!");
			e.printStackTrace();
		}
	}
	public static void transfer(int userAccountID, int recipientAccountID, double amount) {
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.transferFunds(userAccountID, recipientAccountID, amount);
			Account.loadAccountList();//to update java list after every transaction
		} catch (SQLException e) {
			System.out.println("transfer! Database Connection Error!");
			e.printStackTrace();
		}
	}	
	public static void setOpen(int accountID, int state) {//0->cancelled, 1->Open
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.updateCancel(accountID, state);
			Account.loadAccountList();
		} catch (SQLException e) {
			System.out.println("setOpen! Database Connection Error!");
			e.printStackTrace();
		}
	}
	public static void setApproved(int accountID, int state) {//0->not approved, 1->approved
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.updateApproval(accountID, state);
			Account.loadAccountList();
		} catch (SQLException e) {
			System.out.println("setApproved! Database Connection Error!");
			e.printStackTrace();
		}
	}
	public static Account pullFromList(int iD) {
		Account a = null;
		for (int i = 0; i < accountList.size(); i++) {
			if (iD == accountList.get(i).getID())
				a = accountList.get(i);
		}

		return a;
	}
	//this will useful when making a new account I can tie the account id and user id using this
	public static int getNewAccountID() {
		Account.loadAccountList();
		int temp = accountList.size() - 1;//this is to get the last index number of the list
		int id = accountList.get(temp).getID();//this will get me the newest made account id
		return id;
	}
	/*
	 * Want to view the stuff that was removed? Use this link!
	 * https://drive.google.com/open?id=1bgamt97I2hjkOxB8dE6-qDb9vOZZsO8s
	 */
}
