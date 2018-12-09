package bank.main;

import java.util.HashMap;
import java.util.Scanner;

public class Bank {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * Declarations in General
		 */
		String[] txtFile = { "UAccounts.txt", "InProgress.txt" };
		String txt;
		Boolean isRead;
		HashMap<String, String> map = new HashMap<String, String>();
		int number, countError = 0;
		/*
		 * Object Declarations
		 */
		HRDEPT hr1;;
		DataHub dh1;;
		User user1;;
		Registration reg;;

		System.out.println("Welcome to West Bank\nPlease choose one of the "
				+ "following:\nEnter 1 to login to your account.\nEnter 2 to create an account.\nEnter 3 to exit.");
		do {
			if (countError > 0)
				System.out.println("Number needs to be 1 or 2 or 3.");
			while (!UserInputValidation.isInt(input)) {
				input = new Scanner(System.in);
			}
			number = input.nextInt();
			countError++;
		} while (number >= 1 && number <= 3);

		switch (number) {
		case 1: login();
			break;
		case 2: register();
			break;
		case 3: System.exit(0);
			break;
			
		}

	}
	public static void login() {
		System.out.println("Enter ID: ");
		String id = input.nextLine();
		while (!UserInputValidation.isLetters(id)) {
			input = new Scanner(System.in);
			id = input.nextLine();
		}
		System.out.println("Enter password: ");
		String passWord = input.next();
		while (!UserInputValidation.isLetterNumSpecial(id)) {
			input = new Scanner(System.in);
			passWord = input.nextLine();
		}
	}
	
	public static void register() {
		
	}
	
	public static void validate() {
		
	}

}
