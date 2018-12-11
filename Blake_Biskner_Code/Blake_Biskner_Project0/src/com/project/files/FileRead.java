package com.project.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileRead implements FileIO {

	public static int getAcctNum() {
		File file=new File(acctNumDocument);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String acctNumStr=br.readLine();
			int acctNum=Integer.valueOf(acctNumStr);
			return acctNum; // Returns number in AccountNumberFile
			// This way account numbers continue to increment
			// All account numbers thus remain unique even after close
		} catch (IOException e) {
			System.out.println("File Reading Exception");
			return -1;
		}
	}

}
