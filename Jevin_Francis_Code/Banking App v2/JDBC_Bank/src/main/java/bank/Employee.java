package bank;

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

	// Login Employee and return employee
	static Employee employeeLogin(ArrayList<Employee> employeeList) {
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