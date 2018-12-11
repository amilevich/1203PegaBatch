package projectPartZero;

import java.util.ArrayList;

public class CustomerLogin {
	
	public static void loginCustomer() {
		
		int response = 0;
		boolean flag = true;
		
		while(flag) {
			System.out.println("Welcome ");
			System.out.println("1.) Login");
			System.out.println("2.) go back to main menu ");
			System.out.print("Enter response here: ");
			
			String givenUserName = "";
			String givenPassword = "";
			Customer aCustomer = null;
			ArrayList<Account> accounts = new ArrayList<Account>();

			response = Main.input.nextInt();
		
			switch(response) {
			case 1:
				System.out.println("Enter your username and password ");
				System.out.print("Username: ");
				givenUserName = Main.input.next();
				System.out.print("Password: ");
				givenPassword = Main.input.next();
				aCustomer = FileManipulation.getACustomer(givenUserName, givenPassword);
				accounts = FileManipulation.getUserAccounts(Integer.parseInt(aCustomer.getAccountNumber()));
				for(int i = 0; i < accounts.size(); i++) {
					aCustomer.storeAccount(accounts.get(i));
				}
				CustomerMenu.customerMenu(aCustomer);
			case 2:
				flag = false;
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("This is not a valid option." );
			}
		}
	}
		
}
