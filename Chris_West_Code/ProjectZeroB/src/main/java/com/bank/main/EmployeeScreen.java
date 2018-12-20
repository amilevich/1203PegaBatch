package com.bank.main;

import java.util.ArrayList;

public class EmployeeScreen {

	public void employeeScreen() {
		System.out.println("\n\n"+ Bank.msg.tab + "Enter 1 to approve accounts.");
		System.out.println(Bank.msg.tab + "Enter 2 to view a customer.");
		System.out.println(Bank.msg.tab + "Enter 3 to log-out.");
		int x = UserInputValidation.isInRange(Bank.input, 1, 3);
		
		switch (x) {
		case 1: // approve/deny accounts
			approvalDeny();
			break;
		case 2: // view a customer
			int accID = 0;
			do {
				System.out.println("Enter userID: ");
			Bank.input = UserInputValidation.isInt(Bank.input);
			accID = Bank.input.nextInt();

			}while(!Bank.accDao1.accountExist(accID));
			Bank.account2 = Bank.accDao1.getAccount(accID);
			Bank.personalInfo2 = Bank.personalDao1.getPersonalInfo(accID);
			menu();
			break;
		case 3: // log-out
			Bank.customerView.logout();
			break;
		}
		// approvalDeny();
		menu();

	}

	public void personalInformation() {
		System.out.println(Bank.personalInfo2.toString());
		menu();
	}

	public void whichAccount(ArrayList<Integer> arr) { // Accounts needing approval
		int count = 0;
		int accountNumber = 0;
		if (arr.size() > 1) {
			for (Integer x : arr) {
				System.out.println("Account # " + x + "\n ID number: " + count);
				count++;
			}
			do {
				System.out.print("Enter the account ID number that you want to access: ");
				UserInputValidation.isInt(Bank.input);
				accountNumber = Bank.input.nextInt();
			} while (accountNumber < 0 && accountNumber > arr.size());
			Bank.accDao1.updateAccountOpen(arr.get(accountNumber));
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
			employeeScreen();
		}
	}

	public void approvalDeny() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr = Bank.accDao1.accountsClosed();
		whichAccount(arr);
	}

	public void menu() {
		System.out.println("\nMenu Options: ");
		System.out.println(Bank.msg.tab + "Enter 1 for personal information of " + Bank.personalInfo2.getFirstName());
		System.out.println(Bank.msg.tab + "Enter 2 for account information of " + Bank.personalInfo2.getFirstName());
		System.out.println(Bank.msg.tab + "Enter 3 for account balances of " + Bank.personalInfo2.getFirstName());
		System.out.println(Bank.msg.tab + "Enter 4 log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 4);
		options(number);
	}

	public void options(int number) {
		switch (number) {
		case 1: // view personal information
			personalInformation();
			break;
		case 2: // view account information
//			System.out.println("\nID: " + Bank.customer1.getId() + "\nRegular: " + Bank.customer1.getrAcc()
//					+ "\nJoint: " + Bank.customer1.getjAcc());
			menu();
			break;
		case 3:
			// getBalances();

			break;
		case 4: // log-out
			Bank.customerView.logout();
			break;

		}
	}

//	public void getBalances() {
//		if (Bank.customer1.getrAcc() == true) {
//			System.out.println("\nAccount Balances: ");
//			System.out.println(Bank.customer1.getBalance());
//		}
//
//		if (Bank.customer1.getjAcc() == true) {
//			System.out.println("\nJoint Account Balances");
//			ArrayList<String> jAcc = new ArrayList<String>();
//			jAcc = Bank.joint.get(Bank.customer1.getId());
//			for (String x : jAcc) {
//				Bank.customer2 = (Customer) Bank.data.get(x);
//				System.out.println(Bank.customer2.getId() + "\nBalance: " + Bank.customer2.getBalance());
//			}
//		}
//		System.out.println("");
//	}

}
