import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
	private boolean loggedIn = false;
	private String username;
	private Server server;
	private String accountType;
	private Scanner scan;
	/*
	 * NOTE: ADMIN LOGIN: Username: "Admin" Password "Admin"
	 * 		EMPLOYEE LOGIN: Username: "Employee" Password "Employee
	 * 		LOGINS ARE CASE SENSITIVE
	 * 
	 * This is the class that I use as a front end
	 * The Server class is the one used as a backend
	 * All input is received in this class and then communicated to the server
	 * When users register a username/password, they are creating a WebAccount
	 * this WebAccount is stored in an arraylist in a server object
	 * the variable loggedIn is used to determine if a user has logged in
	 * 
	 * To Determine whether a user is an employee or admin, a string variable is stored in the WebAccount class
	 * At the moment I have hardcoded in one employee and one admin account
	 * and it is not possible to create anymore through the program interface
	 * to login as an admin the username is "Admin" and the password is "Admin"
	 * to login as an employee the username is "Employee" and the password is "Employee"
	 * username are case sensitive
	 * 
	 * this class remember what account type is logged in as through the accountType variable
	 */

	public Terminal() {

	}

	public Terminal(Server server, Scanner scan) {
		this.server = server;
		this.scan = scan;
	}
/*
 * the interact method is basically the main menu of the program
 * It determined whether you're logged in, and if you're admin/employee/customer and displays appropriate options
 * reads the options and sends you to whichever option you choose
 */
	public void interact() {

		while (true) {
			if (!loggedIn) {
				System.out.println("Please choose what would you like to do?");
				System.out.println("1. Login");
				System.out.println("2. Register Account");
				System.out.println("3. Exit");

				int choice = scanChoice(1,3);
				if (choice == 2)
					register();
				else if (choice == 1) {
					logIn();
				} else if (choice == 3) {
					break;
				}
			}
			if (loggedIn && accountType.equals("Customer")) {
				System.out.println("Hello, " + username);
				System.out.println("Please choose what would you like to do?");

				System.out.println("1. Apply for Account");
				System.out.println("2. View your accounts");
				System.out.println("3. Make Deposit");
				System.out.println("4. Make Withdrawal");
				System.out.println("5. Make Transfer.");
				System.out.println("6. Apply for Joint Account.");
				System.out.println("7. Logout");
				System.out.println("8. Exit");

				int choice = scanChoice(1,8);
				switch (choice) {

				case (1):
					applyForAccount();
					break;
				case (2):
					viewAccounts();
					break;
				case (3):
					this.makeDeposit();
					break;
				case (4):
					makeWithdraw();
					break;
				case (5):
					this.initiateTransfer();
					break;
				case(6):
					this.applyForJointAccount();
					break;
				case (7):
					loggedIn = false;
					interact();
					break;
				}

				if (choice == 8) {
					break;
				}
			} else if (loggedIn && accountType.equals("Employee")) {
				System.out.println("Hello, " + username);
				System.out.println("Please choose what would you like to do?");

				System.out.println("1. View Account Applications (for Employees)");
				System.out.println("2. View Customer Info");
				System.out.println("3. Browse through each application and applicant.");
				System.out.println("4. Approve Account Application");
				System.out.println("5. Deny an Account Application");
				System.out.println("6. Browse through each joint application and applicant.");
				System.out.println("7. Approve a joint application.");
				System.out.println("8. Deny a joint application.");
				System.out.println("9. Logout");
				System.out.println("10. Exit");

				int choice = scanChoice(1,10);
				switch (choice) {

				case (1):
					viewApplications();
					break;
				case (2):
					viewCustomerInfo();
					break;
				case (3):
					browseApplicants();
					break;
				case(4):
					this.approveAccount();
					break;
				case(5):
					this.declineAccount();
					break;
				case(6):
					this.browseJointApplicants();
					break;
				case(7):
					this.approveJointAccount();
					break;
				case(8):
					this.declineJointAccount();
					break;
				case (9):
					loggedIn = false;
					interact();
					break;
				}

				if (choice == 10) {
					break;
				}
			} else if (loggedIn && accountType == "Admin") {
				System.out.println("Hello, " + username);
				System.out.println("Please choose what would you like to do?");
				System.out.println("1. View Account Applications (for Employees)");
				System.out.println("2. View Customer Info");
				System.out.println("3. Approve Account Application");
				System.out.println("4. Decline Account Application");
				System.out.println("5. Browse through each application and applicant.");
				System.out.println("6. Close an account.");
				System.out.println("7. Deposit money into an account.");
				System.out.println("8. Withdraw money from an account.");
				System.out.println("9. Execute a transfer between two accounts.");
				System.out.println("10. Browse through each joint application and applicant.");
				System.out.println("11. Approve a joint application.");
				System.out.println("12. Deny a joint application.");
				System.out.println("13. Logout");
				System.out.println("14. Exit");

				int choice = scanChoice(1,14);
				switch (choice) {

				case (1):
					viewApplications();
					break;
				case (2):
					viewCustomerInfo();
					break;
				case (3):
					approveAccount();
					break;
				case(4):
					this.declineAccount();
					break;
				case (5):
					browseApplicants();
					break;
				case(6):
					closeAccount();
					break;
				case(7):
					this.adminDeposit();
					break;
				case(8):
					this.adminWithdraw();
					break;
				case(9):
					this.adminTransfer();
					break;
				case(10):
					this.browseJointApplicants();
					break;
				case(11):
					this.approveJointAccount();
					break;
				case(12):
					this.declineJointAccount();
					break;
				case (13):
					loggedIn = false;
					interact();
					break;
				}

				if (choice == 14) {
					break;
				}
			}
		}
	}
