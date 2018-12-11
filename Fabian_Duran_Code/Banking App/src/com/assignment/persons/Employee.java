package com.assignment.persons;

import com.assignment.utilities.Account;

public class Employee extends Person{
	
	
		public Employee(String username, String password, int userType) {
		super(username, password, userType);
		}
		public static void viewPendingAccounts() {
			for (int i = 0; i < Account.hashsize(); i++) {
				if (Account.pullFromHash(i).isApproved()==false && Account.pullFromHash(i).isOpen()==true) {//pending accounts will have isApproved false and isOpen true
					
					System.out.println(Account.pullFromHash(i).getID() + ", "+ Account.pullFromHash(i).getAccType());//will show account number and account type
				}
			}
		}
		static void viewCustomers() {
			for (int i = 0; i < Customer.hashsize(); i++) {
					System.out.println(Account.pullFromHash(i).getID());//pulls the directory of customers
			}
		}
}
