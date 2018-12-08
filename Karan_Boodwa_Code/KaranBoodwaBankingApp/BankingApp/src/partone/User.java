package partone;

/**
 * Abstract user class that encompasses data and functionality shared between
 * different types of users
 * 
 * @author Karan
 *
 */
public abstract class User {

	private String username = "";
	private String password = "";

	// TODO Reference to TransactionHandler to actually process any user transactions

	// Default constructor
	public User() {

	}

	// Non-Default constructor
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * menu() is the custom user menu i/o that should be implemented in all
	 * subclasses provides a specialized user experience
	 */
	public abstract void menu();

	// Getters and Setters:

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}