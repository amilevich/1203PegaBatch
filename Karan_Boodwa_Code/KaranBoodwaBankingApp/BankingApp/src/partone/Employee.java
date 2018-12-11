package partone;

import java.util.ArrayList;

/**
 * Employee class Contains IO and functions related to employee abilities
 * 
 * @author karan
 *
 */
public class Employee extends User {

	// UserAuthorizer Singleton so the Employees can access user information
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();
	

	public Employee() {
		super();
	}

	public Employee(String username, String password) {
		super(username, password);
	}

	@Override
	public void menu() {
		// Input singleton used to get user input
		Input in = Input.getInputSingleton();

		boolean returnToMenu = true;

		while (returnToMenu) {

			System.out.println("Welcome back to The First Bank of Karan, valued employee!");
			System.out.println("1. Customers");
			System.out.println("2. Bank Accounts");
			System.out.println("3. View Pending Accounts");
			System.out.println("4. Approve/Deny Pending Accounts");
			System.out.println("5. Logout");
			
			// Get user input
			int choice = in.getInt();
			
			switch (choice) {
			// Customer Information:
			case 1:
				// Print the customers using the method in the UserAuthorizer, passing in employee credentials
				userAuth.printCustomers(getUsername(), getPassword());
				break;
			// Bank Account Information:
			case 2:
				// Print a list of bank accounts
				// Ask for a user to print account information:
				System.out.println("Enter a username to see bank account information");
				String user = in.get();
				
				// Lookup the users accounts and print the account information
				if( userAuth.usernameExists(user) ) {
					
				}
				else {
					System.out.println("Username not found");
				}
				
				
				break;
			// View Pending Accounts:
			case 3:
				// Get list of pending accounts from the user authorizer
				ArrayList<BankAccountApplication> apps = userAuth.getPendingApplications();
				if(apps.isEmpty()) {
					System.out.println("No pending applications.");
					break;
				}
				
				// Loops through the pending applications and prints 
				for(int i = 0; i < apps.size(); i++) {
					System.out.println(i+1 + ". " + apps.get(i));
				}
				break;
			// Approve/Deny Pending Accounts
			case 4:
				// Get list of pending accounts from the user authorizer
				ArrayList<BankAccountApplication> apps2 = userAuth.getPendingApplications();
				if(apps2.isEmpty()) {
					System.out.println("No pending applications.");
					break;
				}
				System.out.println("Enter the number of the application to Approve/Deny");
				
				
				// Loops through the pending applications and prints 
				for(int i = 0; i < apps2.size(); i++) {
					System.out.println(i+1 + ". " + apps2.get(i));
				}
				
				int appNum = in.getInt() - 1;
				
				// Check if application number is valid before printing
				if(appNum >= 0 && appNum < apps2.size()) {
					BankAccountApplication review = apps2.get(appNum);
					System.out.println(review);
					
					System.out.println("1. Approve");
					System.out.println("2. Deny");
					System.out.println("3. Return to menu");
					
					int choice2 = in.getInt();
					
					switch (choice2) {
					// Approve
					case 1:
						// Create bank account based on credentials in the application
						ArrayList<String> accHolders = review.getHolders();
						
						int accNum = 0;
						// Register the bank account based on the number of holders 
						// 1 = regular account
						// 2 = joint account
						if(accHolders.size() == 1) {
							accNum = tHandler.register(accHolders.get(0));
							System.out.println("Account created!");
						}
						else if(accHolders.size() == 2) {
							accNum = tHandler.registerJointAccount(accHolders.get(0), accHolders.get(1));
							System.out.println("Account created!");
						}
						else {
							System.out.println("Error processing application");
						}
						apps2.remove(appNum);
						
						// Update the customer accounts with their new account information
						for(String username : accHolders) {
							userAuth.getCustomer(username).addAccount(accNum);
						}
						break;
					// Deny
					case 2:
						// removing the application from the list of pending applications acts as a denial
						apps2.remove(appNum);
						break;
					// Return To Menu (aka do nothing)
					case 3:
						break;
					default:
						
						break;
					}
				}
				
				break;
			// Logout:
			case 5:
				System.out.println("Thank you for working with us. Have a great day!");
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}

		}

	}

}
