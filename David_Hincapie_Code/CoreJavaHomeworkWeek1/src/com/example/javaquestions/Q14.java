package com.example.javaquestions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Q14 {
	// Q14.
	// Write a program that demonstrates the switch case. Implement the following
	// functionalities in the cases:java
	// Case 1: Find the square root of a number using the Math class method.
	// Case 2: Display today’s date.
	// Case 3: Split the following string and store it in a string array.
	// “I am learning Core Java”

	public static void main(String[] args) {
		int num = 3;
		switch (num) {
		case 1:
			System.out.println(Math.sqrt(25));
			break;
		case 2:
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));
			break;
		case 3:
			String string = "I am learning Core Java";
			String[] array = new String[string.length()];
			for (int i = 0; i < string.length(); i++) {
				array[i] = string.substring(i, i + 1);
			}
			for (String value : array) {
				System.out.print(value);
			}
			break;

		default:
			break;
		}
	}

}
