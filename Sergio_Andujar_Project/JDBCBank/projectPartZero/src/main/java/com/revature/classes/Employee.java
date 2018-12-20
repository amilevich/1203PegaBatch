package com.revature.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.daoImp.FileManipulation;

import projectPartZero.Main;



public class Employee implements User {

	private String firstName;
	private String lastName;
	
	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	@Override
	public String getName() {
		return ("Greetings: " + this.firstName + " " + this.lastName);
		
	}
	
	
	public void displayAllInfo() {
		FileManipulation dao = new FileManipulation();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			customers = dao.getAllCustomer();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < customers.size(); i++) {
			try {
				accounts = dao.getUserAccounts(customers.get(i).getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Customer Information");
			for (int j = 0; j < accounts.size(); j++) {
				customers.get(i).getAccounts().add(accounts.get(j));
			}
			System.out.println(customers.get(i));
			customers.get(i).displayAccounts();
		}
		
		
	}
	
	
	public void approveOrDenyUnregistered() {
		FileManipulation dao = new FileManipulation();
		ArrayList<Customer> unregs;
		try {
			unregs = dao.getUnregisteredUsers();
			int response = 0;
			System.out.println("Here are the following applications");
			for(int i = 0; i < unregs.size(); i++) {
					System.out.println("Application: " + unregs.get(i).getFirstName() + " " + unregs.get(i).getLastName());
					System.out.println("1.) Approve");
					System.out.println("2.) Deny");
					System.out.print("Enter response here: ");
					response = Main.input.nextInt();
					switch(response) {
					case 1:
						dao.updateCustomerToRegistered(unregs.get(i));
						dao.createAccount(unregs.get(i).getId());
						System.out.println("Application approved");
						break;
					case 2:
						System.out.println("Application denied");
						dao.removeDeclined(unregs.get(i));
						break;
					default:
						System.out.println("Incorrect input");
						--i;
						break;
					}
				}
		} catch (SQLException e) {
			System.out.println("There are no pending applications! ");
		}


	}




}
