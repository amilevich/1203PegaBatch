package com.davidahincapie.busniesslayer;

public class Employee extends User {

	private boolean isAdmin;

	public Employee() {
		super();
		this.setCustomer(false);
	}

	public Employee(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
		this.setCustomer(false);
	}

	public Employee(String firstName, String lastName, String userName, String password, boolean isCustomer,
			boolean isAdmin) {
		super(firstName, lastName, userName, password, isCustomer);
		this.isAdmin = isAdmin;
		this.setCustomer(false);
	}

	public Employee(String firstName, String lastName, String userName, String password, boolean isAdmin) {
		super(firstName, lastName, userName, password);
		this.isAdmin = isAdmin;
		this.setCustomer(false);
	}

	public Employee(String personId, String firstName, String lastName, String userName, String password) {
		super(personId, firstName, lastName, userName);
		this.setCustomer(false);
	}

	public Employee(boolean isAdmin) {
		super();
		this.isAdmin = isAdmin;
		this.setCustomer(false);
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Employee [isAdmin=" + isAdmin + ", toString()=" + super.toString() + "]";
	}

}
