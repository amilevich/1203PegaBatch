package com.revature.businesslayer;

public class Customer extends User {
	private Account account = new Account();
	private boolean approved = false;

	public Customer() {
		super();
	}

	public Customer(Account account, boolean approved) {
		super();
		this.account = account;
		this.approved = approved;
	}

	public Customer(int id, String userId, String firstName, String lastName, String userName, String password,
			String userType, Account account, boolean approved) {
		super(id, userId, firstName, lastName, userName, password, userType);
		this.account = account;
		this.approved = approved;
	}

	public Customer(int id, String userId, String firstName, String lastName, String userName, String password,
			String userType, boolean approved) {
		super(id, userId, firstName, lastName, userName, password, userType);
		this.approved = approved;
	}

	public Customer(int id, String userId, String firstName, String lastName, String userName, String password,
			String userType) {
		super(id, userId, firstName, lastName, userName, password, userType);
	}

	public Customer(int id, String firstName, String lastName, String userName, String password, String userType) {
		super(id, firstName, lastName, userName, password, userType);
	}

	public Customer(String firstName, String lastName, String userName, String password, String userType) {
		super(firstName, lastName, userName, password, userType);
	}

	public Customer(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (approved != other.approved)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [account=" + account + ", approved=" + approved + ", getUserId()=" + getUserId()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getUserName()="
				+ getUserName() + ", getPassword()=" + getPassword() + ", getUserType()=" + getUserType() + ", getId()="
				+ getId() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}





}
