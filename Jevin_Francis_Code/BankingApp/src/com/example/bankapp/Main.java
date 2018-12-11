package com.example.bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Customer> customerList = new ArrayList<>();
	static ArrayList<Employee> employeeList = new ArrayList<>();
	static ArrayList<Customer> newCustomers = new ArrayList<>();

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		createUsers();

		Boolean run = true;
		while (run) {

			// Bank App Start
			System.out.println("------------------------------------");
			System.out.println("Welcome to The Option's Bank!");
			System.out.println("\nPress The Number to Select:");
			System.out.println("1. Customer\n2. Employee\n0. Exit");

			// Get Selection
			String choice = scanner.nextLine();
			switch (choice) {
			case "0": {
				// Chose to Exit the Application
				System.out.println("Application Terminated....");
				run = false;
				break;
			}
			case "1": {
				// Show Menu For Customers
				Customer.customerMenu();
				break;
			}
			case "2": {
				// Show Menu For Employee
				Employee.employeeMenu();
				break;
			}
			default: {
				System.out.println("Invalid Choice!");
				break;
			}
			}
		} // End of While Loop
	}// End of main function

	private static void createUsers() {
		// ArrayList of Customers

		// #### SOME TEST CUSTOMERS #### //
		Customer c = new Customer("Jevin", "Francis", "user", "pass", false, null);
		customerList.add(c);
		c = new Customer("Test", "Customer", "test", "pass", false, null);
		customerList.add(c);

		// ### SOME TEST EMPLOYEE ### //
		Employee e = new Employee("Employee", "e", "e");
		employeeList.add(e);
		e = new Employee("Dave", "employee", "employee");
		employeeList.add(e);

	}

}