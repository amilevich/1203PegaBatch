package com.bank.main;



public class AdminScreen extends EmployeeScreen { // Look at (1) in Bank.java


	public void adminScreen() {
		employeeScreen();
		
	}

	@Override
	public void menu() {
		System.out.println("\n\n^^^^^^"+ " Viewing " + Bank.personalInfo2.getFirstName() + " " + Bank.personalInfo2.getLastName() +" ^^^^^^");
		System.out.println("\nMenu Options: \n");
		System.out.println(Bank.msg.tab + "Enter 1 for personal information.");
		System.out.println(Bank.msg.tab + "Enter 2 for account information.");
		System.out.println(Bank.msg.tab + "Enter 3 for withdraw/deposits/transfers.");
		System.out.println(Bank.msg.tab + "Enter 4 for account deletion.");
		System.out.println(Bank.msg.tab + "Enter 5 to log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 5);
		switch (number) {
		case 1: // view personal information
			personalInformation();

			break;
		case 2: // view account information
			accountInformation();
			menu();
			break;
		case 3:

			moneyScreen();
			break;
		case 4:
			Bank.customerView.deleteAccount();
		case 5: // log-out
			Bank.customerView.logout();
			break;

		}
	}

	public void moneyScreen() {
		System.out.println("\n###### Money Screen ######\nMenu Options:");
		System.out.println(Bank.msg.tab + "Enter 1 to make a transfer");
		System.out.println(Bank.msg.tab + "Enter 2 to make a deposit");
		System.out.println(Bank.msg.tab + "Enter 3 to make a withdraw");
		System.out.println(Bank.msg.tab + "Enter 4 to go back to main menu");
		System.out.println(Bank.msg.tab + "Enter 5 to log-out");
		int number = UserInputValidation.isInRange(Bank.input, 1, 5);

		switch (number) {
		case 1:
			Bank.customerView.transferScreen();
			moneyScreen();
			break;
		case 2:
			Bank.customerView.depositScreen();
			moneyScreen();
			break;
		case 3:
			Bank.customerView.withdrawScreen();
			moneyScreen();
			break;
		case 4:
			menu();
			break;
		case 5:
			Bank.customerView.logout();
			break;
		}
	}


}
