package com.example.bankingapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// #####################################################################
		// Creating the list of Users
		ArrayList<Customer> customers = new ArrayList<>();
		// #### Populate the list from File HERE ####
		Customer c = new Customer("Jevin", "Francis", "user", "pass");
		customers.add(c);
		c = new Customer("Tom", "Ancis", "u", "p");
		customers.add(c);

		// Creating the list of New Account that are Not Yet Approved
		ArrayList<Customer> newCustomers = new ArrayList<>();
		// Creating the list of Employees

		ArrayList<Employee> employees = new ArrayList<>();
		// #### Populate the List from File HERE ####
		Employee e = new Employee("Jevin", "Francis", "e", "e");
		employees.add(e);

		ArrayList<BankAdmin> admins = new ArrayList<>();
		// #### Populate the List from File HERE ####
		BankAdmin a = new BankAdmin("admin", "admin");
		admins.add(a);
		// ######################################################################

		// Starting...
		String choice; // Choice
		Boolean running = true;
		// Loop to keep the program running until selected to exit
		while (running) {
			System.out.println("---------------------------------------------------------");
			System.out.println("Welcome to the Bank of Options.\n");
			System.out.println("Press the Number to select the Option!");
			System.out.println("\nSelect One:\n1. Customer\n2. Employee\n0. Exit");
			choice = scanner.nextLine();
			switch (choice) {
			// If user chose 1 for Customer
			case "0":
				running = false;
				System.out.println("Exiting Application...");
				scanner.nextLine();
				break;
			case "1":
				// Chose Customer
				System.out.println("Press:\n1. Login\n2. New Accounts");
				choice = scanner.nextLine();
				switch (choice) {
				case "0":
					running = false;
					System.out.println("Exiting Application...");
					scanner.nextLine();
					break;
				case "1":
					// Chose Login
					System.out.println("Enter Username:");
					String userName = scanner.nextLine();
					System.out.println("Enter Password:");
					String passWord = scanner.nextLine();
					// Checking if the user is in the list
					if (Customer.logIn(customers, userName, passWord)) {
						Boolean logIn = true;
						// Run Until Logged out
						while (logIn) {
							// User exists log them in HERE!
							System.out.println("---------------------------------------------------------");

							System.out.println("Logged In as " + Customer.getUser(customers, userName).getFirstName());
							System.out.println(
									"Current Balance is: $" + Customer.getUser(customers, userName).getBalance());
							System.out.println("Would you like to:\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Log Out");
							// Deposit Withdraw and Trasfer Here####
							choice = scanner.nextLine();
							switch (choice) {
							case "0":
								running = false;
								logIn = false;
								System.out.println("Exiting Application...");
								scanner.nextLine();
								break;
							case "1":
								// Deposit Amount to Account
								Customer.deposit(customers, userName);
								scanner.nextLine();
								break;
							case "2":
								// Withdraw from account
								Customer.withdraw(customers, userName);
								scanner.nextLine();
								break;
							case "3":
								// Transfer to another account
								Customer.transferFunds(customers, userName);
								scanner.nextLine();
								break;
							case "4":
								// lOG OUT
								System.out.println("Logging Out...\n");
								scanner.nextLine();
								logIn = false;
								break;
							default:
								System.out.println("Invalid Choice!");
								scanner.nextLine();
								break;
							}
						}

					} else {
						System.out.println("Username or Password is Incorrect!");
						scanner.nextLine();
					}
					break;
				case "2":
					// Chose New Account
					System.out.println("What whould you like to do:\n1. New Account\n2. New Joint Account");
					choice = scanner.nextLine();
					switch (choice) {
					case "0":
						running = false;
						System.out.println("Exiting Application...");
						scanner.nextLine();
						break;
					case "1":
						// New Account
						System.out.println("Creating new Account\nEnter First Name:");
						String firstName = scanner.nextLine();
						System.out.println("Enter Last Name:");
						String lastName = scanner.nextLine();
						System.out.println("Enter Username:");
						String newUserName = scanner.nextLine();
						System.out.println("Enter Password:");
						String newPassWord = scanner.nextLine();
						// Check if Username exists
						Boolean userExisits = false;
						for (Customer i : customers) {
							if (i.getUserName().equals(newUserName)) {
								System.out.println("A User With the Username Already Exisits");
								userExisits = true;
								scanner.nextLine();
								break;
							}
						}
						if (!userExisits) {
							// Create new User in Another list for admin to approve.
							c = new Customer(firstName, lastName, newUserName, newPassWord);
							newCustomers.add(c);
							System.out.println("New Account Request Send!");
							scanner.nextLine();
							break;
						}
						break;
					case "2":
						// ########## JOINT ACCOUNT######
						System.out.println("Joint Account Section is Not Yet Implimented");
						scanner.nextLine();
						break;
					default:
						System.out.println("Invalid Choice!");
						scanner.nextLine();
						break;
					}
					break;
				default:
					System.out.println("Invalid Choice!");
					scanner.nextLine();
					break;
				}
				break;

			// ###########################################################################################################################################
			// If user chose 2 for Employee
			case "2":
				// Chose Employee
				System.out.println("Sign In As:\n1. Employee\n2. Admin");
				choice = scanner.nextLine();

				switch (choice) {
				case "0":
					running = false;
					System.out.println("Exiting Application...");
					scanner.nextLine();
					break;
				case "1":
					// login as employyee
					// ### SIGN IN AS AN EMPLOYEE HERE ###
					System.out.println("Enter Employee Username:");
					String eUserName = scanner.nextLine();
					System.out.println("Enter Employee Password:");
					String ePassword = scanner.nextLine();

					if (Employee.logIn(employees, eUserName, ePassword)) {
						Boolean logIn = true;
						// ###################################
						while (logIn) {
							System.out.println(
									"To See\n1. Specific User\n2. View All Users\n3. Appove/Deny Application\n4. Log Out");
							choice = scanner.nextLine();
							// Employee Options
							switch (choice) {
							// See User One at a time
							case "0":
								running = false;
								logIn = false;
								System.out.println("Exiting Application...");
								scanner.nextLine();
								break;
							case "1":
								System.out.println("Enter The Username of the Customer");
								String customerName = scanner.nextLine();

								// Check id user exisits
								if (Customer.hasUser(customers, customerName)) {
									// printing the info to the screen
									Customer.printInfo(customers, customerName);
									scanner.nextLine();
								} else {
									// if user is not found
									System.out.println("User Not Found");
									scanner.nextLine();
								}
								break;
							case "2":
								// See All Users
								for (Customer c1 : customers) {
									Customer.printInfo(customers, c1.getUserName());
									System.out.println("");
								}
								scanner.nextLine();
								break;
							case "3":
								// Approve Deny Accounts
								for (Customer c1 : newCustomers) {
									System.out.println("Review:\nName: " + c1.getFirstName() + " " + c1.getLastName());
									System.out.println("User Name: " + c1.getUserName());
									System.out.println("Password: " + c1.getPassWord());

									System.out.println("1. Approve\n2. Deny");
									choice = scanner.nextLine();
									switch (choice) {
									case "1":
										customers.add(c1);
										System.out.println("Approved");
										scanner.nextLine();
										break;
									case "2":
										System.out.println("Denied");
										scanner.nextLine();
										break;
									default:
										break;
									}
								}
								// Clearing the Waitlist after Review
								newCustomers.removeAll(newCustomers);
								System.out.println("No More Accounts in Review.");
								scanner.nextLine();
								break;
							case "4":
								// lOG oUT
								System.out.println("Logging Out...\n");
								scanner.nextLine();
								logIn = false;
								break;
							default:
								break;
							}

						}

					} else {
						System.out.println("Username or Password is Incorrect!");
						scanner.nextLine();
					}
					break;
				// Login As An ADMIN
				case "2":
					// Asking for admin user and pass
					System.out.println("TEST WITH USERNAME: admin, PASSWORD: admin");
					System.out.println("Enter Admin Username:");
					String aUsername = scanner.nextLine();
					System.out.println("Enter Admin Password:");
					String aPassword = scanner.nextLine();

					// Check if admin exists
					if (BankAdmin.logIn(admins, aUsername, aPassword)) {
						Boolean logIn = true;
						while (logIn) {
							System.out.println("Logged In as An Admin:");
							System.out.println(
									"1. View customer\n2. View All Users\n3. Approve/Deny Application\n4. Log Out");
							// admin choice
							choice = scanner.nextLine();
							// switch case
							switch (choice) {
							// Exit Application
							case "0":
								logIn = false;
								running = false;
								System.out.println("Exiting Application...");
								scanner.nextLine();
								break;
							// View Customer
							case "1":
								System.out.println("Enter The Username of the Customer");
								String customerName = scanner.nextLine();

								// Check id user exisits
								if (Customer.hasUser(customers, customerName)) {
									// printing the info to the screen
									System.out.println("---------------------------------------------------------");

									System.out.println(
											"Logged In as " + Customer.getUser(customers, customerName).getFirstName());
									System.out.println(Customer.getUser(customers, customerName).getFirstName()
											+ " Current Balance is: $"
											+ Customer.getUser(customers, customerName).getBalance());
									System.out.println(
											"Would you like to:\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Delete Account");
									// Deposit Withdraw and Trasfer Here####
									choice = scanner.nextLine();
									switch (choice) {
									case "0":
										running = false;
										logIn = false;
										System.out.println("Exiting Application...");
										scanner.nextLine();
										break;
									case "1":
										// Deposit Amount to Account
										Customer.deposit(customers, customerName);
										scanner.nextLine();
										break;
									case "2":
										// Withdraw from account
										Customer.withdraw(customers, customerName);
										scanner.nextLine();
										break;
									case "3":
										// Transfer to another account
										Customer.transferFunds(customers, customerName);
										scanner.nextLine();
										break;
									case "4":
										// lOG OUT
										System.out.println("Deleting Account...\n");
										scanner.nextLine();
										Customer removeCustomer = Customer.getCusetomer(customers, customerName);
										customers.remove(removeCustomer);
										logIn = false;
										break;

									default:
										System.out.println("Invalid Choice!");
										scanner.nextLine();
										break;
									}
									// Customer.printInfo(customers, customerName);
								} else {
									// if user is not found
									System.out.println("User Not Found");
									scanner.nextLine();
								}
								break;

							case "2":
								// See All Users
								for (Customer c1 : customers) {
									Customer.printInfo(customers, c1.getUserName());
									System.out.println("");
								}
								scanner.nextLine();
								break;

							// Approve Deny
							case "3":
								for (Customer c1 : newCustomers) {
									System.out.println("Review:\nName: " + c1.getFirstName() + " " + c1.getLastName());
									System.out.println("User Name: " + c1.getUserName());
									System.out.println("Password: " + c1.getPassWord());

									System.out.println("1. Approve\n2. Deny");
									choice = scanner.nextLine();
									switch (choice) {
									case "1":
										customers.add(c1);
										System.out.println("Approved");
										scanner.nextLine();
										break;
									case "2":
										System.out.println("Denied");
										scanner.nextLine();
										break;
									default:
										break;
									}
								}
								// Clearing the Waitlist after Review
								newCustomers.removeAll(newCustomers);
								System.out.println("No More Accounts in Review.");
								scanner.nextLine();
								break;
							// Chose to Log Out
							case "4":
								logIn = false;
								System.out.println("Logging Out.");
								scanner.nextLine();
								break;
							default:
								break;
							}
						}
					}
					// login as admin
					break;
				default:
					System.out.println("Invalid Choice!");
					scanner.nextLine();
					break;
				}
				break;
			// ####################################################################################################################
			// If the choice was not 1 or 2
			default:
				System.out.println("Invalid Choice!");
				scanner.nextLine();
				break;
			}
		}

		// ### Save the Data to File Here After the program finish running ###
		System.out.println("################################################################");
		scanner.close();
	}
}
