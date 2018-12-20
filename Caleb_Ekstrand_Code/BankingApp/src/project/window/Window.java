package project.window;

import java.util.Scanner;

import project.account.Account;
import project.users.Admin;
import project.users.Customer;
import project.users.Employee;
import project.users.User;

public class Window {

	public static void main(String[] args) {
		// Create a new scanner object to receive inputs
		Scanner sc = new Scanner(System.in);
		Account.loadAccounts(); // loading accounts from database into local storage for easy use!
		User.loadUsers(); // loading usernames from database into local storage for easy checking!

		// NOTE -- loadAccounts loads the full account objects while loadUsers loads
		// only each users username
		// This is due to design choices done in part 1 of the project

		boolean running = true; // whether program is running/another loop should be started
		while (running) {
			System.out.println("Welcome to FakeBank!");
			System.out.println("Enter (1) for Login, (2) for Signup, or (3) for Exit");
			if (sc.hasNextInt()) {
				int input = sc.nextInt(); // will hold the input of the scanner
				// System.out.println(input); //testing
				if (input == 1) {
					System.out.println("Input your Username");
					String username = sc.next();
					if (User.usernameExists(username)) {
						User current = User.getUser(username);
						// System.out.println(current.toString());
						System.out.println("Input your password");
						String password = sc.next();
						if (current.getPassword().equals(password)) {
							System.out.println("Welcome " + current.getName());
							// Implement Logged in code here // figure out what type of account you're
							// looking at would b step 1...
							if (current instanceof Customer) {
								System.out.println("Welcome Customer");
								while (running) {
									for (Account i : ((Customer) current).getAccounts()) {
										if (Account.accountExists(i.getAccountNumber()) != true) {
											((Customer) current).removeAccount(i.getAccountNumber());
										}
									}
									System.out.println(
											"Options: (1) Withdrawl, (2) Deposit, (3) Transfer, (4) Balance, (5) Request Account or (6) Exit");
									if (sc.hasNextInt()) {
										int option = sc.nextInt();
										switch (option) {
										case 1:
											System.out.println("Withdrawl");
											System.out.println("Enter the number of the account");
											if (sc.hasNextInt()) {
												int number = sc.nextInt();
												if (((Customer) current).hasAccount(number)) {
													if (((Customer) current).getAccount(number).isActivated()) {
														System.out.println("Enter Amount");
														double withdraw = sc.nextDouble();
														if ((withdraw > 0) && (withdraw <= ((Customer) current)
																.getBalance(number))) {
															((Customer) current).withdraw(withdraw, number);
														} else {
															System.out.println("Invalid withdrawl amount");
														}
													} else {
														System.out.println("Account has not been activated");
													}

												} else {
													System.out.println("Account not found");
												}
											} else {
												System.out.println("Invalid Input");
												sc.next();
											}
											break;
										case 2:
											System.out.println("Deposit");
											System.out.println("Enter the number of the account");
											if (sc.hasNextInt()) {
												int number1 = sc.nextInt();
												if (((Customer) current).hasAccount(number1)) {
													if (((Customer) current).getAccount(number1).isActivated()) {
														System.out.println("Enter Amount");
														double deposit = sc.nextDouble();
														if (deposit > 0) {
															((Customer) current).deposit(deposit, number1);
														} else {
															System.out.println("Invalid deposit amount");
														}
													} else {
														System.out.println("Account has not been activated");
													}

												} else {
													System.out.println("Account not found");
												}
											} else {
												System.out.println("Invalid Input");
												sc.next();
											}
											break;
										case 3:
											System.out.println("Transfer");
											System.out.println("Enter the number of the withdrawing account");
											if (sc.hasNextInt()) {
												int account1 = sc.nextInt();
												if ((((Customer) current).hasAccount(account1)
														&& (((Customer) current).getAccount(account1).isActivated()))) {
													System.out.println("Enter the number of the depositing account");
													int account2 = sc.nextInt();
													if ((((Customer) current).hasAccount(account2))
															&& (((Customer) current).getAccount(account2)
																	.isActivated())) {
														System.out.println("Enter Amount to Transfer");
														double amount = sc.nextDouble();
														if ((amount > 0) && (amount <= ((Customer) current)
																.getBalance(account1))) {
															((Customer) current).withdraw(amount, account1);
															((Customer) current).deposit(amount, account2);
														} else {
															System.out.println("Invalid Amount");
														}
													} else {
														System.out.println("Account doesn't exist or isn't activated");
													}
												} else {
													System.out.println("Account doesn't exist or isn't activated");
												}
											} else {
												System.out.println("Invalid Input");
												sc.next();
											}
											break;
										case 4:
											System.out.println("Balance");
											System.out.println("Your accounts");
											for (Account i : ((Customer) current).getAccounts()) {
												System.out.println(i.toString());
											}
											break;
										case 5:
											System.out.println("Request Account");
											System.out.println("(1) Standard or (2) Joint");
											if (sc.hasNextInt()) {
												int type = sc.nextInt();
												if (type == 1) {
													System.out.println("Standard Account Requested");
													Account a = new Account("standard");
													((Customer) current).addAccount(a);
												} else if (type == 2) {
													System.out.println("Joint");
													System.out.println(
															"Enter (0) for new joint account or account number to add an existing account");
													int num = sc.nextInt();
													if (num == 0) {
														System.out.println("New Joint Created");
														Account a = new Account("joint");
														((Customer) current).addAccount(a);
													} else if ((Account.accountExists(num))
															&& (Account.getAccount(num).getType().equals("joint"))
															&& ((Customer) current).hasAccount(num) == false) {
														System.out.println("joint exists");
														((Customer) current).addAccount(Account.getAccount(num));
													} else {
														System.out.println("Invalid Input");
													}
												} else {
													System.out.println("Invalid Input");
												}
											} else {
												System.out.println("Invalid Input");
												sc.next();
											}

											// System.out.println(a.toString());
											// System.out.println("All accounts");
											// for (Account i: Account.getAccounts()) {
											// System.out.println(i.toString());
											// }
											break;
										case 6:
											System.out.println("Thank you, Have a Nice Day");
											running = false;
											break;
										default:
											System.out.println("Invalid input");
											break;
										}
									} else {
										System.out.println("Invalid input");
										sc.next();
									}
								}

								running = true;

							} else if (current instanceof Admin) {
								System.out.println("Welcome Admin");
								while (running) {
									System.out.println(
											"Options: (1) Withdrawl, (2) Deposit, (3) Transfer, (4) View Accounts, (5) View Requests, (6) Cancel Account  or (7) Exit");
									if (sc.hasNextInt()) {
										int option = sc.nextInt();
										switch (option) {
										case 1:
											System.out.println("Withdrawl");
											System.out.println("Enter the number of the account");
											int number = sc.nextInt();
											if (Account.accountExists(number)) {
												if (Account.getAccount(number).isActivated()) {
													System.out.println("Enter Amount");
													double withdraw = sc.nextDouble();
													if ((withdraw > 0)
															&& (withdraw <= Account.getAccount(number).getBalance())) {
														Account.getAccount(number).withdraw(withdraw);
													} else {
														System.out.println("Invalid withdrawl amount");
													}
												} else {
													System.out.println("Account has not been activated");
												}

											} else {
												System.out.println("Account not found");
											}
											break;
										case 2:
											System.out.println("Deposit");
											System.out.println("Enter the number of the account");
											int number1 = sc.nextInt();
											if (Account.accountExists(number1)) {
												if (Account.getAccount(number1).isActivated()) {
													System.out.println("Enter Amount");
													double deposit = sc.nextDouble();
													if (deposit > 0) {
														Account.getAccount(number1).deposit(deposit);
													} else {
														System.out.println("Invalid deposit amount");
													}
												} else {
													System.out.println("Account has not been activated");
												}

											} else {
												System.out.println("Account not found");
											}
											break;
										case 3:
											System.out.println("Transfer");
											System.out.println("Enter the number of the withdrawing account");
											int account1 = sc.nextInt();
											if ((Account.accountExists(account1))
													&& (Account.getAccount(account1).isActivated())) {
												System.out.println("Enter the number of the depositing account");
												int account2 = sc.nextInt();
												if ((Account.accountExists(account2))
														&& (Account.getAccount(account2).isActivated())) {
													System.out.println("Enter Amount to Transfer");
													double amount = sc.nextDouble();
													if ((amount > 0) && (amount <= (Account.getAccount(account1)
															.getBalance()))) {
														Account.getAccount(account1).withdraw(amount);
														Account.getAccount(account2).deposit(amount);
													} else {
														System.out.println("Invalid Amount");
													}
												} else {
													System.out.println("Account doesn't exist or isn't activated");
												}
											} else {
												System.out.println("Account doesn't exist or isn't activated");
											}
											break;
										case 4:
											System.out.println("View Accounts");
											System.out.println("All accounts");
											for (Account i : Account.getAccounts()) {
												System.out.println(i.toString());
											}
											break;
										case 5:
											System.out.println("View Requests");
											for (Account i : Account.getAccounts()) {
												if (i.isActivated() != true) {
													System.out.println(i.toString());
												}
											}
											System.out.println("Which account number do you want to activate");
											int activate = sc.nextInt();
											for (Account i : Account.getAccounts()) {
												if (i.getAccountNumber() == activate) {
													i.setActivated(true);
												}
											}
											break;
										case 6:
											System.out.println("Cancel Account");
											System.out.println("Enter number of account you would like to cancel");
											int account = sc.nextInt();
											if (Account.accountExists(account)) {
												if (Account.getAccount(account).getBalance() == 0) {
													Account.cancelAccount(account);
												} else {
													System.out.println("Account is not empty!");
												}
											} else {
												System.out.println("Account does not exist");
											}
											break;
										case 7:
											System.out.println("Thank you, Have a Nice Day");
											running = false;
											break;
										default:
											System.out.println("Invalid input");
											break;
										}
									} else {
										System.out.println("Invalid input");
										sc.next();
									}
								}
								running = true;
							} else {
								System.out.println("Welcome Employee");
								while (running) {
									System.out.println("Options: (1) View Accounts, (2) View Requests or (3) Exit");
									if (sc.hasNextInt()) {
										int option = sc.nextInt();
										switch (option) {
										case 1:
											System.out.println("View Accounts");
											System.out.println("All accounts");
											for (Account i : Account.getAccounts()) {
												System.out.println(i.toString());
											}
											break;
										case 2:
											System.out.println("View Requests");
											for (Account i : Account.getAccounts()) {
												if (i.isActivated() != true) {
													System.out.println(i.toString());
												}
											}
											System.out.println("Which account number do you want to activate");
											int activate = sc.nextInt();
											for (Account i : Account.getAccounts()) {
												if (i.getAccountNumber() == activate) {
													i.setActivated(true);
												}
											}
											break;
										case 3:
											System.out.println("Thank You, Have a Nice Day");
											running = false;
											break;
										default:
											System.out.println("Invalid input");
											break;
										}
									} else {
										System.out.println("Invalid input");
										sc.next();
									}
								}
								running = true;
							}
						} else {
							System.out.println("Incorrect password");
						}
					} else {
						System.out.println("Username does not exist");
					}
				} else if (input == 2) {
					System.out.println("Enter (1) for Customer or (2) for Employee");
					if (sc.hasNextInt()) {
						input = sc.nextInt();
						if (input == 1) {
							System.out.println("Input your desired username");
							String username = sc.next();
							if (User.usernameExists(username)) {
								System.out.println("That username is taken please choose another");
							} else {
								// get all the info
								System.out.println("Your username is: " + username);
								System.out.println("Input your desired password");
								String password = sc.next();
								System.out.println("Input your first name");
								String name = sc.next();
								System.out.println("Input your last name");
								name = name + " " + sc.next();
								System.out.println("Input your phone number (xxx-xxx-xxxx)");
								String phoneNumber = sc.next();
								System.out.println("Input your Address");
								String address = sc.next();
								System.out.println("Account created");
								// ADD CODE FOR KEEPING TRACK OF OBJECTS //
								Customer c = new Customer(username, password, name, phoneNumber, address); // create the
																											// new
																											// user
								User.addUser(c); // add it to the list of users

							}
							// System.out.println(username); //testing
						} else if (input == 2) {
							// System.out.println("Welcome Employee");
							System.out.println("Input Employee or Admin code");
							if (sc.hasNextInt()) {
								int code = sc.nextInt();
								if (Employee.checkEmployeeCode(code)) {
									System.out.println("Welcome Employee");
									System.out.println("Input your desired username");
									String username = sc.next();
									if (User.usernameExists(username)) {
										System.out.println("That username is taken please choose another");
									} else {
										// get all the info
										System.out.println("Your username is: " + username);
										System.out.println("Input your desired password");
										String password = sc.next();
										System.out.println("Input your name");
										String name = sc.next();
										System.out.println("Account created");
										// ADD CODE FOR KEEPING TRACK OF OBJECTS //
										Employee e = new Employee(username, password, name); // create the new user
										User.addUser(e); // add it to the list of users

									}
								} else if (Admin.checkAdminCode(code)) {
									System.out.println("Welcome Admin");
									System.out.println("Input your desired username");
									String username = sc.next();
									if (User.usernameExists(username)) {
										System.out.println("That username is taken please choose another");
									} else {
										// get all the info
										System.out.println("Your username is: " + username);
										System.out.println("Input your desired password");
										String password = sc.next();
										System.out.println("Input your name");
										String name = sc.next();
										System.out.println("Account created");
										// ADD CODE FOR KEEPING TRACK OF OBJECTS //
										Admin a = new Admin(username, password, name); // create the new user
										User.addUser(a); // add it to the list of users

									}
								} else {
									System.out.println("Incorrect code, Please create a Customer account");
								}
							} else {
								System.out.println("Invalid Input");
								sc.next();
							}
						} else {
							System.out.println("Not a valid input");
						}

					} else {
						System.out.println("Invalid Input");
						sc.next();
					}
				} else if (input == 3) {
					System.out.println("Thanks for using FakeBank");
					running = false;
				} else {
					System.out.println("Not a valid input");
				}
			} else {
				System.out.println("Not a valid input");
				sc.next();
			}

		}
		sc.close();
	}

}
