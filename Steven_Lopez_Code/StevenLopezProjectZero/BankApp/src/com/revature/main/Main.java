package com.revature.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.account.Account;
import com.revature.account.AccountState;
import com.revature.account.CheckingAccount;
import com.revature.account.JointAccount;
import com.revature.account.SavingAccount;
import com.revature.transactions.Transaction;
import com.revature.transactions.TransactionType;
import com.revature.user.Admin;
import com.revature.user.Customer;
import com.revature.user.Employee;
//Main class where the application runs.
public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		Main main = new Main();
		main.startUp();
		System.out.println("Thank you for your time and goodbye!");
	}
	//Main branch where app separates the admin and the customers.
	public void startUp() {
		String option = "";
		String option2 = "";
		Scanner sc = new Scanner(System.in);
		boolean success = false;
		Customer customer;

		do {
			System.out.print("Select User\n	1. Administration"
					+ "\n	2. Customer \n	Or enter any key to exit.\r\rSelect option: ");
			if ((option2 = sc.nextLine()).equals("1")) {
				do {
					System.out.print("\n	1. Sign In as Employee" + "\r	2. Sign In as Admin"
							+ "\n	0. To go back\r\rSelect option: ");

					if ((option = sc.nextLine()).equals("1")) {
						success = true;
						empSignInMenu(sc);

					} else if ((option.equals("2"))) {

						success = true;
						adminSignInMenu(sc);

					} else if ((option.equals("0"))) {
						System.out.println("\r\r\r");
						success = true;
					} else {
						System.out.println("\r\r\rPlease select one of the following available choices.");
						success = false;
					}

				} while (!success);

			} else if (option2.equals("2")) {
				do {
					System.out.print("If you already have an account Sign In(2)\n	1. Register"
							+ "\r	2. Sign In	\n	0. To go back\r\rSelect option: ");

					if ((option = sc.nextLine()).equals("1")) {

						customer = new Customer(sc);
						success = true;
						customerMenu(customer, sc);

					} else if ((option.equals("2"))) {

						success = true;
						custSignInMenu(sc);

					} else if ((option.equals("0"))) {
						System.out.println("\r\r\r");
						success = true;
					} else {
						System.out.println("\r\r\rPlease select one of the following available choices.");
						success = false;
					}

				} while (!success);
			} else
				System.out.println();
		} while (option2.equals("1") || option2.equals("2"));
	}
	//The customer's sign in method, after customers inputs the Customer's Class constructor is called
	//It load an instance of the user by reading from customers.txt, then continue to the Customer's Menu
	public void custSignInMenu(Scanner scan) {
		String usn = "";
		char[] psw = new char[30];
		boolean found = false;
		Customer cst;
		System.out.print("\nFill in the following to sign in");
		do {
			System.out.print("\nUsername: ");
			usn = scan.nextLine();
			System.out.print("Password: ");
			psw = scan.nextLine().toCharArray();
			cst = new Customer(usn, psw);
			if (cst.getName() != null)
				found = true;
		} while (!found);
		customerMenu(cst, scan);
	}

	//Same as the customer's sign in method
	public void adminSignInMenu(Scanner scan) {
		String usn = "";
		char[] psw = new char[30];
		boolean found = false;
		Admin adm;
		System.out.print("\nFill in the following to sign in");
		do {
			System.out.print("\nUsername: ");
			usn = scan.nextLine();
			System.out.print("Password: ");
			psw = scan.nextLine().toCharArray();
			adm = new Admin(usn, psw);
			if (adm.getName() != null)
				found = true;
		} while (!found);
		adminMenu(adm, scan);
	}

	//Same as the customer and admin sign in method
	public void empSignInMenu(Scanner scan) {
		String usn = "";
		char[] psw = new char[30];
		boolean found = false;
		Employee emp;
		System.out.print("\nFill in the following to sign in");
		do {
			System.out.print("\nUsername: ");
			usn = scan.nextLine();
			System.out.print("Password: ");
			psw = scan.nextLine().toCharArray();
			emp = new Employee(usn, psw);
			if (emp.getName() != null)
				found = true;
		} while (!found);
		employeeMenu(emp, scan);
	}
	//The main menu for customers where they can access their accounts, make deposit, transfers, withdrawals and request for another account
	public void customerMenu(Customer cst, Scanner scan) {
		String cFilename = "./customers.txt";
		int choice = 0;
		while (choice != -1) {
			if (cst.getAccounts().isEmpty()) {
				System.out.print("\n\nWelcome Back to the BankApp!\n\n\nPlease select (1) to request for an account."
						+ "\n	1. Open Bank Account\n	-1. Exit\n\nselection: ");
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case 1:
					System.out.print("\n\nSelect what account you would like to open."
							+ "\n	1. Checking\n	2. Saving\n	3. Joint Account\n	-1. Exit\n\nSelection: ");
					choice = scan.nextInt(); // Remember to validate input
					switch (choice) {
					case 1:
						CheckingAccount cAcc = new CheckingAccount(cst, scan);
						cst.saveCustomer(cFilename);
						continue;
					case 2:
						SavingAccount sAcc = new SavingAccount(cst, scan);
						cst.saveCustomer(cFilename);
						continue;
					case 3:
						scan.nextLine();
						System.out.println("To request for a joint account your assosiates must enter hes logging credentials.\nName: ");
						String name = scan.nextLine();
						System.out.print("Password: ");
						String pass = scan.nextLine();
						Customer cust2 = new Customer(name, pass.toCharArray());
						JointAccount jAcc = new JointAccount(cst, scan, cust2);
						cst.saveCustomer(cFilename);
						cust2.saveCustomer(cFilename);
						continue;
					}
					continue;
				case -1:
					continue;
				default:
					System.out.println("Please select one of the following accounts.");
					continue;
				}
			} else {
				System.out.print("\n\nWelcome Back to the Bank App!\n\nPlease select one of the following available "
						+ "activities.\n 1. Open Bank Account\n 2. View Balances\n 3. Withdraw\n 4. Deposit \n"
						+ " 5. Transfer\n 6. View Transactions\n 7. View Person Information\n -1. Exit\nSelection: ");
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				case 1:
					System.out.print("\n\nSelect what account you would like to open."
							+ "\n	1. Checking\n	2. Saving\n	3. Joint Account\n	-1. Exit\n\nSelection: ");
					choice = Integer.parseInt(scan.nextLine());
					switch (choice) {
					case 1:
						CheckingAccount cAcc = new CheckingAccount(cst, scan);
						cst.saveCustomer(cFilename);
						continue;
					case 2:
						SavingAccount sAcc = new SavingAccount(cst, scan);
						cst.saveCustomer(cFilename);
						continue;
					case 3:
						System.out.println("To request for a joint account your assosiates must enter hes logging credentials.\nName: ");
						String name = scan.nextLine();
						System.out.print("Password: ");
						String pass = scan.nextLine();
						Customer cust2 = new Customer(name, pass.toCharArray());
						JointAccount jAcc = new JointAccount(cst, scan, cust2);
						cst.saveCustomer(cFilename);
						cust2.saveCustomer(cFilename);
						continue;
					default:
						continue;
					}
				case 2:
					for (Account account : cst.getAccounts()) {
						System.out.println("Account Nickname: " + account.getName() + "		Current Balance: $"
								+ account.getCurrentBalance() + "\nCurrent State: " + account.getCurrentState() + "			Type: " + account.getClass().getSimpleName() + "\n");
					}
					scan.nextLine();
					continue;
				case 3:
					String nick;
					int x = 0;
					for (Account account : cst.getAccounts()) {
						if (account.getCurrentState() == AccountState.ACCEPTED) {
							x++;
							System.out.println(account.getName() + " $" + account.getCurrentBalance());
						}
					}
					if (x != 0) {
						System.out.println("Write the name of the account: ");
						nick = scan.nextLine();
						for (Account account : cst.getAccounts()) {
							if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
								if (account.getCurrentState() == AccountState.ACCEPTED) {
									System.out.print("Amount to withdraw: ");
									Transaction trans = new Transaction(TransactionType.WITHDRAWL,
											Double.parseDouble(scan.nextLine()), account);
								}
							}
						}
					} else
						System.out.println("No available accounts!");
					cst.saveCustomer(cFilename);
					scan.nextLine();
					continue;
				case 4:
					String nick1;
					x = 0;
					for (Account account : cst.getAccounts()) {
						if (account.getCurrentState() == AccountState.ACCEPTED) {
							x++;
							System.out.println(account.getName() + " $" + account.getCurrentBalance());
						}
					}
					if (x != 0) {
						System.out.print("Write the name of the account: ");
						nick1 = scan.nextLine();
						for (Account account : cst.getAccounts()) {
							if (account.getName().toLowerCase().equals(nick1.toLowerCase())) {
								System.out.print("Amount to deposit: ");
								Transaction trans = new Transaction(TransactionType.DEPOSIT,
										Double.parseDouble(scan.nextLine()), account);
							}
						}
					} else
						System.out.println("No available accounts!");
					cst.saveCustomer(cFilename);
					scan.nextLine();
					continue;
				case 5:
					String nick2;
					ArrayList<Account> accounts = cst.getAccounts();
					x = 0;
					for (Account account : cst.getAccounts()) {
						if (account.getCurrentState() == AccountState.ACCEPTED) {
							x++;
							System.out.println(account.getName() + " $" + account.getCurrentBalance());
						}
					}
					if (x != 0) {
						System.out.print("Write the sender account name: ");
						nick1 = scan.nextLine();
						System.out.print("Write the sender account name: ");
						nick2 = scan.nextLine();

						for (Account send : accounts) {
							if (send.getName().toLowerCase().equals(nick1.toLowerCase())) {
								for (Account rec : accounts)
									if (rec.getName().toLowerCase().equals(nick2.toLowerCase())) {
										System.out.print("Amount to deposit: ");
										Transaction trans = new Transaction(TransactionType.TRANSFER,
												Double.parseDouble(scan.nextLine()), send, rec);
									}
							}
						}
					} else
						System.out.println("No available accounts!");
					cst.saveCustomer(cFilename);
					scan.nextLine();
					continue;
				case 6:
					for (Account account : cst.getAccounts()) {
						System.out.print("\n" + account.getName() + " 	$" + account.getCurrentBalance());
					}
					System.out.print("\rWrite the name of the account: ");
					nick1 = scan.nextLine();
					for (Account account : cst.getAccounts()) {
						if (account.getName().toLowerCase().equals(nick1.toLowerCase())) {
							for (Transaction t : account.getTransactions())
								System.out.println("Type: " + t.getType().toString() + "			Date: "
										+ t.getDate() + "		Amount: " + t.getAmount());
						}
					}
					scan.nextLine();
					continue;
				case 7:
					System.out.println("Customer Information \nName: " + cst.getName() + "\nEmail: " + cst.getEmail()
							+ "\nIf more is added, it goes here.");
					scan.nextLine();
					continue;
				}
			}
		}

	}
	
	//The main menu for employee's where they can access all the customers accounts and personal information
	//Additionally they can approve or deny a request to open an account from a customer.
	public void employeeMenu(Employee emp, Scanner scan) {
		int choice = 0;
		String filename = "./customers.txt";
		ArrayList<Customer> custList = new ArrayList<>();
		System.out.println("\n\nWelcome Back to the Bank App, fellow employee! \n\nSelect a customer by using the ID.");

		for (Customer cust : Customer.getCustomers(filename)) {
			custList.add(cust);
			System.out.println("ID: " + cust.getId() + "	 	Name: " + cust.getName());
		}
		System.out.print("Selection: ");
		choice = Integer.parseInt(scan.nextLine());
		while (choice != -1) {

			for (Customer c : custList) {
				if (c.getId() == choice) {
					while (choice != -1) {
						System.out.print("\n\nPlease select one of the following available "
								+ "activities.\n 1. Approve/Deny Accounts\n 2. View Balances \n 3. View Accounts\n 4. View Personal Information"
								+ "\n -1. Exit\nSelection: ");
						choice = Integer.parseInt(scan.nextLine());
						switch (choice) {
						case 1:
							String nick;
							int x = 0;
							for (Account account : c.getAccounts()) {
								if (account.getCurrentState() == AccountState.PENDING) {
									x++;
									System.out.println(account.getName() + " $" + account.getCurrentBalance());
								}
							}
							if (x != 0) {
								System.out.print("Write the name of the account: ");
								nick = scan.nextLine();
								for (Account account : c.getAccounts()) {
									if (account.getName().equals(nick)) {
										if (account.getCurrentState() == AccountState.PENDING) {
											System.out
													.print("Would you like to Approve(a) or Deny(d) this application?");
											nick = scan.nextLine();
											if (nick.equals("d")) {
												emp.deny(account);
											} else if (nick.equals("a")) {
												emp.approve(account);
											} else
												System.out.println("Invalid selection.");
										} else
											System.out.println("Invalid selection.");
									}
								}
								c.saveCustomer(filename);
							} else
								System.out.println("No pending applications!");
							scan.nextLine();
							continue;

						case 2:
							for (Account account : c.getAccounts()) {
								System.out.println("Account Nickname: " + account.getName() + " 	Current Balance: $"
										+ account.getCurrentBalance());
							}
							scan.nextLine();
							continue;
						case 3:
							for (Account account : c.getAccounts()) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the name of the account: ");
							nick = scan.nextLine();
							for (Account account : c.getAccounts()) {
								if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
									String by = "";
									if (account.getAprovedBy() != null)
										by = account.getAprovedBy().getName();
									System.out.print("Account #: " + account.getAccountNumber() + "		"
											+ "Current State: " + account.getCurrentState().toString()
											+ "\nAproved By: " + by + "\nAproved Date: " + account.getAprovedDate()
											+ "			Current Balance: " + account.getCurrentBalance() + "\n\n");
									for (Transaction t : account.getTransactions())
										System.out.println("Type: " + t.getType() + "		Date: " + t.getDate()
												+ "		Amount: " + t.getAmount());
								}
							}
							scan.nextLine();
							continue;
						case 4:
							System.out.println("Customer Information \nName: " + c.getName() + "\nEmail: "
									+ c.getEmail() + "\nIf more is added, it goes here.");
							scan.nextLine();
							continue;
						}
					}
				}
			}
		}

	}

	//The main menu for admin's to manage and view all the customers account, make deposits, withdrawals and transfers for customers
	//They can also deny, approve and cancel a customers request, they can also edit some account information
	public void adminMenu(Admin ad, Scanner scan) {
		int choice = 0;
		String nick;
		String filename = "./customers.txt";
		ArrayList<Customer> custList = new ArrayList<>();

		System.out.println("\n\nAdmin: " + ad.getUsername() + "\nSelect a customer by using the ID.");
		for (Customer cust : Customer.getCustomers(filename)) {
			custList.add(cust);
			System.out.println("ID: " + cust.getId() + "	Name: " + cust.getName());
		}
		System.out.print("\nSelection: ");
		choice = Integer.parseInt(scan.nextLine());
		while (choice != -1) {

			for (Customer c : custList) {
				if (c.getId() == choice) {
					while (choice != -1) {
						System.out.print("\nPlease select one of the following available "
								+ "activities.\n	1. Change account states\n	2. View Balances \n	3. View Accounts\n	4. View Customers Information"
								+ "\n	5. Withdrawl\n	6. Deposit\n	7. Transfer\n	8. Edit Customer Information\n	9. Register Employee\n"
								+ "	-1. to go back\nSelection: ");
						choice = Integer.parseInt(scan.nextLine());
						switch (choice) {
						case 1:
							for (Account account : c.getAccounts()) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the name of the account: ");
							nick = scan.nextLine();
							for (Account account : c.getAccounts()) {
								if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
									System.out
											.print("Would you like to Approve(a), Deny(d) or Cancel(c) this account? ");
									nick = scan.nextLine();
									if (nick.equals("d")) {
										ad.deny(account);
									} else if (nick.equals("a")) {
										ad.approve(account);
									} else if (nick.equals("c")) {
										ad.cancel(account);
									} else
										System.out.println("Invalid selection.");
								}
							}
							c.saveCustomer(filename);
							scan.nextLine();
							continue;

						case 2:
							for (Account account : c.getAccounts()) {
								System.out.println("Account Nickname: " + account.getName() + "		Current Balance: $"
										+ account.getCurrentBalance());
							}
							break;
						case 3:
							for (Account account : c.getAccounts()) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the name of the account: ");
							nick = scan.nextLine();
							for (Account account : c.getAccounts()) {
								if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
									String by = "";
									if (account.getAprovedBy() != null)
										by = account.getAprovedBy().getName();
									System.out.print("Account #: " + account.getAccountNumber() + "		"
											+ "Current State: " + account.getCurrentState().toString()
											+ "\nAproved By: " + by + "\nAproved Date: " + account.getAprovedDate()
											+ "			Current Balance: " + account.getCurrentBalance() + "\n\n");
									for (Transaction t : account.getTransactions())
										System.out.println("Type: " + t.getType() + "		Date: " + t.getDate()
												+ "		Amount: " + t.getAmount());
								}
							}
							scan.nextLine();
							continue;
						case 4:
							System.out.println("Customer Information \nName: " + c.getName() + "\nEmail: "
									+ c.getEmail() + "\nIf more info is added, it goes here.");
							scan.nextLine();
							continue;

						case 5:
							for (Account account : c.getAccounts()) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the name of the account: ");
							nick = scan.nextLine();
							for (Account account : c.getAccounts()) {
								if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
									System.out.println("Amount to withdraw: ");
									Transaction trans = new Transaction(TransactionType.WITHDRAWL,
											Double.parseDouble(scan.nextLine()), account);
								}
							}
							c.saveCustomer(filename);
							scan.nextLine();
							continue;
						case 6:
							for (Account account : c.getAccounts()) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the name of the account: ");
							nick = scan.nextLine();
							for (Account account : c.getAccounts()) {
								if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
									System.out.print("Amount to deposit: ");
									Transaction trans = new Transaction(TransactionType.DEPOSIT,
											Double.parseDouble(scan.nextLine()), account);
								}
							}
							c.saveCustomer(filename);
							scan.nextLine();
							continue;
						case 7:
							String nick2;
							ArrayList<Account> accounts = c.getAccounts();
							for (Account account : accounts) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the sender account name: ");
							nick = scan.nextLine();
							System.out.print("Write the sender account name: ");
							nick2 = scan.nextLine();

							for (Account send : accounts) {
								if (send.getName().toLowerCase().equals(nick.toLowerCase())) {
									for (Account rec : accounts)
										if (rec.getName().toLowerCase().equals(nick2.toLowerCase())) {
											System.out.print("Amount to deposit: ");
											Transaction trans = new Transaction(TransactionType.TRANSFER,
													Double.parseDouble(scan.nextLine()), send, rec);
										}
								}
							}
							c.saveCustomer(filename);
							scan.nextLine();
							continue;
						case 8:
							for (Account account : c.getAccounts()) {
								System.out.println(account.getName() + " $" + account.getCurrentBalance());
							}
							System.out.print("Write the name of the account: ");
							nick = scan.nextLine();
							for (Account account : c.getAccounts()) {
								if (account.getName().toLowerCase().equals(nick.toLowerCase())) {
									System.out.println("Select account information to edit\n  1. Aproved By\n");
									switch (choice) {
									case 1:
										nick = scan.nextLine();
										account.getAprovedBy().setName(nick);
										continue;
									}
								}
							}
							c.saveCustomer(filename);
							scan.nextLine();
							continue;
						case 9:
							Employee emp = new Employee(scan);
							continue;

						}
					}
				}
			}
		}

	}
}
