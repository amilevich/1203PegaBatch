package com.revature.jdbcbank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.revature.daoimpls.AccountDaoImpl;
import com.revature.daoimpls.User_Account_JunctDaoImpl;
import com.revature.pojos.Account;
import com.revature.pojos.Transaction;

/**
 * Middleman between the accountDaoImpl and the transaction requests
 * 
 * @author karan
 *
 */
public class TransactionHandler {

	// UserAuthorizer instance:
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();

	// reference to the singleton instance
	private static TransactionHandler tHandler = new TransactionHandler();

	// AccountDaoImpl used to actually communicate with database
	private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
	
	private User_Account_JunctDaoImpl user_bank_junction = new User_Account_JunctDaoImpl();

	/*
	 * variable used in account number generation to begin, account numbers can
	 * range from 0-10,000,000 if there are too many accounts this range can be
	 * increased. Can also be dynamically changed based on needs through code
	 */
	private static int accountRange = 10000000;

	// Default constructor
	private TransactionHandler() {}

	/**
	 * Gets the singleton instance of the transaction handler
	 * 
	 * @return singleton instance
	 */
	public static TransactionHandler getTHandler() {
		return tHandler;
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
		} while (accountExists(accNum));

		return accNum;

	}

	/**
	 * accountExists(int)
	 * 
	 * @return - true if account with given account number exists false otherwise
	 * @param accNum - bank account number to check
	 */
	public boolean accountExists(int accNum) {
		Account account = accountDaoImpl.getAccountById(accNum);
		if (account == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * processTransaction processes an incoming deposit/withdrawal (simple transaction) request
	 * using the information within the transaction parameter
	 * 
	 * 
	 * @param transaction
	 * @return true if transaction was processed successfully false if transaction
	 *         was unsuccessful
	 */
	public boolean processTransaction(Transaction transaction) {
		//System.out.println("Processing Transaction");

		// Determine type of transaction to process
		switch (transaction.getOp()) {
		case DEPOSIT:
			return processDeposit(transaction);
		// break;
		case WITHDRAW:
			return processWithdrawal(transaction);
		// break;
		default:
			break;
		}

		return false;
	}

	/**
	 * Private helper function for processing a withdrawal
	 * @param transaction
	 * @return true or false depending on if the transaction went through or not(respectively)
	 */
	private boolean processWithdrawal(Transaction transaction) {
		// Prevent nullptr exceptions
		if (transaction == null) {
			System.out.println("null");
			return false;
		}
		// Step 1: Retrieve account details through accountdaoimpl
		Account account = accountDaoImpl.getAccountById(transaction.getAccNum());
		// Check if account number is valid:
		if (account == null) {
			System.out.println("invalid acc num");
			return false;
		} else {
			// Check if withdrawal amount is valid (positive and <= amount in account):
			if (transaction.getAmount() < 0 || (transaction.getAmount() > account.getBalance())) {
				return false;
			}
			// All checks passed, attempt withdrawal
			if (accountDaoImpl.changeBalance(transaction.getAccNum(), (-1 * transaction.getAmount()))) {
				return true;
			}
			// If withdrawal didn't go through, return false

		}

		// By default, returns false
		return false;
	}

	/**
	 * Private helper function for processing a withdrawal
	 * @param transaction
	 * @return true or false depending on if the transaction went through or not(respectively)
	 */
	private boolean processDeposit(Transaction transaction) {
		// Prevent nullptr exceptions
		if (transaction == null) {
			return false;
		}
		// Step 1: Retrieve account details through accountdaoimpl
		Account account = accountDaoImpl.getAccountById(transaction.getAccNum());
		// Check if account number is valid:
		if (account == null) {
			return false;
		} else {
			// Check if deposit amount is valid (positive):
			if (transaction.getAmount() <= 0) {
				return false;
			}
			// All checks passed, attempt withdrawal
			if (accountDaoImpl.changeBalance(transaction.getAccNum(), (transaction.getAmount()))) {
				return true;
			}
			// If withdrawal didn't go through, return false (aka default return below)

		}

		// By default, returns false
		return false;
	}
	
	
	
	/**
	 * Creates an account and sets the specified username as an owner
	 * @param username
	 * @return 1 if account could not be created, id of account created if it was
	 */
	public int createAccount(String username) {
		// If a given username doesn't exist, return false
		if(!userAuth.usernameExists(username)){
			return -1;
		}
		
		// Step 1: generate a valid account number:
		int newAccountNum = generateAccountNumber();
		while(accountExists(newAccountNum)) {
			newAccountNum = generateAccountNumber();
		}
		
		// Step 2: Create an account with the valid number generated
		if(accountDaoImpl.createAccount(newAccountNum)) {
			// Step 3: lookup user_id of given username
			Integer user_id = userAuth.getUserId(username);
			if(user_id == null) {
				return -1;
			}
			
			// Step 4: add user as an owner of the account (check that relation doesn't exist first just in case)
			if(!user_bank_junction.isOwner(user_id, newAccountNum)) {
				if(user_bank_junction.addUserAccount(user_id, newAccountNum)) {
					return newAccountNum;
				}
			}
			
		}
		
		// Return false by default, only return true if all conditions are satisfied
		return -1;
		
	}
	
	/**
	 * Adds a given user as an owner of the account provided if they aren't already one
	 * @param accNum
	 * @param username
	 * @return 
	 */
	public boolean addOwner(int accNum, String username) {
		Integer user_id = userAuth.getUserId(username);
		// If the user or account don't exist, return false:
		if(user_id == null || !accountExists(accNum)) {
			return false;
		}
		if(!user_bank_junction.isOwner(user_id, accNum)) {
			if(user_bank_junction.addUserAccount(user_id, accNum)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	/**
	 * Returns an account with the given id if one exists
	 * @param accId
	 * @return an account object if one exists with the given id, null if one does not exist
	 */
	public Account getAccountById(int accId) {
		if(accountExists(accId)) {
			return accountDaoImpl.getAccountById(accId);
		}
		else {
			return null;
		}
	}
	
	/**
	 * Gets a list of accounts with the given username as as holder
	 * @param username
	 * @return a list of accounts belonging to a given username (including list of account holders)
	 */
	public List<Account> getAccountsByUsername(String username){
		List<Integer> accountIds = accountDaoImpl.getAccountsByUsername(username);
		List<Account> accounts = new ArrayList<Account>();
		if(accountIds == null) {
			return accounts;
		}
	
		for(int i : accountIds) {
			accounts.add(getAccountById(i));
		}
		return accounts;
	}

	public boolean cancelAccount(int acc) {
		return accountDaoImpl.deleteAccount(acc);
	}

}
