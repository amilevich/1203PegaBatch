package bank.main;

import java.util.Scanner;

public class CustomerScreen {
	private static Scanner input = new Scanner(System.in);

	public CustomerScreen() {

	}

	public void customerScreen() {
		System.out.println("Welcome back " + Bank.customer1.getFirstName() + " " + Bank.customer1.getLastName());
		System.out.println("Account Balance:\n$ " + Bank.customer1.getBalance());
		System.out.println("Menu Options:");
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
			logout();
			break;

		}

	}

	public void personalInformation() {
		Bank.customer1.toString();
		System.out.println("Personal Information Screen\nMenu Options:");
		System.out.println("Type 1 to edit information.");
		System.out.println("Type 2 to go back to main screen");
		System.out.println("Type 3 to log-out.");
		int number = UserInputValidation.isInRange(input);

		switch (number) {
		case 1: // edit personal information
			editInformation();
			break;
		case 2: // main employee screen
			customerScreen();
			break;
		case 3: // log-out
			logout();
			break;
		}
	}

	public void moneyScreen() {
		System.out.println("Money Screen\nMenu Options:");
		System.out.println("Type 1 to make a transfer");
		System.out.println("Type 2 to make a deposit");
		System.out.println("Type 3 to make a withdraw");
		System.out.println("Type 4 to go back to main menu");
		System.out.println("Type 5 to log-out");
		int number = UserInputValidation.isInRange(input);

		switch (number) {
		case 1:
			transferScreen();
			break;
		case 2:
			depositScreen();
			break;
		case 3:
			withdrawScreen();
			return;
		case 4:
			customerScreen();
			break;
		case 5:
			logout();
			break;
		}
	}

	public void withdrawScreen() {
		System.out.println("How much would you like to withdraw? \nCurrent Balance: \n$" + Bank.customer1.getBalance());
		input = UserInputValidation.isDouble(input);
		double withdrawAmount = input.nextDouble();
		double temp = Bank.account1.withdrawMoney(withdrawAmount);
		if(temp >= 0) {
			Bank.customer1.setBalance(temp);
			System.out.println("Your withdraw was sucessful!\nNew Balance: \n$" + Bank.customer1.getBalance());
		}else {
			System.out.println("You don't have sufficient funds!");
		}
		moneyScreen();
	}

	public void depositScreen() {
		System.out.println("How much would you like to deposit? \nCurrent Balance: \n$" + Bank.customer1.getBalance());
		input = UserInputValidation.isDouble(input);
		
		double depositAmount = input.nextDouble();
		if (depositAmount > 0) {
		double temp = Bank.account1.depositMoney(depositAmount);
		
			Bank.customer1.setBalance(temp);
			System.out.println("Your deposit was sucessful!\nNew Balance: \n$" + Bank.customer1.getBalance());
		}else {
			System.out.println("You entered either a zero or negative number. No nothing occurred.");
		}
		moneyScreen();
		
	}

	public void transferScreen() {

	}

	public void editInformation() {

	}

	public void logout() {
		System.out.println("Logging out....");
		return;

	}
//	public void options() {
//		System.out.println("\nMenu Options:");
//		System.out.println("Type 1 to go back to main menu");
//		System.out.println("Type 2 to go back to main menu");
//		System.out.println("Type 3 to log-out");
//		int number = UserInputValidation.isInRange(input);
//
//		switch (number) {
//
//		case 1:
//			customerScreen();
//			break;
//		case 2:
//			logout();
//			break;
//		}
//	}

}
