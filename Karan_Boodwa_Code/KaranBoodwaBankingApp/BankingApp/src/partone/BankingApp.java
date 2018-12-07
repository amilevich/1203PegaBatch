package partone;

import java.util.Scanner;

/**
 * Main driver class for the Banking Application. Handles initialization and
 * initial I/O as well as connections between Users and Accounts
 * 
 * @author Karan
 *
 */
public class BankingApp {

	/*
	 * UserAuthorizer: responsible for processing registration & login requests
	 * provides User object on successful login.
	 * Houses the User 'database'
	 */
	// UserAuthorizer userAuth = UserAuthorizer.getUserAuth();

	/*
	 * TransactionHandler: responsible for processing transactions
	 * Houses the bank account 'database'
	 */
	// TransactionHandler tHandler = TransactionHandler.getTHandler();
	
	/*
	 * menu() function handles I/O and interaction with above
	 * structures
	 */
	public static void mainMenu() {
		System.out.println("Welcome to the First Bank of Karan!");
		
		// Scanner instance to handle console I/O
		Scanner sc = new Scanner(System.in);
		
		// loop to return users back to the 'main menu' after banking operations are done
		boolean running = true;
		while(running) {
			/*
			 * login & registration view
			 */
			int choice = 0;
			
			
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("Please select one of the options above");
			
			
			running = false;
		}
		sc.close();
	}

	public static void main(String[] args) {
		// Call the menu method which serves as the I/O handler for the app
		mainMenu();

	}

}
