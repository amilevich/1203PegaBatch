//Write a program that demonstrates the switch case. Implement the following functionalities in the cases:java
//Case 1: Find the square root of a number using the Math class method.
//Case 2: Display today’s date.
//Case 3: Split the following string and store it in a string array.
//       	 “I am learning Core Java”

package com.example.switchcase;

import java.time.LocalDate;
import java.util.Scanner;

public class SwitchCase {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 1 to Find the Square Root of a number");
		System.out.println("Enter 2 to find out todays Date");
		System.out.println("Enter 3 to split the string \"I am learing Core Jave\"");
		if(scanner.hasNextInt()) {
			int switchCase = scanner.nextInt();

			switch (switchCase) {
			case 1:
				// Find the square root with math class
				System.out.println("Enter the number you want to find the square root of: ");
				double num = scanner.nextDouble(); // Could make it so that the user input the value
				System.out.println(Math.sqrt(num));
				break;
			case 2:
				// Display todays Date
				// Not sure if there is another way
				// Quick Google gave me this result
				LocalDate today = LocalDate.now();
				//Used methods to make it look nice
				System.out.println("Today is: " + today.getMonth() + " " + today.getDayOfMonth() + ", " + today.getYear());
				break;
			case 3:
				// Split the string "I am learning Core Java"
				// Hard Coding because it is a specific string that needs to be split
				String toSplit = "I am learning Core Java";
				String[] splitString = new String[5];
				splitString = toSplit.split(" ");
				for (int x = 0; x < 5; x++) {
					System.out.println("At Index " + x + ": " + splitString[x]);
				}
				break;
			default:
				System.out.println("Not a Valid Case!");
				break;
				
			}
		}
		scanner.close();
	}
}
