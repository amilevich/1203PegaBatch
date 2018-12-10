package partone;

import java.util.ArrayList;

import partone.Transaction.operation;

public class Customer extends User {

	// Data members
	private ArrayList<Integer> bankAccounts = new ArrayList<Integer>();
	
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	/**
	 * Default constructor
	 */
	public Customer() {
		super();
	}

	/**
	 * Non-default constructor
	 */
	public Customer(String username, String password) {
		// calls superclass's 2 parameter constructor
		super(username, password);

	}

	/**
	 * Initial menu for the Customer
	 */
	@Override
	public void menu() {

		// used to get user input
		Input input = Input.getInputSingleton();

		// boolean flag used to exit menu:
		boolean returnToMenu = true;
		System.out.println("Welcome back to the first bank of Karan, " + getUsername() + "!");

		while (returnToMenu) {
			System.out.println("Main Menu");
			System.out.println("1. Apply for Bank Account");
			System.out.println("2. View Account(s)");
			System.out.println("3. Withdraw");
			System.out.println("4. Deposit");
			System.out.println("5. Transfer");
			System.out.println("6. Logout");

			int choice = input.getInt();
			switch (choice) {

			// Apply for account:
			case 1:
				apply();
				break;
			// View Accounts:
			case 2:
				System.out.println(printAccs());
				break;
			// Withdraw:
			case 3:
				this.withdraw();
				break;
			// Deposit:
			case 4:
				this.deposit();
				break;
			// Transfer:
			case 5:
				this.transfer();
				break;
			// Logout:
			case 6:
				System.out.println("Thank you for banking with us. Have a great day!");
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}

	}

	// Transaction methods:

	private void deposit() {
		
		// If the user has no accounts, say so and return them to the menu that brought them here
		if(bankAccounts.isEmpty()) {
			System.out.println("You have no account to deposit into. Apply for one today!");
			return;
		}
		
		/*
		 * Menu flow: 
		 * ask for acc number 
		 * check that acc number is valid & owned by customer 
		 * ask for amount to deposit check that amount is positive (valid)
		 * prepare transaction object 
		 * send deposit to transaction handler to process
		 */
		
		boolean returnToMenu = true;
		Input in = Input.getInputSingleton();
		while(returnToMenu) {
			System.out.println("Deposit");
			System.out.println("Select account:");
			
			// Note: list index is 1 more than loop index
			// i declared and initialized outside of loop to be used after to display a return to main menu option
			int i = 0;
			for(i = 0; i < bankAccounts.size(); i++) {
				System.out.println(i+1 + ". " + bankAccounts.get(i));
			}
			int returnToMenuChoice = i+1;
			
			System.out.println(returnToMenuChoice + ". Return to Main Menu");
			
			// offset by 1 to account for different number displayed to user
			int accountChoice = in.getInt() - 1;
			
			// If they chose to return to the main menu, do so before checking anything else
			if(accountChoice == returnToMenuChoice) {
				System.out.println("Returning to Main Menu...");
				returnToMenu = false;
				break;
			}
			
			
			// Check if they selected an invalid entry on the list and refresh current menu
			if(accountChoice < 0 || accountChoice > bankAccounts.size()-1) {
				System.out.println("Invalid account selected");
				continue;
			}
			
			// List option is valid, prepare a deposit transaction
			else {
				
				// Get the account number from the customers list of accounts
				int accountNumber = bankAccounts.get(accountChoice);
				
				// Get amount to deposit from user:
				System.out.println("Enter amount to deposit");
				Double depositAmount = in.getDouble();
				
				if(depositAmount < 0) {
					System.out.println("Error. Invalid deposit amount entered.");
				}
				
				else {
					// Send transaction to the transaction handler to be processed
					boolean success = tHandler.processTransaction(new Transaction(operation.DEPOSIT,-1, accountNumber, getUsername()));
					
					if(success) {
						System.out.println("Transaction approved");
					}
					else {
						System.out.println("Transaction declined");
					}
				}
				
				
			}
			
		}

	}

	private void withdraw() {
		// TODO: implement
		/*
		 * Menu flow: 
		 * ask for acc number 
		 * check that acc number is valid & owned by customer ask for amount to withdraw 
		 * check against amount in account to see if its valid 
		 * prepare transaction object 
		 * send withdrawal to transaction handler to process
		 */
		System.out.println("Withdrawal");
		

	}

	private void transfer() {
		// TODO: implement

		/*
		 * Menu Flow: 
		 * ask for account 1 
		 * check that acc number is valid & owned by customer 
		 * ask for account 2 
		 * check that acc number is valid & owned by customer
		 * ask for transfer amt 
		 * check that it's positive and that there's enough in acc 1 to transfer
		 * technically speaking, a transfer of funds is a withdrawal followed by a deposit
		 */
		System.out.println("Transferring");
	}

	private void apply() {
		
		Input in = Input.getInputSingleton();
		System.out.println("Applying for a Bank Application");
		BankAccountApplication app = new BankAccountApplication(getUsername());
		
		boolean returnToMenu = true;
		
		while(returnToMenu) {
			System.out.println("Is this a joint account?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			int choice = in.getInt();
			
			switch (choice) {
			case 1:
				System.out.println("Enter the username of the second account holder");
				String secondHolder = in.get();
				if(userAuth.usernameExists(secondHolder)) {
					app.addHolder(secondHolder);
					returnToMenu = false;
				}
				else {
					System.out.println("Specified Username does not exist.");
				}
				break;
			case 2:
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}
		
		// Submits the bank application
		if( userAuth.submitApplication(app) == true) {
			System.out.println("Application submitted!");
		}
		else {
			System.out.println("Error submitting application.");
		}
		
		
	}

	private String printAccs() {
		if(bankAccounts.isEmpty()) {
			return "No Accounts";
		}
		StringBuilder s = new StringBuilder();
		s.append("Accounts: ");
		for(int i : bankAccounts) {
			s.append(i+" ");
		}
		return s.toString();
	}
	
	
	public void addAccount(Integer accNum) {
		bankAccounts.add(accNum);
	}
	
	public void removeAccount(Integer accNum) {
		bankAccounts.remove(accNum);
	}

	public static void main(String[] args) {
		Customer c = new Customer();
		c.bankAccounts.add(c.tHandler.register(c.getUsername()));
		c.deposit();
		
	}

	@Override
	public String toString() {
		return "Customer: " + getUsername() + ", " + printAccs();
	}
	
	
	
}
