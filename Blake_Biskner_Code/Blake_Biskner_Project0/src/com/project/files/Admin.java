package com.project.files;

import java.util.Scanner;

import com.project.files.inputscreens.InputValidation;

public class Admin extends Employee{
	
	public Admin(Integer employeeId,String level) {
		super(employeeId,level);
	}
	
	public void employeeInfo(Scanner userIn) {
		int empId=InputValidation.existEmployeeIdValidate(userIn);
		if(Driver.employees.containsKey(empId)) {
			System.out.println(Driver.employees.get(empId));
		}
	}
	
	public void cancel(Scanner userIn) {
		Integer acctNum=InputValidation.acctNumValidate(userIn);
		if(InputValidation.acctExistValidate(acctNum)==true){
			Account acct=Driver.accounts.get(acctNum);
			for(Customer cust:acct.getHolders()) {
				cust.setAcctStatus('D');
			}
			System.out.println("Cancelled Account "+acct.getAcctNum());
		} else {
			System.out.println("Account Does Not Exist");
		}
	}
}