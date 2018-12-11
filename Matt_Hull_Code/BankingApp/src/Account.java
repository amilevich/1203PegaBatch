import java.text.DecimalFormat;

public class Account {
	private double balance;
	private boolean open;
	private int id; //this keeps track of which account this is for the person
	//IE a person can have multiple accounts, this is used to keep track of each individual account
	
	public Account(int id) {
		balance = 0;
		open = true;
		this.id = id;
	}
	
	public Account(double balance) {
		this.balance = balance;
		open = true;
	}
	public String toString() {
		String acstatus;
		if (open) {
			acstatus = "Open";
		}
		else {
			acstatus = "Closed";
		}
		DecimalFormat df = new DecimalFormat("#.##");
		return("ID: " + id + ", Account Status: " + acstatus + ", Balance: $" + df.format(balance));
	}
	public int getId() {
		return id;
	}
	public void setID(int idnum) {
		id = idnum;
	}
	public double getBalance() {
		return balance;
	}
	public boolean isOpen() {
		return open;
	}
	public void activateAccount() {
		open = true;  //Should I do something when someone tries to activate an already open acc?
	}
	public void closeAccount() {
		open = false; //Should do something when ^^?
	}
	public boolean withdraw(double amount) {  //method returns true/false whether transaction proceeded
		//Blanket statement of invalid input? 
		//Print to console why it's wrong here?
		//Print out at point of method call?
		//Think I'll check for nonsense withdraw requests at method call
		//I'll check if it's possible in account here
		if(balance >= amount && open) {
			balance-=amount;
			return true;
			//return amount;
		}
		return false;
		
	}
	public boolean deposit(double amount) {
		if(amount<0 || !open) {
			return false;
		}
		balance += amount;
		return true;
	}
	
	public boolean canDeposit() {  //maybe accounts can be closed or have a hold or some such
		if(open)
			return true;
		return false;
	}
	public boolean canWithdraw (double amount) {
		if(open&&balance>=amount) {
			return true;
		}
		return false;
	}
	//didnt end up implementing this
	public boolean transfer(double amount, Account acc) {
		if(acc.canDeposit()&&this.canWithdraw(amount)) {	//not sure what to do about when this can't happen
			if(withdraw(amount)) {					//I'll just say transaction not possible or something
				acc.deposit(amount);
				return true;
			}
		}
		return false;
	}

}
