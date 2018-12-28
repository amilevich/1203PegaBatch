package partone;

public class Transaction {
	private enum operation{DEPOSIT, WITHDRAWL, TRANSFER};
	private operation op;
	private int accFrom;
	private int accTo;
	private User user;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public operation getOp() {
		return op;
	}
	public void setOp(operation op) {
		this.op = op;
	}

	public Transaction(operation op, int accFrom, int accTo, User user) {
		this.op = op;
		this.accFrom = accFrom;
		this.accTo = accTo;
		this.user = user;
	}
	
	public Transaction() {
		
	}
	
	@Override
	public String toString() {
		return "Transaction [op=" + op + ", accFrom=" + accFrom + ", accTo=" + accTo + ", user=" + user.getUsername() + "]";
	}
	
	
}