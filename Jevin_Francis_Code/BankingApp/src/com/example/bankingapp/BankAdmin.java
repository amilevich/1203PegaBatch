/*
 * Bank Admin:
 * 	- View And EDIT all accounts
 * 	- Approve/Deny open accounts
 * 	- deposit, withdraw, transfer from all accounts
 * 	- Cancel and account
 * 
 * ## Most likely a child of Employee class ##
 */

package com.example.bankingapp;

import java.util.ArrayList;
import java.util.Scanner;

public class BankAdmin {
	

	private String aUsername;
	private String aPassword;
	public String getaUsername() {
		return aUsername;
	}
	public void setaUsername(String aUsername) {
		this.aUsername = aUsername;
	}
	public String getaPassword() {
		return aPassword;
	}
	public void setaPassword(String aPassword) {
		this.aPassword = aPassword;
	}
	public BankAdmin(String aUsername, String aPassword) {
		super();
		this.aUsername = aUsername;
		this.aPassword = aPassword;
	}
	//Checking if the User Exitits and user and pass is correct
	public static boolean logIn(ArrayList<BankAdmin> admins, String aUsername, String aPassword) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		for(BankAdmin a:admins) {
			if(a.getaUsername().equals(aUsername)&&a.getaPassword().equals(aPassword)) {
				return true;
			}else
			{
				System.out.println("Username or Passord is Incorrect!");
				scanner.nextLine();
				return false;
			}
		}
		return false;
	}
	
	
}
