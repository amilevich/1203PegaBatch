package com.bank.beans;

import java.util.Scanner;

public class BankAdministrator extends Employee {
	int employeeId;
	int managerId;
	String userName;
	String passWd;
	String jobLevel;
	String department;
	int accountApproval = 0;
	int accountCancelled;

	public int getEmployeeID() {
		return employeeId;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeId = employeeID;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return passWd;
	}

	public void setPassword(String password) {
		this.passWd = password;
	}

	public String getJoblevel() {
		return jobLevel;
	}

	public void setJoblevel(String joblevel) {
		this.jobLevel = joblevel;
	}

	public int getManagerID() {
		return managerId;
	}

	public void setManagerID(int managerID) {
		this.managerId = managerID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void viewCustomer(Customer c) { // Takes a customer object, and declares one of its get methods as variable
											// to return!
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter corresponding input view specific customer information: ");
		System.out.println(
				"['N']Fullname \n ['C']Phone Number \n ['L']Login Credentials \n ['P']Account Pin \n ['S']SSI \n ['A']Permanent Address");
		String view = in.next();

		switch (view.charAt(0)) {
		case 'n':
			System.out.println("The customer's fullname is: " + c.getFirstName() + " " + c.getLastName() + ". ");
			break;
		case 'N':
			System.out.println("The customer's fullname is: " + c.getFirstName() + " " + c.getLastName() + ". ");
			break;
		case 'c':
			System.out.println("The customer's phone number is: " + c.getPhone() + ". ");
			break;
		case 'C':
			System.out.println("The customer's phone number is: " + c.getPhone() + ". ");
			break;
		case 'l':
			System.out.println(
					"The customer's username and password is " + c.getUsername() + " " + c.getPassword() + ". ");
			break;
		case 'L':
			System.out.println(
					"The customer's username and password is " + c.getUsername() + " " + c.getPassword() + ". ");
			break;
		case 'p':
			System.out.println("The customer's pin is: " + c.getPin() + ". ");
			break;
		case 'P':
			System.out.println("The customer's pin is: " + c.getPin() + ". ");
			break;
		case 'a':
			System.out.println("The customer's address is ");
			System.out.print(c.getStreetNum() + " " + c.getStreetName() + "\n" + c.getCity() + " " + c.getState() + " "
					+ c.getZipcode() + ". ");
		case 'A':
			System.out.println("The customer's address is ");
			System.out.print(c.getStreetNum() + " " + c.getStreetName() + "\n" + c.getCity() + " " + c.getState() + " "
					+ c.getZipcode() + ". ");
		default:
			System.out.println("\n Invalid input. ");
		}

		in.close();
	}

	public int approveAccount(Customer c, int accid, int pin) {
		// Receive approval or denial. If statement checks if they're not in system, owe
		// money
		if ((c.getAccountID() != accid) && (c.getPin() == pin)) { // If the account doesn't exist for customer.ssi then
																	// give them an account.
			this.accountApproval = 1;

		} else {
			this.accountApproval = 0;
		}

		return accountApproval;
	}

	public String cancelAccount(int accid, boolean status) {
		// Shuts down a customer's account. Does some logic that marks the account
		// closed. (marker)
		status = false;

		String cancelAccount = "Account #" + accid + " has been cancelled. Status is: " + status
				+ ". New reference #XXX" + accid;
		return cancelAccount;
	}

}
