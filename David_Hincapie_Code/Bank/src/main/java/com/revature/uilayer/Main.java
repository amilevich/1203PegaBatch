package com.revature.uilayer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.revature.businesslayer.Account;
import com.revature.businesslayer.Customer;
import com.revature.businesslayer.User;
import com.revature.businesslayer.Usertype;
import com.revature.datalayer.AccountDaoImpl;
import com.revature.datalayer.CustomerDaoImpl;
import com.revature.datalayer.UserDaoImpl;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	public static int option;
	public static double amount;
	public static boolean session = true;
	public static String userName;
	public static String password;
	public static ArrayList<User> userList = new ArrayList<User>();
	public static Account account;
	public static User user;
	public static Customer customerUser;
	public static Customer tempCust;
	public static String inputString;
	public static Logger log = Logger.getLogger(Main.class.getName());
	public static UserDaoImpl userDaoImpl = new UserDaoImpl();
	public static CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	public static AccountDaoImpl accountDaoImpl = new AccountDaoImpl();

	public static void main(String[] args) {
		customerUser = new Customer("Jim", "Beam", "bourbon", "cheers", Usertype.CUSTOMER.toString());
		customerUser.setApproved(true);
		userList.add(customerUser);

		customerUser = new Customer("Jack", "Daniels", "whiskey", "cheers", Usertype.CUSTOMER.toString());
		customerUser.setApproved(true);
		userList.add(customerUser);

		user = new User("Ian", "Jay", "brandy", "cheers", Usertype.ADMIN.toString());
		userList.add(user);

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
			if (user.getUserType() == Usertype.CUSTOMER) {
				customerPrompt();
			} else if (user.getUserType().equals(Usertype.EMPLOYEE) || user.getUserType().equals(Usertype.ADMIN)) {
				employeePrompt();
			}
			break;
		case 2:
			signUpPrompt();
			if (user.getUserType() == Usertype.CUSTOMER) {
				customerPrompt();
			} else if (user.getUserType().equals(Usertype.EMPLOYEE) || user.getUserType().equals(Usertype.ADMIN)) {
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
			customerUser.setUserType(Usertype.CUSTOMER.toString());
			;
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
			user = new User();
			user.setUserType(Usertype.EMPLOYEE.toString());
			System.out.print("\nPlease enter First Name: ");
			user.setFirstName(scanner.next());
			System.out.println("Please enter Last Name: ");
			user.setLastName(scanner.next());
			System.out.println("Please enter User Name: ");
			user.setUserName(scanner.next());
			System.out.println("Please enter Password: ");
			user.setPassword(scanner.next());
			user.toString();
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

	public static void employeePrompt() {
		if (user.getUserType().equals(Usertype.ADMIN)) {
			adminEditPrompt();
		} else {
			employeeEditPrompt();
		}
	}

	private static void customerPrompt() {
		customerUser = (Customer) user;
		if (customerUser.isApproved()) {
			customerEditPrompt();
		} else {
			System.out.println("Account pending approval.");
			initApp();
		}

	}

	public static void adminEditPrompt() {
		System.out.println("\nHello " + user.getFirstName()
				+ ", what would you like to do?\n\n 1) View All Users\n 2) View Employees\n 3) View Customers\n 4) Edit User\n 5) Account Approval\n \n 6) Close Account\n 7) Create User\n 8) Delete User\n "
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
				if (userList.get(i).getUserType().equals(Usertype.EMPLOYEE)
						|| userList.get(i).getUserType().equals(Usertype.ADMIN)) {
					System.out.println(userList.get(i));
				}
			}
			employeePrompt();
			break;
		case 3:
			System.out.println("\n\nCustomers:".toUpperCase());
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					System.out.println(userList.get(i));
				}
			}
			employeePrompt();
			break;

		case 4:
			for (User user : userList) {
				System.out.println(user.getFirstName() + " " + user.getLastName() + ": (User ID) " + user.getUserId());
			}
			System.out.print("\n\nWhich account would you like to edit (User Id)? ");
			inputString = scanner.next();
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserId().toString().equals(inputString)) {
					System.out.println("\nEditing: " + userList.get(i).getFirstName() + " "
							+ userList.get(i).getLastName() + " \n" + userList.get(i).toString());
					if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customerUser = (Customer) userList.get(i);
						System.out.println(
								"\nWhat would you like to edit?\n 1) First Name\n 2) Last Name\n 3) Username\n 4) Password\n 5) Deposit\n 6) Withdraw\n 7) Transfer\n\n 0) Main Menu");
						option = scanner.nextInt();
						switch (option) {
						case 1:
							System.out.print("Enter new First Name: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed first name from "
											+ userList.get(i).getFirstName() + " to " + inputString + ".");
							userList.get(i).setFirstName(inputString);
							break;
						case 2:
							System.out.print("Enter new Last Name: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed last name from "
											+ userList.get(i).getLastName() + " to " + inputString + ".");
							userList.get(i).setLastName(inputString);
							break;
						case 3:
							System.out.print("Enter new Username: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed  username from "
											+ userList.get(i).getUserName() + " to " + inputString + ".");
							userList.get(i).setUserName(inputString);
							break;
						case 4:
							System.out.print("Enter new Password: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed password from "
											+ userList.get(i).getPassword() + " to " + inputString + ".");
							userList.get(i).setPassword(inputString);
							break;
						case 5:
							System.out.print("Enter Deposit amount: ");
							amount = scanner.nextDouble();
							customerUser.getAccount().deposit(amount);
							System.out.println("\nUserID: " + userList.get(i).getUserId() + "\t" + amount
									+ " has been deposited. ");
							break;
						case 6:
							System.out.print("Enter Withdraw amount: ");
							amount = scanner.nextDouble();
							customerUser.getAccount().withdraw(amount);
							System.out.println("\nUserID: " + userList.get(i).getUserId() + "\t" + amount
									+ " has been withdrawn.");
							break;
						case 7:
							for (User user : userList) {
								if (user.getUserType().equals(Usertype.CUSTOMER)) {
									tempCust = (Customer) user;
									System.out.println(tempCust.getFirstName() + " " + tempCust.getLastName()
											+ ": (Account ID) " + tempCust.getAccount().getAccountid());
								}
							}
							System.out.print("\nEnter account to transfer to (Account Id):");
							inputString = scanner.next();
							tempCust = new Customer();
							for (int j = 0; j < userList.size(); j++) {
								if (userList.get(j).getUserType().equals(Usertype.CUSTOMER)) {
									tempCust = (Customer) userList.get(j);
									if (!tempCust.isApproved()) {
										System.out.println(
												"The account for " + tempCust.getFirstName() + " is pending approval.");
									} else if (tempCust.getAccount().getAccountid().equals(inputString)) {
										System.out.print("\nEnter amount you would like to transfer:");
										amount = scanner.nextDouble();
										customerUser.getAccount().transferFunds(tempCust.getAccount(), amount);
										break;
									} else if (j == userList.size() - 1) {
										System.out.println("Account not found.");
									}

								}
							} // end of for
							break;

						case 0:
							employeePrompt();
							break;

						default:
							System.out.println("Invalid Option.");
							employeePrompt();
							break;
						}
					} else {
						System.out.println(
								"\nWhat would you like to edit?\n 1) First Name\n 2) Last Name\n 3) Username\n 4) Password\n\n 0) Main Menu");
						option = scanner.nextInt();
						switch (option) {
						case 1:
							System.out.print("Enter new First Name: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed first name from "
											+ userList.get(i).getFirstName() + " to " + inputString + ".");
							userList.get(i).setFirstName(inputString);
							break;
						case 2:
							System.out.print("Enter new Last Name: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed last name from "
											+ userList.get(i).getLastName() + " to " + inputString + ".");
							userList.get(i).setLastName(inputString);
							break;
						case 3:
							System.out.print("Enter new Username: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed  username from "
											+ userList.get(i).getUserName() + " to " + inputString + ".");
							userList.get(i).setUserName(inputString);
							break;
						case 4:
							System.out.print("Enter new Password: ");
							inputString = scanner.next();
							System.out.println(
									"\nUserID: " + userList.get(i).getUserId() + " " + " Changed password from "
											+ userList.get(i).getPassword() + " to " + inputString + ".");
							userList.get(i).setPassword(inputString);
							break;
						case 0:
							employeePrompt();
							break;

						default:
							System.out.println("Invalid Option.");
							employeePrompt();
							break;
						}
					}
				} else if (i == userList.size()) {
					System.out.println("\nAccount not found.");
				}
			}
			employeePrompt();
			break;
		case 5:
			System.out.println("\n\nCustomers:".toUpperCase());
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					System.out.println(userList.get(i));
				}
			}
			tempCust = new Customer();
			System.out.print("\nWhich account would you like to change approval (Account Id)? ");
			inputString = scanner.next();
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					tempCust = (Customer) userList.get(i);
					if (tempCust.isApproved()) {
						if (tempCust.getAccount().getAccountid().equals(inputString)) {
							((Customer) userList.get(i)).setApproved(false);

							System.out.println("\nThe account for " + tempCust.getFirstName() + " has been denied.");

						} else if (i == userList.size() - 1) {
							System.out.println("\nAccount not found.");
						}
					} else if (!tempCust.isApproved()) {
						((Customer) userList.get(i)).setApproved(true);
						System.out.println("\nThe account for " + tempCust.getFirstName() + " has been approved.");
					}
				}
			}
			employeePrompt();
			break;
		// TODO
		case 6:
			System.out.println("\n\nCustomers:".toUpperCase());
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					System.out.println(userList.get(i));
				}
			}
			System.out.print("\nWhich account would you like to Close (Account Id)? ");
			inputString = scanner.next();
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					customerUser = (Customer) userList.get(i);
					if (customerUser.getAccount().getAccountid().toString().equals(inputString)) {

						if (customerUser.getAccount().getBalance() > 0) {
							System.out.println("\nFunds must be withdrawn before account may be closed.");
							break;
						} else {

							System.out.println("\nACCOUNT " + inputString + " CLOSED.\n" + userList.get(i));
							customerUser.setAccount(new Account());
							customerUser.setApproved(false);
							userList.set(i, customerUser);
							break;
						}
					}
				} else if (i == userList.size() - 1) {
					System.out.println("\nAccount not found.");
				}
			}
			employeePrompt();
			break;
		case 7:// TODO create user
			user = new User();
			user.setUserType(Usertype.EMPLOYEE.toString());
			System.out.print("\nPlease enter First Name: ");
			user.setFirstName(scanner.next());
			System.out.println("Please enter Last Name: ");
			user.setLastName(scanner.next());
			System.out.println("Please enter User Name: ");
			user.setUserName(scanner.next());
			System.out.println("Please enter Password: ");
			user.setPassword(scanner.next());
			user.toString();
			employeePrompt();
			break;
		case 8:
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

	public static void employeeEditPrompt() {
		System.out.println("\nHello " + user.getFirstName()
				+ ", what would you like to do?\n\n 1) View Customers \n 2) Account Approval"
				+ "\n\n 0) Log Out\n\n 10) Exit\n\n");
		option = scanner.nextInt();
		switch (option) {
		case 1:
			System.out.println("\n\nCustomers:".toUpperCase());
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
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
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					tempCust = (Customer) userList.get(i);
					if (tempCust.isApproved()) {
						if (tempCust.getAccount().getAccountid().toString().equals(inputString)) {
							((Customer) userList.get(i)).setApproved(false);

							System.out.println("\nThe account for " + tempCust.getFirstName() + " has been denied.");

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

	public static void customerEditPrompt() {
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
			for (User user : userList) {
				Customer tempCust = new Customer();
				if (user.getUserType().equals(Usertype.CUSTOMER)) {
					tempCust = (Customer) user;
					System.out.println(tempCust.getFirstName() + " " + tempCust.getLastName() + ": "
							+ tempCust.getAccount().getAccountid());
				}

			}
			System.out.print("\nEnter account you would like to transfer to (Account Id):");
			inputString = scanner.next();
			Customer tempCust = new Customer();
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					tempCust = (Customer) userList.get(i);
					if (!tempCust.isApproved()) {
						System.out.println("The account for " + tempCust.getFirstName() + " is pending approval.");
					} else if (tempCust.getAccount().getAccountid().toString().equals(inputString)) {
						System.out.print("\nEnter amount you would like to transfer: ");
						amount = scanner.nextDouble();
						customerUser.getAccount().transferFunds(tempCust.getAccount(), amount);
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
				System.out.println(
						customerUser.getFirstName() + " " + customerUser.getLastName() + " is NOT a joint account.");
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

}
