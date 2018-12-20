package bank;

import java.util.Scanner;

public class Admin {

	static Scanner scanner = new Scanner(System.in);

	// Admin Login to Customer Account
	static Customer loginAsCustomer(String cUsername) {
		// Check is user exisits
		Customer c = Customer.getCustomer(Main.customerList, cUsername);
		return c;
	}

}
