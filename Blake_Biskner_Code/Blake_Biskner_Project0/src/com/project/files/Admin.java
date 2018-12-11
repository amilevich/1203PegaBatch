package com.project.files;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.project.files.inputscreens.InputValidation;

public class Admin extends Employee {
	public Admin() {
		super();
	}
	
	public void cancel(String username) {
		Driver.customers.remove(username);
	}
	
	public void deposit(double num, Customer customer) {
		customer.deposit(num);
	}

	public void withdraw(double num, Customer customer) {
		customer.withdraw(num);
	}

	public void transfer(double num, Customer customerFrom, Customer customerTo) {
		customerFrom.transfer(num, customerTo);
	}
	

}
