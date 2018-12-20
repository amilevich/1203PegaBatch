package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Customer> customerList = new ArrayList<>();
	static ArrayList<Employee> employeeList = new ArrayList<>();
	static ArrayList<Customer> newCustomers = new ArrayList<>();

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		CustomerDao cdao = new CustomerDaoImpl();
		createUsers();
		cdao.getCustomers();
		cdao.getNewCustomers();
		Boolean run = true;
		while (run) {

			// Bank App Start
			System.out.println("------------------------------------");
			System.out.println("Welcome to the Switch Bank!");
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
				Menu.customerMenu();
				break;
			}
			case "2": {
				// Show Menu For Employee
				Menu.employeeMenu();
				break;
			}
			default: {
				System.out.println("Invalid Choice!");
				scanner.nextLine();
				break;
			}
			}
		} // End of While Loop

		// Updating Database
		cdao.updateInDb(); // updating database at the ends
	}// End of main function

	private static void createUsers() {
		// ArrayList of Customers

		// // #### SOME TEST CUSTOMERS #### //
		// Customer c = new Customer("Jevin", "Francis", "user", "pass", "No", null);
		// customerList.add(c);
		// c = new Customer("Test", "Customer", "test", "pass", "No", null);
		// customerList.add(c);

		// ### ADDING TEST EMPLOYEE ### // MOVE TO DATABASE
		Employee e = new Employee("Employee", "e", "e");
		employeeList.add(e);
		e = new Employee("Dave", "employee", "employee");
		employeeList.add(e);

	}

}