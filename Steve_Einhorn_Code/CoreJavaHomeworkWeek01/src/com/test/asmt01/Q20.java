package com.test.asmt01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q20 {

	public static void main(String[] args)  {
		
		try {
			
			FileInputStream fstream = new FileInputStream("Data.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
			String stringLine;
			String [] splitStrings = new String [4];
			
			while ((stringLine = br.readLine()) != null)   {
				  splitStrings = stringLine.split(":");
				  System.out.println("Name:\t" + splitStrings[0] + " " + splitStrings[1]);
				  System.out.println("Age:\t" + splitStrings[2]);
				  System.out.println("State:\t" + splitStrings[3]);
				  System.out.println(" ");
			}
			
		} catch (FileNotFoundException fnfe) {
			
		} catch (IOException ioe) {
			
		}

	}

}
