package com.project.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	// Run next two methods in conjunction to clear and rewrite database with map contents (ie cancelled are gone)
	public static void clearDatabase() {
		File file = new File(customerDataBase);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			
		} catch (IOException e) {
			
		}
	}
	
	public static void overWriteDatabase() {
		File file = new File(customerDataBase);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true))) {
			Set<String> ids=Driver.customers.keySet();
			Iterator<String> itr=ids.iterator();
			Customer value;
			String key;
			char status;
			while(itr.hasNext()) {
				key=itr.next();
				value=Driver.customers.get(key);
				
			}
		}catch (IOException e) {
			
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
}