package com.homework1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Question14 {
	
	// Utilize the java Date library to print today's date
	public static void printTime() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		System.out.println("Today's date is: " + df.format(date));
	}
	
	public static void main(String[] args) {
		
		int desiredCase = 3;
		double num = 64;
		String toSplit = "I am learning Core Java";
		
		switch(desiredCase) {
			case 1: System.out.println(Math.sqrt(num));
					break;
			case 2: printTime();
					break;
			// I assume the string should be split on 'space'
			case 3: String[] arr = toSplit.split(" ");
					for (int i=0; i<arr.length; i++) {
						System.out.print(arr[i] + ", ");
					}
					break;
		}
		
	}
}
