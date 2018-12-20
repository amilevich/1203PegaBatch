package com.revature.pojos;

/**
 * Transaction POJO
 * @author karan
 *
 */
public class Transaction {
	public static enum operation{DEPOSIT, WITHDRAW};
	private operation op;
	private int accNum;
	
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
	public int getAccNum() {
		return accNum;
	}
	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	public operation getOp() {
		return op;
	}
	public void setOp(operation op) {
		this.op = op;
	}

	public Transaction(operation op, int accNum, double amount, String username) {
		this.op = op;
		this.accNum = accNum;
		this.username = username;
		this.amount = amount;
	}
	
	public Transaction() {
		
	}
	
	@Override
	public String toString() {
		return "Transaction [op=" + op + ", accNum=" + accNum + ", user=" + username + "]";
	}
	
	
}