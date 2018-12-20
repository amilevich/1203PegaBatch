package com.bank.main;

import java.util.ArrayList;

public class EmployeeScreen {

	public void employeeScreen() {
		System.out.println("\n\n^^^^^^ Employee Home Screen ^^^^^^");
		System.out.println("\n\n"+ Bank.msg.tab + "Enter 1 to approve accounts.");
		System.out.println(Bank.msg.tab + "Enter 2 to view a customer.");
		System.out.println(Bank.msg.tab + "Enter 3 to log-out.");
		int x = UserInputValidation.isInRange(Bank.input, 1, 3);
		System.out.println("\n\n");
		switch (x) {
		case 1: // approve/deny accounts
			approvalDeny();
			break;
		case 2: // view a customer
			int accID = 0;
			do {
				
				System.out.println("Enter customer ID to view them: ");
			Bank.input = UserInputValidation.isInt(Bank.input);
			accID = Bank.input.nextInt();

			}while(!Bank.accDao1.accountExist(accID));
			Bank.account = Bank.accDao1.getAccount(accID);
			Bank.personalInfo2 = Bank.personalDao1.getPersonalInfo(accID);
			menu();
			break;
		case 3: // log-out
			Bank.customerView.logout();
			break;
		}
		// approvalDeny();
//		menu();

	}

	public void personalInformation() {
		System.out.println(Bank.personalInfo2.toString());
		menu();
	}

	public void whichAccount(ArrayList<Integer> arr) { // Accounts needing approval
		int count = 0;
		int accountNumber = 0;
		if (arr.size() > 1) {
			System.out.println("\n\n<---- BEGIN ---- Accounts for approval ---- BEGIN ---->\n\n");
			for (Integer x : arr) {
				System.out.println("Account # " + x + "\n ID number: " + count+ "\n\n");
				count++;
			}
			System.out.println("\n\n<---- END ---- Accounts for approval ----END---->\n\n");
			do {
				System.out.print("\n\nEnter the account ID number that you want to approve: ");
				UserInputValidation.isInt(Bank.input);
				accountNumber = Bank.input.nextInt();
			} while (accountNumber < 0 && accountNumber > arr.size());
			Bank.accDao1.updateAccountOpen(arr.get(accountNumber));
			System.out.println("Account approved!\nRemoving from list....\n\n");
			arr.remove(accountNumber);
			
			if (!arr.isEmpty()) {
				System.out.println(Bank.msg.tab + "Enter 1 to approve another account.");
				System.out.println(Bank.msg.tab + "Enter 2 to go back to main menu.");
				int x = UserInputValidation.isInRange(Bank.input, 1, 2);

				switch (x) {
				case 1: // remove another account
					whichAccount(arr);
					break;

				case 2: // go back to main menu;
					employeeScreen();
					break;
				}
			}
//			employeeScreen();
		}
	}

	public void approvalDeny() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr = Bank.accDao1.accountsClosed();
		whichAccount(arr);
	}

	public void menu() {
		System.out.println("\n\n^^^^^^"+ " Viewing " + Bank.personalInfo2.getFirstName() + " " + Bank.personalInfo2.getLastName() +" ^^^^^^");
		System.out.println("\nMenu Options: \n");
		System.out.println(Bank.msg.tab + "Enter 1 for personal information.");
		System.out.println(Bank.msg.tab + "Enter 2 for account information.");
		System.out.println(Bank.msg.tab + "Enter 3 log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 3);
		options(number);
	}

	public void options(int number) {
		switch (number) {
		case 1: // view personal information
			personalInformation();
			break;
		case 2: // view account information
			accountInformation();
			menu();
			break;
		case 3: // log-out
			Bank.customerView.logout();
			break;

		}
	}

public void accountInformation() {
	Bank.account2.whichAccount2();
	System.out.println("\n\nAccount #: " + Bank.account2.getAccountID());
	System.out.println("\nAccount type: " + Bank.account2.getAccountType());
	System.out.println("\nAccount balance: " + Bank.account2.getBalance());
	System.out.println("\nNumber of Accounts: " + Bank.account2.getAccountSize());
}

}
