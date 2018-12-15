package com.projectz.bankapp;
import java.util.Scanner;

public class BankApp {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String accounts[][] = new String[100][5];
		int nextAccount = 0;
		String firstName = "";
		String lastName = "";
		String screenname = "";
		String password = "";
		Customer cus;
		Account acc = new Account(accountGenerator.accGen());
		boolean revolve = true;
		
		
		System.out.println("Welcome to Revareva Bank");
		System.out.println();
		
		System.out.print("Would you like to log in or register('L' to login, 'R' to register. Type exit to terminate this program): ");
		String proceed = in.nextLine();
		
		if(proceed.equalsIgnoreCase("exit")) {
			revolve = false;
		}
		
		if (proceed.charAt(0) == 'L' || proceed.charAt(0) == 'l') {
			System.out.print("Enter Username: ");
			screenname = in.nextLine();
			System.out.print("Enter Password: ");
			password = in.nextLine();
			System.out.println();
		}	
		
		else if (proceed.charAt(0) == 'R' || proceed.charAt(0) == 'r') {
			System.out.println();
			System.out.println("\t#######################################");
			System.out.println("\t# Please complete the Registration form #");
			System.out.print("\t# First Name: "); firstName = in.next(); 
			System.out.println("\t#######################################");
			System.out.print("\t# Last Name: "); lastName = in.next(); 
			System.out.println("\t#######################################");
			System.out.print("\t# Screenname: "); screenname = in.next(); 
			System.out.println("\t#######################################");
			System.out.print("\t# Password: "); password = in.next(); 
			System.out.println("\t#######################################");
			
			
			cus = new Customer(firstName, lastName, accountGenerator.accGen(), screenname, password);
			accounts[nextAccount][nextAccount] = cus.getFirstName();
			accounts[nextAccount][nextAccount+1] = cus.getLastName();
			accounts[nextAccount][nextAccount+2] = acc.getStringAccountID();
			accounts[nextAccount][nextAccount+3] = cus.getUsername();
			accounts[nextAccount][nextAccount+4] = cus.getPassword();
			nextAccount++;
			
			System.out.println(cus.toString());
		
			
			for (int i = 0; i < 1; i++) {
				System.out.println("|");
				for (int ii = 0; ii < accounts[0].length; ii++) {
						System.out.print(accounts[i][ii] + " ");
				}
			}
		}
		
		
		if (revolve == true && (proceed == "L" || proceed == "R")) {
		System.out.println("\n");
		System.out.println("\tWelcome to Revareva Bank");
		System.out.println("\t######################################");
		System.out.println("\t#  ('V')View Account                 #");      
		System.out.println("\t#  ('W')Withdraw      ('D')Deposit   #");
		System.out.println("\t#  ('T')Transfer      ('C')Cancel    #");
		System.out.println("\t######################################\n"); 
		
		System.out.print("Enter an option (i.e, 'W' to withdraw.): ");
		String option = in.next();
		
		switch(option.charAt(0)) {
		case 'v':
			acc.viewAccount();
			break;
		case 'V':
			acc.viewAccount();
			break;
		case 'w':
			acc.withdraw(200);
			break;
		case 'W':
			acc.withdraw(200);
			break;
		case 't':
			acc.transfer(acc.getAccountID(), 50);
			break;
		case 'T':
			acc.transfer(acc.getAccountID(), 50);
			break;
		case 'd':
			acc.deposit(5000.80);
			break;
		case 'D':
			acc.deposit(5000.80);
			break;
		case 'c':
			acc.cancel();
			revolve = false;
			break;
		case 'C':
			acc.cancel();
			revolve = false;
			break;
		default: 
			System.out.println("Invalid option.");
		}
		
		}
		
		
		
		in.close();
	}

}
