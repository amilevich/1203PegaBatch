package com.revature.bean;

import java.util.Scanner;

import com.revature.driver.Driver;
import com.revature.util.InputValidation;

/**
 * Admin Class Extension of Employee
 * 
 * @author Blske Biskner
 * @version 2.0
 *
 */

public class Admin extends Employee {
	// Class Constants
	public static final int CANCELLED = 3;

	public Admin(Integer employeeId, int level) {
		super(employeeId, level);
	}

	/**
	 * Method to View Employee Info
	 * 
	 * @param userIn
	 * 
	 */

	public void employeeInfo(Scanner userIn) {
		int empId = InputValidation.existEmployeeIdValidate(userIn);
		if (Driver.employees.containsKey(empId)) {
			System.out.println(Driver.employees.get(empId));
		}
	}

	/**
	 * Method to Cancel Accounts
	 * 
	 * @param userIn
	 * 
	 */

	public void cancel(Scanner userIn) {
		Integer acctNum = InputValidation.acctNumValidate(userIn);
		if (InputValidation.acctExistValidate(acctNum) == true) {
			Account acct = Driver.accounts.get(acctNum);
			for (Customer customer : acct.getHolders()) {
				customer.setAcctStatus(CANCELLED);
			}
			System.out.println("Cancelled Account " + acct.getAcctNum());
		} else {
			System.out.println("Account Does Not Exist");
		}
	}
}
