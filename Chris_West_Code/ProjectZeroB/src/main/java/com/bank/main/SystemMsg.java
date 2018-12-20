package com.bank.main;

public class SystemMsg {
	public String tab = "    "; // "+tab+"

	public void processingRegMsg() {
		System.out.print("Processing Account....");
	}

	public void testMsg() {
		System.out.println("testing");
	}

	public void welcomeMsg() {
		System.out.print("^^^^^^ Welcome to West Bank ^^^^^^\n\n\nPlease choose one of the " + "following:\n\n" + tab
				+ "Enter 1 to login to your account.\n" + tab + "Enter 2 to create an account.\n" + tab
				+ "Enter 3 to exit.\n\n" + tab);
	}

	public void loginMsg() {
		System.out.println("\n\n###### Welcome to Login ######");
	}

	public void loginFailedMsg() {
		System.out.println("Login Attempted Failed");
	}

	public void newCustomerScreenMsg() {
		System.out.println(tab + "Enter 1 to view personal information.");
		System.out.println(tab + "Enter 2 to add a account");
		System.out.println(tab + "Enter 3 to log-out.");
	}
	public void personalInfoMsg() {
		System.out.println("\n###### Personal Information Screen ######\n");
		System.out.println(tab + "Enter 1 to go back to main screen.");
		System.out.println(tab + "Enter 2  to log-out.");
	}
	public void addAccountMsg() {
		System.out.println(tab + "Enter 1 to add a savings account.");
		System.out.println(tab + "Enter 2 to add a checking account.");
		System.out.println(tab + "Enter 3 to add a joint account.");
		System.out.println(tab + "Enter 4 to log-out.");
	}
	public void customerScreen() {
		System.out.println("\nMenu Options:");
		System.out.println(tab + "Enter 1 to view personal information.");
		System.out.println(tab + "Enter 2 to add an account");
		System.out.println(tab + "Enter 3 to transfer a balance, withdraw from your balance, and or make a deposit.");
		System.out.println(tab + "Enter 4 to delete an account.");
		System.out.println(tab + "Enter 5 to log-out.");
	}
	public void moneyScreen() {
		System.out.println("\n###### Money Screen ######\nMenu Options:");
		System.out.println(tab + "Enter 1 to make a transfer");
		System.out.println(tab + "Enter 2 to make a deposit");
		System.out.println(tab + "Enter 3 to make a withdraw");
		System.out.println(tab + "Enter 4 to go back to main menu");
		System.out.println(tab + "Enter 5 to log-out");
	}
}
