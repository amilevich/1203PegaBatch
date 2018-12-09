package partone;

import java.util.ArrayList;

public class Customer extends User {

	// Data members
	private ArrayList<Integer> bankAccounts = new ArrayList<Integer>();

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
				printAccs();
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
		// TODO: implement
		/*
		 * Menu flow: 
		 * ask for acc number 
		 * check that acc number is valid & owned by customer 
		 * ask for amount to deposit check that amount is positive (valid)
		 * prepare transaction object 
		 * send deposit to transaction handler to process
		 */
		
		System.out.println("Depositing");

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
		System.out.println("Withdrawing");

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
		System.out.println("Applying");
		// TODO: stub
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

	public static void main(String[] args) {
		Customer c = new Customer();
		c.menu();
	}

	@Override
	public String toString() {
		return "Customer: " + getUsername() + ", " + printAccs();
	}
	
}
