package com.davidahincapie.busniesslayer;

import java.util.UUID;

public class User {

	private UUID userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean isCustomer;
	private boolean isEmployee;

	public User() {
		super();
		this.userId = UUID.randomUUID();
	}

	public User(String firstName, String lastName, String userName, String password) {
		super();
		this.userId = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	public User(String firstName, String lastName, String userName, String password, boolean isCustomer) {
		super();
		this.userId = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isCustomer = isCustomer;
	}

	public User(String firstName, String lastName, String userName, String password, boolean isCustomer,
			boolean isEmployee) {
		super();
		this.userId = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.isCustomer = isCustomer;
		this.isEmployee = isEmployee;
	}

	public String getUserId() {
		return userId.toString();
	}

	// Don't want user to be able to set
	// public void setUserId(String personId) {
	// this.userId = personId;
	// }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
		if (isCustomer) {
			this.isEmployee = false;
		} else {
			this.isEmployee = true;
		}
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
		if (isEmployee) {
			this.isCustomer = false;
		} else {
			this.isCustomer = true;
		}
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", isCustomer=" + isCustomer + ", isEmployee=" + isEmployee
				+ "]";
	}

}
