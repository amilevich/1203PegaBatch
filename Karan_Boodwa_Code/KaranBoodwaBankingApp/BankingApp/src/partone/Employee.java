package partone;

/**
 * Employee class Contains IO and functions related to employee abilities
 * 
 * @author karan
 *
 */
public class Employee extends User {

	// UserAuthorizer Singleton so the Employees can access user information
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();
	
	private TransactionHandler tHandler = TransactionHandler.getTHandler();

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
			System.out.println("4. Logout");
			
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
				
				break;
			// Logout:
			case 4:
				System.out.println("Thank you for working with us. Have a great day!");
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}

		}

	}

}
