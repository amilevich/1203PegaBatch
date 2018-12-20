package com.revature.bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.revature.driver.Driver;
import com.revature.util.InputValidation;

/**
 * Employee POJO
 * 
 * @author Blake Biskner
 * @version 2.0
 * 
 */

public class Employee {
	// Class Constants
	public static final int PERSONAL = 1;
	public static final int JOINT = 2;

	public static final int REVIEW = 1;
	public static final int APPROVED = 2;
	public static final int DENIED = 3;

	// Class State
	private Integer employeeId;
	private int level;
	private Map<Integer, Integer> flagged;

	// Class Behavior
	// Constructors
	public Employee(Integer employeeId, int level) {
		this.employeeId = employeeId;
		this.level = level;
	}

	// Getters and Setters
	public Integer getId() {
		return employeeId;
	}

	public int getLevel() {
		return level;
	}

	// Custom Methods

	/**
	 * Application Review Method
	 * 
	 * @param userIn
	 * 
	 */

	public void approveApp(Scanner userIn) {
		// Variable Declaration
		int maxOp = 2;
		String key;
		int answer;
		Customer applicant;
		// Object Instantiation
		flagged = new HashMap<Integer, Integer>();
		Set<String> keys = Driver.customers.keySet();
		Iterator<String> itr = keys.iterator();
		// Iterate through customer hashmap
		while (itr.hasNext()) {
			key = itr.next();
			applicant = Driver.customers.get(key);
			// If this is the second joint account holder
			if ((flagged.isEmpty() != true) && (flagged.containsKey(applicant.getAcctNum()))) {
				Integer status = flagged.get(applicant.getAcctNum());
				if (status == APPROVED) {
					// applicant.setAcctStatus(APPROVED);
					// Account jtAcct=Driver.accounts.get(applicant.getAcctNum());
					// jtAcct.addHolder(applicant);
					// applicant.setAcct(jtAcct);
					// // Write to account now that both users are on account
					// FileWrite;
				} else {
					applicant.setAcctStatus(DENIED);
				}
			}
			// If this is the first joint account holder or a personal account holder
			if (applicant.getAcctStatus() == REVIEW) {
				System.out.println("Application " + applicant.getAcctNum());
				System.out.println(applicant.getFirstName() + " " + applicant.getLastName());
				System.out.println("[1] Approve\n[2] Deny");

				answer = InputValidation.optionValidate(userIn, maxOp);
				// Approved
				if (answer == APPROVED) {
					applicant.setAcctStatus(APPROVED);
					// Account acct=new Account(applicant.getAcctNum(),applicant);
					// Driver.accounts.put(applicant.getAcctNum(),acct);
					// if(applicant.getAcctType()==JOINT) {
					// flagged.put(applicant.getNum(), applicant.getAcctStatus());
				} else {
					// FileWrite
				}
				// Denied
			} else {
				applicant.setAcctStatus(DENIED);
				if (applicant.getAcctType() == JOINT) {
					flagged.put(applicant.getAcctNum(), applicant.getAcctStatus());
				}
			}
		}
	}

	/**
	 * Method to View Customer Personal Info
	 * 
	 * @param userIn
	 * 
	 */

	public void customerInfo(Scanner userIn) {
		String username = InputValidation.existUserNameValidate(userIn);
		if (Driver.customers.containsKey(username)) {
			System.out.println(Driver.customers.get(username));
		}
	}

	/**
	 * Method to View Account Info
	 *
	 * @param userIn
	 * 
	 */

	public void acctInfo(Scanner userIn) {
//		Integer acctNum = inputValidation.acctNumValidate(userIn);
//		if (Driver.accounts.containsKey(acctNum)) {
//			System.out.println(Driver.accounts.get(acctNum));
//		}
	}

	@Override
	public String toString() {
		String id = ("Employee id " + employeeId);
		String empLevel = ("Employee level " + level);
		return (id + " " + empLevel);
	}
}
