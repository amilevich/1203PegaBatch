package com.revature.views;

import java.sql.SQLException;
import java.util.List;

import com.revature.DAO.AccountDaoImpl;
import com.revature.DAO.CustomerDaoImpl;
import com.revature.controllers.TransactionManager;
import com.revature.enums.Account_State;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Transaction;
import com.revature.util.Validation;

public class MenuForm {
	public void showBankAppIntro() {
		System.out.println("********************************");
		System.out.println("* Welcome to The Lopez BankApp *");
		System.out.println("********************************");
	}

	public void showCustomerMenu() {
		System.out.print(
				"\n\nWelcome Back to the Bank App!\n\nPlease select one of the following available " + "activities.\n");
		System.out.print("\n 1. Open Bank Account\n 2. View Balances\n 3. Withdraw\n 4. Deposit \n"
				+ " 5. Transfer\n 6. View Transactions\n 7. Close/Exit an Account\n 8. View Personal Information\n 0. Sign out\nSelection: ");
	}

	public void showEmployeeMenu(int id) {
		Customer cust = new CustomerDaoImpl().getCustomerById(id);
		System.out.print("\nManaging customer: " + cust.getFirstname() + " " + cust.getLastname());
		System.out.print("\nPlease select one of the following available "
				+ "activities.\n 1. Approve/Deny Accounts\n 2. View Balances \n 3. View Personal Information \n 4. View Transactions"
				+ "\n 0. Log out\nSelection: ");
	}

	public void showAdminMenu(int id) {
		Customer cust = new CustomerDaoImpl().getCustomerById(id);
		System.out.print("\nManaging customer: " + cust.getFirstname() + " " + cust.getLastname());

		System.out.print("\nPlease select one of the following available "
				+ "activities.\n	1. Change account states\n	2. View Balances \n	3. View Transactions\n	4. View Customers Information"
				+ "\n	5. Withdrawl\n	6. Deposit\n	7. Transfer\n	8. Edit Customer Information\n	9. Register Employee\n"
				+ "	0. Log out\nSelection: ");
	}

	public int showOpenAccount() {
		int choice = 0;

		System.out.print("\n\nSelect the account you would like to open."
				+ "\n	1. Account\n	2. Joint Account\n	0. Exit\n\nSelection: ");

		do {
			choice = Validation.getIntInput();

		} while (choice != 0 && choice != 2 && choice != 1);
		return choice;
	}

	public void showDeposit(Account account, int user) {
		if (account == null) {
			System.out.println("Account not available!");
		} else {
			System.out.print("\nAmount to deposit: ");
			double amount = Validation.getDoubleInput();
			try {
				new TransactionManager().depositForm(amount, account, user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void showWithdrawal(Account account, int user) {
		if (account == null) {
			System.out.println("Account not available!");
		} else {
			System.out.print("Amount to withdraw: ");
			double amount = Validation.getDoubleInput();
			new TransactionManager().withdrawalForm(amount, account, user);
		}
	}

	public void showTransfer(Account account, int user) {
		if (account == null) {
			System.out.println("Account not available!");
		} else {
			System.out.print("Write the account number: ");
			int account_num = Validation.getIntInput();
			System.out.print("Amount to transfer: ");
			double amount = Validation.getDoubleInput();
			new TransactionManager().transferForm(amount, account, new AccountDaoImpl().getAccountById(account_num),
					user, user);
		}
	}

	public void showPersonalInformation(Customer cst) {
		System.out.println("\n\n\nCustomer Information \nFirst Name: " + cst.getFirstname() + "\nMiddle Name: "
				+ cst.getMiddlename() + "\nLast Name: " + cst.getLastname() + "\nIf more is added, it goes here.");
	}

	public void showAccountDetails(Account account) {
		System.out.print("Account #: " + account.getId() + "		" + "Current State: " + account.getState().name()
				+ "\nAproved By: " + account.getApprovedBy() + "\nAproved Date: " + account.getApprovedDate()
				+ "			Current Balance: " + account.getBalance() + "\n\n");
	}

	public void showAccountBalance(Account account) {
		System.out.println("Account Number: " + account.getId() + "		Current Balance: $" + account.getBalance()
				+ "\nCurrent State: " + account.getState().name() + "			" + "Type: "
				+ account.getClass().getSimpleName() + "\n");

	}

	public void showAccountBalances(List<Account> accountList) {
		if (accountList.isEmpty()) {
			System.out.println("\nNo available accounts!");
		} else {
			for (Account account : accountList) {
				System.out.println("Account Number: " + account.getId() + "		Current Balance: $"
						+ account.getBalance() + "\nCurrent State: " + account.getState().name() + "			"
						+ "Type: " + account.getClass().getSimpleName() + "\n");
			}
		}
	}

	public Account showCustomerAccountsC(List<Account> accountList) {
		if (accountList.isEmpty()) {
			System.out.println("No available accounts!");
			return null;
		} else {
			int index = 0;
			for (Account account : accountList) {
				if (account.getState() == Account_State.APPROVED) {
					index++;
					System.out.println("Account Number: " + account.getId() + "		Current Balance: $"
							+ account.getBalance() + "\nCurrent State: " + account.getState().name() + "			"
							+ "Type: " + account.getClass().getSimpleName() + "\n");
				}
			}
			System.out.print("\nSelect account: ");
			int chk = Validation.getIntInput();
			Account account = new AccountDaoImpl().getAccountById(chk);
			if (account == null)
				return null;
			else
				return account;
		}
	}

	public Account showCustomerAccountsE(List<Account> accountList) {
		if (accountList.isEmpty()) {
			System.out.println("No available accounts!");
			return null;
		} else {
			int index = 0;
			for (Account account : accountList) {
				if (account.getState() == Account_State.APPROVED && account.getState() == Account_State.DENIED) {
					System.out.println("Account Number: " + account.getId() + "		Current Balance: $"
							+ account.getBalance() + "\nCurrent State: " + account.getState().name() + "			"
							+ "Type: " + account.getClass().getSimpleName() + "\n");
				} else {
					accountList.remove(index);
				}
			}
			System.out.print("\nSelect account: ");
			int chk = Validation.getIntInput();
			Account account = new AccountDaoImpl().getAccountById(chk);
			if (account == null)
				return null;
			else
				return account;
		}
	}

	public Account showCustomerAccountsA(List<Account> accountList) {
		if (accountList.isEmpty()) {
			System.out.println("No available accounts!");
			return null;
		} else {
			int index = 0;
			for (Account account : accountList) {
				index++;
				System.out.println("Account Number: " + account.getId() + "		Current Balance: $"
						+ account.getBalance() + "\nCurrent State: " + account.getState().name() + "			"
						+ "Type: " + account.getClass().getSimpleName() + "\n");
			}
			System.out.print("\nSelect account: ");
			int chk = Validation.getIntInput();
			Account account = new AccountDaoImpl().getAccountById(chk);
			if (account == null)
				return null;
			else
				return account;
		}
	}

	public boolean showAccountTransactions(List<Transaction> transactions) {
		if (transactions == null) {
			System.out.println("No transactions in this account.");
			return false;
		} else {
			for (Transaction t : transactions)
				System.out.println("Type: " + t.getType().name() + "		Date: " + t.getTimestamp()
						+ "		Amount: " + t.getAmount());
			return true;
		}
	}

	public void showAllCustomers(List<Customer> custList) {
		System.out.println("ID"+"	"+"Name");
		for (Customer cust : custList) {
			System.out.println(cust.getId() + "	" + cust.getLastname() + ", " + cust.getFirstname());
		}
	}

}
