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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.account.Account;
import com.revature.account.AccountState;
//Employee Class that extends User
public class Employee extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4653375669153571514L;
	private int eid;
	
	//Used when registering new employees(can only be done by admins).
	public Employee(Scanner scan) {
		eid = super.getId();
		this.setDateCreated(LocalDate.now());
		if (this.register(scan)) {
			
		}
	}
	//Constructor used when sign in, calls the sinIn method.
	public Employee(String username, char[] password) {
		super(username, password);
		String filename = "./employees.txt";
		while (!signIn(filename, username, password)) {
			System.out.println("\nError: We do not recognize your username and/or password.\nPlease try again.");
			return;
		}
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	//For approving account applications
	public void approve(Account account) {
		account.setCurrentState(AccountState.ACCEPTED);
		account.setAprovedBy(this);
		account.setAprovedDate(LocalDate.now());
	}
	//For denying account applications
	public void deny(Account account) {
		account.setCurrentState(AccountState.DENIED);
		account.setAprovedBy(this);
		account.setAprovedDate(LocalDate.now());
	}
	
	//Reads from the file first de-serializing the bytes into object and then checks if there's an instance with the same username & password
	public boolean signIn(String filename, String username, char[] password) {
		Employee emp;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			while ((emp = (Employee) ois.readObject()) != null) {
				if (emp.getUsername().toLowerCase().equals(username.toLowerCase()) && Arrays.equals(emp.getPassword(), password)) {
					this.setId(emp.getId());
					this.setName(emp.getName());
					this.setUsername(emp.getUsername());
					this.setEmail(emp.getEmail());
					this.setPassword(emp.getPassword());
					this.setDateCreated(emp.getDateCreated());
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

	//Validates registration inputs, if successful calls the CreateEmployee method
	public boolean register(Scanner scn) {
		String name = "";
		String username = "";
		char[] password = new char[30];
		String email = "";
		boolean success = false;
		String filename = "./employees.txt";
		ArrayList<Employee> users = new ArrayList<>();
		users = getEmployee(filename);
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
		createEmployee(filename);
		System.out.println();
		return true;
	}

	//Add a new employee to the employees.txt
	public boolean createEmployee(String filename) {
		ArrayList<Employee> employees = getEmployee(filename);
		Employee emp2 = this;
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {

			if (employees != null)
				for (Employee e : employees) {
					oos.writeObject(e);
				}
			oos.writeObject(emp2);
			oos.writeObject(null);
			oos.flush();
			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//Gets all the employees in the employees.txt
	public static ArrayList<Employee> getEmployee(String filename) {
		Object obj;
		ArrayList<Employee> employees = new ArrayList<>();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			while ((obj = (Admin) ois.readObject()) != null) {
				employees.add((Employee) obj);
			}
			return employees;

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


}
