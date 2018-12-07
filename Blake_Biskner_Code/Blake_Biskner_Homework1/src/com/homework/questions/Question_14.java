package com.homework.questions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Switch Statement
 * @author Blake Biskner
 * @version 1.14
 */

public class Question_14{
	
	public static void main(String[] args) {
		// Variable Initialization
		int switchNum=1; // Change to execute different cases
		double numToRoot=1.0;
		String str="I am learning Core Java";
		
		// Switch Statement
		switch(switchNum) {
		case 1:
			double squareRoot=Math.sqrt(numToRoot);
			System.out.println(squareRoot);
			break;
		case 2:
			DateTimeFormatter dateFormat=DateTimeFormatter.ofPattern("MM/dd/yyyy"); // Formate date
			LocalDateTime today=LocalDateTime.now(); // Get date
			System.out.println(dateFormat.format(today));
			break;
		case 3:
			String strArray[]=str.split(" "); // Parse array on spaces
			for(String word:strArray) {
				System.out.println(word);
			}
			break;
		}
	}
}