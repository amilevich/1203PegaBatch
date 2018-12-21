package com.revature.uilayer;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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

public class ConsoleApp {
	// XXX VARIABLES
	static Scanner scanner = new Scanner(System.in);
	public static int option;
	public static double amount;
	public static boolean session = true;
	public static String username;
	public static String password;
	public static ArrayList<User> userList = new ArrayList<User>();
	public static ArrayList<Customer> customerList = new ArrayList<>();
	public static Account account;
	public static User user;
	public static Customer customer;
	public static Customer tempCustomer;
	public static String inputString;
	public static Logger log = Logger.getLogger(Main.class.getName());
	public static UserDaoImpl userDaoImpl = new UserDaoImpl();
	public static CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
	public static AccountDaoImpl accountDaoImpl = new AccountDaoImpl();

	public static void main(String[] args) {
		// create admin
		// user = new User("Arthur", "Morgan", "admin", "admin", "ADMIN");

		try {
			// userDaoImpl.insertUser(user);
			userList = (ArrayList<User>) userDaoImpl.getUserList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		while (session) {
			initApp();
		}

		scanner.close();
	}

	// XXX initApp
	public static void initApp() {
		if (!session) {
			return;
		}
		welcomeMessage();
		if (scanner.hasNextInt()) {
			option = scanner.nextInt();
		} else {
			// If an int hasn't been entered, error is displayed and what they typed in is
			// removed from the standard input before attempting to read again
			System.out.println("Error: please enter a number");
			scanner.next();
		}
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
			if (!session) {
				return;
			}
			if (user.getUserType() == Usertype.CUSTOMER) {
				customerPrompt();
			} else if (user.getUserType().equals(Usertype.EMPLOYEE) || user.getUserType().equals(Usertype.ADMIN)) {
				employeePrompt();
			}
		case 10:
			session = false;
			exitMessage();
			return;
		default:
			System.out.println("\nInvalid Option.");
			initApp();
			break;
		}

	}

