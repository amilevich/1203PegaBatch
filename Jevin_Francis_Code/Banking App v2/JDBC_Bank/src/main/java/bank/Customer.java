package bank;

import java.util.ArrayList;
import java.util.Scanner;

public class Customer {

	static CustomerDao cdao = new CustomerDaoImpl();
	// Start Scanner
	static Scanner scanner = new Scanner(System.in);

	// Customer Variables
	private String customerFirstName;
	private String customerLastName;
	private String customerUsername;
	private String joinCustomerUsername; // Username of the joint ustomer only for Join Accounts
	private String customerPassword;
	private Double accountBalance;
	private String jointAccount; // Set if Its a Joint Account

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

	public String getJointAccount() {
		return jointAccount;
	}

	public void setJointAccount(String string) {
		this.jointAccount = string;
	}
	// End Getters and Setters

	// Constructor
	public Customer(String customerFirstName, String customerLastName, String customerUsername, String customerPassword,
			String jointAccount, String joinCustomerUsername) {
		super();
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
		this.jointAccount = jointAccount;
		this.joinCustomerUsername = joinCustomerUsername;
		this.setAccountBalance(0.0);
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	public static void transferJointCustomer(Customer c, Customer c2, Customer c3, double amount) {
		// Setting both Account to be the same balace
		if (c.getAccountBalance() >= amount) {
			// withdrawing money from acc
			c.setAccountBalance(c.getAccountBalance() - amount);
			cdao.updateAmountCustomer(c);
			c2.setAccountBalance(c.getAccountBalance());
			cdao.updateAmountCustomer(c2);
			c3.setAccountBalance(c3.getAccountBalance() + amount);
			cdao.updateAmountCustomer(c3);
			System.out.println("Transfering $" + amount + "....");
			scanner.nextLine();

		} else {
			System.out.println("Do not have enough money!");
			scanner.nextLine();
		}

	}

	// Withdraw From Joint Account
	public static void withdrawJointCustomer(Customer c, Customer c2, double amount) {

		// Checking if the account have enoguh money for withdraw
		if (c.getAccountBalance() >= amount) {
			// withdrawing money from acc
			c.setAccountBalance(c.getAccountBalance() - amount);
			cdao.updateAmountCustomer(c);
			c2.setAccountBalance(c.getAccountBalance());
			cdao.updateAmountCustomer(c2);
			System.out.println("Withdrew $" + amount + " from Account");
			scanner.nextLine();
		} else {
			System.out.println("Do not have enough money!");
			scanner.nextLine();
		}
	}

	// Deposit for joint Customer
	public static void depositJointCustomer(Customer c, Customer c2, double amount) {
		// Updating Both Customers Balance
		c2.setAccountBalance(c2.getAccountBalance() + amount);
		cdao.updateAmountCustomer(c2);
		System.out.println("Added $" + amount + " to Account");
		scanner.nextLine();
		c.setAccountBalance(c2.getAccountBalance());
		cdao.updateAmountCustomer(c);
	}

	// Creating Connected Accounts
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
		c1.setJointAccount("Yes");
		cdao.updateJoin(c1);
		c2.setJoinCustomerUsername(cUser);
		c2.setJointAccount("Yes");
		cdao.updateJoin(c2);
	}

	public static void createCustomer(String cFirst, String cLast, String cUser, String cPass) {
		// Check if Username Is in List
		if (checkUsername(cUser)) {
			// Username is in the list
			System.out.println("UserName is in the list");
			scanner.nextLine();
		} else {
			Customer c = new Customer(cFirst, cLast, cUser, cPass, "No", null);
			Main.newCustomers.add(c);
			cdao.addNewCustomer(c);
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
		cdao.updateAmountCustomer(c);
		scanner.nextLine();

	}

	// Withdraw Money From Account
	public static void withdrawCustomer(Customer c, double amount) {

		// Checking if the account have enoguh money for withdraw
		if (c.getAccountBalance() >= amount) {
			// withdrawing money from acc
			c.setAccountBalance(c.getAccountBalance() - amount);
			cdao.updateAmountCustomer(c);
			System.out.println("Withdrew $" + amount + " from Account");
			scanner.nextLine();

		} else {
			System.out.println("Do not have enough money!");
			scanner.nextLine();
		}
	}

	// Transfer money from one acc to the next;
	public static void transferAmount(Customer c, Customer c2, double amount) {
		if (c.getAccountBalance() >= amount) {
			// withdrawing money from acc
			if (c2.getJointAccount().equals("Yes")) {
				System.out.println("Transfering $" + amount + "....");
				c.setAccountBalance(c.getAccountBalance() - amount);
				cdao.updateAmountCustomer(c);
				c2.setAccountBalance(c2.getAccountBalance() + amount);
				cdao.updateAmountCustomer(c2);
				Customer c3 = getCustomer(Main.customerList, c2.getJoinCustomerUsername());
				c3.setAccountBalance(c2.getAccountBalance());
				cdao.updateAmountCustomer(c3);
				scanner.nextLine();
			} else {
				c.setAccountBalance(c.getAccountBalance() - amount);
				cdao.updateAmountCustomer(c);
				c2.setAccountBalance(c2.getAccountBalance() + amount);
				cdao.updateAmountCustomer(c2);
				System.out.println("Transfering $" + amount + "....");
				scanner.nextLine();
			}

		} else {
			System.out.println("Do not have enough money!");
			scanner.nextLine();
		}

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
		scanner.nextLine();
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

	// Prints Customer Info in Good Format
	public static void printCustomerInfo(String cUsername) {
		Boolean findUser = false;
		for (Customer c : Main.customerList) {
			if (c.getCustomerUsername().equals(cUsername)) {
				findUser = true;
				System.out.println("Name : " + c.getCustomerFirstName() + " " + c.getCustomerLastName());
				System.out.println("Account Balance: " + c.getAccountBalance());
				System.out.println("Username: " + c.getCustomerUsername());
				System.out.println("Password: " + c.getCustomerPassword());
				System.out.println("Joint Accout : " + c.getJointAccount());
				if (c.getJointAccount().equals("Yes")) {
					System.out.println("Joint Username: " + c.getJoinCustomerUsername());
				}
			}
		}
		if (findUser == false) {
			System.out.println("Could Not Find The user");
			scanner.nextLine();
		}
	}

}
