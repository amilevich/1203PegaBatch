package bank.main;

import java.util.ArrayList;

public class EmployeeScreen {

	public void employeeScreen() {
		approvalDeny();
		menu();

	}

	public void personalInformation() {
		System.out.println(Bank.customer1.toString());
		menu();
	}

	public void approvalDeny() {
		String id;
		Boolean found = false;
		do {
			System.out.println("Please enter id of customer: ");
			id = Bank.input.next();
			UserInputValidation.isLetterNum(id);
			if (Bank.data.containsKey(id)) {
				found = true;
			} else if (Bank.processing.containsKey(id)) {
				found = true;
			}
		} while (!found);
		if (Bank.data.containsKey(id)) {
			Bank.customer1 = (Customers) Bank.data.get(id);

		}

		if (Bank.processing.containsKey(id)) {
			Bank.reg = (Registration) Bank.processing.get(id);
			Bank.reg.toString();
			System.out.println("Type 1 to Approve. Type 2 to Deny");
			int number = UserInputValidation.isInRange(Bank.input, 1, 2);

			if (number == 1) {
				Bank.reg.setNewAccount(true);
				Bank.dh1.accountApproved(id);
			}
		}
	}

	public void menu() {
		System.out.println("\nMenu Options:");
		System.out.println("Type 1 to for personal information of " + Bank.customer1.getId());
		System.out.println("Type 2 to for account information of " + Bank.customer1.getId());
		System.out.println("Type 3 to for account balances of " + Bank.customer1.getId());
		System.out.println("Type 4 to log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 4);
		options(number);
	}

	public void options(int number) {
		switch (number) {
		case 1: // view personal information
			personalInformation();
			break;
		case 2: // view account information
			System.out.println("\nID: " + Bank.customer1.getId() + "\nRegular: " + Bank.customer1.getrAcc()
					+ "\nJoint: " + Bank.customer1.getjAcc());
			menu();
			break;
		case 3:
			getBalances();

			break;
		case 4: // log-out
			Bank.customerView.logout();
			break;

		}
	}

	public void getBalances() {
		if (Bank.customer1.getrAcc() == true) {
			System.out.println("\nAccount Balances: ");
			System.out.println(Bank.customer1.getBalance());
		}

		if (Bank.customer1.getjAcc() == true) {
			System.out.println("\nJoint Account Balances");
			ArrayList<String> jAcc = new ArrayList<String>();
			jAcc = Bank.joint.get(Bank.customer1.getId());
			for (String x : jAcc) {
				Bank.customer2 = (Customers) Bank.data.get(x);
				System.out.println(Bank.customer2.getId() + "\nBalance: " + Bank.customer2.getBalance());
			}
		}
		System.out.println("");
	}
}
