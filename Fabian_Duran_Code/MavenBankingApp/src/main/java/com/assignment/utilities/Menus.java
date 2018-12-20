package com.assignment.utilities;

import java.sql.SQLException;
import java.util.List;

import com.assignment.daoimpl.AccountsDAOImpl;
import com.assignment.exceptions.ActiveAccountException;
import com.assignment.exceptions.BadPasswordException;
import com.assignment.exceptions.BadSelectionException;
import com.assignment.exceptions.BadUsernameException;
import com.assignment.exceptions.InaccessibleAccountException;
import com.assignment.exceptions.NotYourAccountException;
import com.assignment.exceptions.OverdrawnException;
import com.assignment.persons.Customer;
import com.assignment.persons.Person;

public class Menus {//this will also be a singleton, because main will only need to run through one instance of menus and its objects
	
	private static Menus menu = new Menus();
	Input input = Input.getInput();
	private int userType = 0;//this will hold what kind of user is current on the system
	private String user_Name = "";//will hold the user's username
	private int userID;
	private int userAccountID = 0;//to hold current account number
	private int tempI = 0;
	private double tempD = 0.0;
	private String tempS = "";
	
	private Menus() {}//private constructor 
	
	public static Menus getMenu() {//create singleton object
		return menu;
	}
	
	public int getUserInt() {//to be used in Main method-direct to see if object will be Customer, Employee or Bank Admin
		return userType;
	}

	public String getUserString() {
		return user_Name;
	}

	//initial menu screen, its what users will see first
	public void initialPrompt(){
		if (Person.getLoaded() == false&&Customer.getLoaded()==false&&Account.getLoaded()==false&&AccountHolders.getLoaded()==false) {
			//load in the lists, for easier comparisons
			Customer.loadCustomerList();
			Person.loadPersonList();
			Account.loadAccountList();
			AccountHolders.loadAccountHolders();
			System.out.println("Loaded.");
		}
		
		System.out.println("What type of user are you?");
		System.out.println("1. Customer");
		System.out.println("2. Employee");
		System.out.println("3. Bank Admin");
		System.out.println("4. SuperUser");
		System.out.println("5. Quit");
		System.out.print("Enter a number: ");
		userType = input.inputInt();
		if (userType == 1||userType == 2||userType == 3||userType == 4) {
			System.out.println("Success!\n");
			loginPrompt();
		}
		else if (userType == 5) {
			Account.logout();
		}
		else {
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {initialPrompt();}
		}
	}
	
