package com.assignment.persons;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.daoimpl.AccountsDAOImpl;

public class Customer{

	static boolean loaded = false;
	int userID;
	int accountID;
	static List<Customer> accHolders = new ArrayList<Customer>();//this will hold the ids of customers which contain the user ID and the bank account their tied to
	
	
	public Customer() {}//new customer constructor will only maintain alist of customer id and account ids
	public Customer(int userID, int accountID) {
		super();
		this.userID=userID;
		this.accountID=accountID;
	}
	public int getUserID() {
		return userID;
	}
	public int getAccountID() {
		return accountID;
	}
	public static boolean getLoaded() {
		return loaded;
	}
	//this will replace addAccount as it will add user and account ID into junction table
	public static void addToAccountHolder(int userID, int accountID) {
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			aimpl.addAccountHolder(userID, accountID);
			Customer.loadCustomerList();
		} catch (SQLException e) {
			System.out.println("addToAccountHolder! Database Connection Error!");
			e.printStackTrace();
		}		
	}
	//loads table into java, easier to compare data
	public static void loadCustomerList() {
		AccountsDAOImpl aimpl = new AccountsDAOImpl();
		try {
			accHolders = aimpl.getAccountHolders();//loads all the accounts onto a java list, easier access after this
		} catch (SQLException e) {
			System.out.println("loadCustomerList! Database Connection Error!");
			e.printStackTrace();
		}
		if (loaded==false)
			loaded = true;
	}
	//to check if account customer is trying to access does belong to the customer...
	public static boolean checkCustomer (int userID, int accountID) {
		for (int i = 0; i < accHolders.size(); i++) {
			//Customer c = accHolders.get(i);
			if (accHolders.get(i).getUserID()==userID&&accHolders.get(i).getAccountID()==accountID)
				return true;
		}
		return false;
	}
	public static List<Customer> getList() {
		return accHolders;
	}
	/*
	 * Want to view the stuff that was removed? Use this link!
	 * https://drive.google.com/open?id=1pNfwXGTubGJaUkhAULWxzs0hEiX7fWDp
	 */
}
