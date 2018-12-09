package bank.main;

import java.util.HashMap;
import java.util.Scanner;

public class Bank {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * Declarations in General
		 */
		String[] txtFile = {"UAccounts.txt", "InProgress.txt"};
		String txt;
		Boolean isRead;
		HashMap<String, String> map = new HashMap<String, String>();
		
		/*
		 * Object Declarations
		 */	
		HRDEPT hr1 = new HRDEPT();
		DataHub dh1 = new DataHub();
		User user1 = new User();
		Registration reg = new Registration();
		
		System.out.println(
				"Welcome to West Bank\nPlease choose one of the "
				+ "following:\nEnter 1 to login to your account.\nEnter 2 to create an account");
		
		while(!UserInputValidation.isInt(input)) {
			input = new Scanner(System.in);
			
		}
		int number = input.nextInt();
		
		switch (number) {

		}

	}

}
