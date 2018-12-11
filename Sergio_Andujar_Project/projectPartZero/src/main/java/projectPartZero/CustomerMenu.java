package projectPartZero;

public class CustomerMenu {
	
	public static void customerMenu(Customer aCustomer) {
		
		boolean flag = true;
		
		while(flag) {
				Account accountToInteract = AccountMenu.choiceAccount(aCustomer);
				System.out.println("Select what you want to do");
				System.out.println("1.) Withdraw");
				System.out.println("2.) Deposit");
				System.out.println("3.) transfer");
				System.out.println("4.) log out" );
				System.out.print("Enter response here: ");
				int response = 0;
				response = Main.input.nextInt();
				switch(response) {
				case 1:
					System.out.println("Enter amount to withdraw: ");
					response = Main.input.nextInt();
					int amount;
					amount = accountToInteract.withdraw(response);
					System.out.println("You have withdrawn: " + response);
					FileManipulation.updateAccount(aCustomer);
					break;
				case 2:
					System.out.println("Enter amount to deposit: ");
					response = Main.input.nextInt();
					accountToInteract.deposit(response);
					System.out.println("You have deposited: " + response);
					FileManipulation.updateAccount(aCustomer);
					break;
				case 3:
					System.out.println("Which account do you want to transfer to?");
					System.out.println("1.) Your account?");
					System.out.println("2.) Another customer account?(Under Construction)");
					System.out.println("Enter response here: ");
					response = Main.input.nextInt();
					switch(response) {
					case 1:
						Account accountToInteract2 = AccountMenu.choiceAccount(aCustomer);
						System.out.println("Enter amount to transfer over: ");
						response = Main.input.nextInt();
						accountToInteract.transfer(response, accountToInteract2);
						System.out.println("Transfer successful");
						System.out.println("Here are the balances of your accounts");
						System.out.println(accountToInteract.getBalance());
						System.out.println(accountToInteract2.getBalance());
						FileManipulation.updateAccount(aCustomer);
						break;
					case 2:
						break;
					}
					break;
				case 4:
					System.out.println("logging out");
					flag = false;
				default:
					System.out.println("This is not a valid option");
					break;	
				}
		}
	}

}
