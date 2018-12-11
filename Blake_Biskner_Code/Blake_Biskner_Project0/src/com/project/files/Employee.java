package com.project.files;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.project.files.inputscreens.InputValidation;

public class Employee {
	// Class State
	private int empNumber;
	
	// Class Behavior
	public Employee()
	{
	}
	
	public void view(String username) {
		System.out.println(Driver.customers.get(username));
	}
	
	public void review(Scanner userIn) {
		Set<String> ids=Driver.customers.keySet();
		Iterator<String> itr=ids.iterator();
		Customer value;
		String key;
		char status;
		while(itr.hasNext()) {
			key=itr.next();
			value=Driver.customers.get(key);
			status=value.getAcctStatus();
			if(status=='R') {
				System.out.println("Customer to Review");
				System.out.println(value);
				System.out.println("Please Enter Decision \n[1] Approve\n[2] Deny");
				InputValidation.optionValidate(userIn, 2);
			}
		}
	}
}
