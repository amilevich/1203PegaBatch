package com.revature.Menus;

import java.io.IOError;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.classes.Account;
import com.revature.classes.Customer;
import com.revature.daoImp.FileManipulation;

import projectPartZero.Main;

public class CustomerMenu {
	
	public static void customerMenu(Customer aCustomer) {
			ArrayList<Account> accounts = null;
			boolean flag = true;
			FileManipulation dao = new FileManipulation();
			System.out.println(aCustomer);
			while(flag) {
				int response = 0;
				System.out.println("What would you like to do today?");
				System.out.println("1.) Make an account");
				System.out.println("2.) delete an existing empty account");
				System.out.println("3.) select an account for transactions");
				System.out.println("4: Exit");
				System.out.println("enter response here: ");
				response = Main.input.nextInt();
				switch(response) {
				case 1:
					try {
						dao.createAccount(aCustomer.getId());
						System.out.println("New account created");
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("Something went wrong when making a new account ");
					}
					break;
				case 2:
					try {
						accounts = dao.getUserAccounts(aCustomer.getId());
						ArrayList<Account> empty = new ArrayList<Account>();
						for(int i = 0; i < accounts.size(); i++) {
							if(accounts.get(i).getBalance() == 0) {
								empty.add(accounts.get(i));
							}
						}
						while(true) {
							System.out.println("Here are the following accounts");
							for(int j = 0; j < empty.size(); j++) {
								System.out.println(j + ".) " + empty.get(j));
							}
							System.out.println("Select which one you want to cancel or enter -1 to exit: ");
							response = Main.input.nextInt();
							if(response == -1) {break;}
							dao.removeFromUserAccount(aCustomer.getId(), empty.get(response).getID());
							dao.removeAccount(empty.get(response).getID());
							empty.remove(response);
							System.out.println("Account removed");
						}
						
						
					} catch (SQLException | IOError e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 3:
					try {
						accounts = dao.getUserAccounts(aCustomer.getId());
						aCustomer.setAccounts(accounts);
						TransactionMenu.transactionMenu(aCustomer);
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("No accounts were found");
					}
					break;
					
				default:
					flag = false;
					break;
				}
			}
			
	}
}