	//argument will differentiate between customer, employee, and bank admin
	public void loginPrompt () {//customer will have additional option
		tempS = "";//this will be the only instance where you will have to compare/need the password
		System.out.println("Please enter your username and password:\t\tEnter esc to quit");
		if (userType == 1)
			System.out.println("If you are new customer, please type new in username.");
		System.out.print("username: ");
		user_Name = new String(input.inputString());
		System.out.println();

		if (user_Name.equals("esc")) {//priority check, if user wants to quit
			Account.logout();
		}
		else if (userType == 1 && user_Name.equals("new")) {//secondary check, if new user go to new user screen
			System.out.println("New Customer Screen\n");
			newCustomer();
		}
		else {
			switch (userType) {//will check specific class hashmap based on int 
			case 1://for customers
				if(Person.checkUserMatch(user_Name,userType)) {//checks if username and usertype are a match
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Person.checkPassword(tempS)) {//if password is correct, take user to see their accounts
						System.out.println("Success!\n");
						accountsDisplay(user_Name);
					}
					else {
						try {
							throw new BadPasswordException();
						} catch (BadPasswordException e) {
							e.printStackTrace();
						}
						finally{loginPrompt();}//prompts user to try again
					}
				}
				else {
					try {
						throw new BadUsernameException();
					} catch (BadUsernameException e) {
						e.printStackTrace();
					}
					finally{loginPrompt();}//prompts user to try again
				}
			case 2://for employees
				if(Person.checkUserMatch(user_Name,userType)) {//checks if username and usertype are a match
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Person.checkPassword(tempS)) {//if password is correct, take user to see their accounts
						System.out.println("Success!\n");
						employeeActions();//employee Actions will show different things depending on user type
					}else {
						try {
							throw new BadPasswordException();
						} catch (BadPasswordException e) {
							e.printStackTrace();
						}
						finally{loginPrompt();}//prompts user to try again
						}
				}else {
					System.out.println("That is not a correct username. Please re-enter Username and Password.");
					loginPrompt();//prompts user to try again
				}
			case 3://for bank admins
				if(Person.checkUserMatch(user_Name,userType)) {//checks if username and usertype are a match
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Person.checkPassword(tempS)) {//if password is correct, take user to see their accounts
						System.out.println("Success!\n");
						accountsDisplay(user_Name);//employee Actions will show different things depending on user type
					}else {
						try {
							throw new BadPasswordException();
						} catch (BadPasswordException e) {
							e.printStackTrace();
						}
						finally{loginPrompt();}//prompts user to try again
					}
				}else {
					try {
						throw new BadUsernameException();
					} catch (BadUsernameException e) {
						e.printStackTrace();
					}
					finally{loginPrompt();}//prompts user to try again
				}
			case 4://for SuperUsers
				if(Person.checkUserMatch(user_Name,userType)) {//checks if username and usertype are a match
					System.out.print("pasword: ");//prompts for password input
					tempS = input.inputString();//takes in string
					System.out.println();
					if (Person.checkPassword(tempS)) {//if password is correct, take user to see their accounts
						System.out.println("Success!\n");
						employeeActions();
//change this-------------------->accountsDisplay(user_Name);//employee Actions will show different things depending on user type
					}else {
						try {
							throw new BadPasswordException();
						} catch (BadPasswordException e) {
							e.printStackTrace();
						}
						finally{loginPrompt();}//prompts user to try again
					}
				}else {
					try {
						throw new BadUsernameException();
					} catch (BadUsernameException e) {
						e.printStackTrace();
					}
					finally{loginPrompt();}//prompts user to try again
				}
			default:
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {loginPrompt();}
			}
		}
		
	}
	
	
	//to be used to store new usernames for customers, employees and BankAdmins should not create usernames through this screen for as it is a security risk
	public void newCustomer() {
		String userPassword = "";
		System.out.print("Please enter a username: ");
		user_Name = input.inputString();//verify it is string
		if (Person.checkUsername(user_Name)) {//if true, that username already exists
			try {
				throw new BadUsernameException();
			} catch (BadUsernameException e) {
				e.printStackTrace();
			}finally{
				System.out.println("That username is already taken, please enter a different username.");
				newCustomer();//would these be considered recursion?
			}
		}else {//username does not exists
			System.out.println("Success!\n");
			System.out.print("Please enter a password: ");
			userPassword = input.inputString();
			System.out.println("New Customer created!");//new line
			Person.addToUserDB(user_Name, userPassword, userType);
			accountsDisplay(user_Name);//directs to view customer accounts ->this will show just screen prompt as they do not have any accounts...
		}
	}
	
	
	//this will probably need to be looked at again...
	public void accountsDisplay (String username) {//to be accessed only by Customer or Bank Admin - Views all accounts of specificed customer
		String empTemp = "";//to hold string
		userID = Person.pullFromList(username).getPersonID();
		System.out.println("Here are the accounts for user: " + username+"\t\tEnter esc to logout");
		AccountHolders.viewCustomerAccounts(userID);
		if (userType==1) {
			System.out.println("Please enter 0 to make a new account: ");
		}
		if (userType == 2||userType == 3||userType == 4) {
			System.out.print("Enter back to go back: ");//only prompts employee to go back
			empTemp = input.inputString();
			if (empTemp.equals("back")) {
				System.out.println("Success!\n");
				employeeActions();
			}else if (empTemp.equals("esc")) {
				Account.logout();
			}
			else {
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Incorrect input, please enter valid input: ");
				accountsDisplay(username);
				}
			}
		} else {
			System.out.print("Enter account number: ");
			tempS = input.inputString();
			if (tempS.equals("esc")) {
				Account.logout();
			}else {
				userAccountID = Integer.parseInt(tempS);
			}
			if (userAccountID == 0) {//directs to make a new account
				System.out.println("Success!\n");
				newAccountPrompt();
			}else if (Customer.checkCustomer(userID, userAccountID)) {//directs to account actions on that account
				int t1 = Account.pullFromList(userAccountID).getOpen();
				int t2 = Account.pullFromList(userAccountID).getApproved();
				if(t1==1&&t2==1) {
					System.out.println("Success!\n");
					accountActions(userAccountID);
				}else {
					try {
						throw new InaccessibleAccountException();
					} catch (InaccessibleAccountException e) {
						e.printStackTrace();
					}//forces user to stay in this menu until they submit correct response
					finally {System.out.println("Please select a different account");
					accountsDisplay(username);
					}
				}
			}else {
				try {
					throw new NotYourAccountException();
				} catch (NotYourAccountException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("That account number does not belong to this customer!");
				accountsDisplay(username);
				}
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
		int newAID;//account ID for new account
		System.out.println();
		switch (tempI) {
		
		case 1://make a regular account
			Account.addToAccountDB();
			newAID = Account.getNewAccountID();
			Customer.addToAccountHolder(userID, newAID);
			System.out.println("Request for new account has been submitted. \nIt will be accessible once it has been approved.");
			accountsDisplay(user_Name);//go back to accounts display
		case 2://make a joint account
			System.out.println("Please enter the user name of the other account holder: ");//joint accounts take two people
			tempS = input.inputString();
			if (Person.checkUserMatch(tempS, 1)) {
				Account.addToAccountDB();
				newAID = Account.getNewAccountID();
				int otherID = Person.pullFromList(tempS).getPersonID();//gets the userID number of other customer, to be add to account to database
				Customer.addToAccountHolder(userID, newAID);//add account to submitters accounts
				Customer.addToAccountHolder(otherID, newAID);//add account to joint user's accounts
				System.out.println("Request for new account has been submitted. \n It will be accessible once it has been approved.");
				accountsDisplay(user_Name);//go back to accounts display
			}else {
				try {
					throw new BadUsernameException();
				} catch (BadUsernameException e) {
					e.printStackTrace();
				}finally{System.out.println("Please enter a valid username!");
				newAccountPrompt();
				}
			}
		case 3:
			Account.logout();
		default:
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {System.out.println("Please enter a valid number!");
			newAccountPrompt();
			}
		}
		
	}
	//for customers and bank admins only
	public void accountActions(int userAccount) {//will show additional option for Bank Admin
		tempD = Account.pullFromList(userAccount).getBalance();
		int tempA = 0;//used to for transfer method
		System.out.println("Account: " + userAccount + " has a balance of $" + tempD +"\nWhat would you like to do?");
		if (userType == 1|| userType == 4)//only customers and Superusers have access to this
			System.out.println("0. Delete Account");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Transfer");
		System.out.println("4. Go Back");
		System.out.println("5. Logout");
		if (userType == 3||userType==4)//secret menu! for bank admins!
			System.out.println("6. Cancel Account");
		System.out.println("Please enter a number: ");
		tempI = input.inputInt();
		System.out.println();
		switch(tempI) {
			case 0://delete account
				if (Account.pullFromList(userAccount).getBalance()==0.0) {
					AccountsDAOImpl aimpl = new AccountsDAOImpl();
					try {
						aimpl.deleteAccount(userAccount);
					} catch (SQLException e) {
						System.out.println("deleteAccount! Database Connection Error!");
						e.printStackTrace();
					}
					System.out.println("Account deleted.");
					accountsDisplay(user_Name);
				}else {
					try {
						throw new ActiveAccountException();
					} catch (ActiveAccountException e) {
						e.printStackTrace();
					}//forces user to stay in this menu until they submit correct response
					finally {System.out.println("An active account cannot be deleted.\nPlease withdraw all money from this account if you wish to delete it.");
					accountActions(userAccount);
					}
				}								
			case 1://deposit money
				System.out.print("Note: The system will only read the first two values after the decimal point!\nDeposit amount: ");
				tempD =input.inputDouble();
				Account.deposit(userAccountID,tempD);
				System.out.println("Success! $" + tempD + " has been deposited to this account.");
				accountActions(userAccount);
			case 2://withdraw money
				System.out.print("Note: The system will only read the first two values after the decimal point!\nWithdraw amount: ");
				tempD =input.inputDouble();
				if (tempD > Account.pullFromList(userAccount).getBalance()) {//if attempting to withdraw more money than the balance
					try {
						throw new OverdrawnException();
					} catch (OverdrawnException e) {
						e.printStackTrace();
					}//forces user to stay in this menu until they submit correct response
					finally {System.out.println("This account cannot be overdrawn!");
					}
					
				}else {
					Account.withdraw(userAccountID, tempD);
					System.out.println("Success! $" + tempD + " has been withdrawn from this account.");
				}
				accountActions(userAccount);
			case 3:
				System.out.print("Please enter account number of transfer recipient: ");
				tempA = input.inputInt();
				System.out.println();
				if (Account.pullFromList(tempA).getID() == tempA) {//if account exists
					System.out.print("Note: The system will only read the first two values after the decimal point!\nTransfer amount: ");
					tempD = input.inputDouble();
					System.out.println();
					if (tempD > Account.pullFromList(userAccount).getBalance()) {//if attempting to withdraw more money than the balance
						try {
							throw new OverdrawnException();
						} catch (OverdrawnException e) {
							e.printStackTrace();
						}//forces user to stay in this menu until they submit correct response
						finally {System.out.println("This account cannot be overdrawn!");
						}
					}else {
						Account.transfer(userAccount, tempA, tempD);
						System.out.println("Success! $" + tempD + " has been transferred to " + tempA +".");
					}

				}else {
					try {
						throw new BadSelectionException();
					} catch (BadSelectionException e) {
						e.printStackTrace();
					}//forces user to stay in this menu until they submit correct response
					finally {System.out.println("That account number does not exist!");
					}
				}
				accountActions(userAccount);
			case 4:
				accountsDisplay (user_Name);
			case 5:
				Account.logout();
			case 6:
				if (userType==1||userType==2) {//if user is a customer or employee
					try {
						throw new BadSelectionException();
					} catch (BadSelectionException e) {
						e.printStackTrace();
					}//forces user to stay in this menu until they submit correct response
					finally {System.out.println("Invalid selection! Please choose a valid number.");
					accountActions(userAccount);
					}
				}else {
					Account.setOpen(userAccount, 0);
					System.out.println("Account has been cancelled.");
					accountActions(userAccount);
				}
			default:
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Invalid selection! Please choose a valid number.");
				}
				accountActions(userAccount);
		}	
	}
	
	public void employeeActions() {//will show additional options for Bank Admin
		System.out.println("What would you like to do?");
		System.out.println("1. View Customer and Account information");
		System.out.println("2. View all pending accounts");
		System.out.println("3. Logout");
		if (userType==4) {
			System.out.println("4. View all Users");
			System.out.println("5. Create new User");
			System.out.println("6. Update User's username");
			System.out.println("7. Delete User");
		}
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
		case 4:
			if(userType !=4) {
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Invalid selection! Please choose a valid number.");
				employeeActions();
				}
			}else
				viewUsers();
		case 5:
			if(userType !=4) {
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Invalid selection! Please choose a valid number.");
				employeeActions();
				}
			}else
				createUser();
		case 6:
			if(userType !=4) {
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Invalid selection! Please choose a valid number.");
				employeeActions();
				}
			}else
				updateUser();
		case 7:
			if(userType !=4) {
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Invalid selection! Please choose a valid number.");
				employeeActions();
				}
			}else
				deleteUser();
		default:
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {System.out.println("Pleaes enter a valid number!");
			employeeActions();
			}
		}	
	}
	
	public void viewPendingAccounts() {
		System.out.println("Here is the list of pending accounts:\t\tEnter 0 to logout");
		AccountHolders.viewPendingAccounts();//done, don't touch anymore
		System.out.print("Enter account number to review account: ");
		tempI = input.inputInt();
		Account a = Account.pullFromList(tempI);
		if (tempI == 0)//this should be fine since, no account should have an ID of 0
			Account.logout();
		else if (tempI == a.getID() && a.getApproved()==0)//ensure only unapproved accounts can be selected
			decidePendingAccount(tempI);
		else {
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {System.out.println("Invalid number! Please a select an account number from this list.");
			viewPendingAccounts();
			}
		}
	}
	
	public void decidePendingAccount(int userAccount) {//for approving accounts
		System.out.println("What would you like to do with Account " + userAccount+"?");
		System.out.println("1. Approve");
		System.out.println("2. Deny");
		System.out.println("3. Logout");
		System.out.print("Enter a number: ");
		tempI = input.inputInt();
		System.out.println();
		switch(tempI) {
		
		case 1:
			Account.setApproved(userAccount, 1);
			System.out.println(userAccount + "is approved!");
			viewPendingAccounts();
		case 2:
			//account is already not approved just cancelling account now too
			Account.setOpen(userAccount, 0);//to deny an account is kinda also to cancel an account, right?
			System.out.println(userAccount + "is denied");
			viewPendingAccounts();
		case 3:
			Account.logout();
		default:
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {System.out.println("Invalid number! Please enter a valid number.");
			decidePendingAccount(userAccount);
			}			
		}
	}
	
	public void customerSearch() {//to be used by Employee & Bank Admins
		System.out.println("Please enter a customer username.\t\tEnter esc to logout");
		System.out.print("Username: ");
		tempS = input.inputString();
		if(tempS.equals("esc"))//no customer should have this username. God help us all if they do.
			Account.logout();
		else if (Person.checkUserMatch(tempS, 1)) {//if the username exists and they're listed as a customer
			user_Name = tempS;//to be used in other functions automatically, employee/BA user names don't matter, all that is needed is userInt for that
			accountsDisplay(user_Name);//shows Customer info
		}else {
			try {
				throw new BadUsernameException();
			} catch (BadUsernameException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {System.out.println("That username does not exist.");
			customerSearch();			
			}
		}
	}
	
	public void viewUsers() {
		List <Person>tempList = Person.getList();
		System.out.println("List of all Users:\nEnter back to go back.");
		for (int i = 0; i < tempList.size();i++) {
			System.out.println(tempList.get(i).getUsername());
		}
		System.out.print("Enter back here: ");
		tempS = input.inputString();
		if (tempS.equals("back"))
			employeeActions();
		else {
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}//forces user to stay in this menu until they submit correct response
			finally {System.out.println("Invalid selection! Please try again.");
			viewUsers();
			}	
		}
	}
	public void createUser() {
		String userPassword = "";
		int tempType =1;
		System.out.print("Create new User:\t\tEnter back to go back.\nPlease enter a username: ");
		tempS = input.inputString();//verify it is string
		if (tempS.equals("back"))
			employeeActions();
		if (Person.checkUsername(tempS)) {//if true, that username already exists
			try {
				throw new BadUsernameException();
			} catch (BadUsernameException e) {
				e.printStackTrace();
			}finally{
				System.out.println("That username is already taken, please enter a different username.");
				createUser();//would these be considered recursion?
			}
		}else {//username does not exists
			System.out.println("Success!\n");
			System.out.print("Please enter a password: ");
			userPassword = input.inputString();
			System.out.println("What type of user?");
			System.out.println("1. Customer");
			System.out.println("2. Employee");
			System.out.println("3. BankAdmin");
			System.out.print("Enter a number: ");
			tempI = input.inputInt();
			switch (tempI) {
			case 1:
				tempType = 1;
				break;
			case 2:
				tempType = 2;
				break;
			case 3:
				tempType = 3;
				break;
			default:
				try {
					throw new BadSelectionException();
				} catch (BadSelectionException e) {
					e.printStackTrace();
				}//forces user to stay in this menu until they submit correct response
				finally {System.out.println("Invalid number! Start over.");
				createUser();
				}	
			}
			System.out.println("New User created!");//new line
			Person.addToUserDB(user_Name, userPassword, tempType);
			employeeActions();
		}
	}
	public void updateUser() {
		System.out.print("\nEnter the username of the User you wish to update:\t\tEnter back to go back.");
		tempS = input.inputString();
		String temp2 = "";
		System.out.println();
		if (tempS.equals("back")) {
			employeeActions();
		}else if(Person.checkUsername(tempS)) {
			System.out.print("Enter a new username: ");
			temp2 = input.inputString();
			System.out.println();
			if(Person.checkUsername(temp2)) {
				try {
					throw new BadUsernameException();
				} catch (BadUsernameException e) {
					e.printStackTrace();
				}finally{
					System.out.println("That username is already taken, please enter a different username.");
					updateUser();//would these be considered recursion?
				}
			}else {
				Person.updateUserDB(tempS, temp2);
				System.out.println("Username has been updated to " + temp2 + "!");
				employeeActions();
			}
		}else {
			try {
				throw new BadSelectionException();
			} catch (BadSelectionException e) {
				e.printStackTrace();
			}finally{
				System.out.println("Invalid selection. Please enter a valid username or back.");
				updateUser();//would these be considered recursion?
			}
		}
	}
	public void deleteUser() {
		System.out.print("Enter the name of the User you wish to delete:");
		tempS = input.inputString();
		System.out.println();
		if (Person.checkUsername(tempS)) {
			Person.deleteUserDB(tempS);
			System.out.println("User: "+ tempS+ " has been deleted.");
			employeeActions();
		}
		else {
			try {
				throw new BadUsernameException();
			} catch (BadUsernameException e) {
				e.printStackTrace();
			}
			finally{
				System.out.println("Please enter a valid username.");
				deleteUser();
			}
		}
			
	}
}
