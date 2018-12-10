package bank.main;

import java.util.HashMap;
import java.util.Scanner;

public class CustomerScreen {
	private static Scanner input = new Scanner(System.in);

	public CustomerScreen() {

	}

	public void customerOptions() {
		System.out.println("Welcome back " + Bank.customer1.getFirstName() + " " + Bank.customer1.getLastName());
		System.out.println("Account Balance:\n$ " + Bank.customer1.getBalance());
		System.out.println("Options:");
		System.out.println("Type 1 to view personal information.");
		System.out.println("Type 2 to transfer a balance, withdraw from your balance, and or make a deposit.");
		System.out.println("Type 3 to log-out.");
		int number = UserInputValidation.isInRange(input);

		switch (number) {
		case 1: // view personal information
			personalInformation();
			break;
		case 2: // make transfer, withdraw, deposit
			moneyScreen();
			break;
		case 3: // log-out
			System.out.println("Logging out....");
			return;

		}

	}

	public void personalInformation() {
		Bank.customer1.toString();
		System.out.println("Options:");
		System.out.println("Type 1 to edit information.");
		System.out.println("Type 2 to go back to main screen");
		System.out.println("Type 3 to log-out.");
		int number = UserInputValidation.isInRange(input);

		switch (number) {
		case 1: // edit personal information
			break;
		case 2: // main employee screen
			customerOptions();
			break;
		case 3: // log-out
			System.out.println("Logging out....");
			return;
		}
	}

	public void moneyScreen() {
		System.out.println("Options:");
		System.out.println("Type 1 to make a transfer");
		System.out.println("Type 2 to make a deposit");
		System.out.println("Type 3 to make a withdraw");
		System.out.println("Type 4 to go back to main menu");
		System.out.println("Type 5 to log-out");
		int number = UserInputValidation.isInRange(input);

		switch (number) {
		case 1: // edit personal information
			break;
		case 2: // main employee screen
			customerOptions();
			break;
		case 3: // log-out
			System.out.println("Logging out....");
			return;
		case 4: //
		}
	}
	
	public void withdrawScreen() {
		
	}
	public void depositScreen() {
		
	}
	public void transferScreen() {
		
	}

}
