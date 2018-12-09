package bank.main;

import java.util.Scanner;

public class UserInputValidation {
	private static String errorMessage = "Input is wrong data type!";
	public static Boolean isInt(Scanner input) {
		if (input.hasNextInt()) {
			return true;
		}
		System.out.println(errorMessage);
		return false;
	}

	public static Boolean isDouble(Scanner input) {
		if (input.hasNextDouble()) {
			return true;
		}
		System.out.println(errorMessage);
		return false;
	}

	public static Boolean isString(String input) {
		
		if (input.matches("[a-zA-Z]+")) {
			return true;
		}
		System.out.println(errorMessage);
		return false;
	}
}
