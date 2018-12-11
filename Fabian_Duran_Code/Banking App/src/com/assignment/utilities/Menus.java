package com.assignment.utilities;

import java.util.ArrayList;

import com.assignment.persons.Customer;
import com.assignment.persons.Employee;
import com.assignment.persons.Person;

public class Menus {//this will also be a singleton, because main will only need to run through one instance of menus and its objects
	
	private static Menus menu = new Menus();
	Input input = Input.getInput();
	private int userInt = 0;//all these 'user' variables will have accessors that main can use to direct user depending on input
	private String userString = "";
	int userAccount = 0;//to hold account number
	int tempI = 0;
	double tempD = 0.0;
	String tempS = "";
	
	private Menus() {}//private constructor 
	
	public static Menus getMenu() {//create singleton object
		return menu;
	}
	
	public int getUserInt() {//to be used in Main method-direct to see if object will be Customer, Employee or Bank Admin
		return userInt;
	}

	public String getUserString() {
		return userString;
	}

	//initial menu screen, its what users will see first
	public void initialPrompt(){
		if (Person.getloaded() == false&&Customer.getcloaded()==false&&Account.getaload()==false) {
			Person.loadEmployees();
			Customer.cloaded();
			Customer.loadarrays();
			Account.aloaded();
		}
		
		System.out.println("What type of user are you?");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Bank Admin");
		System.out.println("4. Quit");
		System.out.print("Enter a number: ");
		userInt = input.inputInt();
		if (userInt == 1||userInt == 2||userInt == 3) {
			loginPrompt();
		}
		else if (userInt == 4) {
			Account.logout();
		}
		else {
			System.out.println("Please enter a correct number!");//forces user to stay in this menu until they submit correct response
			initialPrompt();
		}
	}
	
