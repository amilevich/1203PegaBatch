package partone;

public class Transaction {
	public static enum operation{DEPOSIT, WITHDRAW, TRANSFER};
	private operation op;
	private int accFrom;
	private int accTo;
	private String username;
	private double amount;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getAccFrom() {
		return accFrom;
	}
	public void setAccFrom(int accFrom) {
		this.accFrom = accFrom;
	}
	public int getAccTo() {
		return accTo;
	}
	public void setAccTo(int accTo) {
		this.accTo = accTo;
	}

	public operation getOp() {
		return op;
	}
	public void setOp(operation op) {
		this.op = op;
	}

	public Transaction(operation op, int accFrom, int accTo, String username) {
		this.op = op;
		this.accFrom = accFrom;
		this.accTo = accTo;
		this.username = username;
	}
	
	public Transaction() {
		
	}
	
	@Override
	public String toString() {
		return "Transaction [op=" + op + ", accFrom=" + accFrom + ", accTo=" + accTo + ", user=" + username + "]";
	}
	
	
}