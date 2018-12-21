package com.revature.util;

import java.util.ArrayList;
import java.util.List;

import com.revature.DAO.CustomerDaoImpl;
import com.revature.DAO.UserDaoImpl;
import com.revature.models.Customer;
import com.revature.models.User;

public class Authentication {

	public Customer register() {
		Customer customer = new Customer();
		String fname = "";
		String uname = "";
		String lname = "";
		String mname = "";
		String phone = "";
		String password = "";
		System.out.print("Please fill out the required field to register" + "\rFirst Name: ");
		boolean success = false;
		fname = Validation.getStringInput();
		while (!success) {
			if ((fname = Validation.getStringInput()).length() <= 2) {
				System.out.print("\r\rError: the Last Name field must be more than 2 characters\nFirst Name:");
			} else if (fname.length() > 30) {
				System.out.print("\n\n\nSorry, the First Name field can't have more than 30 characters.\nFirst Name:");
			} else if (fname.matches(".*\\d+.*")) {
				System.out.print("\n\n\nError: Do not use numbers on the First Name field\nFirst Name:");
			} else {
				customer.setFirstname(fname.toLowerCase());
				success = true;
			}
		}
		System.out.print("Middle Name: ");
		success = false;
		while (!success) {

			if ((mname = Validation.getStringInput()).length() > 30) {
				System.out
						.print("\n\n\nSorry, the Middle Name field must be more than 2 characters \nMiddle Name:");
			} else if (mname.matches(".*\\d+.*")) {
				System.out.print("\n\n\nError: Do not use numbers on the Middle Name field\nMiddle Name:");
			} else {
				customer.setMiddlename(mname.toLowerCase());
				success = true;
			}
		}

		System.out.print("Last Name: ");
		success = false;
		while (!success) {
			if ((lname = Validation.getStringInput()).length() <= 2) {
				System.out.print("\r\rError: the Last Name field must be more than 2 characters\nLast Name:");
			} else if (lname.length() > 30) {
				System.out.print("\n\n\nSorry, the Last Name field can't have more than 30 characters");
			} else if (lname.matches(".*\\d+.*")) {
				System.out.print("\n\n\nError: Do not use numbers on the Last Name field\nLast Name:");
			} else {
				customer.setLastname(lname.toLowerCase());
				success = true;
			}
		}

		List<User> users = new ArrayList<>();
		users = new UserDaoImpl().getAllUsers();
		System.out.print("Username: ");
		success = false;
		boolean found = false;
		while (!success) {
			if ((uname = Validation.getStringInput()).length() <= 2) {
				System.out.print("\r\rError: the Username field must be more than 2 characters\nUsername:");
			} else if (uname.length() > 40) {
				System.out.print("Error: The Username field can't be more than 40 characters\nUsername: ");
			} else if (users == null) {
				success = true;
			} else {
				for (User u : users) {
					if (u.getUsername().equals(uname)) {
						System.out.print("Error: That username is unavailable\nUsername: ");
						found = true;
					} else {
						success=true;
					}
				}
			}
			if(found == true)
				success=false;
			else
				customer.setUsername(uname.toLowerCase());
		}

		System.out.print("Password: ");
		success = false;
		while (!success) {

			if ((password = Validation.getStringInput()).length() <= 0) {
				System.out.print("\r\rError: Do not leave the Password field blank\nPassword:");
			} else if (password.length() > 40 || password.length() < 3) {
				System.out.print(
						"Error: The Password field can't be more than 40 characters or less than 3.\nPassword: ");
			} else {
				customer.setPassword(password);
				success = true;
			}
		}

		System.out.print("Phone: ");
		success = false;
		while (!success) {
			if ((phone = Validation.getStringInput()).length() >= 13 || phone.length() <= 9) {
				System.out.print("Error: The Phone field can't be more than 13 characters or less than 10.\nPhone: ");
			} else {
				customer.setPhone((phone.replace('-', ' ')).trim());
				success = true;
			}
		}
		customer.setRole("CUSTOMER");
		int id = new UserDaoImpl().createUser(new User(customer.getUsername(), customer.getPassword(), customer.getRole()));
		customer.setId(id);
		new CustomerDaoImpl().createCustomer(customer);
		return customer;

	}

	// Read operation from db to check if there's a record with the same username &
	// password
	public User signIn() {
		User user = null;
		boolean found = false;
		String uname;
		String pword;
		Validation.getPause();
		while (!found) {
			System.out.print("\nUsername: ");
			uname = Validation.getStringInput();
			System.out.print("Password: ");
			pword = Validation.getStringInput();

			user = new UserDaoImpl().getUserByIdentifier(uname.toLowerCase(), pword.toLowerCase());

			if (user != null) {
				found = true;
			} else
				System.out.println("\nError: We do not recognize your username and/or password.\nPlease try again.");
		}
		return user;
	}

}
