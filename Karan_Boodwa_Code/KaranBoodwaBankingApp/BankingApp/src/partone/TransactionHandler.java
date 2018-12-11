package partone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * TransactionHandler singleton that processes all transactions and account
 * queries
 * 
 * @author Karan
 *
 */
public class TransactionHandler {

	/**
	 * HashMap that maps bank account numbers to the account objects
	 */
	private HashMap<Integer, BankAccount> bankAccounts = new HashMap<Integer, BankAccount>();

	// List of transactions that have been processed
	private ArrayList<Transaction> transactionHistory = new ArrayList<Transaction>();

	// UserAuthorizer instance:
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	/*
	 * variable used in account number generation to begin, account numbers can
	 * range from 0-10,000,000 if there are too many accounts this range can be
	 * increased.
	 * Can also be dynamically changed based on needs through code
	 */
	private static int accountRange = 10000000;

	// reference to the singleton instance
	private static TransactionHandler tHandler = new TransactionHandler();

	// Default constructor
	private TransactionHandler() {

	}

	/**
	 * Gets the singleton instance of the transaction handler
	 * 
	 * @return singleton instance
	 */
	public static TransactionHandler getTHandler() {
		return tHandler;
	}

	/**
	 * processTransaction processes an incoming deposit/withdrawal/transfer request
	 * using the information within the transaction parameter
	 * 
	 * 
	 * @param transaction
	 * @return true if transaction was processed successfully false if transaction
	 *         was unsuccessful
	 */
	public boolean processTransaction(Transaction transaction) {
		System.out.println("Processing Transaction");

		// Determine type of transaction to process
		switch (transaction.getOp()) {
		case DEPOSIT:
			return processDeposit(transaction);
		// break;
		case WITHDRAW:
			return processWithdrawal(transaction);
		// break;
		case TRANSFER:
			return processTransfer(transaction);
		// break;
		default:
			break;
		}

		return true;
	}

	/**
	 * private helper function to process deposits
	 * 
	 * @param transaction
	 * @return True if transaction was processed successfully, false otherwise
	 */
	private boolean processDeposit(Transaction transaction) {

		// Populate local variables with info from transaction 'form'
		int accTo = transaction.getAccTo();
		Double amount = transaction.getAmount();
		String username = transaction.getUsername();

		// boolean flag for admin transactions
		boolean adminTransaction = false;

		// Check that account exists and the amount is valid
		if (!bankAccounts.containsKey(accTo) || amount < 0) {
			return false;
		}

		// Store a reference to the bank account in question
		BankAccount acc = bankAccounts.get(accTo);

		// See if the user is not a holder of the account:
		if (!(acc.getHolders().contains(username))) {

			// See if the user is an admin, making this an admin transaction
			if (userAuth.isAdmin(username)) {
				Input in = Input.getInputSingleton();
				System.out.println("Please enter your password to authorize transaction");
				String password = in.get();

				// Password is incorrect. Decline transaction
				if (userAuth.login(username, password) == null) {
					System.out.println("INVALID PASSWORD");
					return false;
				} else {
					adminTransaction = true;
				}
			} else {
				System.out.println("Unauthorized Transaction. (Not an account holder)");
				return false;
			}

		}

		// If none of the checks above have failed, process the transaction
		acc.deposit(amount);

		// Add transaction to transaction history
		transactionHistory.add(transaction);
		return true;
	}

	/**
	 * private helper function to process deposits
	 * 
	 * @param transaction
	 * @return True if transaction was processed successfully, false otherwise
	 */
	private boolean processWithdrawal(Transaction transaction) {
		// Populate local variables with info from transaction 'form'
		int accFrom = transaction.getAccFrom();
		Double amount = transaction.getAmount();
		String username = transaction.getUsername();

		// boolean flag for admin transactions
		boolean adminTransaction = false;

		// Check that account exists and the amount is valid
		if (!bankAccounts.containsKey(accFrom) || amount < 0) {
			return false;
		}

		// Store a reference to the bank account in question
		BankAccount acc = bankAccounts.get(accFrom);

		// Check that amount being withdrawn is greater than or equal to the balance in
		// the account
		if (amount >= acc.getBalance()) {
			return false;
		}

		// See if the user is not a holder of the account:
		if (!(acc.getHolders().contains(username))) {

			// See if the user is an admin, making this an admin transaction
			if (userAuth.isAdmin(username)) {
				Input in = Input.getInputSingleton();
				System.out.println("Please enter your password to authorize transaction");
				String password = in.get();

				// Password is incorrect. Decline transaction
				if (userAuth.login(username, password) == null) {
					System.out.println("INVALID PASSWORD");
					return false;
				} else {
					adminTransaction = true;
				}
			} else {
				return false;
			}

		}

		// If none of the checks above have failed, process the transaction
		acc.withdraw(amount);

		// Add transaction to transaction history
		transactionHistory.add(transaction);
		return true;
	}

