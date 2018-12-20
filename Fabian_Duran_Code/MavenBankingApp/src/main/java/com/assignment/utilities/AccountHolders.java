package com.assignment.utilities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.daoimpl.AccountsDAOImpl;
import com.assignment.persons.Customer;

public class AccountHolders{
		public AccountHolders() {}//will not be used..
		static List<Integer> pendingList = new ArrayList<Integer>();
		private static boolean loaded = false;
		
		public static boolean getLoaded() {
			return loaded;
		}
		public static void loadAccountHolders(){
			AccountsDAOImpl aimpl = new AccountsDAOImpl();
			try {
				pendingList = aimpl.viewPendingAccounts();//its  because the view created only gets the account IDs
			} catch (SQLException e) {//which are ints, so the only thing necessary is a list of ints, not accounts
				System.out.println("loadAccountHolders! Database Connection Error!");
				e.printStackTrace();
			}
			if (loaded==false)
				loaded = true;
		}
		public static void viewPendingAccounts() {
			for(int i = 0; i < pendingList.size(); i++) {
				int a =pendingList.get(i);
				System.out.println(a);
			}
		}
		//this will show all the accounts for one customer...I think...
		public static void viewCustomerAccounts(int userID) {
			List<Integer>customeraccounts = new ArrayList<Integer>();
			List<Customer>tempList = Customer.getList();
			String open = "";
			String approved = "";
			for (int i = 0; i < tempList.size(); i++) {
				if (userID==tempList.get(i).getUserID()) 
					customeraccounts.add(tempList.get(i).getAccountID());		
			}
			for (int i = 0; i < customeraccounts.size(); i++) {
				Account a = Account.pullFromList(customeraccounts.get(i));
				if (a.getApproved() == 0 )
					approved = "Not Approved";
				else
					approved = "Approved";
				if (a.getOpen() == 0)
					open = "Cancelled";
				else
					open = "Open";
				System.out.println(a.getID() + "->\t Balance: $" + a.getBalance() + "\t" + approved + "\t" + open);
			}
		}
}