/*
 * This is the method that allows registration.
 * It reads in a username, password, firstname, and lastname, and then sends it to the server
 * the server then instantiates a WebAccount object with this information
 * and stores it in an arraylist to be accessed later
 */
	public boolean register() {


		boolean okName = false;
		String username = null;
		while (!okName) {
			System.out.println("What would you like your username to be? \nPlease remember that usernames are case sensitive.");
			username = scan.nextLine();
			if (username.contains(" ")) {
				System.out.println("Spaces are not allowed in user names.");
			} else {
				okName = true;
			}
		}
		System.out.println("What would you like your password to be?");
		String password = scan.nextLine();

		System.out.println("What is your first name?");
		String firstName = scan.nextLine();

		System.out.println("What is your last name?");
		String lastName = scan.nextLine();

		if (server.addWebAccount(username, password, firstName, lastName)) {
			return true;
		}
		System.out.println("Unable to create account");
		return false;
	}
/*
 * the loging method allows logging in
 * It reads in a username and password and sends it to the server for verification
 * once verified it gets the account type, then stores the username and account type in object variables to remember
 */
	public boolean logIn() {
		System.out.println("Please enter your username.");
		String username = scan.nextLine();
		System.out.println("Please enter your password.");
		String password = scan.nextLine();
		loggedIn = server.logIn(username, password);
		if (loggedIn) {
			System.out.println("Successfully logged in.");
			this.username = username;
			this.accountType = server.getAccountType(username);
			return true;
		}
		System.out.println("Unable to login.");
		return false;
		// System.out.println("1. Try again.");
	}
/*
 * This method is used to submit an application for an account.
 * if the server returns false for trying this it means it was unable to complete the request for some reason
 */
	public void applyForAccount() {
		if (server.enterApplication(username))
			System.out.println("Application Submitted");
		else
			System.out.println("Unable to submit application.");
	}
