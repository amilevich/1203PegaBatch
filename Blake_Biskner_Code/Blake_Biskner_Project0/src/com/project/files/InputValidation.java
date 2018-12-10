package com.project.files;

import java.util.Scanner;

/**
 * Utility Class for Input Validation (cannot be instantiated= like Math)
 * 
 * @author Blake Biskner
 */

public class InputValidation {

	private static final String ERROR_MESSAGE = "Please Enter Valid Input";

	/**
	 * Validates input for menu with integer switch statement
	 * 
	 * @param userIn
	 * @param maxOp
	 * @return returns the validated integer corresponding to a menu option
	 */

	public static int optionValidate(Scanner userIn, int maxOp) {
		int userNum = 0;
		int minOp = 1;
		do {
			while (!userIn.hasNextInt()) {
				userIn.next(); // Need this line to get next input or loops infinitely
				System.out.println(ERROR_MESSAGE);
			}
			userNum = userIn.nextInt();
			// IMPORTANT
			// The nextLine() below is used to take in the extra \n left after nextInt()
			// Without it Scanner will not work properly for future nextLine() calls
			// It will take in the \n without stopping to wait for user input
			// DO NOT DELETE
			userIn.nextLine();

			if ((userNum < minOp) || (userNum > maxOp)) {
				System.out.println(ERROR_MESSAGE);
			}
		} while ((userNum < minOp) || (userNum > maxOp));
		return userNum;
	}

	/**
	 * Method to validate user input first and last name
	 * 
	 * @param userIn
	 * @return returns the String array consisting of first and last name
	 */

	public static String[] nameValidate(Scanner userIn) {
		// Variable Declaration and Initialization
		String userStrArray[];
		String userStr;

		int nameLength = 2; // Desired length (ie first and last)
		boolean correctIn = false;

		String inputSyntax = "Please Enter in Form \"First_Name Last_Name\"";

		do {
			do {
				System.out.println(inputSyntax);
				userStr = userIn.nextLine(); // Get name from scanner
				correctIn = confirm(userIn, userStr); // Confirm user input what they desired
			} while (correctIn != true); // Loops until user says they entered desired input
			userStrArray = userStr.split(" "); 
			if (userStrArray.length != nameLength) { // Check that they entered two names (first last) with array length
				System.out.println(ERROR_MESSAGE);
			}
		} while (userStrArray.length != nameLength); // If they entered too many or too few names
		return userStrArray;
	}

	/**
	 * Method to echo user input and ensure their data is what they intended
	 * 
	 * @param userIn
	 * @param inputStr
	 * @return returns true if user input is as desired and false if not
	 */

	private static boolean confirm(Scanner userIn, String inputStr) {
		int maxOption = 2; // Only two input choices yes or no
		boolean correctInput = true;
		String message1 = "You Entered ";
		String message2 = "\nIs This Correct\n[1] Yes \n[2] No";
		System.out.println(message1 + inputStr + message2);

		int userNum = optionValidate(userIn, maxOption);
		switch (userNum) {
		case 1: // They selected [1] Yes
			return correctInput;
		case 2: // They selected [2] No
		default: // Default coupled with 2 to ensure correctInput is assigned a value
			correctInput = false;
			return correctInput;
		}
	}
}
