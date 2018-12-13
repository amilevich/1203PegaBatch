package com.project.files;

import java.util.ArrayList;

public class Account {
	// Class State
	private int id;
	private double balance;
	private ArrayList<Customer> holders=new ArrayList<Customer>(); 
	// Use arraylist as it is dynamic so if more than one holder (joint) can add
	// Rather than have every account have a 2 element array (waste of memory)
	
	public Account(int id, Customer holder) {
		this.id=id;
		this.balance=0.0;
		this.holders.add(holder);
	}
	
	public Account(int id, Customer holder1, Customer holder2) { // Joint Account
		this(id, holder1);
		this.holders.add(holder2);
	}
	
	public Account(int id, Double balance, Customer holder1) {
		this.id=id;
		this.balance=balance;
		this.holders.add(holder1);
	}
	public Account(int id, Double balance, Customer holder1, Customer holder2) {
		this(id, balance,holder1);
		this.holders.add(holder2);
	}
	public void addHolder(Customer customer) {
		holders.add(customer);
	}
	public String toString() {
		String toStr=(id+":"+balance+":"+(holders.get(0)).getUsername());
		if(holders.size()==2) {
			String joint=(":"+(holders.get(1)).getUsername());
			toStr=toStr+joint;
		}
		return(toStr+'\n');
	}
}
