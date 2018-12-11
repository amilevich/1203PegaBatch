package bank.main;

public class EmployeeScreen {

	public void employeeScreen() {
		System.out
				.println("\nWelcome back " + Bank.customer1.getFirstName() + " " + Bank.customer1.getLastName() + "!");
		String id;
		do {
		System.out.println("Please enter id of customer: ");
		id = Bank.input.next();
		UserInputValidation.isLetterNum(id);
		}while(Bank.data.containsKey(id));
		Bank.customer1 = (Customers) Bank.data.get(id);
		menu();

	}
	public void personalInformation() {
		System.out.println(Bank.customer1.toString());
		menu();
	}
	public void menu() {
		System.out.println("\nMenu Options:");
		System.out.println("Type 1 to view personal information of " + Bank.customer1.getId());
		System.out.println("Type 2 to view account information of " + Bank.customer1.getId());
		System.out.println("Type 3 to view account balances of " + Bank.customer1.getId());
		System.out.println("Type 4 to log-out.");
		int number = UserInputValidation.isInRange(Bank.input, 1, 4);
		switch (number) {
		case 1: // view personal information
			personalInformation();
			break;
		case 2: // view account information
			System.out.println(Bank.customer1.getId() "\nRegular: " + Bank.customer1);
			break;
		case 3: // make transfer, withdraw, deposit
			// moneyScreen();
			break;
		case 4: // log-out
			// logout();
			break;

		}
	}
}
