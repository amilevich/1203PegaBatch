package com.assignment.persons;

import java.util.ArrayList;
import java.util.HashMap;

import com.assignment.utilities.Account;

public class Customer extends Person {
	//inherits from Person to use password, user name, and hashmap
	
	private static HashMap<String, Customer> customerMap = new HashMap<>();
	private static HashMap<Integer, String> customerIDMap = new HashMap<>();//a list of account ids to corresponding customers, to be used for pending approval screen	
	ArrayList <Integer> customerAccounts = new ArrayList<>();//stores each customer's accounts ids, only approved accounts
	String username, password;
	static boolean cloaded = false;
	static ArrayList <Integer> c1 = new ArrayList<>();
	static ArrayList <Integer> c2 = new ArrayList<>();
	static ArrayList <Integer> c3 = new ArrayList<>();
	static ArrayList <Integer> c4 = new ArrayList<>();
	
	static Customer c_1 = new Customer ("c1", "c1", 1, c1);//these will serve as tests, in reality we'll want to pull these from database/text file
	static Customer c_2 = new Customer ("c2", "c2", 1, c2);
	static Customer c_3 = new Customer ("c3", "c3", 1, c3);
	static Customer c_4 = new Customer ("c4", "c4", 1, c4);
	
	public Customer(String username, String password, int userType, ArrayList <Integer> customerAccounts) {
		super(username, password, userType);
		this.customerAccounts = customerAccounts;
	}
	public Customer() {}
	
	public static void cloaded() {//to test functionality
		customerMap.put("c1", c_1);
		customerMap.put("c2", c_2);
		customerMap.put("c3", c_3);
		customerMap.put("c4", c_4);
		cloaded = true;
	}
	public static boolean getcloaded() {
		return cloaded;
	}
	
	public static void loadarrays()	{
		c1.add(1);
		c1.add(5);
		c2.add(2);
		c2.add(6);
		c3.add(3);
		c1.add(7);
		c4.add(4);
		c1.add(8);
	}
	
	public void addAccount (int iD) {//adds account's Id it to customers accounts list and joint accounts too
			customerAccounts.add(iD);
	}
	public boolean checkAccount(int iD) {
		for (int i = 0; i < customerAccounts.size(); i++) {
			if (iD == customerAccounts.get(i))
				return true;				
		}
		return false;
	}

	public void viewCustomerAccounts(String username) {
		//meant to show the username and all account IDs and corresponding balances
		for (int i = 0; i < customerAccounts.size(); i++ ) {
			if (Account.pullFromHash(customerAccounts.get(i)).isApproved()) {
				System.out.println(customerAccounts.get(i) + " -> Balance: $" + Account.pullFromHash(customerAccounts.get(i)).getBalance());//shows specific Id and corresponding balance
			}
		}
	}
	public void addToIDHash(int iD) {
		customerIDMap.put(iD, this.username);
	}
	public static String pullFromIDHash(int iD) {
		return customerIDMap.get(iD);
	}
	
	public static int iDHashSize() {
		return customerIDMap.size();
	}

	public static void addToCHash(Customer toBeAdded, String username) {
		customerMap.put(username , toBeAdded);
	}
	public static Customer pullFromCHash(String username) {
		Customer n = customerMap.get(username);
		return n;
	}
	public static int hashsize() {
		return customerMap.size();
	}
	
	public static boolean checkCUsername (String input) {
		Customer n = customerMap.get(input);
		String tempS = n.getUsername();
		if (tempS.equals(input))
			return true;
		else
			return false;			
	}
	public static boolean checkCPassword (String input) {
		Customer n = customerMap.get(input);
		String tempS = n.getPassword();
		if (tempS.equals(input))
			return true;
		else
			return false;
	}

}
