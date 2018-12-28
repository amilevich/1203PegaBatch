package com.revature.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.DAO.AccountDaoImpl;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.Authentication;
import com.revature.util.Validation;

public class AccountManager {

	public void JointAccountAppForm(int id) {
		Account account = new Account();
		System.out.print("\nThe minimun deposit is " + Account.MINIMUM_DEPOSIT + "\nAmount: ");
		boolean success = false;
		double amount = 0;
		while (!success) {
			if ((amount = Validation.getDoubleInput()) <= 0) {
				return;
			} else if (amount <= Account.MINIMUM_DEPOSIT) {
				System.out
						.print("\n\n\nSorry, The minimum initial deposit is " + Account.MINIMUM_DEPOSIT + "\nAmount:");
			} else {
				account.setBalance(amount);
				account.setJoint(1);
				new AccountDaoImpl().createAccount(account, id);
				System.out.println("Request send with a deposit of $" + amount);
				try {
					new TransactionManager().depositForm(amount, account, id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				success = true;
			}
		}
		int choice = 0;
		boolean finish = false;
		List<User> userL = new ArrayList<>();
		while (!finish || choice == -1) {
			System.out.println("1. Add another associate\n2. Finish");
			choice = Validation.getIntInput();
			switch (choice) {
			case 1:
				
				System.out.println(
						"To request for a joint account you must enter the associates log-in credentials.\nUsername: ");
				User user = new User();
				
				// check if associate is already in the joint account
				if ((user = new Authentication().signIn()) != null
						&& user.getRole() == "CUSTOMER") {
					for (Account acc : new AccountDaoImpl().getAccountsByUserId(user.getId())) {
						if (acc.getId() == account.getId()) {
							System.out.println("Invalid Entry: Customer already on the list");
							break;
						}
					}
					userL.add(user);
				}
				break;
			case 2:
				for(User u : userL) {
					new AccountDaoImpl().createAccountConnection(account.getId(), u.getId());
				}
				return;

			case -1:
				return;
			}
		}
	}

	public void SoleAccountAppForm(int user) {
		System.out.print("\nThe minimun deposit: " + Account.MINIMUM_DEPOSIT + "\nAmount: ");
		boolean success = false;
		double amount = 0;
		while (!success) {
			if ((amount = Validation.getDoubleInput()) <= 0) {
				return;
			} else if (amount <= Account.MINIMUM_DEPOSIT) {
				System.out
						.print("\n\n\nSorry, The minimum initial deposit is " + Account.MINIMUM_DEPOSIT + "\nAmount:");
			} else {
				Account account = new Account(0, 0);
				int id = new AccountDaoImpl().createAccount(account, user);
				account = new AccountDaoImpl().getAccountById(id);
				System.out.println("\nRequest send with a deposit of $" + amount);
				try {
					new TransactionManager().depositForm(amount, account, user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				success = true;
			}
		}
	}

}
