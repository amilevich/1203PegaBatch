package com.bank.main;

import java.util.Random;

public class CustomerScreen { // Look at (1) in Bank.java
	public void newCustomerScreen() {
		System.out.println("\n\n^^^^^^ Customer Home Screen ^^^^^^");
		System.out.println(
				"\n\nWelcome " + Bank.personalInfo1.getFirstName() + " " + Bank.personalInfo1.getLastName() + "!");
		Bank.msg.newCustomerScreenMsg();
		int number = UserInputValidation.isInRange(Bank.input, 1, 3);
		switch (number) {
		case 1: // view personal information
			personalInformation(0);
			break;
		case 2: // add an account
			addAccount(0);
			break;
		case 3: // log-out
			 logout();
			break;

		}
	}

	public void customerScreen() {
		System.out.println("\n\n^^^^^^ Customer Home Screen ^^^^^^");
		System.out.println(
				"\nWelcome back " + Bank.personalInfo1.getFirstName() + " " + Bank.personalInfo1.getLastName() + "!");
		System.out.println("Account Balance:\n$ " + Bank.account.getBalance());
		Bank.msg.customerScreen();
		int number = UserInputValidation.isInRange(Bank.input, 1, 5);

		switch (number) {
		case 1: // view personal information
			personalInformation(1);
			break;
		case 2: // add an account
			addAccount(1);
			break;
		case 3: // make transfer, withdraw, deposit
			moneyScreen();
			break;
		case 4:
			deleteAccount();
			break;
		case 5: // log-out
			 logout();
			break;

		}

	}
	public void deleteAccount() {
		Bank.account.whichAccount();
		if (Bank.account.getBalance() == 0.0) {
			if (Bank.account.getAccountType() != "JOINT") {
				Bank.accDao1.removeAccount();
				Bank.account = Bank.accDao1.getAccount(Bank.user1.getId());
			}else {
				Bank.accDao1.removeJointAccount();
				Bank.accDao1.removeAccount();
				Bank.account = Bank.accDao1.getAccount(Bank.user1.getId());
			}
		}else {
			System.out.println("Can't delete the account because you got a balance.");
		}
	}
	public void addAccount(int x) {
		Bank.msg.addAccountMsg();
		int number = UserInputValidation.isInRange(Bank.input, 1, 4);
		int accountNum = 0;
		switch (number) {
		case 1: // add savings account
			accountNum = createAccountNumber();
			Bank.accDao1.addNewAccount(accountNum, "SAVINGS");
			Bank.msg.processingRegMsg();
			break;
		case 2: // add checking account
			accountNum = createAccountNumber();
			Bank.accDao1.addNewAccount(accountNum, "CHECKING");
			Bank.msg.processingRegMsg();

			break;
		case 3: // add joint account come back to this feature
			addJoint();
			break;
		case 4: // log-out
			 logout();
			break;

		}
		if (x == 1) {
			customerScreen();
		} else
			newCustomerScreen();
	}
	public void addJoint() {
		int accountNum = 0;
		String userid = "";
		accountNum = Bank.account.createAccountNumber();
		do {
			System.out.println("\nPlease enter a valid email/userid: ");
			userid = Bank.input.next();
			UserInputValidation.isLetterNumSpecial(userid);
			
			
		}while (!Bank.Bankuser.userIdCheck(userid,userid));
		Bank.accDao1.addNewAccount(accountNum, "JOINT");
		Bank.accDao1.addJointAccount(accountNum, Bank.Bankuser.userId(userid,userid));
	}

	public void personalInformation(int x) {
		Bank.msg.personalInfoMsg();
		System.out.println("\n\n" + Bank.personalInfo1.toString());
		int number = UserInputValidation.isInRange(Bank.input, 1, 2);

		switch (number) {
		case 1: // main employee screen
			if (x == 1) {
				customerScreen();
			} else
				newCustomerScreen();

			break;
		case 2: // log-out
			 logout();
			break;
		}
	}

	public void moneyScreen() {
		Bank.msg.moneyScreen();
		int number = UserInputValidation.isInRange(Bank.input, 1, 5);

		switch (number) {
		case 1:
			Bank.account.whichAccount();
			transferScreen();
			break;
		case 2:
			Bank.account.whichAccount();
			depositScreen();
			break;
		case 3:
			Bank.account.whichAccount();
			withdrawScreen();
			break;
		case 4:
			customerScreen();
			break;
		case 5:
			 logout();
			break;
		}
		moneyScreen();

	}

	public void withdrawScreen() {
		System.out.println("<---- Withdraw Screen ---->");
		System.out.println("\nHow much would you like to withdraw? \nCurrent Balance: \n$" + Bank.account.getBalance());
		Bank.input = UserInputValidation.isDouble(Bank.input);
		double withdrawAmount = Bank.input.nextDouble();
		double temp = Bank.account.withdrawMoney(withdrawAmount);

		if (temp >= 0) {
			Bank.account.setBalance(temp);
			System.out.println("Your withdraw was sucessful!\nNew Balance: \n$" + Bank.account.getBalance());
		} else {
			System.out.println("You don't have sufficient funds!");
		}
		Bank.accDao1.updateAccountBalance();
	}

	public void depositScreen() {
		System.out.println("<---- Deposit Screen ---->");
		System.out.println("\nHow much would you like to deposit? \nCurrent Balance: \n$" + Bank.account.getBalance());
		Bank.input = UserInputValidation.isDouble(Bank.input);

		double depositAmount = Bank.input.nextDouble();
		if (depositAmount > 0) {
			double temp = Bank.account.depositMoney(depositAmount);

			Bank.account.setBalance(temp);
			System.out.println("Your deposit was sucessful!\nNew Balance: \n$" + Bank.account.getBalance());
		} else {
			System.out.println("You entered either a zero or negative number. No nothing occurred.");
		}
		Bank.accDao1.updateAccountBalance();

	}

	public void transferScreen() {
		System.out.println("<---- Transfer Screen ---->");
		System.out.println(
				"\nHow much would you like to transfer? \nCurrent Balance: \n$" + Bank.account.getBalance());
		Bank.input = UserInputValidation.isDouble(Bank.input);

		double transferAmount = Bank.input.nextDouble();
		System.out.println("\nWho would you like to transfer to?");
		Bank.account.transferTo();
		if ((transferAmount > 0) & (transferAmount <= Bank.account.getBalance())) {
			double temp = Bank.account2.transferFunds(transferAmount);
			
			Bank.account.setBalance(temp);
			Bank.account2.setBalance(Bank.account2.getBalance() + transferAmount);
			// System.out.println(Bank.personalInfo2.getBalance());
			System.out.println("\nYour transfer was sucessful!\nNew Balance: \n$" + Bank.account.getBalance());
		} else {
			System.out.println(
					"\nYou entered either a zero or negative number. Wrong account name. Or you don't have enough money. No nothing occurred.");
		}
		Bank.accDao1.updateAccountBalance();
		Bank.accDao1.updateAccountBalance2();

	}

	public void logout() {
		System.out.println("\nLogging out....");
		Bank.welcome();

	}
	public int createAccountNumber() {
		Random rm = new Random();
		int number = 0;
		do {
			number = rm.nextInt(900000000) + 100000000;
		} while (Bank.accDao1.accountExist2(number));

		return number;
	}

}
