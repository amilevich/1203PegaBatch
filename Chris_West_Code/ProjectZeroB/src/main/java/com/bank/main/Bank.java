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

import java.util.Scanner;

import com.bank.daoImp.AccountDaoImp;
import com.bank.daoImp.BankUserDaoImp;
import com.bank.daoImp.PermDaoImp;
import com.bank.daoImp.PersonalDaoImp;

public class Bank {

	protected static Scanner input = new Scanner(System.in);
	static BankUserDaoImp Bankuser = new BankUserDaoImp();
	static PermDaoImp perm1 = new PermDaoImp();
	static PersonalDaoImp personalDao1 = new PersonalDaoImp();
	static AccountDaoImp accDao1 = new AccountDaoImp();
	static RegistrationForm regForm = new RegistrationForm();
	public static Registration reg = new Registration();
	public static Account account, account2, account3;
	public static SystemMsg msg = new SystemMsg();
	public static AdminScreen adminView = new AdminScreen();
	public static CustomerScreen customerView = new CustomerScreen();
	public static EmployeeScreen employeeView = new EmployeeScreen();
	public static User user1, user2;
	static PersonalInformation personalInfo1, personalInfo2; // personal1 is accessed by everyone.
	// personal2 is for employee; personal3 is for employee
	// static Access access = new Access();

	public static void main(String[] args) {

		welcome();
		userSwitch();

	}

	/*
	 * zkoch4835 qOjGdg2w
	 * 
	 */
	public static void login() {
		msg.loginMsg();
		System.out.println("\nEnter username/email address: ");
		String username = "Juvenile@westbank.west2coast"; // input.next(); "cm2050"
		// UserInputValidation.isLetterNumSpecial(username);

		System.out.println("Enter password: ");
		String passWord = "qEV4nNUv"; // input.next();"123"
		// UserInputValidation.isLetterNumSpecial(passWord);
		user1 = Bankuser.getUserById(username, passWord, username);

	}

	public static void register() {
		regForm.regNames();
		regForm.regAddress();
		regForm.regPhoneNumber();
		regForm.regPassword();
		regForm.regEmail();
		regForm.regUserName();
		msg.processingRegMsg();
		Bank.Bankuser.addNewUser();
		welcome();
	}

	public static void userSwitch() {
		switch (perm1.getUserPerm(user1.getPermission())) {
		case "CUSTOMER":
			personalInfo1 = personalDao1.getPersonalInfo(user1.getId()); // gets the personal info from the db in the
																			// PersonalDao
			if (Bank.accDao1.accountExist(user1.getId())) {
				account = Bank.accDao1.getAccount(user1.getId());
				customerView.customerScreen();
			} else {
				customerView.newCustomerScreen();
			}
			break;
		case "EMPLOYEE":
			employeeView.employeeScreen();
			break;
		case "ADMIN":
			adminView.adminScreen();
			break;
		case "SUPERUSER":
			System.out.println("YES");
			// adminView.adminScreen();
			break;

		}
	}

	public static void welcome() {
		int number;
		msg.welcomeMsg();
		number = UserInputValidation.isInRange(input, 1, 3);

		switch (number) {
		case 1: // login
			login();
			break;
		case 2: // register
			register();
			break;
		case 3: // Exit
			return;
		}
	}

}
