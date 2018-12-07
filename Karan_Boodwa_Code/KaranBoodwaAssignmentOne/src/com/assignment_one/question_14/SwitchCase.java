package com.assignment_one.question_14;

/*
 * Q14. Write a program that demonstrates the switch case. Implement the following functionalities in the cases:java
 * Case 1: Find the square root of a number using the Math class method.
 * Case 2: Display today’s date.
 * Case 3: Split the following string and store it in a string array.
 * 			“I am learning Core Java”
 */
public class SwitchCase {

	public static void main(String[] args) {
		// Loops through all cases
		for (int choice = 1; choice < 4; choice++) {
			System.out.println("Case " + choice);

			switch (choice) {

			// Case 1: Find the square root of a number using the Math class method.
			case 1:
				double n = 16.0;
				System.out.println("The Square root of " + n + " is " + Math.sqrt(n));
				break;

			// Case 2: Display today’s date.
			case 2:
				System.out.println(java.time.LocalDate.now());
				break;

			// Case 3: Split the following string and store it in a string array: “I am
			// learning Core Java”
			case 3:
				String toSplit = "I am learning Core Java";
				String toSplitArray[] = toSplit.split(" ");

				for (String str : toSplitArray) {
					System.out.println(str);
				}
				break;

			default:
				break;
			}
			System.out.println("");
		}

	}

}