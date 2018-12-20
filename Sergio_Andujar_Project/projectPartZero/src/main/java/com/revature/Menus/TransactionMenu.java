package com.revature.Menus;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.classes.Account;
import com.revature.classes.Customer;
import com.revature.daoImp.FileManipulation;

import projectPartZero.Main;

public class TransactionMenu {
	
	public static void transactionMenu(Customer aCustomer) {
		
		boolean flag = true;
		
		FileManipulation dao = new FileManipulation();
		
		while(flag) {
				Account accountToInteract = AccountMenu.choiceAccount(aCustomer);
				System.out.println("Select what you want to do");
				System.out.println("1.) Withdraw");
				System.out.println("2.) Deposit");
				System.out.println("3.) transfer");
				System.out.println("4.) log out" );
				System.out.print("Enter response here: ");
				int response = 0;
				response = Main.input.nextInt();
				switch(response) {
				case 1:
					System.out.println("Enter amount to withdraw: ");
					response = Main.input.nextInt();
					int amount;
					amount = accountToInteract.withdraw(response);
					System.out.println("You have withdrawn: " + response);
					try {
						dao.updateAccount(accountToInteract);
					} catch (SQLException e) {
						System.out.println("Failed to do transaction");
					}
					break;
				case 2:
					System.out.println("Enter amount to deposit: ");
					response = Main.input.nextInt();
					accountToInteract.deposit(response);
					System.out.println("You have deposited: " + response);
					try {
						dao.updateAccount(accountToInteract);
					} catch (SQLException e) {
						System.out.println("Failed to do transaction");
					}
					break;
				case 3:
					System.out.println("Which account do you want to transfer to?");
					System.out.println("1.) Your account?");
					System.out.println("2.) Another customer account?");
					System.out.println("Enter response here: ");
					response = Main.input.nextInt();
					switch(response) {
					case 1:
						if(aCustomer.getAccounts().size() > 1) {
							Account accountToInteract2 = AccountMenu.choiceAccount(aCustomer);
							System.out.println("Enter amount to transfer over: ");
							response = Main.input.nextInt();
							accountToInteract.transfer(response, accountToInteract2);
							System.out.println("Transfer successful");
							System.out.println("Here are the balances of the accounts");
							System.out.println("New Balance of account number " + accountToInteract.getID()
							+ ": " + accountToInteract.getBalance());
							System.out.println("New Balance of account number " + accountToInteract2.getID()
							+ ": " + accountToInteract2.getBalance());
							try {
								dao.updateAccount(accountToInteract);
								dao.updateAccount(accountToInteract2);
							} catch (SQLException e) {
								System.out.println("Failed to do transaction");
							}
						}
						else {
							System.out.println("No other account to transfer to. You only have one account.");
						}
						break;
					case 2:
						try {
							ArrayList<Account> allAccounts = dao.getAllAccounts();
							Customer temp = new Customer();
							temp.setAccounts(allAccounts);
							Account accountToInteract3 = AccountMenu.choiceAccount(temp);
							System.out.println("Enter amount to transfer over: ");
							response = Main.input.nextInt();
							accountToInteract.transfer(response, accountToInteract3);
							System.out.println("Transfer successful");
							System.out.println("Here are the balances of the accounts");
							System.out.println("New Balance of account number " + accountToInteract.getID()
							+ ": " + accountToInteract.getBalance());
							System.out.println("New Balance of account number " + accountToInteract3.getID()
							+ ": " + accountToInteract3.getBalance());
							dao.updateAccount(accountToInteract);
							dao.updateAccount(accountToInteract3);
						} catch (SQLException e) {
							System.out.println("There are no accounts to transfer to");
						}

					}
					break;
				case 4:
					System.out.println("logging out");
					flag = false;
					break;
				default:
					System.out.println("This is not a valid option");
					break;	
				}
		}
	}

}
