/*
 * Comments to specific lines in code (come back here when it say's to look at
 * (#))
 * 
 * 
 * (1) There are 3 view points the Admin, Employee, Customer. Admin inherits from Employee and the Customer inherits from the User.
 * 
 * (2) The DataHub is the place that adds and removes data from the hashmaps of data, processing, login, joint. Except when deleting an account entirely. 
 * That option is left in the admin for now.
 * 
 * (3) So, I'm using the Random class to randomly generate a 4 digit number: the number inside the () is 
 * the max value the number added to the value is the min. number
 * 
 * Other notes:
 * A lot of logic became repetitive so I created methods or even classes just dedicated for that logic. So, I wouldn't have more code than needed.
 */

package com.bank.main;



import java.util.Random;
import java.util.Scanner;

import com.bank.daoImp.AccountDaoImp;
import com.bank.daoImp.BankUserDaoImp;
import com.bank.daoImp.PermDaoImp;
import com.bank.daoImp.PersonalDaoImp;

public class Bank {

	protected static Scanner input = new Scanner(System.in);
	static BankUserDaoImp Bankuser = new BankUserDaoImp();
	static PermDaoImp perm1 = new PermDaoImp();
	static PersonalDaoImp personal1 = new PersonalDaoImp(); 
	static AccountDaoImp accDao1 = new AccountDaoImp();
	
	static Registration reg = new Registration();
	static Account account;
	private static User user1;
	static PersonalInformation personalInfo1, personalInfo2;
	//static Access access = new Access();

	public static void main(String[] args) {

		welcome();
		userSwitch();

	}

	public static void login() {
		System.out.println("\n\n###### Welcome to Login ######");
		System.out.println("\nEnter username/email address: ");
		String username = input.next();
		UserInputValidation.isLetterNumSpecial(username);

		System.out.println("Enter password: ");
		String passWord = input.next();
		UserInputValidation.isLetterNumSpecial(passWord);
		user1 = Bankuser.getUserById(username, passWord, username);
		System.out.println(user1.getPermission());


	}

	public static void register() {
		System.out.println("Please enter your first name, middle initial, and last name: ");
		String firstName = input.next();
		UserInputValidation.isLetters(firstName);
		Bank.reg.setFirstName(firstName);
		String middleInitial = input.next();
		UserInputValidation.isALetter(middleInitial);
		Bank.reg.setMiddleInitial(middleInitial);
		String lastName = input.next();
		UserInputValidation.isLetters(lastName);
		Bank.reg.setLastName(lastName);

		System.out.println("Please enter your address: ");
		input.nextLine();
		String address = input.nextLine();
		UserInputValidation.isLetterNumLine(address);
		Bank.reg.setAddress(address);

		System.out.println("Please enter your city, state, zip: ");
		String city = input.next();
		UserInputValidation.isLetters(city);
		Bank.reg.setCity(city);
		String state = input.next();
		UserInputValidation.isLetters(state);
		Bank.reg.setState(state);
		input = UserInputValidation.isInt(input);
		int zip = input.nextInt();
		Bank.reg.setZip(zip);

		System.out.println("Please enter your phone number: ");
		input = UserInputValidation.isLong(input);
		Long phoneNum = input.nextLong();
		Bank.reg.setPhoneNum(phoneNum);

		System.out.println("Please enter your passWord(Letters, numbers, special characters(%^!@): ");
		String passWord = input.next();
		UserInputValidation.isLetterNumSpecial(passWord); // make this more functional later
		Bank.reg.setPassWord(passWord);
		//accType();
		System.out.println("Please enter your email address: ");
		String email = input.next();
		UserInputValidation.isLetterNumSpecial(email); // make this more functional later
//		Bank.reg.set;

		//Boolean newAccount = true;
		String id = generateID(firstName, lastName);
		System.out.println(id);
		System.out.println();

//		dh1.addProcessing();
		System.out.print("Processing Account....");
	}

	public static String generateID(String first, String last) { // Look at (3)
		Random rm = new Random();
		String id = first.charAt(0) + last, temp;
		int number;
		do {
			temp = "";
			number = rm.nextInt(9000) + 1000;
			temp = id + number;

		} while (Bankuser.userIdCheck(temp, temp));

		return temp;

	}

	public static void userSwitch() {
		switch (perm1.getUserPerm(user1.getPermission())) {
		case "CUSTOMER":
			personalInfo1 = personal1.getPersonalInfo(user1.getId());
			
			// customerView.customerScreen();
			break;
		case "EMPLOYEE":
			// employeeView.employeeScreen();
			break;
		case "ADMIN":
			// adminView.adminScreen();
			break;
		case "SUPERUSER":
			System.out.println("YES");
			// adminView.adminScreen();
			break;

		}
	}

	public static void welcome() {
		int number;
		String tab = "    "; //"+tab+"
		System.out.print("^^^^^^ Welcome to West Bank ^^^^^^\n\n\nPlease choose one of the "
				+ "following:\n\n"+tab+"Enter 1 to login to your account.\n"+ tab + "Enter 2 to create an account.\n"+ tab + "Enter 3 to exit.\n\n"+ tab);
		number = UserInputValidation.isInRange(input, 1, 3);

		switch (number) {
		case 1:
			login();
			break;
		case 2:
			// register();
			login();
			break;
		case 3:
			return;
		}
	}
//
//	public static void accType() {
//		int number;
//		System.out.println("\nEnter 1 to open a regular account.\nEnter 2 to create a joint account.");
//		number = UserInputValidation.isInRange(input, 1, 2);
//
//		switch (number) {
//		case 1:
//			rAcc = true;
//			break;
//		case 2:
//			jAcc = true;
//			break;
//
//		}
//	}

}
