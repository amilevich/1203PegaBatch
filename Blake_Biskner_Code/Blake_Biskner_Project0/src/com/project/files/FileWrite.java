package com.project.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FileWrite implements FileIO {

	public static void writeToCustomerDataBase(Customer customer) {
		File file = new File(customerDataBase);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true))) {
			// Use getAbsolute to overwrite
			bw.append(customer.toString());
		} catch (IOException e) {

		}
	}
	
	public static void writeToAccountDataBase(Account account) {
		File file= new File(accountDataBase);
		try(BufferedWriter bw=new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true))){
			bw.append(account.toString());
		} catch (IOException e) {
		
		}
	}
	
	public static void incrementAcctNum() {
		File file=new File(acctNumDocument);
		Integer acctNum=FileRead.getAcctNum();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			acctNum++; // Increment Account Number
			String acctNumStr = acctNum.toString(); // Change to String to write to file
			//System.out.println(acctNum)
			bw.write(acctNumStr);
		} catch (IOException e) {

		}
	}
	
	public static void updateCustomerDataBase() {
		File file=new File(customerDataBase);
		try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))){
			HashMap<String,Customer> map=Driver.customers;
			Set<String> keys=map.keySet();
			Iterator<String> itr=keys.iterator();
			while(itr.hasNext()) {
				String key=itr.next();
				Customer cust=map.get(key);
				bw.write(cust.toString());
			}
		} catch(IOException e) {
		
		}
	}
			
	public static void updateAccountDataBase() {
		File file=new File(accountDataBase);
		try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))){
			HashMap<Integer,Account> map=Driver.accounts;
			Set<Integer> keys=map.keySet();
			Iterator<Integer> itr=keys.iterator();
			while(itr.hasNext()) {
				Integer key=itr.next();
				Account acct=map.get(key);
				bw.write(acct.toString());
			}
		} catch(IOException e) {
			
		}
	}
}
