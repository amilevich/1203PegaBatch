package bank.main;

import java.util.Scanner;

public class UserInputValidation {
	private static String errorMessage = "Input is wrong data type!";
	private static Scanner input = new Scanner(System.in);

	public static Scanner isInt(Scanner input) {
		while (!input.hasNextInt()) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
		}

		return input;
	}
	public static Scanner isLong(Scanner input) {
		while (!input.hasNextLong()) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
		}

		return input;
	}
	

	public static Scanner isDouble(Scanner input) {
		while (!input.hasNextDouble()) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
		}

		return input;
	}

	public static String isLetters(String s) {
		while (!s.matches("[a-zA-Z]+")) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
			s = input.next();
		}

		return s;
	}

	public static String isLetterNum(String s) {
		while (!s.matches("[a-zA-Z0-9]+")) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
			s = input.next();
		}

		return s;
	}

	public static String isLetterNumLine(String s) {// Need to get it to accept white spaces
		while (!s.matches("[\\sa-zA-Z0-9]+")) {
			System.out.println(errorMessage+2);
			input = new Scanner(System.in);
			s = input.nextLine();
		}

		return s;
	}

	public static String isLetterNumSpecial(String s) {
		while (!s.matches("[a-zA-Z0-9%^!@]+")) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
			s = input.next();
		}

		return s;
	}
	public static String isALetter(String s) {
		while (!s.matches("[a-zA-Z]")) {
			System.out.println(errorMessage);
			input = new Scanner(System.in);
			s = input.next();
		}

		return s;
	}

}
