package com.revature.models;

import java.sql.Timestamp;

import com.revature.enums.Transaction_Type;

public class Transaction {

	private int id;
	private int account;
	private int user;
	private double amount;
	private Transaction_Type type;
	private Timestamp timestamp;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(int id, int account, int user, double amount, Transaction_Type type, Timestamp timestamp) {
		super();
		this.id = id;
		this.account = account;
		this.user = user;
		this.amount = amount;
		this.type = type;
		this.timestamp = timestamp;
	}
	
	public Transaction(int account, int user, double amount, Transaction_Type type, Timestamp timestamp) {
		super();
		this.account = account;
		this.user = user;
		this.amount = amount;
		this.type = type;
		this.timestamp = timestamp;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Transaction_Type getType() {
		return type;
	}

	public void setType(Transaction_Type type) {
		this.type = type;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + user;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (account != other.account)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (id != other.id)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (type != other.type)
			return false;
		if (user != other.user)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", account=" + account + ", user=" + user + ", amount=" + amount + ", type="
				+ type + ", timestamp=" + timestamp + "]";
	}
	
	
	
}
