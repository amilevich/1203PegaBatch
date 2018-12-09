package partone;

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
	
	/* variable used in account number generation
	 * to begin, account numbers can range from 0-10,000,000
	 * if there are too many accounts this range can be increased 
	 */
	private static int accountRange = 10000000;
	
	// reference to the singleton instance
	private static TransactionHandler tHandler = new TransactionHandler();

	// Default constructor
	private TransactionHandler() {
		
	}

	/**
	 * Gets the singleton instance of the transaction handler
	 * @return singleton instance
	 */
	public static TransactionHandler getTHandler() {
		return tHandler;
	}
	
	/**
	 * processTransaction processes an incoming deposit/withdrawal/transfer
	 * request using the information within the transaction parameter
	 * 
	 * throws exceptions when given invalid data
	 * 
	 * @param transaction
	 * @return true if transaction was processed successfully
	 *         false if transaction was unsuccessful
	 */
	public boolean processTransaction(Transaction transaction) {
		System.out.println("Processing Transaction");
		return true;
	}
	
	/**
	 * Registers a new account into the bankAccounts hashmap
	 * @return - account number of account created
	 */
	public int register(String username) {
		// Generate a new unused account number
		int accNumber = generateAccountNumber();
		
		BankAccount ba = new BankAccount();
		ba.addHolder(username);
		
		bankAccounts.put(accNumber, ba);
		
		return accNumber;
	}
	
	/**
	 * Registers a new joint account into the bankAccounts hashmap
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
	 * @return - an account number not in use, between 
	 */
	private int generateAccountNumber() {
		// Random number generator courtesy of java.utl.Random
		Random rand = new Random();
		int accNum = 0;
		do {
			// Generates a random integer in the range [0,10000000)
			accNum = rand.nextInt(accountRange);
		}
		while( bankAccounts.containsKey(accNum));
		
		return accNum;
		
	}
	
	/**
	 * accountExists(int)
	 * @return - true if account with given account number exists
	 * 			 false otherwise
	 * @param accNum - bank account number to check
	 */
	public boolean accountExists(int accNum) {
		return bankAccounts.containsKey(accNum) ? true : false;
	}
	
	/**
	 * getBalance(int)
	 * returns the balance of the account in question if the account exists
	 * @param accNum - bank account number to query
	 * @return balance of account number accNum if account exists, -1 if account does not exist
	 */
	public double getBalance(int accNum) {
		return bankAccounts.containsKey(accNum) ? (bankAccounts.get(accNum)).getBalance() : -1;
	}
	
	public static void main(String[] args) {
		

	}

}
