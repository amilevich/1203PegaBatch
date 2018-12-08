package partone;

public class Customer extends User{

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
		System.out.println("Welcome to the first bank of Karan!");
	}
	
}
