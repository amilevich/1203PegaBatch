package projectPartZero;

public class AccountMenu {
	
	public static int getAccountIndex(Customer aCustomer) {
		System.out.println("Which of the following accounts will you be making changes to?");
		int counter = 0;
		for(Account account : aCustomer.getAccounts()) {
			System.out.println(counter + ": " + "account" + "-" + "Balance: " + account.getBalance());
			++counter;
		}
		int response = 0;
		response = Main.input.nextInt();
		return response;
		
	}

	public static Account choiceAccount(Customer aCustomer) {
		
		System.out.println("Which of the following accounts will you be making changes to?");
		int counter = 0;
		for(Account account : aCustomer.getAccounts()) {
			System.out.println(counter + ": " + "account" + "-" + "Balance: " + account.getBalance());
			++counter;
		}
		int response = 0;
		response = Main.input.nextInt();
		return aCustomer.getAccount(response);
			
	}

}