/*
 * this method displays all the open Account applications
 */
	public void viewApplications() {
		ArrayList<String> applicantions = server.getApplicants();
		for (String uname : applicantions) {
			System.out.println(uname);
		}
	}

	/*
	 * This method gets username of customer to view data from, sends it to server
	 * then server creates an object filled with username information and sends it back here
	 * for us to read and print out
	 */
	public void viewCustomerInfo() {
		String customer = scanUsername("Please enter the username of the customer you wish to view.");
		CustomerInfo custInfo = server.getCustomerInfo(customer);
		System.out.println("Here is the information on that customer: ");
		System.out.println(custInfo);
	}
/*
 * Allows for approval of account applications
 */
	public void approveAccount() {
		String customer;
		System.out.println("Please enter the username of the account you wish to approve.");
		customer = scanUsername("Please enter the username of the account you wish to approve.");
		server.approveAccount(customer);
		System.out.println("Account has been approved and created.");
	}
	/*
	 * allows for approval of joint account applications
	 */
	public void approveJointAccount() {
		String customer;
		System.out.println("Please enter the first username of the joint account you wish to approve.");
		customer = scanUsername("Please enter the first username of the joint account you wish to approve.");
		String customer2 = scanUsername("Please enter the second username of the joint account you wish to approve.");
		if(server.approveJointAccountApplication(customer,customer2)) {
			System.out.println("Account has been approved.");
		}
		else {
			System.out.println("Unable to process request.");
		}
	}
/*
 * allows for decline of account applications
 */
	public void declineAccount() {
		String customer;
		System.out.println("Please enter the username of the account you wish to decline.");
		customer = scanUsername("Please enter the username of the account you wish to decline.");
		server.declineAccount(customer);
		System.out.println("Account has been declined.");
	}
	
	/*
	 * allows for declination of join account applications
	 */
	public void declineJointAccount() {
		String customer;
		System.out.println("Please enter the first username of the joint account you wish to decline.");
		customer = scanUsername("Please enter the first username of the joint account you wish to decline.");
		String customer2 = scanUsername("Please enter the second username of the joint account you wish to decline.");
		server.declineJointAccount(customer,customer2);
		System.out.println("Account has been declined.");
	}
