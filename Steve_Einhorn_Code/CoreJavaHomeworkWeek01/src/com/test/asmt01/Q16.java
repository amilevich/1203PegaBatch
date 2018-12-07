package com.test.asmt01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16 {

	public static void main(String[] args) throws IOException {
		
		String inputString;
		
//		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
//		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		
//		System.out.println("Please enter a String.");
		
//		inputString = bufferedReader.readLine();
		
		inputString = args[0];
		
		System.out.println("Your entered String is: " + inputString);
		System.out.println("It has " + inputString.length() + " characters.");

	}

}
