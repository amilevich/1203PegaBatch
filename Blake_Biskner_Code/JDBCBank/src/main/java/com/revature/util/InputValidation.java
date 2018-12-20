package com.revature.util;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.revature.driver.Driver;

/**
 * Input Validation Utility Class
 * 
 * @author Blake Biskner
 * @version 2.0
 */

public class InputValidation {

	private static final String ERROR_MESSAGE = "Please Enter Valid Input";

	// Public Methods

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

		String inputSyntax = "Please Enter Name in Form \"First_Name Last_Name\"";

		do {
			userStr = confirmDriver(userIn, inputSyntax);
			userStrArray = userStr.split(" ");
			if (userStrArray.length != nameLength) { // Check that they entered two names (first last) with array length
				System.out.println(ERROR_MESSAGE);
			}
		} while (userStrArray.length != nameLength); // If they entered too many or too few names
		return userStrArray;
	}

	/**
	 * Method to Validate Year Put in for user Birthday
	 * 
	 * @parm userIn
	 * @return returns the user age
	 */

	public static int ageValidate(Scanner userIn) {
		// Variable Declaration and Initialization
		// User Variables
		String userStr = "";
		int age = 0;
		int userYear = 0;
		// Method Values
		int minYear = 1868; // Oldest man was 146 so person should have birth year before this year
		int minAge = 18; // Minimum age to open account
		String minAgeMessage = "Those Under 18 Must Apply for Joint Account";
		String inputSyntax = "Please Enter Year of Birth in Form \"yyyy\"";

		// Create a date variable to find current year
		// This will be used to determine if user is old enough (by year) for account
		LocalDateTime date = LocalDateTime.now();
		int currYear = date.getYear();
		// Get Programmer Validated Input
		do {
			// Get User Validated Input
			userStr = confirmDriver(userIn, inputSyntax);
			if (intTest(userStr) == true) {
				// Set userYear only if input string can convert to integer
				// Otherwise leave it at 0
				userYear = Integer.valueOf(userStr);
			}
			if ((userYear < minYear) || (userYear > currYear)) {
				// Executes for invalid input (invalidYear), too old, or too young
				// (ie born after this year in the future)
				System.out.println(ERROR_MESSAGE);
			}
		} while ((userYear < minYear) || (userYear > currYear));
		age = currYear - userYear;
		if (age < minAge) {
			System.out.println(minAgeMessage);
		}
		return age;
	}

	/**
	 * Method to Validate User Social Security Number
	 * 
	 * @param userIn
	 * @return returns a String representing social security number
	 */

	public static String socSecValidate(Scanner userIn) {
		// Variable Declaration and Initialization
		String userStr = "";
		int socSecLen = 9;
		String inputSyntax = "Please Enter Social Security in Form \"nnnnnnnnnn\"";
		do {
			userStr = confirmDriver(userIn, inputSyntax);
			if ((intTest(userStr) != true) || (userStr.length() != socSecLen)) {
				// Check if of wrong type (has nonnumeric chars) or wrong length
				System.out.println(ERROR_MESSAGE);
			}
		} while ((intTest(userStr) != true) || (userStr.length() != socSecLen));
		return userStr;
	}

	/**
	 * Method to Ensure Username is User Verified, Unique, and Composed Correctly
	 * 
	 * @parm userIn
	 * @return returns the username String
	 */

	public static String usernameValidate(Scanner userIn) {
		// Variable Declaration and Initialization
		String userStr = "";
		String inputSyntax = "Please Enter Username Consisting of Letters and Digits ONLY";
		String uniqueMessage = "Username Already Taken";
		boolean nameTaken;
		do {
			userStr = confirmDriver(userIn, inputSyntax);
			if (Driver.customers.containsKey(userStr)) {
				nameTaken = true;
			} else {
				nameTaken = false;
			}
			if (nameTaken == true) {
				System.out.println(uniqueMessage);
			} else if (numLetterTest(userStr) != true) {
				System.out.println(ERROR_MESSAGE);
			}
		} while ((nameTaken == true) || (numLetterTest(userStr) != true));
		return userStr;
	}

	/**
	 * Method to Ensure User Password is User Approved and of Digits and Letters
	 * 
	 * @param userIn
	 * @return returns password String
	 */

	public static String passwordValidate(Scanner userIn) {
		// Variable Declaration and Initialization
		String userStr = "";
		String inputSyntax = "Please Enter Password Consisting of Numbers and Digits ONLY";
		do {
			userStr = confirmDriver(userIn, inputSyntax);
			if (numLetterTest(userStr) != true) {
				System.out.println(ERROR_MESSAGE);
			}
		} while (numLetterTest(userStr) != true);
		return userStr;
	}

	/**
	 * Method to Determine and Validate Account Type Selection
	 * 
	 * @param userIn
	 * @return returns 'J' char for joint and 'P' char for personal
	 */

	public static int acctTypeValidate(Scanner userIn) {
		int selection = 0;
		int maxOptions = 2;
		String inputSyntax = "Please Select an Account Type (Those Under 18 Must Select Joint)\n[1] Personal\n[2] Joint";
		System.out.println(inputSyntax);
		selection = optionValidate(userIn, maxOptions);
		if (selection == 1) {
			return 1; // Returns 1 for personal
		} else {
			return 2; // Returns 2 for joint
		}
	}

	/**
	 * Method to Validate Username
	 * 
	 * @param userIn
	 * @return the customer validated String
	 */

	public static String existUserNameValidate(Scanner userIn) {
		String userStr;
		String inputSyntax = "Please Enter UserName";
		userStr = confirmDriver(userIn, inputSyntax);
		return userStr;
	}

	/**
	 * Method to Validate Username
	 * 
	 * @param userIn
	 * @return user validated password
	 */

	public static String existPasswordValidate(Scanner userIn) {
		String userStr;
		String inputSyntax = "Please Enter Password";
		userStr = confirmDriver(userIn, inputSyntax);
		return userStr;
	}

	/**
	 * Method to Validate EMployee Id
	 *
	 * @param userIn
	 * @return user validated id number
	 */

	public static Integer existEmployeeIdValidate(Scanner userIn) {
		String userStr;
		String inputSyntax = "Please Enter Employee Id";
		do {
			userStr = confirmDriver(userIn, inputSyntax);
		} while (intTest(userStr) != true);
		return Integer.valueOf(userStr);
	}

	/**
	 * Method to Vet Transfer Number
	 * 
	 * @param userIn
	 * @return Integer Account Number
	 * 
	 */

	public static Integer acctNumValidate(Scanner userIn) {
		String userStr;
		String inputSyntax = "Please Enter Account Number";
		do {
			userStr = confirmDriver(userIn, inputSyntax);
		} while (intTest(userStr) != true);
		return Integer.valueOf(userStr);
	}

	/**
	 * Method to Ensure Transfer Number Exists
	 * 
	 * @param acctNum
	 * @return Boolean indicating whether account exists
	 * 
	 */

	public static Boolean acctExistValidate(int acctNum) {
		boolean acctExists = true;
		if (Driver.accounts.containsKey(acctNum) == true) {
			return acctExists;
		} else {
			acctExists = false;
			return acctExists;
		}
	}

	// Private Methods

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

	/**
	 * Driver Method for Confirming Input which Loops Until User Confirms Input
	 * 
	 * @param userIn
	 * @param message
	 * @return returns true if user confirmed input
	 */

	private static String confirmDriver(Scanner userIn, String message) {
		String userStr;
		boolean correct;
		do {
			System.out.println(message);
			userStr = userIn.nextLine();
			correct = confirm(userIn, userStr);
		} while (correct != true);
		return userStr;
	}

	/**
	 * Tests if Input String Can be Converted to Integer (to avoid exception)
	 * 
	 * @param intStr
	 * @return returns true if can convert to int and false otherwise (has
	 *         nonnumeric characters)
	 */

	private static boolean intTest(String intStr) {
		boolean isInt = true;
		char testChar;
		for (int i = 0; i < intStr.length(); i++) {
			testChar = intStr.charAt(i);
			if (Character.isDigit(testChar) != true) {
				isInt = false;
				return isInt;
			}
		}
		return isInt;
	}

	/**
	 * Method to Determine if String Composed of Numbers and Digits
	 * 
	 * @param intStr
	 * @return returns true if String only mad up of letters and digits
	 */

	private static boolean numLetterTest(String intStr) {
		boolean isNumLet = true;
		char testChar;
		for (int i = 0; i < intStr.length(); i++) {
			testChar = intStr.charAt(i);
			if (Character.isLetterOrDigit(testChar) != true) {
				isNumLet = false;
				return isNumLet;
			}
		}
		return isNumLet;
	}
}
