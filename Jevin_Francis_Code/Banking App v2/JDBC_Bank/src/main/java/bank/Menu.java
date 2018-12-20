package bank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Menu {

	static CustomerDao cdao = new CustomerDaoImpl();
	static Scanner scanner = new Scanner(System.in);

	// Start Customer Menu
	public static void customerMenu() {
		System.out.println("Selected Customer");
		System.out.println("1. LogIn\n2. Sign Up");

		String choice = scanner.nextLine();
		if (choice.equals("1")) {

			// LogIn Customer
			System.out.println("Selected LogIn");
			// Signed In As
			Customer c = Customer.customerLogin(Main.customerList);
			// Joint Account User
			showUserLoginMenu(c);
		} else if (choice.equals("2"))

		{
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
				if (Customer.checkUsername(cUser)) {
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
				Customer.createCustomer(cFirst, cLast, cUser, cPass);
				System.out.println("Sending Account for Rivew.");
				scanner.nextLine();
				break;
			}
			case "2": {
				// Selected Joint Account
				System.out.println("Enter your Username:");
				String cUser = scanner.nextLine();
				if (Customer.checkUsername(cUser)) {
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
				if (Customer.checkUsername(cjUser) || cjUser.equals(cUser)) {
					System.out.println("Cannot Create Account with that Username!");
					break;
				}
				System.out.println("Enter Joint User First Name:");
				String cjFirst = scanner.nextLine();
				System.out.println("Enter Joint user Last Name:");
				String cjLast = scanner.nextLine();

				System.out.println("Enter jointuser Password:");
				String cjPass = scanner.nextLine();
				// Creating Joint Account
				Customer.createJointCustomer(cFirst, cLast, cUser, cPass, cjFirst, cjLast, cjUser, cjPass);
				System.out.println("Sending Account for Rivew.....");
				scanner.nextLine();
				break;
			}
			}
		} else {
			System.out.println("Invalid Choice!");
			scanner.nextLine();
		}
	}

	// UI for User when loggedin Menu
	static void showUserLoginMenu(Customer c) {
		if (c != null) {
			// Logged In Sucessfully
			Boolean logIn = true;
			// Runs Until Selected Log out or Exit with 0
			while (logIn) {
				System.out.println("------------------------------------");
				System.out.println("Logged In as " + c.getCustomerFirstName() + " " + c.getCustomerLastName());
				System.out.println("Account Balance: $" + c.getAccountBalance());
				System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Log Out\n\n9. Delete");
				String choice = scanner.nextLine();
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
							if (c.getJointAccount().equals("Yes")) {
								Customer c2 = Customer.getCustomer(Main.customerList, c.getJoinCustomerUsername());
								Customer.depositJointCustomer(c, c2, amount);
								scanner.nextLine();
							} else {
								Customer.depositCustomer(c, amount);
								scanner.nextLine();
							}
						} else {
							// Was Negative
							System.out.println("Invalid Amount");
							scanner.nextLine();
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
							if (c.getJointAccount().equals("Yes")) {
								Customer c2 = Customer.getCustomer(Main.customerList, c.getJoinCustomerUsername());
								Customer.withdrawJointCustomer(c, c2, amount);
								scanner.nextLine();
							} else {
								Customer.withdrawCustomer(c, amount);
								scanner.nextLine();
							}
						} else {
							// Was Negative
							System.out.println("Invalid Amount");
							scanner.nextLine();
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
					Customer c3 = Customer.getCustomer(Main.customerList, toUsername);
					// if c2 is not null
					if (c3 != null) {
						System.out.println("Enter the Amount you want to Trasfer");
						if (scanner.hasNextDouble()) {
							double amount = scanner.nextDouble();
							if (amount >= 0) {

								if (c.getJointAccount().equals("Yes")) {
									Customer c2 = Customer.getCustomer(Main.customerList, c.getJoinCustomerUsername());
									Customer.transferJointCustomer(c, c2, c3, amount);
									scanner.nextLine();
								} else {
									Customer.transferAmount(c, c3, amount);
									scanner.nextLine();
								}
							} else {
								System.out.println("Invalid Amount");
								scanner.nextLine();
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
				} else if (choice.equals("9")) {
					if (c.getAccountBalance() == 0) {
						System.out.println("Deleting.....");
						// Deleting //If Join account
						if (c.getJointAccount().equals("Yes")) {
							Customer c2 = Customer.getCustomer(Main.customerList, c.getJoinCustomerUsername());
							Main.customerList.remove(c);
							cdao.removeCustomer(c);
							Main.customerList.remove(c2);
							cdao.removeCustomer(c2);
							logIn = false;
						} else {
							Main.customerList.remove(c);
							cdao.removeCustomer(c);
							logIn = false;
						}
						logIn = false;
						scanner.nextLine();
					} else {
						System.out.println("Cannot Delete with remaining balance..");
						scanner.nextLine();
					}
				} else {
					System.out.println("Invalid Choice!");
				}
			} // While loop Ends
		} else {
			System.out.println("Could Not Find the user");
			scanner.nextLine();
		}
	}

	// Employee Menu
	public static void employeeMenu() {

		System.out.println("Selected Employee");
		System.out.println("1. Employee Login\n2. Admin Login");
		String choice = scanner.nextLine();
		switch (choice) {
		case "1":
			// login Employee
			Employee e = Employee.employeeLogin(Main.employeeList);

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
						Customer.printCustomerInfo(cUsername);
						scanner.nextLine();
						break;
					case "2":
						// View All User In List
						for (Customer c : Main.customerList) {
							Customer.printCustomerInfo(c.getCustomerUsername());
							System.out.println();
						}
						scanner.nextLine();
						break;
					case "3":
						// Approve Deny Accounts
						for (int i = 0; i < Main.newCustomers.size(); i++) {
							Customer c = Customer.getCustomer(Main.newCustomers,
									Main.newCustomers.get(i).getCustomerUsername());
							System.out.println(
									"Review:\nName: " + c.getCustomerFirstName() + " " + c.getCustomerLastName());
							System.out.println("User Name: " + c.getCustomerUsername());
							System.out.println("Password: " + c.getCustomerPassword());
							System.out.println("Joint Accout : " + c.getJointAccount());
							if (c.getJointAccount().equals("Yes")) {
								System.out.println("Joint Username: " + c.getJoinCustomerUsername());
							}
							System.out.println("1. Approve\n2. Deny");
							choice = scanner.nextLine();
							switch (choice) {
							case "1":
								Main.customerList.add(c);
								Main.newCustomers.remove(i);
								cdao.addCustomer(c);
								cdao.removeNewCustomer(c);
								System.out.println("Approved");
								break;
							case "2":
								System.out.println("Denied");
								Main.newCustomers.remove(i);
								cdao.removeNewCustomer(c);
								break;
							default:
								// Clearing the Waitlist after Review
								break;
							}
						}
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
				scanner.nextLine();
				break;
			}
			break;
		case "2":
			System.out.println("Admin Login");
			adminMenu();

			break;
		default:
			break;
		}
	}

	// Admin Menu
	public static void adminMenu() {
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("database.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Enter Admin Username: ");
		String adminUser = scanner.nextLine();
		System.out.println("Enter Admin Password: ");
		String adminPass = scanner.nextLine();

		Boolean logIn = false;
		if (adminUser.equals(prop.getProperty("adminuser")) && adminPass.equals(prop.getProperty("adminpass"))) {
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
				System.out.println("Enter the Username of the Customer.");
				String cUsername = scanner.nextLine();
				Customer c2 = Admin.loginAsCustomer(cUsername);
				Menu.showUserLoginMenu(c2);

				break;
			case "2":
				// View All User In List
				for (Customer c : Main.customerList) {
					Customer.printCustomerInfo(c.getCustomerUsername());
					System.out.println();
				}
				scanner.nextLine();
				break;
			case "3":
				// Approve Deny Accounts
				for (int i = 0; i < Main.newCustomers.size(); i++) {
					Customer c = Customer.getCustomer(Main.newCustomers,
							Main.newCustomers.get(i).getCustomerUsername());
					System.out.println("Review:\nName: " + c.getCustomerFirstName() + " " + c.getCustomerLastName());
					System.out.println("User Name: " + c.getCustomerUsername());
					System.out.println("Password: " + c.getCustomerPassword());
					System.out.println("Joint Accout : " + c.getJointAccount());
					if (c.getJointAccount().equals("Yes")) {
						System.out.println("Joint Username: " + c.getJoinCustomerUsername());
					}

					System.out.println("1. Approve\n2. Deny");
					choice = scanner.nextLine();
					switch (choice) {
					case "1":
						Main.customerList.add(c);
						Main.newCustomers.remove(i);
						i--;
						cdao.addCustomer(c);
						cdao.removeNewCustomer(c);
						System.out.println("Approved");
						scanner.nextLine();
						break;
					case "2":
						System.out.println("Denied");
						Main.newCustomers.remove(i);
						i--;
						cdao.removeNewCustomer(c);
						scanner.nextLine();
						break;
					default:
						break;
					}
				}
				// Clearing the Waitlist after Review
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
