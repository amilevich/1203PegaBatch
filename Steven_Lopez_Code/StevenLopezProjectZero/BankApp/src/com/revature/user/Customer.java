package com.revature.user;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.account.Account;
//Class for handling the customers
public class Customer extends User implements Serializable {

	/**
	 * 
	 */
	protected static final long serialVersionUID = -8974060612562560605L;
	private int cid;
	private ArrayList<Account> accounts = new ArrayList<>();
	
	//Used when registering new customers, call the register method
	public Customer(Scanner scan) {
		cid = super.getId();
		this.setDateCreated(LocalDate.now());
		if (this.register(scan)) {
		}
	}

	//Constructor used for signing in customers, calls the signIn method.
	public Customer(String username, char[] password) {
		super(username, password);
		String filename = "./customers.txt";
		while (!signIn(filename, username, password)) {
			System.out.println("\nError: We do not recognize your username and/or password.\nPlease try again.");
			return;
		}
	}

	public int getId() {
		return cid;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", accounts=" + accounts + ", getName()=" + getName() + ", getUsername()="
				+ getUsername() + ", getPassword()=" + Arrays.toString(getPassword()) + ", getEmail()=" + getEmail()
				+ ", getDateCreated()=" + getDateCreated() + "]";
	}
	
	//Reads from the file first de-serializing the bytes into object and then checks if there's an instance with the same username & password
	public boolean signIn(String filename, String username, char[] password) {
		Customer cus;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			while ((cus = (Customer) ois.readObject()) != null) {
				if (cus.getUsername().toLowerCase().equals(username.toLowerCase()) && Arrays.equals(cus.getPassword(), password)) {
					this.setId(cus.getId());
					this.setName(cus.getName());
					this.setUsername(cus.getUsername());
					this.setEmail(cus.getEmail());
					this.setPassword(cus.getPassword());
					this.setDateCreated(cus.getDateCreated());
					this.setAccounts(cus.getAccounts());
					return true;
				}
			}
		} catch (EOFException e) {
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

		return false;
	}
	//Validates registration inputs, if successful calls the createCustomer method
	public boolean register(Scanner scn) {
		String name = "";
		String username = "";
		char[] password = new char[30];
		String email = "";
		boolean success = false;
		String filename = "./customers.txt";
		ArrayList<Customer> users = new ArrayList<>();
		users = getCustomers(filename);
		Pattern emailP = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",
				Pattern.CASE_INSENSITIVE);
		Matcher m = emailP.matcher(email);
		System.out.print("Please fill out the required field to register" + "\rName: ");

		while (!success) {

			if ((name = scn.nextLine()).length() <= 0) {
				System.out.print("\r\rError: Do not leave the Name field blank\nName:");
			} else if (name.length() > 30) {
				System.out.print("\n\n\nSorry, the name field can't have more than 30 characters");
			} else if (name.matches(".*\\d+.*")) {
				System.out.print("\n\n\nError: Do not use numbers on the Name field\nName:");
			} else
				this.setName(name);
			success = true;
		}

		success = false;
		System.out.print("Email: ");
		while (!success) {
			if ((email = scn.nextLine()).length() <= 0) {
				System.out.print("\r\rError: Do not leave the Email field blank\nEmail:");
			} else if (email.length() > 50) {
				System.out.print("Sorry, the email field can't have more than 40 characters\nEmail:");
			} else if (m.find()) {
				System.out.print(
						"\r\rError: The email must contain the following format:\r" + "	example@example.com\nEmail:");
			} else if (users != null) {
				for (User u : users) {
					if (u.getEmail().equals(email)) {
						System.out.print("This email is already registered.\r\r\nEmail:");
						break;
					} else {
						this.setEmail(email);
						success = true;
					}
				}
			} else {
				this.setEmail(email);
				success = true;
			}
		}

		System.out.print("Username: ");
		success = false;
		while (!success) {
			if ((username = scn.nextLine()).length() <= 0) {
				System.out.print("\r\rError: Do not leave the Username field blank\nUsername:");
			} else if (username.length() > 40) {
				System.out.print("Error: The Username field can't be more than 40 characters\nUsername: ");
			} else if (users != null) {
				for (User u : users) {
					if (u.getUsername().equals(username)) {
						System.out.print("\n\n\nError: That username is unavailable\nUsername: ");
						break;
					} else {
						this.setUsername(username);
						success = true;
					}
				}
			} else {
				this.setUsername(username);
				success = true;
			}
		}
		System.out.print("Password: ");
		success = false;
		while (!success) {

			if ((password = scn.nextLine().toCharArray()).length <= 0) {
				System.out.print("\r\rError: Do not leave the Password field blank\nPassword:");
			} else if (password.length > 40) {
				System.out.print("Error: The Email field can't be more than 40 characters\nPassword");
			} else
				this.setPassword(password);
			success = true;
		}
		createCustomer(filename);
		System.out.println();
		return true;
	}
	//Add a new customer to the customers.txt
	public boolean createCustomer(String filename) {
		ArrayList<Customer> customers = getCustomers(filename);
		Customer customer2 = this;
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {

			if (customers != null)
				for (Customer c : customers) {
					oos.writeObject(c);
				}
			oos.writeObject(customer2);
			oos.writeObject(null);
			oos.flush();
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//Gets all the customers in the customers.txt
	public static ArrayList<Customer> getCustomers(String filename) {
		Object obj;
		ArrayList<Customer> customers = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			while ((obj = (Customer) ois.readObject()) != null) {
				customers.add((Customer) obj);
			}
			return customers;

		} catch (EOFException e) {
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	//Used to save any changes done to an intance of a customer,including accounts and transaction
	public void saveCustomer(String filename) {
		ArrayList<Customer> customers = Customer.getCustomers(filename);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {

			if (customers != null)
				for (Customer c : customers) {
					if (c.getUsername().equals(this.getUsername())) {
						oos.writeObject(this);
					} else {
						oos.writeObject(c);
					}
				}
		
			oos.writeObject(null);
			oos.flush();
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
