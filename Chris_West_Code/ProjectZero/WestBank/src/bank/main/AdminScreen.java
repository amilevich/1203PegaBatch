package bank.main;

import java.util.ArrayList;

public class AdminScreen extends EmployeeScreen { // Look at (1) in Bank.java


	public void adminScreen() {
		employeeScreen();
		
	}

	@Override
	public void menu() {
		System.out.println("\nMenu Options:");
		System.out.println("Type 1 for personal information of " + Bank.customer1.getId());
		System.out.println("Type 2 for account information of " + Bank.customer1.getId());
		System.out.println("Type 3 for account balances of " + Bank.customer1.getId());
		System.out.println("Type 4 for account deletion of " + Bank.customer1.getId() );
		System.out.println("Type 5 to log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 5);
		switch (number) {
		case 1: // view personal information
			
			personalInformation();
			System.out.println("Change password: ");
			String passWord = Bank.input.next();
			Bank.customer1.setPassWord(passWord);
			break;
		case 2: // view account information
			System.out.println(Bank.customer1.getId() + "\nRegular: " + Bank.customer1.getrAcc() + "\nJoint: "
					+ Bank.customer1.getjAcc());
			menu();
			break;
		case 3:
			getBalances();
			moneyScreen();
			break;
		case 4:
			deleteAccount();
		case 5: // log-out
			Bank.customerView.logout();
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
			Bank.customerView.transferScreen();
			break;
		case 2:
			Bank.customerView.depositScreen();
			break;
		case 3:
			Bank.customerView.withdrawScreen();
			return;
		case 4:
			menu();
			break;
		case 5:
			Bank.customerView.logout();
			break;
		}
	}

	public void deleteAccount() {
		System.out.println("\nType 1 to delete account\nType 2 to return to previous menu.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 2);
		switch (number) {
		case 1:
			removeAllMentions();
			employeeScreen();
			break;

		case 2:
			moneyScreen();
			break;
		}

	}
	public void removeAllMentions() {
		String s = Bank.customer1.getId();
		ArrayList <String> jAcc = new ArrayList<String>();
		ArrayList <String> jAcc2 = new ArrayList<String>();
		if(Bank.joint.containsKey(Bank.customer1.getId())) {
			jAcc = Bank.joint.get(Bank.customer1.getId());
			for (int x = 0; x < jAcc.size();x++) {
				String id2 =jAcc.get(x);
				Bank.customer2 = (Customer) Bank.data.get(id2);
				jAcc2 = Bank.joint.get(Bank.customer2.getId());
				for (String y : jAcc2)
					jAcc2.remove(s);
			}
		}else {
			Bank.login.remove(s);
			Bank.data.remove(s);
		}
	}
}
