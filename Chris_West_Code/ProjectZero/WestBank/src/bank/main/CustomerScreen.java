package bank.main;

public class CustomerScreen {

	public void customerScreen() {
		System.out
				.println("\nWelcome back " + Bank.customer1.getFirstName() + " " + Bank.customer1.getLastName() + "!");
		System.out.println("Account Balance:\n$ " + Bank.customer1.getBalance());
		System.out.println("\nMenu Options:");
		System.out.println("Type 1 to view personal information.");
		System.out.println("Type 2 to view and add a joint account");
		System.out.println("Type 3 to transfer a balance, withdraw from your balance, and or make a deposit.");
		System.out.println("Type 4 to log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 4);

		switch (number) {
		case 1: // view personal information
			personalInformation();
			break;
		case 2: // view personal information
			viewJoint();
			break;
		case 3: // make transfer, withdraw, deposit
			moneyScreen();
			break;
		case 4: // log-out
			logout();
			break;

		}

	}

	public void personalInformation() {
		Bank.customer1.toString();
		System.out.println("\n***** Personal Information Screen *****\nMenu Options:");
		System.out.println("Type 1 to edit information.");
		System.out.println("Type 2 to go back to main screen");
		System.out.println("Type 3 to log-out.");
		System.out.println(Bank.customer1.toString());
		int number = UserInputValidation.isInRange(Bank.input, 1, 3);

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
		System.out.println("\n***** Money Screen *****\nMenu Options:");
		System.out.println("Type 1 to make a transfer");
		System.out.println("Type 2 to make a deposit");
		System.out.println("Type 3 to make a withdraw");
		System.out.println("Type 4 to go back to main menu");
		System.out.println("Type 5 to log-out");
		int number = UserInputValidation.isInRange(Bank.input, 1, 5);

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
		System.out
				.println("\nHow much would you like to withdraw? \nCurrent Balance: \n$" + Bank.customer1.getBalance());
		Bank.input = UserInputValidation.isDouble(Bank.input);
		double withdrawAmount = Bank.input.nextDouble();
		double temp = Bank.account1.withdrawMoney(withdrawAmount);

		if (temp >= 0) {
			Bank.customer1.setBalance(temp);
			System.out.println("Your withdraw was sucessful!\nNew Balance: \n$" + Bank.customer1.getBalance());
		} else {
			System.out.println("You don't have sufficient funds!");
		}
		moneyScreen();
	}

	public void depositScreen() {
		System.out
				.println("\nHow much would you like to deposit? \nCurrent Balance: \n$" + Bank.customer1.getBalance());
		Bank.input = UserInputValidation.isDouble(Bank.input);

		double depositAmount = Bank.input.nextDouble();
		if (depositAmount > 0) {
			double temp = Bank.account1.depositMoney(depositAmount);

			Bank.customer1.setBalance(temp);
			System.out.println("Your deposit was sucessful!\nNew Balance: \n$" + Bank.customer1.getBalance());
		} else {
			System.out.println("You entered either a zero or negative number. No nothing occurred.");
		}
		moneyScreen();

	}

	public void transferScreen() {
		System.out
				.println("\nHow much would you like to transfer? \nCurrent Balance: \n$" + Bank.customer1.getBalance());
		Bank.input = UserInputValidation.isDouble(Bank.input);

		double transferAmount = Bank.input.nextDouble();
		System.out.println("\nWho would you like to transfer to?");
		String id = Bank.input.next();
		UserInputValidation.isLetterNum(id);
		if ((transferAmount > 0) & Bank.data.containsKey(id) & (transferAmount <= Bank.customer1.getBalance())) {
			double temp = Bank.account1.transferFunds(transferAmount);
			Bank.customer2 = (Customers) Bank.data.get(id);
			Bank.customer1.setBalance(temp);
			Bank.customer2.setBalance(Bank.customer2.getBalance()+transferAmount);
			//System.out.println(Bank.customer2.getBalance());
			System.out.println("\nYour transfer was sucessful!\nNew Balance: \n$" + Bank.customer1.getBalance());
		} else {
			System.out.println("\nYou entered either a zero or negative number. Wrong account name. Or you don't have enough money. No nothing occurred.");
		}
		moneyScreen();
	}

	public void editInformation() {

	}

	public void logout() {
		System.out.println("\nLogging out....");
		return;

	}

	public void jointAcc() {
		if (Bank.joints.isEmpty()) {
			System.out.println("Joint Accounts: No");
		} else {
			System.out.println("Joint Accounts: Yes");
			for (String s : Bank.joints)
				System.out.println(s);
		}

	}

	public void viewJoint() {
		System.out.println("\n***** Joint Account Screen *****\nMenu Options:");
		System.out.println("Type 1 to add a joint Account");
		System.out.println("Type 2 to go back to main menu");
		System.out.println("Type 3 to log-out");
		int number = UserInputValidation.isInRange(Bank.input, 1, 3);

		switch (number) {
		case 1:
			transferScreen();
			break;
		case 2:
			customerScreen();
			break;
		case 3:
			logout();
			break;
		}
	}

	public void addJoint() {
		System.out.println("Please enter the account: ");
		String id = Bank.input.next();
		UserInputValidation.isLetterNum(id);
		if (Bank.data.containsKey(id)) {
			Bank.dh1.addingJointAcc(id);
		} else {
			System.out.println("Account doesn't exist...sending you back to Joint account menu.");
			viewJoint();
		}
	}

}