	// XXX 7 loginPrompt
	public static void loginInPrompt() {
		if (!session) {
			return;
		}
		try {
			userList = (ArrayList<User>) userDaoImpl.getUserList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!userList.isEmpty()) {

			System.out.print("\nEnter your Username: ");
			username = scanner.next();

			System.out.print("Enter your Password: ");
			password = scanner.next();

			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserName().equals(username) && userList.get(i).getPassword().equals(password)) {
					System.out.println("\n" + username + " found.");
					if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						user = userList.get(i);

						try {
							customer = customerDaoImpl.findCustomerByUserId(user.getUserId());
							customer.setAccount(accountDaoImpl.findAccountById(customer.getId()));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						System.out.println("\n" + customer.toString());
						return;
					} else {
						user = userList.get(i);
						System.out.println(user.toString());
						return;
					}
				} else if (i == userList.size() - 1) {
					System.out.println("\n" + username + " NOT found.");
					initApp();
				}
			}
		} else {
			System.out.println("\nThere are no users.");
			initApp();
		}
	}

	// XXX 6 signUpPrompt
	public static void signUpPrompt() {
		if (!session) {
			return;
		}
		System.out.println("\n\nSign up");
		System.out.println("-------");
		System.out.println("\nType of user:\n 1) Customer \n 2) Employee\n\n 0) Go Back ");
		System.out.print("\nSelection: ");
		option = scanner.nextInt();
		switch (option) {
		case 1:
			customer = new Customer();
			customer.setUserType(Usertype.CUSTOMER.toString());
			customer.setApproved(false);
			System.out.print("\nPlease enter First Name: ");
			customer.setFirstName(scanner.nextLine());
			System.out.print("Please enter Last Name: ");
			customer.setLastName(scanner.nextLine());
			System.out.print("Please enter User Name: ");
			username = scanner.next();
			customer.setUserName(username);
			System.out.print("Please enter Password: ");
			customer.setPassword(scanner.next());
			customer.toString();
			try {

				if (isAvailble(username)) {
					userDaoImpl.insertUser(customer);
					customer = customerDaoImpl.findCustomerByUserId(customer.getUserId());
					customer.setUserId(customer.getUserId());
					customerDaoImpl.insertCustomerUser(customer);
					customer.getAccount().setId(customer.getId());
					accountDaoImpl.insertAccount(customer.getAccount());
					userList = (ArrayList<User>) userDaoImpl.getUserList();
					user = customer;
				}

			} catch (SQLIntegrityConstraintViolationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			break;
		case 2:
			user = new User();
			user.setUserType(Usertype.EMPLOYEE.toString());
			System.out.print("\nPlease enter First Name: ");
			user.setFirstName(scanner.next());
			System.out.print("Please enter Last Name: ");
			user.setLastName(scanner.next());
			System.out.print("Please enter User Name: ");
			username = scanner.next();
			user.setUserName(username);
			System.out.print("Please enter Password: ");
			user.setPassword(scanner.next());
			user.toString();
			try {
				if (isAvailble(username)) {
					userDaoImpl.insertUser(user);
					userList = (ArrayList<User>) userDaoImpl.getUserList();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 0:
			initApp();
			return;
		default:
			System.out.println("Invalid Option.");
			signUpPrompt();
			break;
		}
	}

	// XXX 5 employeePrompt
	public static void employeePrompt() {
		if (!session) {
			return;
		}
		if (user.getUserType().equals(Usertype.ADMIN)) {
			adminEditPrompt();
		} else {
			employeeEditPrompt();
		}
	}

	// XXX 4 customerPrompt
	private static void customerPrompt() {
		if (!session) {
			return;
		}
		if (customer.isApproved()) {
			customerEditPrompt();
		} else {
			System.out.println("Account pending approval.");
			initApp();
		}

	}

	// XXX 3 adminEditPrompt
	public static void adminEditPrompt() {
		if (!session) {
			return;
		}
		try {
			userList = (ArrayList<User>) userDaoImpl.getUserList();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("\nHello " + user.getFirstName()
				+ ", what would you like to do?\n\n 1) View All Users\n 2) View Employees\n 3) View Customers\n 4) Edit User\n 5) Account Approval\n \n 6) Close Account\n 7) Create User\n 8) Delete User\n "
				+ "\n\n 0) Log Out\n\n 10) Exit");
		System.out.print("\nSelection: ");
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
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customerList.get(i).getAccount().setId(customerList.get(i).getId());
						System.out.println(customerList.get(i));
					}
				}
				employeePrompt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 4:
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				userList = (ArrayList<User>) userDaoImpl.getUserList();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// XXX FIX
			for (User user : userList) {
				System.out.println(user);
			}

			System.out.print("\n\nWhich account would you like to edit (User Id)? ");
			inputString = scanner.next();
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
					System.out.println("HERE");
					if (userList.get(i).getUserId().equals(inputString)) {
						System.out.println("\nEditing: " + userList.get(i).getFirstName() + " "
								+ userList.get(i).getLastName() + " \n" + userList.get(i).toString());
						customer = (Customer) userList.get(i);
						System.out.println(
								"\nWhat would you like to edit?\n 1) First Name\n 2) Last Name\n 3) Username\n 4) Password\n 5) Deposit\n 6) Withdraw\n 7) Transfer\n\n 0) Main Menu");
						System.out.print("\nSelection: ");
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
							customer.getAccount().deposit(amount);
							System.out.println("\nUserID: " + userList.get(i).getUserId() + "\t" + amount
									+ " has been deposited. ");
							break;
						case 6:
							System.out.print("Enter Withdraw amount: ");
							amount = scanner.nextDouble();
							customer.getAccount().withdraw(amount);
							System.out.println("\nUserID: " + userList.get(i).getUserId() + "\t" + amount
									+ " has been withdrawn.");
							break;
						case 7:
							try {
								customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
								System.out.println("\n\nCustomers:".toUpperCase());
								for (int j = 0; j < customerList.size(); j++) {
									if (customerList.get(j).getUserType().equals(Usertype.CUSTOMER)) {
										customerList.get(j).getAccount().setId(customerList.get(j).getId());
										System.out.println(customerList.get(j));
									}
								}

								System.out.print("\nEnter account you would like to transfer to (Account Id):");
								inputString = scanner.next();
								for (int k = 0; k < customerList.size(); k++) {
									if (customerList.get(k).getUserType().equals(Usertype.CUSTOMER)) {
										tempCustomer = (Customer) customerList.get(k);
										if (tempCustomer.getAccount().getAccountid().equals(inputString)) {
											if (!tempCustomer.isApproved()) {
												System.out.println("The account for " + tempCustomer.getFirstName()
														+ " " + tempCustomer.getLastName() + " is pending approval.");
											} else {
												System.out.print("\nEnter amount you would like to transfer: $");
												amount = scanner.nextDouble();
												customer.getAccount().transferFunds(tempCustomer.getAccount(), amount);
												accountDaoImpl.updateAccount(customer.getAccount());
												customerDaoImpl.updateUser(customer);
												userDaoImpl.updateUser(customer);
												accountDaoImpl.updateAccount(tempCustomer.getAccount());
												customerDaoImpl.updateUser(tempCustomer);
												userDaoImpl.updateUser(tempCustomer);
												customerPrompt();
											}
										} else if (i == customerList.size() - 1) {
											System.out.println("Account not found.");
										}

									}
								} // end of for
							} catch (Exception e) {
								e.printStackTrace();
							}
							customerPrompt();
							break;

						case 0:
							employeePrompt();
							break;

						default:
							System.out.println("Invalid Option.");
							employeePrompt();
							break;
						}
					} else if (i == userList.size()) {
						System.out.println("\nAccount not found.");
					}
				} else {
					System.out.println("\nEditing: " + userList.get(i).getFirstName() + " "
							+ userList.get(i).getLastName() + " \n" + userList.get(i).toString());
					System.out.println(
							"\nWhat would you like to edit?\n 1) First Name\n 2) Last Name\n 3) Username\n 4) Password\n\n 0) Main Menu");
					System.out.print("\nSelection: ");
					option = scanner.nextInt();
					switch (option) {
					case 1:
						System.out.print("Enter new First Name: ");
						inputString = scanner.next();
						System.out
								.println("\nUserID: " + userList.get(i).getUserId() + " " + " Changed first name from "
										+ userList.get(i).getFirstName() + " to " + inputString + ".");
						userList.get(i).setFirstName(inputString);
						break;
					case 2:
						System.out.print("Enter new Last Name: ");
						inputString = scanner.next();
						System.out.println("\nUserID: " + userList.get(i).getUserId() + " " + " Changed last name from "
								+ userList.get(i).getLastName() + " to " + inputString + ".");
						userList.get(i).setLastName(inputString);
						break;
					case 3:
						System.out.print("Enter new Username: ");
						inputString = scanner.next();
						System.out.println("\nUserID: " + userList.get(i).getUserId() + " " + " Changed  username from "
								+ userList.get(i).getUserName() + " to " + inputString + ".");
						userList.get(i).setUserName(inputString);
						break;
					case 4:
						System.out.print("Enter new Password: ");
						inputString = scanner.next();
						System.out.println("\nUserID: " + userList.get(i).getUserId() + " " + " Changed password from "
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
			}
			employeePrompt();
			break;
		case 5:
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customerList.get(i).getAccount().setId(customerList.get(i).getId());
						System.out.println(customerList.get(i));
					}
				}
				System.out.print("\nWhich account would you like to change approval (Account Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					customerList.get(i).getAccount().setId(customerList.get(i).getId());
					customer = customerList.get(i);
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						if (customerList.get(i).getAccount().getAccountid().equals(inputString)) {

							System.out.println("HERE " + customer);
							if (customer.isApproved()) {

								customer = customerList.get(i);
								customer.getAccount().setId(customer.getId());
								customer.setApproved(false);
								userDaoImpl.updateUser(customer);
								customerDaoImpl.updateUser(customer);
								accountDaoImpl.updateAccount(customer.getAccount());
								System.out
										.println("\nThe account for " + customer.getFirstName() + " has been denied.");

							} else if (!customer.isApproved()) {
								customer = customerList.get(i);
								customer.setApproved(true);
								userDaoImpl.updateUser(customer);
								customerDaoImpl.updateUser(customer);
								accountDaoImpl.updateAccount(customer.getAccount());
								System.out.println(
										"\nThe account for " + customer.getFirstName() + " has been approved.");
							}
						}
					} else if (i == customerList.size()) {
						System.out.println("\nAccount not found.");
					}
				}
				employeePrompt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 6:
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customerList.get(i).getAccount().setId(customerList.get(i).getId());
						System.out.println(customerList.get(i));
					}
				}

				System.out.print("\nWhich account would you like to Close (Account Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					customerList.get(i).getAccount().setId(customerList.get(i).getId());
					customer = customerList.get(i);
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						if (customerList.get(i).getAccount().getAccountid().equals(inputString)) {
							if (customer.getAccount().getBalance() > 0) {
								System.out.println("\nFunds must be withdrawn before account may be closed.");
								break;
							} else {

								accountDaoImpl.deleteAccount(customer.getAccount());
								customer.setAccount(new Account());
								customer.getAccount().setId(customer.getId());
								userDaoImpl.updateUser(customer);
								customerDaoImpl.updateUser(customer);
								accountDaoImpl.updateAccount(customer.getAccount());
								System.out
										.println("\nThe account for " + customer.getFirstName() + " has been closed.");
							}
						} else if (i == customerList.size()) {
							System.out.println("\nAccount not found.");
						}
					}
				}
				adminEditPrompt();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			try {
				userList = (ArrayList<User>) userDaoImpl.getUserList();
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				System.out.println("\n\nALL USERS:".toUpperCase());
				for (int i = 0; i < userList.size(); i++) {
					System.out.println(userList.get(i));
				}

				System.out.print("\nWhich user would you like to Close (User Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					customerList.get(i).getAccount().setId(customerList.get(i).getId());
					customer = customerList.get(i);
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						if (customerList.get(i).getUserId().equals(inputString)) {
							if (customer.getAccount().getBalance() > 0) {
								System.out.println("\nFunds must be withdrawn before user may be deleted.");
								break;
							} else {
								accountDaoImpl.deleteAccount(customer.getAccount());
								customerDaoImpl.deleteCustomerUser(customer);
								userDaoImpl.deleteUser(customer);
								System.out.println("\nThe user " + customer.getFirstName() + " "
										+ customer.getLastName() + " has been deleted.");
							}
						}
					} else if (customerList.get(i).getUserType().equals(Usertype.USER)
							|| customerList.get(i).getUserType().equals(Usertype.EMPLOYEE)
							|| customerList.get(i).getUserType().equals(Usertype.ADMIN)) {

						if (customerList.get(i).getUserId().equals(inputString)) {
							userDaoImpl.deleteUser(customer);
							System.out.println("\nThe user " + customer.getFirstName() + " " + customer.getLastName()
									+ " has been deleted.");
						}
					} else if (i == customerList.size()) {
						System.out.println("\nUser not found.");
					}
				}
				adminEditPrompt();
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	// XXX 2 employeeEditPrompt
	public static void employeeEditPrompt() {
		if (!session) {
			return;
		}
		System.out.println("\nHello " + user.getFirstName()
				+ ", what would you like to do?\n\n 1) View Customers \n 2) Account Approval"
				+ "\n\n 0) Log Out\n\n 10) Exit");
		System.out.print("\nSelection: ");
		option = scanner.nextInt();
		switch (option) {
		case 1:
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();

				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customer = customerList.get(i);
						customer.getAccount().setId(customer.getId());
						System.out.println(customerList.get(i));
					}
				}
				employeePrompt();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customerList.get(i).getAccount().setId(customerList.get(i).getId());
						System.out.println(customerList.get(i));
					}
				}
				System.out.print("\nWhich account would you like to change access for (Account Id)? ");
				inputString = scanner.next();
				for (int i = 0; i < userList.size(); i++) {
					customerList.get(i).getAccount().setId(customerList.get(i).getId());
					customer = customerList.get(i);
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						if (customerList.get(i).getAccount().getAccountid().equals(inputString)) {
							if (customer.isApproved()) {

								customer = customerList.get(i);
								customer.getAccount().setId(customer.getId());
								customer.setApproved(false);
								userDaoImpl.updateUser(customer);
								customerDaoImpl.updateUser(customer);
								accountDaoImpl.updateAccount(customer.getAccount());
								System.out.println("\nThe access for account " + customer.getFirstName() + " "
										+ customer.getLastName() + " has been denied.");

							} else if (!customer.isApproved()) {
								customer = customerList.get(i);
								customer.setApproved(true);
								userDaoImpl.updateUser(customer);
								customerDaoImpl.updateUser(customer);
								accountDaoImpl.updateAccount(customer.getAccount());
								System.out.println("\nThe access for account " + customer.getFirstName() + " "
										+ customer.getLastName() + " has been approved.");
							}
						}
					} else if (i == customerList.size()) {
						System.out.println("\nAccount not found.");
					}

				}

				employeePrompt();
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	// XXX 1 customerEditPrompt
	public static void customerEditPrompt() {
		if (!session) {
			return;
		}
		System.out.print("\nHello " + customer.getFirstName()
				+ ", what would you like to do?\n\n 1) Display Balance \n 2) Deposit"
				+ "\n 3) Withdraw\n 4) Transfer Funds\n 5) Joint Account\n\n 0) Log Out\n\n 10) Exit");
		System.out.print("\n\nSelection: ");
		option = scanner.nextInt();
		customer.getAccount().setId(customer.getId());
		switch (option) {
		case 1:
			System.out.println("\n" + customer.getUserName() + " Balance: $" + customer.getAccount().getBalance());
			customerPrompt();
			break;
		case 2:
			try {
				System.out.print("\nEnter deposit amount: $");
				customer.getAccount().deposit(scanner.nextDouble());
				accountDaoImpl.updateAccount(customer.getAccount());
				customerDaoImpl.updateUser(customer);
				userDaoImpl.updateUser(customer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			customerPrompt();
			break;
		case 3:
			try {
				System.out.print("Enter withdraw amount: $");
				customer.getAccount().withdraw(scanner.nextDouble());
				accountDaoImpl.updateAccount(customer.getAccount());
				customerDaoImpl.updateUser(customer);
				userDaoImpl.updateUser(customer);
				customerPrompt();
			} catch (Exception e) {
			}
			break;
		case 4:
			try {
				customerList = (ArrayList<Customer>) accountDaoImpl.getAccountCustomerList();
				System.out.println("\n\nCustomers:".toUpperCase());
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						customerList.get(i).getAccount().setId(customerList.get(i).getId());
						System.out.println(customerList.get(i));
					}
				}

				System.out.print("\nEnter account you would like to transfer to (Account Id):");
				inputString = scanner.next();
				for (int i = 0; i < customerList.size(); i++) {
					if (customerList.get(i).getUserType().equals(Usertype.CUSTOMER)) {
						tempCustomer = (Customer) customerList.get(i);
						if (tempCustomer.getAccount().getAccountid().equals(inputString)) {
							if (!tempCustomer.isApproved()) {
								System.out.println("The account for " + tempCustomer.getFirstName() + " "
										+ tempCustomer.getLastName() + " is pending approval.");
							} else {
								System.out.print("\nEnter amount you would like to transfer: $");
								amount = scanner.nextDouble();
								customer.getAccount().transferFunds(tempCustomer.getAccount(), amount);
								accountDaoImpl.updateAccount(customer.getAccount());
								customerDaoImpl.updateUser(customer);
								userDaoImpl.updateUser(customer);
								accountDaoImpl.updateAccount(tempCustomer.getAccount());
								customerDaoImpl.updateUser(tempCustomer);
								userDaoImpl.updateUser(tempCustomer);
								customerPrompt();
							}
						} else if (i == customerList.size() - 1) {
							System.out.println("Account not found.");
						}

					}
				} // end of for
			} catch (Exception e) {
				e.printStackTrace();
			}
			customerPrompt();
			break;
		case 5:
			try {
				if (customer.getAccount().isJointAccount()) {
					System.out.println(customer.getFirstName() + " " + customer.getLastName() + " is a joint account.");
					System.out.println("Would you like to keep your account a joint account?\n 1) Yes\n 2) No");
					System.out.print("\nSelection: ");
					option = scanner.nextInt();
					switch (option) {
					case 1:
						customer.getAccount().setJointAccount(true);
						System.out.println("Your account has not changed.");
						break;
					case 2:
						customer.getAccount().setJointAccount(false);
						accountDaoImpl.updateAccount(customer.getAccount());

						System.out.println("Your account is no longer a joint account.");
					default:
						System.out.println("Invalid Input.");
						break;
					}
				} else {
					System.out.println(
							customer.getFirstName() + " " + customer.getLastName() + " is NOT a joint account.");
					System.out.println("Would you like to make your account a joint account?\n 1) Yes\n 2) No");
					System.out.print("\nSelection: ");
					option = scanner.nextInt();
					switch (option) {
					case 1:
						customer.getAccount().setJointAccount(true);
						accountDaoImpl.updateAccount(customer.getAccount());
						System.out.println("Your account is now a joint account.");
						break;
					case 2:
						customer.getAccount().setJointAccount(false);
						System.out.println("Your account has not changed.");
						break;
					default:
						System.out.println("Invalid Input.");
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
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
		System.out.println("\n\n\n\tHello");
		System.out.println("\nWelcome to Revature Bank");
		System.out.println("\n 1) Login");
		System.out.println(" 2) Sign Up");
		System.out.println("\n 10) Exit\n");
		System.out.print("\nSelection: ");
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

	public static boolean isAvailble(String username) {
		boolean result = false;
		try {
			userList = (ArrayList<User>) userDaoImpl.getUserList();
			for (int i = 0; i < userList.size(); i++) {
				if (username.equals(userList.get(i).getUserName())) {
					System.out.println("\n" + username + " username not available.");
					signUpPrompt();
					return false;
				} else {
					result = true;
				}
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