/*
 * allows you to browse through the applications and associated applicants one at a time, declining, approving
 * or skipping applications as wanted
 */
	public void browseApplicants() {
		ArrayList<String> applications = server.getApplicants();
		for (String applicant : applications) {
			System.out.println("Applicant " + (applications.indexOf(applicant) + 1) + "/" + applications.size());
			server.getCustomerInfo(applicant);
			System.out.println("What would you like to do?");
			System.out.println("1. Approve application");
			System.out.println("2. Decline application");
			System.out.println("3. Skip this application ");
			int choice = scanChoice(1,3);
			switch (choice) {

			case (1):
				if (accountType.equals("Admin")||accountType.equals("Employee")) {
					System.out.println(server.approveAccount(applicant));
					System.out.println("Application Approved");
				}
				else {
					System.out.println("You do not have permission to do this.");
				}
				break;
			case (2):
				if (accountType.equals("Admin")||accountType.equals("Employee")) {
					server.declineAccount(applicant);
				server.declineAccount(applicant);
				System.out.println("Application Declined");
				}
				else {
					System.out.println("You do not have permission to do this.");
				}
				break;
			case (3):
				System.out.println("Passing over this application.");
				break;
			}
		}	
	}
	/*
	 * allows you to browse through the joint applications and associated applicants one at a time, declining, approving
	 * or skipping applications as wanted
	 */
	public void browseJointApplicants() {
		ArrayList<String> applications = server.getJointApplicants();
		for (String applicant : applications) {
			System.out.println("Applicant " + (applications.indexOf(applicant) + 1) + "/" + applications.size());
			String[] apps = applicant.split(" ");
			System.out.println(server.getCustomerInfo(apps[0]));
			System.out.println(server.getCustomerInfo(apps[1]));
			System.out.println("What would you like to do?");
			System.out.println("1. Approve application");
			System.out.println("2. Decline application");
			System.out.println("3. Skip this application ");
			int choice = scanChoice(1,3);
			switch (choice) {

			case (1):
				if (accountType.equals("Admin")||accountType.equals("Employee")) {
					server.approveJointAccountApplication(apps[0],apps[1]);
					System.out.println("Application Approved");
				}
				else {
					System.out.println("You do not have permission to do this.");
				}
				break;
			case (2):
				if (accountType.equals("Admin")||accountType.equals("Employee")) {
					server.approveJointAccountApplication(apps[0],apps[1]);
				server.declineJointAccount(apps[0],apps[1]);
				System.out.println("Application Declined");
				}
				else {
					System.out.println("You do not have permission to do this.");
				}
				break;
			case (3):
				System.out.println("Passing over this application.");
				break;
			}
		}
	}
	/*
	 * this method allows a customer to view the accounts they have
	 */
	public void viewAccounts() {
		ArrayList<String> accInfos = server.getAccountInfo(username);
		if(accInfos.size() == 0) {
			System.out.println("You currently have no open accounts.");
		}
		for(String a : accInfos) {
			System.out.println(a);
		}
	}
	/*
	 * this method all the user to deposit into an account they have
	 * they must indicate which account of theirs they wish to deposit into by specifying the account id
	 * the id can be found when looking at the information of an account
	 */
	public void makeDeposit() {
		ArrayList<String> accInfos = server.getAccountInfo(username);
		if(accInfos.size() == 0) {
			System.out.println("You currently have no open accounts.");
			return;
		}
		System.out.println("Enter the ID of the account you like to deposit into?");
		ArrayList<Integer> ids = new ArrayList<Integer>();

		for(String a : accInfos) {
			System.out.println(a);
		}
		
		int choice = scanId("Please enter the ID.");
		System.out.println("Very well, how much do you wish to deposit?");
		double money = scanMoney("Very well, how much do you wish to deposit?");
		server.deposit(username, choice, money);
		System.out.println("Money successfully deposited.");
	}
	/*
	 * this allows an admin to make a deposit into an account by specifying username and account id
	 */
	public void adminDeposit() {
		String username = scanUsername("Enter the username of the person whose account you wish to deposit into.");
		int id = scanId("Enter the id of the account you wish to deposit into.");
		double money = scanMoney("How much money do you wish to deposit into this account?");
		boolean success = server.deposit(username, id, money);
		if(success) {
			System.out.println("Successfully depositing money into this account.");
		}
		else {
			System.out.println("Unable to execute the deposit.");
		}
	}
	/*
	 * this allows a customer to make a withdrawal by specifying account
	 * if they try to withdraw more money than they have they are unable to.
	 */
	public void makeWithdraw() {
		ArrayList<String> accInfos = server.getAccountInfo(username);
		if(accInfos.size() == 0) {
			System.out.println("You currently have no open accounts.");
			return;
		}
		System.out.println("Enter the ID of the account you like to withdraw from?");
		ArrayList<Integer> ids = new ArrayList<Integer>();

		for(String a : accInfos) {
			System.out.println(a);
		}
		
		int choice = scanId("Please enter the ID.");
		double money = scanMoney("Very well, how much do you wish to withdraw?");
		boolean success = server.withdraw(username, choice, money);
		if(success) {
			System.out.println("Money successfully deposited.");	
		}
		else {
			System.out.println("Unable to withdraw money.");
		}
	}
	/*
	 * allows admin to withdraw money from a user's account
	 */
	public void adminWithdraw() {
		String username = scanUsername("Enter the username of the person whose account you wish to withdraw from.");
		int id = scanId("Enter the id of the account you wish to withdraw from.");
		double money = scanMoney("How much money do you wish to withdraw from this account?");
		boolean success = server.withdraw(username, id, money);
		if(success) {
			System.out.println("Successfully withdrew money from this account.");
		}
		else {
			System.out.println("Unable to execute the withdrawal.");
		}		
	}
	/*
	 * allows a customer to make an account transfer
	 * they have to indicate which account they wish to transfer from
	 * they have to indicate the username of the person whose account they wish to transfer to
	 * they have to indicate the account ID of the account of that person
	 * if they have input valid arguments and there are no problems such as too small account balance
	 * or an account is closed then it will go through
	 */
	public void initiateTransfer() {
		if(!server.hasAccount(username)) {
			System.out.println("You must have an account in order to make a transfer");
			return;
		}
		System.out.println("You may initiate a transfer from your account.");
		int initId = scanId("Please enter the ID of the account you wish to transfer from.");
		String recepient = scanUsername("Please enter the username of the person whose account you wish to transfer to.");
		int recipId = scanId("Please enter the ID of that person's account.");
		double money = scanMoney("How much do you wish to transfer?");

		boolean success = server.transferMoney(username, initId, recepient, recipId, money);
		if(success) {
			System.out.println("The transfer went through successfully.");
		}
		else {
			System.out.println("Unable to process transfer.");
		}
	}
	/*
	 * admin is able to initiate transfer between 2 accounts
	 */
	public void adminTransfer() {
		String initUsername = scanUsername("Please enter the username of the person whose account you wish to transfer money from");
		int initId = scanId("Please enter the ID of the account you wish to transfer money from. ");
		String recepUsername = scanUsername("Please enter the username of the person who you wish to transfer money into");
		int recepId = scanId("Please enter the id of the account you wish to transfer money into");
		double money = this.scanMoney("How much money do you wish to transfer?");
		boolean success = server.transferMoney(initUsername, initId, recepUsername, recepId, money);
		if(success) {
			System.out.println("Transfer successfully executed.");
		}
		else {
			System.out.println("Unable to complete transaction.");
		}
	}
	/*
	 * an admin can close an account
	 */
	public void closeAccount() {
		String victim = scanUsername("Please enter the username of the account you wish to close.");
		int recipId = scanId("Please enter the ID of that person's account");

		boolean success = server.closeAccount(victim, recipId);
		if(success) {
			System.out.println("Successfully close this account.");
		}
		else {
			System.out.println("Unable to close account.");
		}
	}
	/*
	 * abstracted this code because I was using it too much
	 */
	public int scanId(String message) {
		int recipId = -1;
		do {
			System.out.println(message);
			if (!scan.hasNextInt()) {
				scan.next();
			} else
				recipId = scan.nextInt();

		} while (recipId==-1);
		scan.nextLine(); //get rid of newline character
		return recipId;
	}
	/*
	 * abstracted this code because I was using it too much
	 */
	public String scanUsername(String message) {
		String username;
		do {
			System.out.println(message);

			username = scan.nextLine();
			
		} while (!server.hasWebAccount(username));
		return username;
	}
	/*
	 * abstracted this code because I was using it too much
	 */
	public double scanMoney(String message) {
		System.out.println(message);
		double money = 0;
		do {

			if (!scan.hasNextDouble()) {
				System.out.println("Please enter a valid amount.");
				scan.next();
			} else
				money = scan.nextDouble();

		} while (money < 0);
		scan.nextLine(); //get rid of newline character
		return money;
	}
	/*
	 * abstracted this code because I was using it too much
	 */
	public int scanChoice(int min, int max) {
		int choice = 0;
		do {

			if (!scan.hasNextInt()) {
				System.out.println("Please enter a valid number.");
				scan.next();
			} else
				choice = scan.nextInt();

		} while (choice > max || choice < min);
		scan.nextLine();  //get rid of newline character
		return choice;
	}
	/*
	 * allows a customer to apply for a joint account
	 * they must specify the username of the person who they wish to open a joint account with
	 * this means that person must already have registered with the bank
	 */
	public void applyForJointAccount() {
		String partner = scanUsername("What is the username of the person you wish to open a joint account with?");
		boolean success = server.applyForJointAccount(username, partner);
		if(success) {
			System.out.println("Successfully applied for join account.");
		}
		else {
			System.out.println("Unable to complete application.");
		}
	}
}
