package com.project.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite implements FileIO {

	public static void writeToCustomerDataBase(Customer customer) {
		File file = new File(customerDataBase);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true))) {
			// Use getAbsolute to overwrite
			bw.append(customer.toString());
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
}