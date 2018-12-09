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
	 */
	public void register(String username) {
		// Generate a new unused account number
		int accNumber = generateAccountNumber();
		
		BankAccount ba = new BankAccount();
		ba.addHolder(username);
		
		bankAccounts.put(accNumber, ba);
	}
	
	/**
	 * Registers a new joint account into the bankAccounts hashmap
	 */
	public void registerJoint(String username, String username2) {
		// Generate a new unused account number
		int accNumber = generateAccountNumber();
		
		BankAccount ba = new BankAccount();
		ba.addHolder(username);
		ba.addHolder(username2);
		
		bankAccounts.put(accNumber, ba);
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
			accNum = rand.nextInt(10000000);
		}
		while( bankAccounts.containsKey(accNum));
		
		return accNum;
		
	}
	
	
	public static void main(String[] args) {
		

	}

}
