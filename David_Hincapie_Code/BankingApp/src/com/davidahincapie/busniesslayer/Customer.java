package com.davidahincapie.busniesslayer;

public class Customer extends User {
	private Account account = new Account();
	private boolean approved = false;

	public Customer() {
		super();
		this.setCustomer(true);
	}

	public Customer(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
		this.setCustomer(true);
	}

	public Customer(String personId, String firstName, String lastName, String userName, String password) {
		super(personId, firstName, lastName, userName);
		this.setCustomer(true);
	}

	public Customer(String firstName, String lastName, String userName, String password, boolean isCustomer) {
		super(firstName, lastName, userName, password, isCustomer);
		this.setCustomer(true);
	}

	public Customer(String firstName, String lastName, String userName, String password, boolean isCustomer,
			boolean approved) {
		super();
		this.approved = approved;
		this.setCustomer(true);
	}

	public Customer(String firstName, String lastName, String userName, String password, boolean isCustomer,
			boolean isEmployee, boolean approved) {
		super(firstName, lastName, userName, password, isCustomer, isEmployee);
		this.approved = approved;
		this.setCustomer(true);
	}

	public Customer(Account account, boolean approved) {
		super();
		this.account = account;
		this.approved = approved;
		this.setCustomer(true);
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
	public String toString() {
		return "Customer [account=" + account.toString() + ", approved=" + approved + ", toString()=" + super.toString() + "]";
	}

}
