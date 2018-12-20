import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Terminal {
	private Scanner scan;
	private boolean loggedIn = false; // Setting this to false, can change when login
	private BankUser user; // usernames are converted to all lowercase when entered and are stored that way
	private ServerCommunicator serv;
	private static final int MAXOPENACCOUNTS = 5;
	/*
	 * 
	 */

	public Terminal(Scanner scan, ServerCommunicator serv) {
		this.scan = scan;
		this.serv = serv;

	}

	public void interact() {
		while (true) {
			if (!loggedIn) {
				System.out.println("Please choose what would you like to do?");
				System.out.println("1. Login");
				System.out.println("2. Register Account");
				System.out.println("3. Exit");

				int choice = scanChoice(1, 3);
				if (choice == 2)
					register();
				else if (choice == 1) {
					logIn();
				} else if (choice == 3) {
					return;
				}
			} else if (loggedIn && user.getAccountType() == 1) {
				System.out.println("Hello, " + user.getFullName());
				System.out.println("Please choose what would you like to do?");

				System.out.println("1. View your accounts");
				System.out.println("2. Open an account");
				System.out.println("3. Open a joint account");
				System.out.println("4. Close an account");
				System.out.println("5. Make Withdrawal");
				System.out.println("6. Make Deposit.");
				System.out.println("7. Transfer money between accounts.");
				System.out.println("8. View Transactions.");
				System.out.println("9. Logout");
				System.out.println("10. Exit");

				int choice = scanChoice(1, 10);
				switch (choice) {

				case (1):
					viewAccounts();
					break;
				case (2):
					openAccount();
					break;
				case (3):
					openJointAccount();
					break;
				case (4):
					closeAccount();
					break;
				case (5):
					withdraw();
					break;
				case (6):
					deposit();
					break;
				case(7):
					this.makeTransfer();
					break;
				case (8):
					viewTransactions();
					break;
				case (9):
					logOut();
					break;
				case (10):
					return;

				}
			} else if (loggedIn && user.getAccountType() == 2) {
				System.out.println("Greetings, " + user.getFullName());

				System.out.println("1. View all users");
				System.out.println("2. View user information");
				System.out.println("3. View account information");
				System.out.println("4. View user transactions.");
				System.out.println("5. View account transactions.");
				System.out.println("6. Logout");
				System.out.println("7. Exit");

				int choice = scanChoice(1, 7);
				switch (choice) {

				case (1):
					viewUsers();
					break;
				case (2):
					this.viewUser();
					break;
				case (3):
					this.viewAccount();
					break;
				case (4):
					this.viewUserTransactions();
					break;
				case(5):
					this.viewAccountTransactions();
					break;
				case (6):
					logOut();
					break;
				case (7):
					return;
				}

			}else if (loggedIn && user.getAccountType() == 3) {
				System.out.println("Greetings, " + user.getFullName());

				System.out.println("1. View all users");
				System.out.println("2. View user information");
				System.out.println("3. View account information");
				System.out.println("4. View user transactions.");
				System.out.println("5. View account transactions.");
				System.out.println("6. Deposit into account.");
				System.out.println("7. Withdraw from account.");
				System.out.println("8. Transfer money between accounts.");
				System.out.println("9. Close an account.");
				System.out.println("10. Logout");
				System.out.println("11. Exit");

				int choice = scanChoice(1, 11);
				switch (choice) {

				case (1):
					viewUsers();
					break;
				case (2):
					this.viewUser();
					break;
				case (3):
					this.viewAccount();
					break;
				case (4):
					this.viewUserTransactions();
					break;
				case(5):
					this.viewAccountTransactions();
					break;
				case(6):
					this.adminDeposit();
					break;
				case(7):
					this.adminWithdraw();
					break;
				case(8):
					this.adminMakeTransfer();
					break;
				case(9):
					this.adminCloseAccount();
					break;
				case (10):
					logOut();
					break;
				case (11):
					return;
				}

			} else if (loggedIn && user.getAccountType() == 1) {
				System.out.println("Hello, " + user.getFullName());
				System.out.println("Please choose what would you like to do?");

				System.out.println("1. View your accounts");
				System.out.println("2. Open an account");
				System.out.println("3. Open a joint account");
				System.out.println("4. Close an account");
				System.out.println("5. Make Withdrawal");
				System.out.println("6. Make Deposit.");
				System.out.println("7. View Transactions.");
				System.out.println("8. Logout");
				System.out.println("9. Exit");

				int choice = scanChoice(1, 9);
				switch (choice) {

				case (1):
					viewAccounts();
					break;
				case (2):
					openAccount();
					break;
				case (3):
					openJointAccount();
					break;
				case (4):
					closeAccount();
					break;
				case (5):
					withdraw();
					break;
				case (6):
					deposit();
					break;
				case (7):
					viewTransactions();
					break;
				case (8):
					logOut();
					break;
				case (9):
					return;

				}
			} else if (loggedIn && user.getAccountType() == 4) {
				System.out.println("Greetings, " + user.getFullName());
				System.out.println("Please choose what would you like to do, oh great super user.");

				System.out.println("1. View all users");
				System.out.println("2. View details of a user.");
				System.out.println("3. Create a user");
				System.out.println("4. Delete a user");
				System.out.println("5. Update a user");
				System.out.println("6. Logout");
				System.out.println("7. Exit");

				int choice = scanChoice(1, 7);
				switch (choice) {

				case (1):
					viewUsers();
					break;
				case(2):
					viewUser();
					break;
				case (3):
					createUser();
					break;
				case (4):
					deleteUser();
					break;
				case (5):
					updateUser();
					break;
				case (6):
					logOut();
					break;
				case (7):
					return;
				}

			}

		}
	}

	public void register() {

		String username = scanUsername("What would you like your username to be?", true);

		System.out.println("What would you like your password to be?");
		String password = createPassword();

		String firstName = this.scanName("Enter your first name.");
		String lastName = this.scanName("Enter your last name.");
		BankUser user = new BankUser(firstName, lastName, username, password);
		if (serv.createUser(user) != null) {
			System.out.println("Successfully registered user.");
		} else {
			System.out.println("Unable to register user.");
		}

	}

	public void logIn() {
		String username = this.scanUsername("Enter your username.", false);
		System.out.println("Enter your password.");
		String password = scan.nextLine();
		BankUser user = new BankUser(username, password);
		user = serv.login(user);
		if (user == null) {
			System.out.println("Unable to login.");
			return;
		} else {
			this.user = user;
			loggedIn = true;
			// System.out.println("Hello, " + user.getFullName());
		}
	}

	public void logOut() {
		loggedIn = false;
		user = null;
	}

	public void viewAccounts() {
		ArrayList<BankAccount> accounts = serv.getUserAccounts(user);
		accounts.forEach(e -> {
			System.out.println(e);
		});
		if (accounts.size() == 0) {
			System.out.println("You have no accounts at this bank.");
		}
	}

	public void openAccount() {
		if(getNumAccount() > MAXOPENACCOUNTS) {
			System.out.println("You may not open more accounts whilst having " + MAXOPENACCOUNTS + " or more accounts open at a time.");
			return;
		}
		BankAccount acc = serv.createAccount(new BankAccount(true), user);
		if (acc == null) {
			System.out.println("Unable to create account.");
		} else {
			System.out.println("Account successfully created.");
			System.out.println("Here is the information for your new account!");
			System.out.println(acc);
		}
	}
	public int getNumAccount() {
		ArrayList<BankAccount> accs = serv.getUserAccounts(user);
		return accs.size();
	}
	public void openJointAccount() {
		if(getNumAccount() > MAXOPENACCOUNTS) {
			System.out.println("You may not open more accounts whilst having " + MAXOPENACCOUNTS + " or more accounts open at a time.");
			return;
		}
		ArrayList<BankUser> partners = new ArrayList<BankUser>();
		int choice;
		do {
			String username = scanUsername(
					"Please enter the username of the person you wish to open a joint account with.", false);
			BankUser partner = serv.getUser(username);
			if (partner == null || partner.getAccountType() != 1) {
				System.out.println("There is no such customer at our bank.");
				return;
			} else {
				partners.add(partner);
				System.out.println("Current account partners: ");
				partners.forEach(e -> {
					System.out.println(e);
				});
				System.out.println("Choose from the following options:");
				System.out.println("1. Add another person to the joint account.");
				System.out.println("2. Create joint account with selected individual(s)");
				System.out.println("3. Exit joint account creation without opening an account.");
				choice = scanChoice(1, 3);
			}
		} while (choice == 1);
		if (choice == 3) {
			return;
		}
		partners.add(this.user);
		BankAccount acc = new BankAccount(true);
		acc = serv.createJointAccount(acc, partners);
		if (acc == null) {
			System.out.println("Unable to create account.");
		} else {
			System.out.println("Account successfully created.");
			System.out.println(acc);
		}
	}

	public void closeAccount() {
		int accountNum = scanId("What is the account number of the account you wish to close.");
		BankAccount acc = serv.getAccount(new BankAccount(accountNum), user);
		if (acc == null) {
			System.out.println("There is no such account in our records.");
			return;
		} else if (acc.getBalance() > 0) {
			System.out.println("An account must be empty in order to close it.");
			return;
		} else {
			if (serv.deleteAccount(acc)) {
				System.out.println("Account successfully deleted.");
			} else {
				System.out.println("Unable to delete account.");
			}
		}

	}
	public void adminCloseAccount() {
		int accountNum = scanId("What is the account number of the account you wish to close.");
		BankAccount acc = serv.getAccount(accountNum);
		if (acc == null) {
			System.out.println("There is no such account in our records.");
			return;
		} else if (acc.getBalance() > 0) {
			System.out.println("An account must be empty in order to close it.");
			return;
		} else {
			if (serv.deleteAccount(acc)) {
				System.out.println("Account successfully deleted.");
			} else {
				System.out.println("Unable to delete account.");
			}
		}

	}
	
	public void adminDeposit() {
		System.out.println(user.getAccountType());
		if (user.getAccountType() <3) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		int accountNumber = this.scanId("Please enter the account number of the account you wish to deposit into.");
		BankAccount acc = serv.getAccount(accountNumber);
		if (acc == null) {
			System.out.println("Invalid account number.");
			return;
		}
		double amount = this.scanMoney("How much would you like to deposit?");
		if (!acc.deposit(amount)) {
			System.out.println("Unable to process request.");
			return;
		}
		Transaction trans = new Transaction(user.getId(), acc.getAccountNumber(), amount, acc.getBalance());
		acc = serv.updateAccount(acc,trans);
		if (acc == null) {
			System.out.println("Unable to update account.");
			return;
		}
		System.out.println("Deposit successfully executed.");

	}
	public void adminWithdraw() {
		System.out.println(user.getAccountType());
		if (user.getAccountType() <3) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		int accountNumber = this.scanId("Please enter the account number of the account you wish to withdraw from.");
		BankAccount acc = serv.getAccount(accountNumber);
		if (acc == null) {
			System.out.println("Invalid account number.");
			return;
		}
		double amount = this.scanMoney("How much would you like to withdraw?");
		if (acc.getBalance() < amount) {
			System.out.println("You may not overdraw this account.");
			return;
		}
		if (!acc.withdraw(amount)) {
			System.out.println("Unable to process request.");
			return;
		}
		Transaction trans = new Transaction(user.getId(), acc.getAccountNumber(), amount * -1, acc.getBalance());
		acc = serv.updateAccount(acc,trans);
		if (acc == null) {
			System.out.println("Unable to update account.");
			return;
		}
		System.out.println("Withdraw successfully executed.");
	}
	public void deposit() {
		ArrayList<BankAccount> accs = serv.getUserAccounts(user);
		if(accs==null || accs.size()==0) {
			System.out.println("You have no open accounts at this bank.");
			return;
		}
		System.out.println("Please choose which account you would like to deposit into.");
		accs.forEach(e->{
			System.out.print(accs.indexOf(e) + 1 + ". ");
			System.out.println(e);
		});
		int exitChoice = accs.size()+1;
		System.out.println(exitChoice + ". Go back to main menu.");
		int choice = scanChoice(1,exitChoice);
		if(choice==exitChoice) {
			return;
		}
		BankAccount acc = accs.get(choice-1);
//		int accountNumber = this.scanId("Please enter the account number of the account you wish to deposit into.");
//		BankAccount acc = serv.getAccount(accountNumber, user.getId());
//		if (acc == null) {
//			System.out.println("Invalid account number.");
//			return;
//		}
		double amount = this.scanMoney("How much would you like to deposit?");
		if (!acc.deposit(amount)) {
			System.out.println("Unable to process request.");
			return;
		}
		Transaction trans = new Transaction(user.getId(), acc.getAccountNumber(), amount, acc.getBalance());
		acc = serv.updateAccount(acc,trans);
		if (acc == null) {
			System.out.println("Unable to update account.");
			return;
		}
		System.out.println("Deposit successfully executed.");

	}

	public void withdraw() {
		ArrayList<BankAccount> accs = serv.getUserAccounts(user);
		if(accs==null || accs.size()==0) {
			System.out.println("You have no open accounts at this bank.");
			return;
		}
		System.out.println("Please choose which account you would like to withdraw from.");
		accs.forEach(e->{
			System.out.print(accs.indexOf(e) + 1 + ". ");
			System.out.println(e);
		});
		int exitChoice = accs.size()+1;
		System.out.println(exitChoice + ". Go back to main menu.");
		int choice = scanChoice(1,exitChoice);
		if(choice==exitChoice) {
			return;
		}
		BankAccount acc = accs.get(choice-1);
		//		int accountNumber = this.scanId("Please enter the account number of the account you wish to withdraw from.");
//		BankAccount acc = serv.getAccount(accountNumber, user.getId());
//		if (acc == null) {
//			System.out.println("Invalid account number.");
//			return;
//		}
		double amount = this.scanMoney("How much would you like to withdraw?");
		if (acc.getBalance() < amount) {
			System.out.println("You may not overdraw your account.");
			return;
		}
		if (!acc.withdraw(amount)) {
			System.out.println("Unable to process request.");
			return;
		}
		Transaction trans = new Transaction(user.getId(), acc.getAccountNumber(), amount * -1, acc.getBalance());
		acc = serv.updateAccount(acc,trans);
		if (acc == null) {
			System.out.println("Unable to update account.");
			return;
		}
		System.out.println("Withdraw successfully executed.");
	}

	public void makeTransfer() {
		ArrayList<BankAccount> accs = serv.getUserAccounts(user);
		if(accs==null || accs.size()==0) {
			System.out.println("You have no open accounts at this bank.");
			return;
		}
		System.out.println("Please choose which account you would like to withdraw from for your transaction.");
		accs.forEach(e->{
			System.out.print(accs.indexOf(e) + 1 + ". ");
			System.out.println(e);
		});
		int exitChoice = accs.size()+1;
		System.out.println(exitChoice + ". Go back to main menu.");
		int choice = scanChoice(1,exitChoice);
		if(choice==exitChoice) {
			return;
		}
		BankAccount acc = accs.get(choice-1);
		int accountNumber = this.scanId("Please enter the account number of the account you wish to transfer into.");
		BankAccount acc2 = serv.getAccount(accountNumber);
		if (acc == null) {
			System.out.println("Invalid account number.");
			return;
		}	
		double amount = this.scanMoney("How much would you like to transfer?");
		if (!acc.withdraw(amount)||!acc2.deposit(amount)) {
			System.out.println("Unable to process request.");
			return;
		}
		Transaction tran1 = new Transaction(user.getId(), acc.getAccountNumber(), amount*-1, acc.getBalance());
		Transaction tran2 = new Transaction(user.getId(), acc2.getAccountNumber(), amount, acc2.getBalance());
		if(!serv.makeTransfer(acc, acc2, tran2, tran1)) {
			System.out.println("Unable to process request.");
			return;
		}
		System.out.println("Transfer successfully completed.");
	}
	
	public void adminMakeTransfer() {
		System.out.println(user.getAccountType());
		if (user.getAccountType() <3) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		int accountNumber = this.scanId("Please enter the account number of the account you wish to withdraw from.");
		BankAccount acc = serv.getAccount(accountNumber);
		if (acc == null) {
			System.out.println("Invalid account number.");
			return;
		}
		accountNumber = this.scanId("Please enter the account number of the account you wish to transfer into.");
		BankAccount acc2 = serv.getAccount(accountNumber);
		if (acc2 == null) {
			System.out.println("Invalid account number.");
			return;
		}	
		double amount = this.scanMoney("How much would you like to transfer?");
		if (!acc.withdraw(amount)||!acc2.deposit(amount)) {
			System.out.println("Unable to process request.");
			return;
		}
		Transaction tran1 = new Transaction(user.getId(), acc.getAccountNumber(), amount*-1, acc.getBalance());
		Transaction tran2 = new Transaction(user.getId(), acc2.getAccountNumber(), amount, acc2.getBalance());
		if(!serv.makeTransfer(acc, acc2, tran2, tran1)) {
			System.out.println("Unable to process request.");
			return;
		}
		System.out.println("Transfer successfully completed.");
	}
	public void viewTransactions() {
		System.out.println("1. View all personal transactions.");
		System.out.println("2. View all transactions on an account.");
		int choice = this.scanChoice(1, 2);
		ArrayList<Transaction> trans;
		if (choice == 1) {
			trans = serv.getUserTransactions(user);
		} else {
			int accountNumber = this.scanId(
					"Please enter the account number of the account you wish to view the transaction history of.");
			BankAccount acc = serv.getAccount(accountNumber, user.getId());
			if (acc == null) {
				System.out.println("Invalid account number.");
				return;
			}
			trans = serv.getAccountTransactions(acc);
		}
		if (trans == null) {
			System.out.println("Unable to process request.");
			return;
		}
		System.out.println(trans.size());
		trans.forEach(e -> {
			System.out.println(e);
		});

	}

	public void viewUsers() {
		System.out.println(user.getAccountType());
		if (user.getAccountType() == 1) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		ArrayList<BankUser> users = serv.getAllUsers();
		if (users.size() == 0) {
			System.out.println("Unable to process request.");
			return;
		}
		users.forEach(e -> {
			System.out.println(e);
		});

	}

	public void deleteUser() {
		if (user.getAccountType() < 4) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String username = scanUsername("What is the username of the account you wish to delete.", false);
		if(username.equals(user.getUsername())) {
			System.out.println("haha, no.");
		}
		BankUser user = serv.getUser(username);
		if(user==null) {
			System.out.println("No such user exists.");
			return;
		}
		System.out.println("Deleting - - ");
		System.out.println(user);

		if (!serv.deleteUser(user)) {
			System.out.println("Unable to process request.");
			return;
		}
		System.out.println(
				"The user, the accounts belonging only to the user,\n and the transaction history of those accounts have been deleted.");

	}

	public void createUser() {
		if (user.getAccountType() < 4) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String username = scanUsername("What would you like the username to be?", true);
		System.out.println("What would you like the password to be?");
		String password = createPassword();

		String firstName = this.scanName("Enter the first name of the user.");
		String lastName = this.scanName("Enter the last name of the user.");
		String prompt = "Please choose the account type of this user:";
		prompt += "\n1. Customer level account";
		prompt += "\n2. Employee level account";
		prompt += "\n3. Admin level account";
		prompt += "\n4. Superuser account";
		System.out.println(prompt);
		int accType = scanChoice(1, 4);
		if (serv.createUser(new BankUser(firstName, lastName, username, password, accType)) != null) {
			System.out.println("Successfully registered user.");
		} else {
			System.out.println("Unable to register user.");
		}
	}

	public void viewUser() {
		if(user.getAccountType() < 2) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String username = scanUsername("What is the username of the person you wish to view.",false);
		BankUser usr = serv.getUser(username);
		if(usr==null) {
			System.out.println("No user with that username in our records.");
			return;
		}
		System.out.println("User Info:");
		System.out.println(usr);
		ArrayList<BankAccount> accs = serv.getUserAccounts(usr);
		if (accs == null) {
			System.out.println("This user has no accounts.");
		}
		System.out.println("User Accounts:  ");
		accs.forEach(e -> {
			System.out.println(e);
		});
	}
	public void viewAccount() {
		if(user.getAccountType() < 2) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		int accid = scanId("Please enter the account number of the account you wish to view.");
		BankAccount acc = serv.getAccount(accid);
		if(acc==null) {
			System.out.println("There is no account with the given account number in our records.");
			return;
		}
		System.out.println(acc);
		ArrayList<BankUser> users = serv.getAccountOwners(acc);
		System.out.println("Individuals with access to this account");
		users.forEach(e -> {
			System.out.println(e);
		});
	}
	public void viewUserTransactions() {
		if (user.getAccountType() < 2) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String username = scanUsername("What is the username of the user whose transaction you wish to view?", false);
		BankUser usr = serv.getUser(username);
		if (usr == null) {
			System.out.println("No user with that username in our records.");
			return;
		}
		ArrayList<Transaction> trans = new ArrayList<>();
		trans = serv.getUserTransactions(usr);
		if(trans==null) {
			System.out.println("Unable to view transactions.");
			return;
		}
		trans.forEach(e -> {
			System.out.println(e);
		});
	}
	public void viewAccountTransactions() {
		if (user.getAccountType() < 2) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		int accID = scanId("What is the account number of the account whose transaction you wish to view?");
		BankAccount acc = serv.getAccount(accID);
		if (acc == null) {
			System.out.println("No account with that number in our records.");
			return;
		}
		ArrayList<Transaction> trans = new ArrayList<>();
		trans = serv.getAccountTransactions(acc);
		if(trans==null) {
			System.out.println("Unable to view transactions.");
			return;
		}
		trans.forEach(e -> {
			System.out.println(e);
		});
	}
	public void updateUser() {
		if (user.getAccountType() < 4) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String username = scanUsername("What is the username of the user you would like to modify?", false);
		BankUser usr = serv.getUser(username);
		if (usr == null) {
			System.out.println("No user with that username in our records.");
			return;
		}
		System.out.println("User Info:");
		System.out.println(usr);
		System.out.println("How would you like to update this user?");
		System.out.println("1. Change username.");
		System.out.println("2. Change actual name.");
		System.out.println("3. Change password.");
		System.out.println("4. Return to main menu.");

		int choice = scanChoice(1, 4);
		switch (choice) {

		case (1):
			changeUsername(usr);
			break;
		case (2):
			changeName(usr);
			break;
		case (3):
			changePassword(usr);
			break;
		case (4):
			return;
		}
	}

	public void changeUsername(BankUser usr) {
		if (user.getAccountType() < 4) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String newUsername = this.scanUsername("Please enter the new username desired.", true);
		usr.setUsername(newUsername);
		usr = serv.updateUser(usr);
		if (usr == null) {
			System.out.println("Unable to process request.");
			return;
		} else {
			System.out.println("User information successfully updated.");
		}

	}

	public void changeName(BankUser usr) {
		if (user.getAccountType() < 3) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String firstName = this.scanName("Please enter the new first name.");
		String lastname = this.scanName("Please enter the new last name.");

		usr.setFirstname(firstName);
		usr.setLastname(lastname);
		usr = serv.updateUser(usr);
		if (usr == null) {
			System.out.println("Unable to process request.");
			return;
		} else {
			System.out.println("User information successfully updated.");
		}

	}

	public void changePassword(BankUser usr) {
		if (user.getAccountType() < 4) {
			System.out.println("You do not have permission to perform this operation.");
			return;
		}
		String password = this.createPassword();
		usr.setPassword(password);
		usr = serv.updateUser(usr);
		if (usr == null) {
			System.out.println("Unable to process request.");
			return;
		} else {
			System.out.println("User information successfully updated.");
		}

	}

	/*
	 * Below this are methods private to this class these mainly consist of things I
	 * have abstracted due to frequent use such as reading in inputs
	 */
	private int scanId(String message) {
		int recipId = -1;
		do {
			System.out.println(message);
			if (!scan.hasNextInt()) {
				scan.next();
			} else
				recipId = scan.nextInt();

		} while (recipId == -1);
		scan.nextLine(); // get rid of newline character
		return recipId;
	}

	/*
	 * abstracted this code because I was using it too much
	 */
	private String scanUsername(String message, boolean newAccount) { // this newAccount variable is to check whether
																		// user is making
		boolean okName = false; // new account or just logging into existing one
		String username = null;
		while (!okName) {
			System.out.println(message);
			username = scan.nextLine();
			if (username.contains(" ")) {
				System.out.println("Spaces are not allowed in user names.");
			} else {
				if (newAccount && serv.userExists(username)) {
					System.out.println("There already exists a user with this username.");
					scan.next();
				} else {
					okName = true;
				}

			}
		}
		username = username.toLowerCase();
		return username;
	}

	/*
	 * abstracted this code because I was using it too much
	 */
	private double scanMoney(String message) {
		System.out.println(message);
		double money = 0;
		do {

			if (!scan.hasNextDouble()) {
				System.out.println("Please enter a valid amount.");
				scan.next();
			} else
				money = scan.nextDouble();
			if (money > 50000) {
				System.out.println("Due to local laws, no transaction may exceed $50,000.");
			}
			if(money < 0.01) {
				System.out.println("Invalid amount.");
			}
		} while (money < 0.01 || money > 50000);
		scan.nextLine(); // get rid of newline character
		DecimalFormat df = new DecimalFormat("#.##");      
		money = Double.valueOf(df.format(money));
		return money;
	}

	/*
	 * abstracted this code because I was using it too much
	 */
	private int scanChoice(int min, int max) {
		int choice = 0;
		do {

			if (!scan.hasNextInt()) {
				System.out.println("Please enter a valid number.");
				scan.next();
			}
			else
				choice = scan.nextInt();
			if(choice > max || choice < min) {
				System.out.println("Invalid number");
			}

		} while (choice > max || choice < min);
		scan.nextLine(); // get rid of newline character
		return choice;
	}

	private String scanName(String message) {
		String name = null;
		boolean okname = false;
		System.out.println(message);
		while (!okname) {
			name = scan.nextLine();
			if (name.chars().allMatch(Character::isLetter)) {
				okname = true;
			} else {
				System.out.println("Invalid Input.");
			}
		}
		// name = name.toLowerCase();
		return name;
	}

	private String createPassword() {
		System.out.println("Your password must be at least 5 characters long.");
		System.out.println("Your password must be less than 20 characters long.");
		String password = null;
		do {
			System.out.println("Please enter your preferred password.");
			password = scan.nextLine();
			if (password.length() < 5 || password.length() >= 20) {
				System.out.println("Invalid password.");
			}
		} while (password.length() < 5 || password.length() >= 20);

		return password;
	}
}
