package com.revature.uilayer;

import java.sql.SQLException;

import com.revature.businesslayer.Customer;
import com.revature.businesslayer.User;
import com.revature.datalayer.AccountDaoImpl;
import com.revature.datalayer.CustomerDaoImpl;
import com.revature.datalayer.UserDaoImpl;

public class Driver {

	static User user;
	static Customer customerUser;

	public static void main(String[] args) {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		try {
			// customerUser = new Customer("Jim", "Beam", "bourbon", "cheers",
			// Usertype.CUSTOMER.toString());
			// customerUser.setApproved(true);
			// ArrayList<User> users = new ArrayList<>();
			// userDaoImpl.insertUser(customerUser);
			// users = (ArrayList<User>) userDaoImpl.getUserList();
			// user = users.get(0);
			//
			// user.setFirstName("Jimmy");
			// userDaoImpl.updateUser(users.get(0));
			// user = users.get(0);
			//
			// customerUser.setId(user.getId());
			//
			// customerDaoImpl.insertCustomerUser(customerUser);
			// customerUser.setApproved(false);
			// customerDaoImpl.updateUser(customerUser);
			// customerUser = customerDaoImpl.findCustomerById(1);
			// userDaoImpl.findUserById(2);
			// userDaoImpl.findUserByUserId("eb124769-6660-44b7-b8d0-e91eae0942c4");
			// System.out.println("ACCOUNTS: " + accountDaoImpl.getAccountList());
			// System.out.println("ACCOUNTS CUSTOMERS: " +
			// accountDaoImpl.getAccountCustomerList());
			customerUser = customerDaoImpl.findCustomerByUserId("eb124769-6660-44b7-b8d0-e91eae0942c4");
			System.out.println(accountDaoImpl.findAccountCustomerById(customerUser.getId()));
			System.out.println(accountDaoImpl.findAccountCustomerByUserId(customerUser.getUserId().toString()));
			// customerUser.setApproved(true);
			// customerUser.getAccount().setBalance(250);
			// customerUser.getAccount().setId(customerUser.getId());
			// customerDaoImpl.updateUser(customerUser);
			// accountDaoImpl.updateAccount(customerUser.getAccount());
			// System.out.println(
			// accountDaoImpl.findAccountrByAccountId(customerUser.getAccount().getAccountid().toString()));
			// System.out.println(accountDaoImpl.findAccountById(customerUser.getAccount().getId()));
			// accountDaoImpl.insertAccount(customerUser.getAccount());
			// accountDaoImpl.deleteAccount(customerUser.getAccount());
			// customerDaoImpl.deleteCustomerUser(customerUser);
			// customerUser = new Customer("Jack", "Daniels", "whiskey", "cheers",
			// Usertype.CUSTOMER.toString());
			// System.out.println(userDaoImpl.getUserList());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void print() {
		System.out.println("----------------------------------------");
		System.out.println("CUSTOMER USER: " + customerUser);
		System.out.println("USER: " + user);
		System.out.println("----------------------------------------");
	}

}
