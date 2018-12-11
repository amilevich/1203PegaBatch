package partone;

import partone.Transaction.operation;

/**
 * Admins are a more specialized class of Employees which have more abilities
 * @author karan
 *
 */

public class Admin extends Employee {
	
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();
	
	public Admin() {
		super();
	}
	
	public Admin(String username, String password) {
		super(username, password);
	}
	
	@Override
	public void menu() {
		Input in = Input.getInputSingleton();
		
		boolean returnToMenu = true;
		
		while(returnToMenu) {
			System.out.println("1. Admin Operations");
			System.out.println("2. Employee Operations");
			System.out.println("3. Logout");
			
			int choice = in.getInt();
			
			switch (choice) {
			case 1:
				adminMenu();
				break;
			case 2:
				super.menu();
				break;
			case 3:
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection.");
				break;
			}
		}
		
	}
	
	
	/**
	 * menu to house admin operations
	 */
	public void adminMenu() {
		Input in = Input.getInputSingleton();
		boolean returnToMenu = true;
		
		while(returnToMenu) {
			System.out.println("Top Secret Admin Menu. Shhhhh");
			System.out.println("1. Admin Withdraw");
			System.out.println("2. Admin Deposit");
			System.out.println("3. Admin Transfer");
			System.out.println("4. Cancel Account");
			System.out.println("5. Exit Admin Menu");
			
			int choice = in.getInt();
			
			switch (choice) {
			// Admin Withdraw
			case 1:
				adminWithdraw();
				break;
			// Admin Deposit
			case 2:
				adminDeposit();
				break;
			// Admin Transfer
			case 3:
				adminTransfer();
				break;
			// Cancel Account
			case 4:
				cancelAccount();
				break;
			// Exit Admin Menu
			case 5:
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}
	}
	
	// Menu methods, modularized:
	
	public void adminWithdraw() {
		Input in = Input.getInputSingleton();
		System.out.println("Enter the account number");
		
		// Get account to withdraw from
		int accFrom = in.getInt();
		
		// Account number entered does not exist
		if(!tHandler.accountExists(accFrom)) {
			System.out.println("Error. Invalid account number.");
			return;
		}
		
		// Get amount to withdraw
		System.out.println("Enter amount to withdraw");
		double amount = in.getDouble();
		
		// If the amount is greater than the balance, end transaction there
		if( amount < 0 || amount > tHandler.getBalance(accFrom)) {
			System.out.println("Error. Not enough funds in selected account");
			return;
		}
		
		// Package transaction and send it to transaction handler to handles
		Transaction transaction = new Transaction(operation.WITHDRAW, accFrom, -1, amount, getUsername());
		boolean success = tHandler.processTransaction(transaction);
		
		if(success) {
			System.out.println("Transaction Approved");
		}
		else {
			System.out.println("Transaction Declined");
		}
	}
	
	public void adminDeposit() {
		Input in = Input.getInputSingleton();
		System.out.println("Enter the account number");
		
		// Get account to deposit into
		int accTo= in.getInt();
		
		// Account number entered does not exist
		if(!tHandler.accountExists(accTo)) {
			System.out.println("Error. Invalid account number.");
			return;
		}
		
		// Get amount to deposit
		System.out.println("Enter amount to deposit");
		double amount = in.getDouble();
		
		if(amount < 0) {
			System.out.println("Error. Invalid amount");
			return;
		}
		
		// Package transaction and send it to transaction handler to handles
		Transaction transaction = new Transaction(operation.DEPOSIT, -1, accTo, amount, getUsername());
		boolean success = tHandler.processTransaction(transaction);
		
		if(success) {
			System.out.println("Transaction Approved");
		}
		else {
			System.out.println("Transaction Declined");
		}
	}
	
	public void adminTransfer() {
		Input in = Input.getInputSingleton();
		System.out.println("Enter the account to withdraw from");
		int accFrom = in.getInt();
		
		// See if account exists:
		if(!tHandler.accountExists(accFrom)) {
			System.out.println("Error: Invalid account number");
			return;
		}
		
		System.out.println("Enter the account to deposit into");
		int accTo = in.getInt();
		
		// See if account exists:
		if(!tHandler.accountExists(accTo)) {
			System.out.println("Error: Invalid account number");
		}
		
		System.out.println("Enter amount to transfer:");
		double amount = in.getDouble();
		
		// Check that amount is not negative and that there's enough funds in the account it's being withdrawn from
		if(amount < 0 || amount > tHandler.getBalance(accFrom)) {
			System.out.println("Error: Not enough funds to transfer");
		}
		
		// All checks passed, prepare transaction and send to transaction handler to process
		Transaction transaction = new Transaction(operation.TRANSFER, accFrom, accTo, amount, getUsername());
		boolean success = tHandler.processTransaction(transaction);
		
		if(success) {
			System.out.println("Transaction Approved");
		}
		else {
			System.out.println("Transaction Declined");
		}
		
	}
	/**
	 * Admin method used to cancel a bank account
	 */
	public void cancelAccount() {
		Input in = Input.getInputSingleton();
		System.out.println("**WARNING USE WITH CAUTION**");
		System.out.println("Enter account number to cancel");
		int acc = in.getInt();
		
		if(!tHandler.accountExists(acc)) {
			System.out.println("Invalid Account Number");
			return;
		}
		System.out.println("Are you sure you want to cancel account " + acc + " ?");
		System.out.println("1. Cancel");
		System.out.println("2. Return to Menu");
		
		int choice = in.getInt();
		switch (choice) {
		case 1:
			boolean success = tHandler.cancelAccount(acc, getUsername());
			if(success) {
				System.out.println("Account Canceled");
			}
			else {
				System.out.println("Error. Account Not Canceled.");
			}
			break;
		case 2:
			System.out.println("Returning to Menu...");
			break;
		default:
			System.out.println("Invalid selection.");
			System.out.println("Returning to Menu...");
			break;
		}
		
	}
}