	//argument will differentiate between customer, employee, and bank admin
	public void loginPrompt () {//customer will have additional option
		tempS = "";//this will be the only instance where you will have to compare/need the password
		System.out.println("Please enter your username and password:\t\tEnter esc to quit");
		if (userInt == 1)
			System.out.println("If you are new customer, please type new in username.");
		System.out.print("username: ");
		userString = new String(input.inputString());
		System.out.println();
		//testing occured here
		System.out.println(userString);
		System.out.println(Customer.pullFromCHash(userString));
		System.out.println("something");
		//test ends here
		if (userString.equals("esc")) {//priority check, if user wants to quit
			Account.logout();
		}
		else if (userInt == 1 && userString.equals("new")) {//secondary check, if new user go to new user screen
			newCustomer();
		}
		else {
			switch (userInt) {//will check specific class hashmap based on int 
			case 1://for customers
				if(Customer.checkCUsername(userString)) {//if username exists in hashmap, proceed
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Customer.checkCPassword(tempS)) {//if password is correct, take user to see their accounts
						accountsDisplay(userString);
					}
					else {
						System.out.println("That is not the correct password. Please re-enter Username and Password.");
						loginPrompt();//prompts user to try again
					}
				}
				else {
					System.out.println("That is not a correct username. Please re-enter Username and Password.");
					loginPrompt();//prompts user to try again
				}
			case 2://for employees
				if(Person.checkUsername(userString)&&Person.pullFromHash(userString).getUserType()==2) {//if username exists in hashmap, proceed
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Person.checkPassword(tempS)) {//if password is correct, take user to see their accounts
						employeeActions();//employee Actions will show different things depending on user type
					}else {
						System.out.println("That is not the correct password. Please re-enter Username and Password.");
						loginPrompt();//prompts user to try again
						}
				}else {
					System.out.println("That is not a correct username. Please re-enter Username and Password.");
					loginPrompt();//prompts user to try again
				}
			case 3://for bank admins
				if(Person.checkUsername(userString)&&Person.pullFromHash(userString).getUserType()==3) {//if username exists in hashmap, proceed
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Person.checkPassword(tempS)) {//if password is correct, take user to see their accounts
						accountsDisplay(userString);//employee Actions will show different things depending on user type
					}else {
						System.out.println("That is not the correct password. Please re-enter Username and Password.");
						loginPrompt();//prompts user to try again
					}
				}else {
					System.out.println("That is not a correct username. Please re-enter Username and Password.");
					loginPrompt();//prompts user to try again
				}
			}
		}
		
	}
	
	
	//to be used to store new usernames for customers, employees and BankAdmins should not create usernames through this screen for as it is a security risk
	public void newCustomer() {
		String userPassword = "";
		System.out.print("Please enter a username: ");
		userString = input.inputString();//verify it is string
		if (Customer.checkUsername(userString)) {//if true, that username already exists
			System.out.println("That username is already taken, please enter a different username.");
			newCustomer();//would these be considered recursion?
		}else {//username does not exists
			System.out.print("\nPlease enter a password: ");
			userPassword = input.inputString();
			System.out.println();//new line
			ArrayList<Integer> customerAccounts = new ArrayList<>();
			Customer c = new Customer(userString, userPassword, userInt, customerAccounts);//create a new customer object
			Customer.addtoHash(c, userString);//adds it to customer hashmap
			accountsDisplay(userString);//directs to view customer accounts ->this will show just screen prompt as they do not have any accounts...
		}
	}
	
	
	//this will probably need to be looked at again...
	public void accountsDisplay (String username) {//to be accessed only by Customer or Bank Admin - Views all accounts of specificed customer
		String empTemp = "";//to hold string
		System.out.println("Here are the accounts for user: " + username);
		if (userInt==1) {
			System.out.println("Please enter 0 to make a new account: ");
		}
		Customer c = Customer.pullFromCHash(username);
		c.viewCustomerAccounts(username);//this will probably have to be reviewed
		if (userInt == 2) {
			System.out.print("Enter back to go back.");//only prompts employee to go back
			empTemp = input.inputString();
			if (empTemp.equals("back")) {
				employeeActions();
			}else {
				System.out.println("Incorrect input, please enter valid input: ");
				accountsDisplay(username);
			}
		} else {
			System.out.print("Enter account number: ");
			userAccount = input.inputInt();
			if (userAccount == 0) {//directs to make a new account
				newAccountPrompt();
			}else if (c.checkAccount(userAccount)) {//directs to account actions on that account
				if(Account.pullFromHash(userAccount).isOpen) {
					accountActions(userAccount);
				}else {
					System.out.println("That account has been cancelled, please select a different account");
					accountsDisplay(username);
				}
			}else {
				System.out.println("That account number does belong to this customer!");
				accountsDisplay(username);
			}
		}
		
	}
	
	
	public void newAccountPrompt() {//accessed by Customers only...
		System.out.println("What kind of account would you like to make?:");
		System.out.println("1. Regular");
		System.out.println("2. Joint");
		System.out.println("3. Logout");
		System.out.print("Enter a number: ");
		tempI = input.inputInt();
		System.out.println();
		switch (tempI) {
		
		case 1://make a regular account
			Account a = new Account();
			a.setID();
			a = new Account(a.getID(), true, false, 'r', 0.00);//gives Id number, isOpen:true, isApproved:false, regular account, balance of 0
			Account.addtoHash(a, a.getID());//adds account to hash
			Customer.pullFromCHash(userString).addAccount(a.getID());//adds account to customer arraylist
			System.out.println("Request for new account has been submitted. \n It will display once it has been approved.");
			accountsDisplay(userString);//go back to accounts display
		case 2://make a joint account
			System.out.println("Please enter the user name of the other account holder: ");//joint accounts take two people
			tempS = input.inputString();
			if (Customer.checkCHash(tempS)) {
				Account b = new Account();
				b.setID();
				b = new Account(b.getID(), true, false, 'j', 0.00);//gives Id number, isOpen:true, isApproved:false, joint account, balance of 0
				Account.addtoHash(b, b.getID());//adds account to hash
				Customer.pullFromCHash(userString).addAccount(b.getID());//adds account to customer arraylist
				System.out.println("Request for new account has been submitted. \n It will display once it has been approved.");
				accountsDisplay(userString);//go back to accounts display
			}else {
				System.out.println("That username is incorrect!");
				newAccountPrompt();
			}
		case 3:
			Account.logout();
		default:
			System.out.println("Please enter a valid number!");
			newAccountPrompt();
		}
		
	}
	//for customers and bank admins only
	public void accountActions(int userAccount) {//will show additional option for Bank Admin
		tempD = Account.pullFromHash(userAccount).getBalance();
		int tempA = 0;//used to for transfer method
		System.out.println("Account: " + userAccount + "has a balance of $" + tempD +"\nWhat would you like to do?");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Transfer");
		System.out.println("4. Go Back");
		System.out.println("5. Logout");
		if (userInt == 3)//secret menu! for bank admins!
			System.out.println("6. Cancel Account");
		System.out.println("Please enter a number: ");
		tempI = input.inputInt();
		System.out.println();
		switch(tempI) {
		
			case 1://deposit money
				System.out.print("Note: The system will only read the first two values after the decimal point!\nDeposit amount: ");
				tempD =input.inputDouble();
				Account.pullFromHash(userAccount).deposit(tempD);
				System.out.println("Success! $" + tempD + "has been deposited to this account.");
				accountActions(userAccount);
			case 2://withdraw money
				System.out.print("Note: The system will only read the first two values after the decimal point!\nWithdraw amount: ");
				tempD =input.inputDouble();
				if (tempD > Account.pullFromHash(userAccount).getBalance()) {//if attempting to withdraw more money than the balance
					System.out.println("This account cannot be overdrawn!");
				}else {
					Account.pullFromHash(userAccount).withdraw(tempD);
					System.out.println("Success! $" + tempD + "has been withdrawn from this account.");
				}
				accountActions(userAccount);
			case 3:
				System.out.print("Please enter account number of transfer recipient: ");
				tempA = input.inputInt();
				System.out.println();
				if (Account.pullFromHash(tempA).getID() == tempA) {//if account exists
					System.out.print("Note: The system will only read the first two values after the decimal point!\nTransfer amount: ");
					tempD = input.inputDouble();
					System.out.println();
					if (tempD > Account.pullFromHash(userAccount).getBalance()) {//if attempting to withdraw more money than the balance
						System.out.println("This account cannot be overdrawn!");
					}else {
						Account.pullFromHash(userAccount).transfer(tempD, tempA);
						System.out.println("Success! $" + tempD + "has been transferred to " + tempA +".");
					}

				}else {
					System.out.println("That account number does not exist!");
				}
				accountActions(userAccount);
			case 4:
				accountsDisplay (userString);
			case 5:
				Account.logout();
			case 6:
				if (userInt==1) {//if user is a customer
					System.out.println("Invalid selection! Please choose a valid number.");
					accountActions(userAccount);
				}else {
					Account.pullFromHash(userAccount).setOpen(false);
					System.out.println("Account has been cancelled.");
					accountActions(userAccount);
				}
			default:
				System.out.println("Invalid selection! Please choose a valid number.");
				accountActions(userAccount);
		}
		System.out.println();
		System.out.print("Enter a number: ");
		
	}
	
	public void employeeActions() {//will show additional options for Bank Admin
		System.out.println("What would you like to do?");
		System.out.println("1. View Customer and Account information");
		System.out.println("2. View all pending accounts");
		System.out.println("3. Logout");
		System.out.println("Please enter a number: ");
		tempI = input.inputInt();
		System.out.println();
		switch(tempI) {
		case 1:
			customerSearch();
		case 2:
			viewPendingAccounts();
		case 3:
			Account.logout();
		default:
			System.out.println("Pleaes enter a valid number!");
			employeeActions();
		}	
	}
	
	public void viewPendingAccounts() {
		System.out.println("Here is the list of pending accounts:\t\tEnter 0 to logout");
		Employee.viewPendingAccounts();
		System.out.print("Enter account number to review account: ");
		tempI = input.inputInt();
		if (tempI == 0)//this should be fine since, no account should have an ID of 0
			Account.logout();
		else if (tempI == Account.pullFromHash(tempI).getID() && Account.pullFromHash(tempI).isApproved==false)//ensure only unapproved accounts can be selected
			decidePendingAccount(tempI);
		else {
			System.out.println("Invalid number! Please a select an account number from this list.");
			viewPendingAccounts();
		}
	}
	
	public void decidePendingAccount(int userAccount) {//for approving accounts
		System.out.println("Here is the account:");
		System.out.println(Customer.pullFromIDHash(userAccount) + Account.pullFromHash(userAccount).toString());
		System.out.println("1. Approve");
		System.out.println("2. Deny");
		System.out.println("3. Logout");
		System.out.print("Enter a number: ");
		tempI = input.inputInt();
		System.out.println();
		switch(tempI) {
		
		case 1:
			Account.pullFromHash(userAccount).setApproved(true);
			System.out.println(userAccount + "is approved!");
			viewPendingAccounts();
		case 2:
			Account.pullFromHash(userAccount).setApproved(false);
			Account.pullFromHash(userAccount).setOpen(false);//to deny an account is kinda also to cancel an account, right?
			System.out.println(userAccount + "is denied");
			viewPendingAccounts();
		case 3:
			Account.logout();
		default:
			System.out.println("Invalid number! Please enter a valid number.");
			decidePendingAccount(userAccount);
			
		}
	}
	
	public void customerSearch() {//to be used by Employee & Bank Admins
		System.out.println("Please enter a customer username.\t\tEnter esc to logout");
		System.out.print("Username: ");
		tempS = input.inputString();
		if(tempS.equals("esc"))//no customer should have this username. God help us all if they do.
			Account.logout();
		else if (Customer.pullFromCHash(tempS).getUsername().equals(tempS)) {
			userString = tempS;//to be used in other functions automatically, employee/BA user names don't matter, all that is needed is userInt for that
			accountsDisplay(userString);//shows Customer info
		}else {
			System.out.println("That username does not exist.");
			customerSearch();
		}
	}
	
}
