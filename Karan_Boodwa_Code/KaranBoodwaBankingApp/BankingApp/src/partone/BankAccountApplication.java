package partone;

import java.util.ArrayList;

/**
 * BankAccountApplication
 * class to hold the information required for a bank account
 * @author karan
 *
 */
public class BankAccountApplication {
	
	// Proposed account holders
	// Currently that is all that is required to submit a bank account application
	// This account is meant to serve as an extensible application 
	// in case more information is needed in the future
	private ArrayList<String> holders = new ArrayList<String>();
	
	/**
	 * Default Constructor
	 */
	public BankAccountApplication() {
		
	}
	
	/**
	 * non-default constructor
	 * @param username
	 */
	public BankAccountApplication(String username) {
		holders.add(username);
	}
	
	/**
	 * adds a username to the list of holders
	 * @param username
	 */
	public void addHolder(String username) {
		holders.add(username);
	}
	
	
	
	// getter for the list of holders
	public ArrayList<String> getHolders(){
		return holders;
	}
	
	
	/**
	 * returns a string containing the usernames of the account holders
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Holders: ");
		
		// enhanced for loop to print the entries of the holders ArrayList
		for(String i : holders) {
			sb.append(i+" ");
		}
		
		return sb.toString();
	}
}
