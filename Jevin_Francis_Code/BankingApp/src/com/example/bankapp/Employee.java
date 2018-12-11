package com.example.bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

	private String eName;
	private String eUsername;
	private String ePassword;

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteUsername() {
		return eUsername;
	}

	public void seteUsername(String eUsername) {
		this.eUsername = eUsername;
	}

	public String getePassword() {
		return ePassword;
	}

	public void setePassword(String ePassword) {
		this.ePassword = ePassword;
	}

	public Employee(String eName, String eUsername, String ePassword) {
		super();
		this.eName = eName;
		this.eUsername = eUsername;
		this.ePassword = ePassword;
	}

	// Start Scanner
	static Scanner scanner = new Scanner(System.in);

	public static void employeeMenu() {

		System.out.println("Selected Employee");
		System.out.println("1. Employee Login\n2. Admin Login");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			// login Employee
			Employee e = employeeLogin(Main.employeeList);

			// LogedIn Sucessful
			if (e != null) {
				Boolean logIn = true;
				while (logIn) {
					System.out.println("------------------------------------");
					System.out.println("Logged In As " + e.geteName());
					System.out.println("1. Find User\n2. View All User\n3. Approve/Deny\n4. Logout");
					choice = scanner.nextLine();
					switch (choice) {
					case "1":
						// Find Specific User
						System.out.println("Enter the Username of the Customer.");
						String cUsername = scanner.nextLine();
						// Function that prints user into to screen
						printCustomerInfo(cUsername);
						scanner.nextLine();
						break;
					case "2":
						// View All User In List
						for(Customer c: Main.customerList) {
							printCustomerInfo(c.getCustomerUsername());
							System.out.println();
						}
						scanner.nextLine();
						break;
					case "3":
						// Approve Deny Accounts
						for (Customer c : Main.newCustomers) {
							System.out.println("Review:\nName: " + c.getCustomerFirstName() + " " + c.getCustomerLastName());
							System.out.println("User Name: " + c.getCustomerUsername());
							System.out.println("Password: " + c.getCustomerPassword());
							System.out.println("Joint Accout : " + c.getJointAccount());
							if(c.getJointAccount()) {
								System.out.println("Joint Username: "+ c.getJoinCustomerUsername());
							}

							System.out.println("1. Approve\n2. Deny");
							choice = scanner.nextLine();
							switch (choice) {
							case "1":
								Main.customerList.add(c);
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
						Main.newCustomers.removeAll(Main.newCustomers);
						System.out.println("No More Accounts in Review.");
						scanner.nextLine();
						break;
					case "4":
						// Chose Log Out
						logIn = false;
						System.out.println("Logging out....");
						scanner.nextLine();
						break;

					default:
						System.out.println("Invalid Choice!");
						break;
					}

				} // Runs while login = true

			} else { // login unsucessful
				System.out.println("Could Not Find the Employee");
				break;
			}

			break;
		case "2":
			System.out.println("Admin Login");
			Admin.adminMenu();

			break;
		default:
			break;
		}

	}

	// Prints Customer Info in Good Format
	static void printCustomerInfo(String cUsername) {
		Boolean findUser = false;
		for(Customer c : Main.customerList) {
			if(c.getCustomerUsername().equals(cUsername)) {
				findUser = true;
				System.out.println("Name : " +c.getCustomerFirstName()+" " + c.getCustomerLastName());
				System.out.println("Account Balance: " + c.getAccountBalance());
				System.out.println("Username: "+ c.getCustomerUsername());
				System.out.println("Password: "+ c.getCustomerPassword());
				System.out.println("Joint Accout : " + c.getJointAccount());
				if(c.getJointAccount()) {
					System.out.println("Joint Username: "+ c.getJoinCustomerUsername());
				}
			}
		}
		if(findUser == false) {
			System.out.println("Could Not Find The user");
		}
	}

	// Login Employee and return employee
	private static Employee employeeLogin(ArrayList<Employee> employeeList) {
		// LogIn for Employee
		System.out.println("Enter the Employee Username!");
		String eUsername = scanner.nextLine();
		System.out.println("Enter the Employee Password!");
		String ePassword = scanner.nextLine();

		// Checking if user is in the list
		for (Employee e : employeeList) {
			// Checking if Username and Password Match
			if (e.geteUsername().equals(eUsername) && e.getePassword().equals(ePassword)) {
				// Returning the user with the correct Username and Password
				System.out.println("Logging In....");
				scanner.nextLine();
				return e;
			}
		}
		return null;
	}

}