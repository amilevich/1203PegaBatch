package com.revature.classes;
import java.io.IOError;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Menus.AccountMenu;
import com.revature.Menus.TransactionMenu;
import com.revature.daoImp.FileManipulation;

import projectPartZero.Main;



public class BankAdmin extends Employee{

	public BankAdmin(String firstName, String lastName) {
		super(firstName, lastName);
		
	}
	
	public void nuke() {
		System.out.println("Database compromised! Nuke it!");
		FileManipulation dao = new FileManipulation();
		try {
			dao.dropTables();
		} catch (SQLException | IOError e) {
			System.out.println("Can't drop database tables");
		}
	}
	
	public void cancelAnAccount() {
		
		FileManipulation dao = new FileManipulation();
		ArrayList<Customer> allCustomers;
		try {
			allCustomers = dao.getAllCustomer();
			for (int i = 0; i < allCustomers.size(); i++) {
				allCustomers.get(i).setAccounts(dao.getUserAccounts(allCustomers.get(i).getId()));
			}
			while(true) {
				System.out.println("Here are the following accounts");
				for(int j = 0; j < allCustomers.size(); j++) {
					System.out.println(j + ".) " + allCustomers.get(j));
					allCustomers.get(j).displayAccounts();
				}
				System.out.println("Select which one you want to cancel or enter -1 to exit: ");
				int response = 0;
				response = Main.input.nextInt();
				if(response == -1) {break;}
				else if(allCustomers.get(response).getNumberOfAccounts() == 1) {
					dao.removeFromUserAccount(allCustomers.get(response).getId(), allCustomers.get(response).getAccount(0).getID());
					dao.removeCustomer(allCustomers.get(response));
					dao.removeAccount(allCustomers.get(response).getAccount(0).getID());
					dao.removeLogin(allCustomers.get(response).getId());
					allCustomers.remove(response);
					System.out.println("Customer and all related accounts removed");
				}
				else if(allCustomers.get(response).getNumberOfAccounts() > 1) {
					int index;
					index = AccountMenu.getAccountIndex(allCustomers.get(response));
					dao.removeFromUserAccount(allCustomers.get(response).getId(), allCustomers.get(response).getAccount(index).getID());
					dao.removeAccount(allCustomers.get(response).getAccount(index).getID());
					allCustomers.get(response).getAccounts().remove(index);
					System.out.println("Account removed");
				}
				
			}
		} catch (SQLException | IOError e) {
			System.out.println("Error retrieving accounts, reset connection to database");
		}
		

		
	}
	
	public void editAllAccounts() {
		FileManipulation dao = new FileManipulation();
		ArrayList<Customer> allCustomers = new ArrayList<Customer>();
		try {
			allCustomers = dao.getAllCustomer();
			for (int i = 0; i < allCustomers.size(); i++) {
				ArrayList<Account> accounts = dao.getUserAccounts(allCustomers.get(i).getId());
				for (int j = 0; j < accounts.size(); j++) {
					allCustomers.get(i).getAccounts().add(accounts.get(j));
				}
			}
			while(true) {
				System.out.println("Here are the following accounts");
				for(int j = 0; j < allCustomers.size(); j++) {
					System.out.println(j + ".) " + allCustomers.get(j).getName());
					allCustomers.get(j).displayAccounts();
				}
				System.out.println("Select which one you want to edit or enter -1 to exit: ");
				int response = 0;
				response = Main.input.nextInt();
				if(response == -1) {break;}
				TransactionMenu.transactionMenu(allCustomers.get(response));
			}

		} catch (SQLException | IOError e) {
			// TODO Auto-generated catch block
			System.out.println("An error has occured or there are no users or accounts ");
		}
		
	}
}
