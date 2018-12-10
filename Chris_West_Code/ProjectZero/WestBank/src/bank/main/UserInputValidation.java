package bank.main;

import java.util.Scanner;

public class UserInputValidation {
	private static String errorMessage = "Input is wrong data type!";
	//private static Scanner input = new Scanner(System.in);

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
			Bank.input = new Scanner(System.in);
			s = Bank.input.next();
		}

		return s;
	}

	public static String isLetterNum(String s) {
		while (!s.matches("[a-zA-Z0-9]+")) {
			System.out.println(errorMessage);
			Bank.input = new Scanner(System.in);
			s = Bank.input.next();
		}

		return s;
	}

	public static String isLetterNumLine(String s) {// Need to get it to accept white spaces
		while (!s.matches("[\\sa-zA-Z0-9]+")) {
			System.out.println(errorMessage+2);
			Bank.input = new Scanner(System.in);
			s = Bank.input.nextLine();
		}

		return s;
	}

	public static String isLetterNumSpecial(String s) {
		while (!s.matches("[a-zA-Z0-9%^!@]+")) {
			System.out.println(errorMessage);
			Bank.input = new Scanner(System.in);
			s = Bank.input.next();
		}

		return s;
	}
	public static String isALetter(String s) {
		while (!s.matches("[a-zA-Z]")) {
			System.out.println(errorMessage);
			Bank.input = new Scanner(System.in);
			s = Bank.input.next();
		}

		return s;
	}
	public static int isInRange(Scanner input, int min, int max) {
		
		int countError = 0, number = 0;
		do {
			if (countError > 0)
				System.out.println("Number needs to be a number from " + min + " to " + max);
			input = UserInputValidation.isInt(input);
			number = input.nextInt();
			countError++;
		} while (!(number >= min && number <= max));
		
		return number;
		
	}

}
