package com.bank.util;

import java.sql.SQLException;
import java.util.Scanner;
import com.bank.daoimpl.*;

public class BankAppDriver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String firstName = "";
		String lastName = "";
		String screenname = "";
		String password = "";
		int streetNum = 0;
		String streetName = "";
		String city = "";
		String cstate = "";
		int zipcode = 000000;
		long phone = 0;
		String email = "";
		int accid = 0;
		int pin = 0;
		String proceed;
		boolean revolve = true;
		// Employee e = new Employee();
		// BankManager m = new BankManager();
		// AccountDAOImpl adi;

		// adi = new AccountDAOImpl(); //accounts.
		// adi.getActiveAccounts();
		// adi.getAllAccountBalances();
		// adi.getAllAccountCreationDates();
		// adi.getAllAccountIds();
		// adi.getAllAccountTypes();
		// adi.getFullAccountList();
		// adi.getLastBalance(63);

		while (revolve) {
			System.out.println("--------------------------------");
			System.out.println("|$| Welcome to Revareva Bank |$|");
			System.out.println("--------------------------------");

			System.out.print(
					"####################################################################################################");
			System.out.print(
					"######\n### Would you like to login('L') or register('R') a customer or exit('E') to terminate this program ######\n");
			System.out.println(
					"##########################################################################################################");
			System.out.print("Input: ");
			proceed = in.next();

			if ((proceed.charAt(0) == 'R') || (proceed.charAt(0) == 'r')) {
				System.out.println();
				System.out.println("\t#######################################");
				System.out.println("\t# Please complete the Registration form #");
				System.out.print("\t# First Name: ");
				firstName = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# Last Name: ");
				lastName = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# Screenname: ");
				screenname = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# Password: ");
				password = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# Street number: ");
				streetNum = in.nextInt();
				System.out.println("\t#######################################");
				System.out.print("\t# Street name: ");
				streetName = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# City: ");
				city = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# State: ");
				cstate = in.next();
				System.out.println("\t#######################################");
				System.out.print("\t# Zipcode: ");
				zipcode = in.nextInt();
				System.out.println("\t#######################################");
				System.out.print("\t# Phone number (exclude zipcode): ");
				phone = in.nextInt();
				System.out.println("\t#######################################");
				System.out.print("\t# Email: ");
				email = in.next();
				System.out.println("\t#######################################");
				System.out.println("\t# Accid will be automated.... ");
				accid = 12;
				System.out.println("\t#######################################");
				System.out.print("\t# Pin: ");
				pin = in.nextInt();
				System.out.println("\t#######################################");

				CustomerDAOImpl cst = new CustomerDAOImpl();
				// adi = new AccountDAOImpl();

				try {
					cst.createCustomer(firstName, lastName, streetNum, streetName, city, cstate, zipcode, phone, email,
							accid, pin, screenname, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			else if (proceed.charAt(0) == 'L' || proceed.charAt(0) == 'l') {

				if (revolve == true && (proceed.charAt(0) == 'l')) {
					System.out.println("\n");
					System.out.println("\tWelcome to Revareva Bank");
					System.out.println("\t######################################");
					System.out.println("\t#  ('V')View Account                 #");
					System.out.println("\t#  ('W')Withdraw      ('D')Deposit   #");
					System.out.println("\t#  ('T')Transfer      ('E')Logout    #");
					System.out.println("\t######################################\n");

					System.out.print("Enter an option (i.e, 'W' to withdraw.): ");
					proceed = in.next();

					if (proceed.charAt(0) == 'E' || proceed.charAt(0) == 'e') {
						System.out.println("");
						System.out.println("###########################");
						System.out.println("#You have been logged out.#");
						System.out.println("###########################");
						System.out.println("");
						System.out.println("");
						System.out.println("");
						revolve = false;
					} else if ((proceed.charAt(0) == 'D') || (proceed.charAt(0) == 'd')) {
						System.out.println("Deposit.");
					} else if ((proceed.charAt(0) == 'T') || (proceed.charAt(0) == 't')) {
						System.out.println("Transfer.");
					} else if ((proceed.charAt(0) == 'W') || (proceed.charAt(0) == 'w')) {
						System.out.println("Withdraw.");
					} else if ((proceed.charAt(0) == 'V') || (proceed.charAt(0) == 'v')) {
						System.out.println("View.");
					} else {
						System.out.println("Invalid input.");
					}
				} // end if
				revolve = true;

			} // end first else if

			else if (proceed.charAt(0) == 'E' || proceed.charAt(0) == 'e') {
				revolve = false;
			} else {
				System.out.print("Input invalid.");
			}

		}

		in.close();
		System.out.println("");
		System.out.println("#####################");
		System.out.println("#Thank you. Goodbye!#");
		System.out.println("#####################");
	} // end while
} // end main