package com.project.files;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.project.files.inputscreens.InputValidation;

public class Employee {
	private Integer employeeId;
	private String level;
	private HashMap<String, Customer> customers;
	private HashMap<Integer,Character> flagged; // Create flagged arraylist in which to put denied joint account numbers
	
	public Employee(Integer employeeId, String level) {
		this.employeeId=employeeId;
		this.level=level;
	}
	
	public String getLevel() {
		return level;
	}
	public Integer getId() {
		return employeeId;
	}
	
	public void approveApp(Scanner userIn) {
		String key;
		int answer;
		Customer applicant;
		flagged=new HashMap<Integer, Character>();
		customers=Driver.customers; // Get customer hashmap
		Set<String> keys=customers.keySet();
		Iterator<String> itr=keys.iterator();
		while(itr.hasNext()) {
			key=itr.next();
			applicant=customers.get(key);
			if((flagged.isEmpty()!=true)&&(flagged.containsKey(applicant.getAcctNum()))){ 
				Character status=flagged.get(applicant.getAcctNum());
				if(status=='A') {
					applicant.setAcctStatus('A');
					Account jtAcct=Driver.accounts.get(applicant.getAcctNum());
					jtAcct.addHolder(applicant);
					FileWrite.writeToAccountDataBase(jtAcct);
					// Write to database now that both users are on the account
				} else {
					applicant.setAcctStatus('D');
				}
			}
			if(applicant.getAcctStatus()=='R') { // If application is under review
				System.out.println("Application " +applicant.getAcctNum());
				System.out.println(applicant.getFirstName()+ " "+applicant.getLastName());
				System.out.println("[1] Approve\n[2] Deny");
				answer=InputValidation.optionValidate(userIn, 2);
				if(answer==1) { // Approved
					applicant.setAcctStatus('A');
					Account acct=new Account(applicant.getAcctNum(),applicant);
					Driver.accounts.put(applicant.getAcctNum(),acct);
					if(applicant.getAcctType()=='J') {
						flagged.put(applicant.getAcctNum(), applicant.getAcctStatus());
					} else {
						// Only write personal accounts now as joint will be updated by adding the other holder name
						FileWrite.writeToAccountDataBase(acct);
					}
				}else {
					applicant.setAcctStatus('D');
					if(applicant.getAcctType()=='J') {
						flagged.put(applicant.getAcctNum(),applicant.getAcctStatus());
					}
				}
			}
		}
	}
}

