package com.example.bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

	// Start Scanner
	static Scanner scanner = new Scanner(System.in);

	// Customer Variables
	private String customerFirstName;
	private String customerLastName;
	private String customerUsername;
	private String joinCustomerUsername; // Username of the joint ustomer only for Join Accounts
	private String customerPassword;
	private Double accountBalance;
	private Boolean jointAccount; // Set if Its a Joint Account

	// Getters and Setters
	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getJoinCustomerUsername() {
		return joinCustomerUsername;
	}

	public void setJoinCustomerUsername(String joinCustomerUsername) {
		this.joinCustomerUsername = joinCustomerUsername;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Boolean getJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(Boolean jointAccount) {
		this.jointAccount = jointAccount;
	}
	// End Getters and Setters

	// Constructor
	public Customer(String customerFirstName, String customerLastName, String customerUsername, String customerPassword,
			Boolean jointAccount, String joinCustomerUsername) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.jointAccount = jointAccount;
		this.joinCustomerUsername = joinCustomerUsername;
		this.setAccountBalance(0.0);
	}

	// Start Customer Menu
	public static void customerMenu() {
		System.out.println("Selected Customer");
		System.out.println("1. LogIn\n2. Sign Up");

		String choice = scanner.nextLine();
		if (choice.equals("0")) {
		} else if (choice.equals("1")) {

			// LogIn Customer
			System.out.println("Selected LogIn");
			// Signed In As
			Customer c = customerLogin(Main.customerList);
			// Joint Account User
			if (c != null) {
				// Logged In Sucessfully
				Boolean logIn = true;
				// Runs Until Selected Log out or Exit with 0
				while (logIn) {
					System.out.println("------------------------------------");
					System.out.println("Logged In as " + c.getCustomerFirstName() + " " + c.getCustomerLastName());
					System.out.println("Account Balance: $" + c.getAccountBalance());
					System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Log Out");
					choice = scanner.nextLine();
					if (choice.equals("1")) {
						// Deposit
						System.out.println("Selected Deposit.");
						System.out.println("Enter the Amount You Like to Deposit.");
						// Checking the input for Double
						if (scanner.hasNextDouble()) {
							double amount = scanner.nextDouble();
							// Checking for negative number
							if (amount >= 0) {
								// If Its Joint Account
								if (c.getJointAccount()) {
									Customer c2 = getCustomer(Main.customerList, c.getJoinCustomerUsername());
									depositJointCustomer(c, c2, amount);
									scanner.nextLine();
								} else {
									depositCustomer(c, amount);
									scanner.nextLine();
								}
							} else {
								// Was Negative
								System.out.println("Negative Number");
								scanner.nextLine();
							}
						} else {
							// Not Double
							System.out.println("Invalid Format");
							scanner.nextLine();
						}
					} else if (choice.equals("2")) {
						System.out.println("Withdraw");
						// Withdraw
						System.out.println("Selected Withdraw.");
						System.out.println("Enter the Amount You Like to Withdraw.");
						// Checking the input for Double
						if (scanner.hasNextDouble()) {
							double amount = scanner.nextDouble();
							// Checking for negative number
							if (amount >= 0) {
								if (c.getJointAccount()) {
									Customer c2 = getCustomer(Main.customerList, c.getJoinCustomerUsername());
									withdrawJointCustomer(c, c2, amount);
									scanner.nextLine();
								} else {
									withdrawCustomer(c, amount);
									scanner.nextLine();
								}

							} else {
								// Was Negative
								System.out.println("Negative Number");
								scanner.nextLine();
							}
						} else {
							// Not Double
							System.out.println("Invalid Format");
							scanner.nextLine();
						}
					} else if (choice.equals("3")) {
						// Transfer
						System.out.println("Transfer");
						System.out.println("Enter the Username of the person you want to transfer money to");
						String toUsername = scanner.nextLine();
						// get the user if user exitits
						Customer c3 = getCustomer(Main.customerList, toUsername);
						// if c2 is not null
						if (c3 != null) {
							System.out.println("Enter the Amount you want to Trasfer");
							if (scanner.hasNextDouble()) {
								double amount = scanner.nextDouble();
								if (amount >= 0) {

									if (c.getJointAccount()) {
										Customer c2 = getCustomer(Main.customerList, c.getJoinCustomerUsername());
										transferJointCustomer(c, c2, c3, amount);
										scanner.nextLine();
									} else {
										transferAmount(c, c3, amount);
										scanner.nextLine();
									}

								} else {
									System.out.println("Negative Amount");
									scanner.nextLine();
								}
							} else {
								System.out.println("Invalid Format");
								scanner.nextLine();
							}
						}
					} else if (choice.equals("4")) {
						// Chose Log Out
						logIn = false; // setting login to false of the while loop ends
					} else {
						System.out.println("Invalid Choice!");
					}
				} // While loop Ends
			} else {
				System.out.println("Could Not Find the user");
			}
		} else if (choice.equals("2")) {

			// Sign Up
			System.out.println("Selected Sign Up");
			System.out.println("1. New Account\n2. New Joint Account");
			choice = scanner.nextLine();
			switch (choice) {
			case "1": {
				// Selected Single Account
				System.out.println("Selected Single Acc");
				System.out.println("Enter your Username:");
				String cUser = scanner.nextLine();
				if (checkUsername(cUser)) {
					System.out.println("Username Existis!");
					break;
				}
				System.out.println("Enter your First Name:");
				String cFirst = scanner.nextLine();
				System.out.println("Enter your Last Name:");
				String cLast = scanner.nextLine();
				System.out.println("Enter your Password:");
				String cPass = scanner.nextLine();
				// Create acc with info
				createCustomer(cFirst, cLast, cUser, cPass);
				System.out.println("Sending Account for Rivew.");
				scanner.nextLine();
				break;
			}
			case "2": {
				// Selected Joint Account
				System.out.println("Enter your Username:");
				String cUser = scanner.nextLine();
				if (checkUsername(cUser)) {
					System.out.println("Username Existis!");
					break;
				}
				System.out.println("Enter your First Name:");
				String cFirst = scanner.nextLine();
				System.out.println("Enter your Last Name:");
				String cLast = scanner.nextLine();
				System.out.println("Enter your Password:");
				String cPass = scanner.nextLine();
				System.out.println("Enter joint user Username:");
				String cjUser = scanner.nextLine();
				if (checkUsername(cjUser)) {
					System.out.println("Username Existis!");
					break;
				}
				System.out.println("Enter Joint User First Name:");
				String cjFirst = scanner.nextLine();
				System.out.println("Enter Joint user Last Name:");
				String cjLast = scanner.nextLine();

				System.out.println("Enter jointuser Password:");
				String cjPass = scanner.nextLine();
				// Creating Joint Account
				createJointCustomer(cFirst, cLast, cUser, cPass, cjFirst, cjLast, cjUser, cjPass);
				System.out.println("Sending Account for Rivew.");
				scanner.nextLine();
				break;
			}
			}
		} else {
			System.out.println("Invalid Choice!");
			scanner.nextLine();
		}
	}

	public static void transferJointCustomer(Customer c, Customer c2, Customer c3, double amount) {
		// Setting both Account to be the same balace
		c.setAccountBalance(c.getAccountBalance() - amount);
		c2.setAccountBalance(c.getAccountBalance());
		c3.setAccountBalance(c3.getAccountBalance() + amount);
	}

	// Withdraw From Joint Account
	public static void withdrawJointCustomer(Customer c, Customer c2, double amount) {

		// Checking if the account have enoguh money for withdraw
		if (c.getAccountBalance() >= amount) {
			// withdrawing money from acc
			c.setAccountBalance(c.getAccountBalance() - amount);
			c2.setAccountBalance(c.getAccountBalance());
			System.out.println("Withdrew $" + amount + " from Account");
			scanner.nextLine();

		} else {
			System.out.println("Do not have enough money!");
			scanner.nextLine();
		}
	}

	public static void depositJointCustomer(Customer c, Customer c2, double amount) {
		// Updating Both Customers Balance
		c.setAccountBalance(c.getAccountBalance() + amount);
		System.out.println("Added $" + amount + " to Account");
		c2.setAccountBalance(c.getAccountBalance());
	}

	// Creating 2 Accounts connected
	public static void createJointCustomer(String cFirst, String cLast, String cUser, String cPass, String cjFirst,
			String cjLast, String cjUser, String cjPass) {
		// Create First User
		createCustomer(cFirst, cLast, cUser, cPass);
		// Create Second User
		createCustomer(cjFirst, cjLast, cjUser, cjPass);
		// Connect the user

		// Get the customer object
		Customer c1 = getCustomer(Main.newCustomers, cUser);
		Customer c2 = getCustomer(Main.newCustomers, cjUser);

		// Set jointcustomer username/ joint accout to true
		c1.setJoinCustomerUsername(cjUser);
		c1.setJointAccount(true);
		c2.setJoinCustomerUsername(cUser);
		c2.setJointAccount(true);
	}

	public static void createCustomer(String cFirst, String cLast, String cUser, String cPass) {
		// Check if Username Is in List
		if (checkUsername(cUser)) {
			// Username is in the list
			System.out.println("UserName is in the list");
			scanner.nextLine();
		} else {
			Customer c = new Customer(cFirst, cLast, cUser, cPass, false, null);
			Main.newCustomers.add(c);
		}
	}

	// Returns true if the username given is in the customer list
	public static boolean checkUsername(String cUser) {
		for (Customer c : Main.customerList) {
			if (c.getCustomerUsername().equals(cUser)) {
				// return true if user name is found
				return true;
			}
		}
		return false;
	}

	// Deposit Money to Account
	public static void depositCustomer(Customer c, double amount) {
		// Adding the Money to the Account
		c.setAccountBalance(c.getAccountBalance() + amount);
		System.out.println("Added $" + amount + " to Account");
		scanner.nextLine();
	}

	// Withdraw Money From Account
	public static void withdrawCustomer(Customer c, double amount) {
		// Checking if the account have enoguh money for withdraw
		if (c.getAccountBalance() >= amount) {
			// withdrawing money from acc
			c.setAccountBalance(c.getAccountBalance() - amount);
			System.out.println("Withdrew $" + amount + " from Account");
			scanner.nextLine();

		} else {
			System.out.println("Do not have enough money!");
			scanner.nextLine();
		}
	}

	// Transfer money from one acc to the next;
	public static void transferAmount(Customer c, Customer c2, double amount) {
		c.setAccountBalance(c.getAccountBalance() - amount);
		c2.setAccountBalance(c2.getAccountBalance() + amount);
	}

	// returns the ustomer with only username;
	public static Customer getCustomer(ArrayList<Customer> customerList, String toUsername) {
		for (Customer c : customerList) {
			if (c.getCustomerUsername().equals(toUsername)) {
				// User Exitis
				return c;
			}
		}
		System.out.println("Could not Find User!");
		return null;
	}

	// Return the Customer with the username and password
	public static Customer customerLogin(ArrayList<Customer> customerList) {
		// LogIn for Customer
		System.out.println("Enter the Username!");
		String cUsername = scanner.nextLine();
		System.out.println("Enter the Password!");
		String cPassword = scanner.nextLine();

		// Checking if user is in the list
		for (Customer c : customerList) {
			// Checking if Username and Password Match
			if (c.getCustomerUsername().equals(cUsername) && c.getCustomerPassword().equals(cPassword)) {
				// Returning the user with the correct Username and Password
				System.out.println("Logging In....");
				scanner.nextLine();
				return c;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Customer [customerFirstName=" + customerFirstName + ", customerLastName=" + customerLastName
				+ ", customerUsername=" + customerUsername + ", joinCustomerUsername=" + joinCustomerUsername
				+ ", customerPassword=" + customerPassword + ", accountBalance=" + accountBalance + ", jointAccount="
				+ jointAccount + "]";
	}

	@Override
	protected void finalize() throws Throwable {
		scanner.close();
		super.finalize();
	}

}