	/**
	 * private helper function to process deposits
	 * 
	 * @param transaction
	 * @return True if transaction was processed successfully, false otherwise
	 */
	private boolean processTransfer(Transaction transaction) {
		// Populate local variables with info from form
		int accFrom = transaction.getAccFrom();
		int accTo = transaction.getAccTo();
		double amount = transaction.getAmount();
		String username = transaction.getUsername();

		boolean adminTransaction = false;

		// ensure that both accounts exist:
		if (!bankAccounts.containsKey(accFrom) || !bankAccounts.containsKey(accTo)) {
			return false;
		}

		// Get the accounts in question
		BankAccount acc1 = bankAccounts.get(accFrom);
		BankAccount acc2 = bankAccounts.get(accTo);

		// Ensure that acc1 has enough money to transfer
		if (amount > acc1.getBalance()) {
			return false;
		}

		// See if the user is an admin, making this an admin transaction
		if (userAuth.isAdmin(username)) {
			Input in = Input.getInputSingleton();
			System.out.println("Please enter your password to authorize transaction");
			String password = in.get();

			// Password is incorrect. Decline transaction
			if (userAuth.login(username, password) == null) {
				System.out.println("INVALID PASSWORD");
				return false;
			} else {
				adminTransaction = true;
			}
		}

		// ensure that this is an admin transaction OR that the user is a holder on both
		// accounts before proceeding
		if (adminTransaction || (acc1.getHolders().contains(username) && acc2.getHolders().contains(username))) {
			acc1.withdraw(amount);
			acc2.deposit(amount);
			// Add transaction to transaction history
			transactionHistory.add(transaction);
		}

		return false;
	}

	/**
	 * Registers a new account into the bankAccounts hashmap
	 * 
	 * @return - account number of account created
	 */
	public int register(String username) {
		// Generate a new unused account number
		int accNumber = generateAccountNumber();

		BankAccount ba = new BankAccount();
		ba.addHolder(username);
		ba.setAccountNumber(accNumber);
		bankAccounts.put(accNumber, ba);
		
		
		// Checking to ensure the account was successfully hashed
		System.out.println(bankAccounts.get(accNumber).toString());
		return accNumber;
	}

	/**
	 * Registers a new joint account into the bankAccounts hashmap
	 * 
	 * @return - account number of account created
	 */
	public int registerJointAccount(String username, String username2) {
		// Generate a new unused account number
		int accNumber = generateAccountNumber();

		BankAccount ba = new BankAccount();
		ba.addHolder(username);
		ba.addHolder(username2);

		bankAccounts.put(accNumber, ba);

		return accNumber;
	}

	/**
	 * private helper function to generate an account number not in use
	 * 
	 * @return - an account number not in use, between
	 */
	private int generateAccountNumber() {
		// Random number generator courtesy of java.utl.Random
		Random rand = new Random();
		int accNum = 0;
		do {
			// Generates a random integer in the range [0,10000000)
			accNum = rand.nextInt(accountRange);
		} while (bankAccounts.containsKey(accNum));

		return accNum;

	}

	/**
	 * accountExists(int)
	 * 
	 * @return - true if account with given account number exists false otherwise
	 * @param accNum - bank account number to check
	 */
	public boolean accountExists(int accNum) {
		return bankAccounts.containsKey(accNum) ? true : false;
	}

	/**
	 * getBalance(int) returns the balance of the account in question if the account
	 * exists
	 * 
	 * @param accNum - bank account number to query
	 * @return balance of account number accNum if account exists, -1 if account
	 *         does not exist
	 */
	public double getBalance(int accNum) {
		return bankAccounts.containsKey(accNum) ? (bankAccounts.get(accNum)).getBalance() : -1;
	}
	
	
	/**
	 * cancelAccount(int)
	 * cancels the bank account with the provided account number
	 * @param account account number to cancel
	 */
	boolean cancelAccount(int account, String username) {
		
		// Return false if the account doesn't exist
		if(!accountExists(account)) {
			return false;
		}
		
		
		// First check user credentials:
		if (userAuth.isAdmin(username)) {
			Input in = Input.getInputSingleton();
			System.out.println("Please enter your password to authorize account cancelation");
			String password = in.get();

			// Password is incorrect. Decline cancellation
			if (userAuth.login(username, password) == null) {
				System.out.println("INVALID PASSWORD");
				return false;
			} else {
				// Admin authenticated, proceed with cancellation
				// get account in question:
				BankAccount acc = bankAccounts.get(account);
				
				// Remove account from every holder's list of accounts
				for(String holder : acc.getHolders()) {
					if(userAuth.usernameExists(holder)) {
						Customer cust = userAuth.getCustomer(holder);
						cust.removeAccount(account);
					}
				}
				// Remove bank account from hashmap
				bankAccounts.remove(account);
				
				return true;
				
				
			}
		} else {
			return false;
		}
		
	}
	
	
	
	public static void main(String[] args) {

	}

}
