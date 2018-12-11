package com.davidahincapie.uilayer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.davidahincapie.busniesslayer.Account;
import com.davidahincapie.busniesslayer.Customer;
import com.davidahincapie.busniesslayer.Employee;
import com.davidahincapie.busniesslayer.User;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static int option;
	static boolean session = true;
	static String userName;
	static String password;
	static ArrayList<User> userList = new ArrayList<>();
	static Account account;
	static User user;
	static Employee employeeUser;
	static Customer customerUser;
	static String inputString;

	public static void main(String[] args) {
		customerUser = new Customer("Jim", "Beam", "bourbon", "cheers", true);
		customerUser.setApproved(true);
		userList.add(customerUser);

		customerUser = new Customer("Jack", "Daniels", "whiskey", "cheers", true);
		customerUser.setApproved(true);
		userList.add(customerUser);

		Employee employeeUser = new Employee("Ian", "Jay", "brandy", "cheers");
		employeeUser.setAdmin(true);
		userList.add(employeeUser);

		while (session) {
			initApp();
		}

		System.out.println("LAST STATEMENT!!!!!");
		scanner.close();
	}

	public static void initApp() {
		welcomeMessage();
		option = scanner.nextInt();
		switch (option) {

		case 1:
			loginInPrompt();
			if (user.isCustomer()) {
				customerPrompt();
			} else if (user.isEmployee()) {
				employeePrompt();
			}
			break;
		case 2:
			signUpPrompt();
			if (user.isCustomer()) {
				customerPrompt();
			} else if (user.isEmployee()) {
				employeePrompt();
			}
		case 10:
			session = false;
			exitMessage();
			break;
		default:
			System.out.println("Invalid Option.");
			initApp();
			break;
		}
	}

	// TODO employee and admin menu
	private static void employeePrompt() {
		employeeUser = (Employee) user;
		if (employeeUser.isAdmin()) {
			System.out.println("\nHello " + employeeUser.getFirstName()
					+ ", what would you like to do?\n\n 1) View All Users\n 2) View Employees\n 3) View Customers\n 4) Account Approval\n 5) Edit Account\n 6) Close Account"
					+ "\n\n 0) Log Out\n\n 10) Exit\n\n");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println("\n\nAll Users:".toUpperCase());
				for (User u : userList) {
					System.out.println(u);
				}
				employeePrompt();
				break;
			case 2:
				System.out.println("\n\nEmployees:".toUpperCase());
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isEmployee()) {
						System.out.println(userList.get(i));
					}
				}
				employeePrompt();
				break;
			case 3:
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isCustomer()) {
						System.out.println(userList.get(i));
					}
				}
				employeePrompt();
				break;
			case 4:
				Customer tempCust = new Customer();
				System.out.print("\nWhich account would you like to change approval (Account Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isCustomer()) {
						tempCust = (Customer) userList.get(i);
						if (tempCust.isApproved()) {
							if (tempCust.getAccount().getAccountId().toString().equals(inputString)) {
								((Customer) userList.get(i)).setApproved(false);

								System.out
										.println("\nThe account for " + tempCust.getFirstName() + " has been denied.");

							} else if (i == userList.size() - 1) {
								System.out.println("\nAccount not found.");
							}
						} else if (!tempCust.isApproved()) {
							((Customer) userList.get(i)).setApproved(false);
							System.out.println("\nThe account for " + tempCust.getFirstName() + " has been approved.");
						}
					}
				}
				employeePrompt();
				break;
			case 5:
				System.out.println("##########  IN PROGRESS  ##########");
				User tempUser = new User();
				System.out.print("\n\nWhich account would you like to edit (User Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getUserId().toString().equals(inputString)) {
						System.out.println(
								"\nWhat would you like to edit?\n 1) First Name\n 2) Last Name\n 3) Username\n 4) Password\n 5) Deposit\n 6) Withdraw\n 7) Transfer");
						switch (option) {
						case 1:
							System.out.println("Enter new First Name: ");
							inputString = scanner.next();
							userList.get(i).setFirstName(inputString);
							break;

						default:
							break;
						}
					} else if (i == userList.size() - 1) {
						System.out.println("\nAccount not found.");
					}
				}
				employeePrompt();
				break;
			case 6:
				System.out.print("\nWhich account would you like to Close (User Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getUserId().toString().equals(inputString)) {
						System.out.println("\nACCOUNT CLOSED.\n" + userList.get(i));
						userList.remove(i);
						break;
					} else if (i == userList.size() - 1) {
						System.out.println("\nAccount not found.");
					}
				}
				employeePrompt();
				break;

			case 0:
				initApp();
				break;
			case 10:
				session = false;
				exitMessage();
				break;

			default:
				System.out.println("Invalid Option.");
				employeePrompt();
				break;
			}
		} else {
			System.out.println("\nHello " + employeeUser.getFirstName()
					+ ", what would you like to do?\n\n 1) View Customers \n 2) Account Approval"
					+ "\n\n 0) Log Out\n\n 10) Exit\n\n");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isCustomer()) {
						System.out.println(userList.get(i));
					}
				}
				employeePrompt();
				break;
			case 2:
				Customer tempCust = new Customer();
				System.out.print("\nWhich account would you like to change approval (Account Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isCustomer()) {
						tempCust = (Customer) userList.get(i);
						if (tempCust.isApproved()) {
							if (tempCust.getAccount().getAccountId().toString().equals(inputString)) {
								((Customer) userList.get(i)).setApproved(false);

								System.out
										.println("\nThe account for " + tempCust.getFirstName() + " has been denied.");

							} else if (i == userList.size() - 1) {
								System.out.println("\nAccount not found.");
							}
						} else if (!tempCust.isApproved()) {
							((Customer) userList.get(i)).setApproved(false);
							System.out.println("\nThe account for " + tempCust.getFirstName() + " has been approved.");
						}
					}
				}
				employeePrompt();
				break;
			case 0:
				initApp();
				break;
			case 10:
				session = false;
				exitMessage();
				break;

			default:
				System.out.println("Invalid Option.");
				employeePrompt();
				break;
			}
		}
	}

	private static void customerPrompt() {
		customerUser = (Customer) user;
		if (customerUser.isApproved()) {
			System.out.print("\nHello " + customerUser.getFirstName()
					+ ", what would you like to do?\n\n 1) Display Balance \n 2) Deposit"
					+ "\n 3) Withdraw\n 4) Transfer Funds\n 5) Joint Account\n\n 0) Log Out\n\n 10) Exit\n\n");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				System.out.println(customerUser.getUserName() + " balance: " + customerUser.getAccount().getBalance());
				customerPrompt();
				break;
			case 2:
				System.out.print("Enter deposit amount: ");
				customerUser.getAccount().deposit(scanner.nextDouble());
				customerPrompt();
				break;
			case 3:
				System.out.print("Enter withdraw amount: ");
				customerUser.getAccount().withdraw(scanner.nextDouble());
				customerPrompt();
				break;
			case 4:
				System.out.print("Enter account ID:");
				inputString = scanner.next();
				Customer tempCust = new Customer();
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).isCustomer()) {
						tempCust = (Customer) userList.get(i);
						System.out.println("\n" + tempCust.getUserName() + " : "
								+ tempCust.getAccount().getAccountId().toString());
						if (!tempCust.isApproved()) {
							System.out.println("The account for " + tempCust.getFirstName() + " is pending approval.");
						} else if (tempCust.getAccount().getAccountId().toString().equals(inputString)) {
							System.out.print("\nEnter amount you would like to transfer:");
							customerUser.getAccount().transferFunds(tempCust.getAccount(), scanner.nextDouble());
							customerPrompt();
						} else if (i == userList.size() - 1) {
							System.out.println("Account not found.");
						}

					}
				} // end of for

				customerPrompt();
				break;
			case 5:
				if (customerUser.getAccount().isJointAccount()) {
					System.out.println(
							customerUser.getFirstName() + " " + customerUser.getLastName() + " is a joint account.");
					System.out.println("Would you like to keep your account a joint account?\n 1) Yes\n 2) No");
					option = scanner.nextInt();
					switch (option) {
					case 1:
						customerUser.getAccount().setJointAccount(true);
						System.out.println("Your account has not changed.");
						break;
					case 2:
						customerUser.getAccount().setJointAccount(false);
						System.out.println("Your account is no longer a joint account.");
					default:
						System.out.println("Invalid Input.");
						break;
					}
				} else {
					System.out.println(customerUser.getFirstName() + " " + customerUser.getLastName()
							+ " is NOT a joint account.");
					System.out.println("Would you like to make your account a joint account?\n 1) Yes\n 2) No");
					option = scanner.nextInt();
					switch (option) {
					case 1:
						customerUser.getAccount().setJointAccount(true);
						System.out.println("Your account is now a joint account.");
						break;
					case 2:
						customerUser.getAccount().setJointAccount(false);
						System.out.println("Your account has not changed.");
						break;
					default:
						System.out.println("Invalid Input.");
						break;
					}
				}
				customerPrompt();
				break;
			case 0:
				initApp();
				break;
			case 10:
				session = false;
				exitMessage();
				break;

			default:
				System.out.println("Invalid Option.");
				customerPrompt();
				break;
			}
		} else {
			System.out.println("Account pending approval.");
			initApp();
		}

	}

	public static void loginInPrompt() {
		if (!userList.isEmpty()) {

			System.out.print("\nEnter your Username: ");
			userName = scanner.next();

			System.out.print("Enter your Password: ");
			password = scanner.next();

			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserName().contains(userName)
						&& userList.get(i).getPassword().contains(password)) {
					System.out.println("\n" + userName + " found.");
					user = userList.get(i);
					System.out.println(user.toString());
					return;
				} else if (i == userList.size() - 1) {
					System.out.println(userName + " NOT found.");
					initApp();
				}
			}
		} else {
			System.out.println("\nThere are no users.");
			initApp();
		}
	}

	public static void signUpPrompt() {
		System.out.println("\n\nSign up");
		System.out.println("\nType of user:\n 1) Customer \n 2) Employee\n\n 0) Go Back ");
		option = scanner.nextInt();
		switch (option) {
		case 1:
			customerUser = new Customer();
			customerUser.setCustomer(true);
			customerUser.setApproved(false);
			System.out.print("\nPlease enter First Name: ");
			customerUser.setFirstName(scanner.next());
			System.out.print("Please enter Last Name: ");
			customerUser.setLastName(scanner.next());
			System.out.print("Please enter User Name: ");
			customerUser.setUserName(scanner.next());
			System.out.print("Please enter Password: ");
			customerUser.setPassword(scanner.next());
			customerUser.toString();
			userList.add(customerUser);
			user = customerUser;
			break;
		case 2:
			employeeUser = new Employee();
			user.setEmployee(true);
			user = employeeUser;
			employeeUser.setAdmin(false);
			System.out.print("\nPlease enter First Name: ");
			user.setFirstName(scanner.next());
			System.out.println("Please enter Last Name: ");
			user.setLastName(scanner.next());
			System.out.println("Please enter User Name: ");
			user.setUserName(scanner.next());
			System.out.println("Please enter Password: ");
			user.setPassword(scanner.next());
			employeeUser.toString();
			user = employeeUser;
			break;
		case 0:
			initApp();
			break;
		default:
			System.out.println("Invalid Option.");
			signUpPrompt();
			break;
		}
		for (User user : userList) {
			System.out.println(user);
		}
		System.out.println("Hello " + customerUser.getFirstName() + " " + customerUser.getLastName());
	}

	public static void welcomeMessage() {
		System.out.println("\n\tHello");
		System.out.println("\nWelcome to Revature Bank");
		System.out.println("\n 1) Login");
		System.out.println(" 2) Sign Up");
		System.out.println("\n 10) Exit");
	}

	public static void exitMessage() {

		System.out.println("\nThank you");
		System.out.println("Good Bye");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void clearConsole() {
		for (int i = 0; i < 12; i++) {
			System.out.println();

		}
	}
}
