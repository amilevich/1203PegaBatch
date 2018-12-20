
public class Ownership {
	private int accountNumber;
	private int userID;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public Ownership(int accountNumber, int userID) {
		super();
		this.accountNumber = accountNumber;
		this.userID = userID;
	}
	public Ownership(BankUser user, BankAccount acc) {
		accountNumber = acc.getAccountNumber();
		userID = user.getId();
	}
	
}
