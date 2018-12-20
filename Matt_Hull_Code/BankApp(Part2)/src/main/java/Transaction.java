import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Calendar;
/*
 * Object class that maps to the transaction table in the database
 * recipientAccountNumber is not used at this time, left in for future functionality
 * method usersFullName is getting the full name of the user when talking to user in menu easily
 */
public class Transaction {
	private int userID;
	private int accountNumber;
	private int recipientAccountNumber;
	private double amount;
	private double resultingBalance;
	private Timestamp time;
	private String usersFullName;
	
	public Transaction(int userID, int accountNumber, double amount, double resultingBalance) {
		super();
		this.userID = userID;
		this.accountNumber = accountNumber;
		this.amount = amount;
		time = new Timestamp(System.currentTimeMillis());
		this.resultingBalance=(resultingBalance);
		recipientAccountNumber = -1;
	}
	public Transaction(int userID, int accountNumber, double amount, double resultingBalance, int recipAccNumber) {
		super();
		this.userID = userID;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.resultingBalance=(resultingBalance);
		time = new Timestamp(System.currentTimeMillis());
		this.setRecipientAccountNumber(recipAccNumber);
	}
	public Transaction() {
		
	}
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String trans ="";
		trans += "Account Number: " + accountNumber;
		trans += ", Amount: $" + amount;
		trans += ", Resulting Balance: $" + df.format(resultingBalance);
		trans += ", Initiator: " + this.usersFullName;
		trans += ", " + time;
		
		return trans;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getRecipientAccountNumber() {
		return recipientAccountNumber;
	}
	public void setRecipientAccountNumber(int recipientAccountNumber) {
		this.recipientAccountNumber = recipientAccountNumber;
	}
	public void setUsersFullName(String firstname, String lastname) {
		String fname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
		String lname = lastname.substring(0,1).toUpperCase()+ lastname.substring(1);
		this.usersFullName = fname + " " + lname;
	}
	public String getUsersFullName() {
		return usersFullName;
	}
	public double getResultingBalance() {
		return resultingBalance;
	}
	public void setResultingBalance(double resutlingBalance) {
		this.resultingBalance = resutlingBalance;
	}
	
	

}
