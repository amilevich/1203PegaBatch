package com.example.bankapp;

import java.util.Scanner;

public class Admin {

	static Scanner scanner = new Scanner(System.in);

	public static void adminMenu() {
		System.out.println("Enter Admin Username: ");
		String adminUser = scanner.nextLine();
		System.out.println("Enter Admin Paddword: ");
		String adminPass = scanner.nextLine();

		Boolean logIn = false;
		if (adminUser.equals("admin") && adminPass.equals("admin")) {
			logIn = true;
		}
		while (logIn) {
			// Admin Loged In
			System.out.println("------------------------------------");
			System.out.println("Loged In As ADMIN!");
			System.out.println("1. Find User\n2. View All Users\n3. Approve/Deny\n4. Log Out");
			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				// Chose to find specific user
				break;
			case "2":
				// View All User In List
				for(Customer c: Main.customerList) {
					Employee.printCustomerInfo(c.getCustomerUsername());
					System.out.println();
				}
				scanner.nextLine();
				break;
			case "3":
				// Chose Approve Deny
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
				// Chose Logout
				logIn = false;
				System.out.println("Loggin Out....");
				scanner.nextLine();
				break;

			default:
				break;
			}
		}

	}

}
