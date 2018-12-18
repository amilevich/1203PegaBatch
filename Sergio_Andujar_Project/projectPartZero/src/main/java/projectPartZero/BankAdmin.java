package projectPartZero;
import java.util.ArrayList;



public class BankAdmin extends Employee{

	BankAdmin(String userName) {
		super(userName);
		
	}
	
	public void cancelAnAccount() {
		
		ArrayList<Customer> allCustomers = FileManipulation.getAllCustomer();
		for (int i = 0; i < allCustomers.size(); i++) {
			ArrayList<Account> accounts = FileManipulation.getUserAccounts(Integer.parseInt(allCustomers.get(i).getAccountNumber()));
			for (int j = 0; j < accounts.size(); j++) {
				allCustomers.get(i).getAccounts().add(accounts.get(j));
			}
		
		}
		while(true) {
			System.out.println("Here are the following accounts");
			for(int j = 0; j < allCustomers.size(); j++) {
				System.out.println(j + ".) " + allCustomers.get(j));
			}
			System.out.println("Select which one you want to cancel or enter -1 to exit: ");
			int response = 0;
			response = Main.input.nextInt();
			if(response == -1) {break;}
			else if(allCustomers.get(response).getNumberOfAccounts() == 1) {
				allCustomers.get(response).getAccounts().remove(0);
				FileManipulation.removeAccount(allCustomers.get(response));
				allCustomers.remove(response);
			}
			else if(allCustomers.get(response).getNumberOfAccounts() > 1) {
				int index;
				index = AccountMenu.getAccountIndex(allCustomers.get(response));
				allCustomers.get(response).getAccounts().remove(index);
				FileManipulation.removeAccount(allCustomers.get(response));
			}
			
		}

		
	}
	
	public void editAllAccounts() {
		ArrayList<Customer> allCustomers = FileManipulation.getAllCustomer();
		for (int i = 0; i < allCustomers.size(); i++) {
			ArrayList<Account> accounts = FileManipulation.getUserAccounts(Integer.parseInt(allCustomers.get(i).getAccountNumber()));
			for (int j = 0; j < accounts.size(); j++) {
				allCustomers.get(i).getAccounts().add(accounts.get(j));
			}
		
		}
		while(true) {
			System.out.println("Here are the following accounts");
			for(int j = 0; j < allCustomers.size(); j++) {
				System.out.println(j + ".) " + allCustomers.get(j));
			}
			System.out.println("Select which one you want to edit or enter -1 to exit: ");
			int response = 0;
			response = Main.input.nextInt();
			if(response == -1) {break;}
			CustomerMenu.customerMenu(allCustomers.get(response));
		}

	}
}
