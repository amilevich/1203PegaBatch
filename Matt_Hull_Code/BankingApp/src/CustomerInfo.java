import java.util.ArrayList;

public class CustomerInfo {
	private String firstName;
	private String lastName;
	private String username;
	private boolean applied;
	private ArrayList<Account> accounts;
	
	public CustomerInfo(String firstName, String lastName, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		accounts = new ArrayList<Account>();
	}
	@Override
	public String toString() {
		String info = "Customer Info:\n";
		info += "Name: " + firstName + " " + lastName + "\n";
		info += "Username: " + username + "\n";
		if(applied) {
			info += "Open Account Application.\n";
		}
		else {
			info += "No Open Account Application\n";
		}
		if(accounts.isEmpty()) {
			info += "No Open Accounts";
		}
		else {
			info += "Accounts: ";// + accounts;
			for (Account ac : accounts) {
				info += "\n" + ac;
			}
		}
		return info;
	}
	public CustomerInfo(String firstName, String lastName, String username,ArrayList<Account> accs) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		accounts = accs;

	}
	public CustomerInfo(String firstName, String lastName, String username,ArrayList<Account> accs, boolean appli) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		accounts = accs;
		applied = appli;

	}
	public CustomerInfo(String firstName, String lastName, String username, boolean appli) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		applied = appli;
		accounts = new ArrayList<Account>();

	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void addAccount(Account acc) {
		accounts.add(acc);
	}
	public boolean isApplied() {
		return applied;
	}
	public void setApplied(boolean applied) {
		this.applied = applied;
	}
}
