package com.project.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileRead implements FileIO {

	public static int getAcctNum() {
		File file = new File(acctNumDocument);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String acctNumStr = br.readLine();
			int acctNum = Integer.valueOf(acctNumStr);
			return acctNum; // Returns number in AccountNumberFile
			// This way account numbers continue to increment
			// All account numbers thus remain unique even after close
		} catch (IOException e) {
			System.out.println("File Reading Exception");
			return -1;
		}
	}

	public static HashMap<String, Customer> readCustomerDataBase() {
		File file = new File(customerDataBase);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			HashMap<String, Customer> map = new HashMap<String, Customer>();
			String userData;
			String[] dataArray;
			String username;
			int acctNum;
			String firstName;
			String lastName;
			int age;
			String socialSecurity;
			String password;
			char acctType;

			char status;
			while ((userData = br.readLine()) != null) {
				dataArray = userData.split(":");
				username = dataArray[0];
				acctNum = Integer.valueOf(dataArray[1]);
				firstName = dataArray[2];
				lastName = dataArray[3];
				age = Integer.valueOf(dataArray[4]);
				socialSecurity = dataArray[5];
				password = dataArray[6];
				acctType = dataArray[7].charAt(0);

				status = dataArray[8].charAt(0);

				// Input in order (username,acctNum, first name, last name,age,social
				// security,password,acctType,balance,status)
				Customer customer = new Customer(username, acctNum, firstName, lastName, age, socialSecurity, password,
						acctType, status);
				map.put(username, customer);
			}
			return map;
		} catch (IOException e) {
			return null;
		}
	}

	public static HashMap<Integer, Employee> readEmployeeDataBase() {
		File file = new File(employeeDataBase);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			HashMap<Integer, Employee> map = new HashMap<Integer, Employee>();
			String userData;
			String[] dataArray;
			Integer id;
			String level;
			Employee emp;
			while ((userData = br.readLine()) != null) {
				dataArray = userData.split(":");
				id = Integer.valueOf(dataArray[0]);
				level = dataArray[1];
				if(level.equals("employee"))
				{
					emp=new Employee(id, level);
				} else {
					emp=new Admin(id,level); // Will need to downcast later to access unique behavior
				}
				map.put(id, emp);
			}
			return map;
		} catch (IOException e) {
			return null;
		}
	}

	public static HashMap<Integer, Account> readAccountDataBase() {
		File file = new File(accountDataBase);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			HashMap<Integer, Account> map = new HashMap<Integer, Account>();
			Account acct = null;
			String data;
			String[] dataArray;
			Integer id;
			Double balance;
			ArrayList<Customer> holders = new ArrayList<Customer>();
			Customer customer1;
			Customer customer2;
			while ((data = br.readLine()) != null) {
				dataArray = data.split(":");
				id = Integer.valueOf(dataArray[0]);
				balance = Double.valueOf(dataArray[1]);
				customer1 = Driver.customers.get(dataArray[2]);
				holders.add(customer1);
				if (dataArray.length == 4) { // Check if there are 2 holders
					customer2 = Driver.customers.get(dataArray[3]);
					holders.add(customer2);
					acct = new Account(id, balance, customer1, customer2);
					customer1.setAcct(acct); // Set the Customer account field equal to this corresponding account
					customer2.setAcct(acct);
				} else {
					acct = new Account(id, balance, customer1);
					customer1.setAcct(acct);
				}
				map.put(id, acct);
			}
			return map;
		} catch (IOException e) {
			return null;
		}
	}
	
}
