package com.revature.bean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.revature.daoimpl.CustomerDAOImpl;
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
	public static final int ANSWER_APPROVE = 1;
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
		// Get Most Recent Account and Customer Maps
		Driver.pullCustomerMap();
		Driver.pullAccountMap();
		// Variable Declaration
		int maxOp = 2;
		String key;
		int answer;
		Customer applicant;
		// Object Instantiation
		flagged = new HashMap<Integer, Integer>();
		Set<String> keys = Driver.customers.keySet();
		Iterator<String> itr = keys.iterator();
		CustomerDAOImpl cdi = new CustomerDAOImpl();
		// Iterate through customer hashmap
		while (itr.hasNext()) {
			key = itr.next();
			applicant = Driver.customers.get(key);
			// If this is the second joint account holder
			if ((flagged.isEmpty() != true) && (flagged.containsKey(applicant.getAcctNum()))) {
				Integer status = flagged.get(applicant.getAcctNum());
				if (status == APPROVED) {
					applicant.setAcctStatus(APPROVED);
					Account jtAcct = Driver.accounts.get(applicant.getAcctNum());
					jtAcct.addHolder(applicant);
					applicant.setAcct(jtAcct);
					try {
						cdi.updateCustomer(applicant);
					} catch (SQLException e) {
						System.out.println("Error Updating Joint Holder Status in Database");
						e.printStackTrace();
					}
				} else {
					applicant.setAcctStatus(DENIED);
					try {
						cdi.updateCustomer(applicant);
					} catch (SQLException e) {
						System.out.println("Error Updating Customer Status in Database");
						e.printStackTrace();
					}
				}
			} // End of flagged
			// If this is the first joint account holder or a personal account holder
			if (applicant.getAcctStatus() == REVIEW) {
				System.out.println("Application " + applicant.getAcctNum());
				System.out.println(applicant.getFirstName() + " " + applicant.getLastName());
				System.out.println("[1] Approve\n[2] Deny");

				answer = InputValidation.optionValidate(userIn, maxOp);
				// Approved
				if (answer == ANSWER_APPROVE) { // Must use this variable as Approved=2 but the employee selects 1 to
												// approve
					applicant.setAcctStatus(APPROVED);
					// Set applicant account equal to the account from the database
					Account acct = Driver.accounts.get(applicant.getAcctNum());
					// Set applicant account instance to this account
					applicant.setAcct(acct);
					// Add applicant to account object in hashmap
					acct.addHolder(applicant);
					if (applicant.getAcctType() == JOINT) {
						flagged.put(applicant.getAcctNum(), applicant.getAcctStatus());
					}
					try {
						cdi.updateCustomer(applicant);
					} catch (SQLException e) {
						System.out.println("Exception Updating Initial Holder Status");
						e.printStackTrace();
					}
				} else {
					applicant.setAcctStatus(DENIED);
					if (applicant.getAcctType() == JOINT) {
						flagged.put(applicant.getAcctNum(), applicant.getAcctStatus());
					}
					try {
						cdi.updateCustomer(applicant);
					} catch (SQLException e) {
						System.out.println("Exception Updating Customer Status in Database");
						e.printStackTrace();
					}
				} // End of denied
			} // End of Review
		} // End of while loop through map
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
			System.out.print(Driver.customers.get(username));
		}
	}

	/**
	 * Method to View Account Info
	 *
	 * @param userIn
	 * 
	 */

	public void acctInfo(Scanner userIn) {
		Integer acctNum = InputValidation.acctNumValidate(userIn);
		if (Driver.accounts.containsKey(acctNum)) {
			System.out.println(Driver.accounts.get(acctNum));
		}
	}

	@Override
	public String toString() {
		String id = ("Employee id " + employeeId);
		String empLevel = ("Employee level " + level);
		return (id + " " + empLevel);
	}
}
