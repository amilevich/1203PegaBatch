package com.project.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

	public static HashMap<String,Customer> readCustomerDataBase() {
		File file = new File(customerDataBase);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			HashMap<String,Customer> map=new HashMap<String,Customer>();
			String userData;
			String[] dataArray;
			String username;
			int acctNum;
			String name[]=new String[2];;
			int age;
			String socialSecurity;
			String password;
			char acctType;
			double balance;
			char status;
			while ((userData = br.readLine()) != null) {
				dataArray = userData.split(":");
				username=dataArray[0];
				acctNum=Integer.valueOf(dataArray[1]);
				name[0]=dataArray[2];
				name[1]=dataArray[3];
				age=Integer.valueOf(dataArray[4]);
				socialSecurity=dataArray[5];
				password=dataArray[6];
				acctType=dataArray[7].charAt(0);
				balance=Double.valueOf(dataArray[8]);
				status=dataArray[9].charAt(0);
				
				// Input in order (username,acctNum, name[first,last],age,social security,password,acctType,balance,status)
				Customer customer=new Customer(username,acctNum, name,age,socialSecurity,password,acctType,balance,status);
				//System.out.println(customer);
				map.put(username, customer);
			}
			return map;
		} catch (IOException e) {
			return null;
		}
	}
}
