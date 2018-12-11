package com.assignment.persons;

import com.assignment.utilities.Account;

public class Employee extends Person{
	
	
		public Employee(String username, String password, int userType) {
		super(username, password, userType);
		}
		public static void viewPendingAccounts() {
			for (int i = 1; i <= Account.hashsize(); i++) {
				Account a = Account.pullFromHash(i);
				boolean x = a.isApproved();
				boolean y =a.isOpen();
				if (!x && y) {//pending accounts will have isApproved false and isOpen true
					
					System.out.println(a.getID() + ", "+ a.getAccType());//will show account number and account type
				}
			}
		}
}
