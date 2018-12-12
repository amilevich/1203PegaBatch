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

package bank.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bank {

	protected static Scanner input = new Scanner(System.in);
	static Registration reg = new Registration();
	static HashMap<String, Object> data = new HashMap<String, Object>();
	static HashMap<String, Object> login = new HashMap<String, Object>();
	static HashMap<String, Object> processing = new HashMap<String, Object>();
	static HashMap<String, ArrayList<String>> joint = new HashMap<String, ArrayList<String>>();
	private static User user1;
	static Customer customer1, customer2;
	static Account account1 = new Account();
	private static HrDept hr1;
	static ArrayList<String> joints = new ArrayList<String>();
	protected static ArrayList<String> jointAccounts = new ArrayList<String>();
	protected static CustomerScreen customerView = new CustomerScreen();
	protected static EmployeeScreen employeeView = new EmployeeScreen();
	protected static AdminScreen adminView = new AdminScreen();

	static DataHub dh1;

	private static Boolean jAcc = false, rAcc = false;

	public static void main(String[] args) {
		/*
		 * Declarations
		 */

		dh1 = new DataHub();
		dh1.initializeTempData();
		dh1.initialize();
		welcome();
		userSwitch();

	}
	/*
	 * 
	 * Login, Registration, Generate ID
	 * 
	 */

	public static void login() {
		Boolean authenticate = false;
		int attempts = 0;
		do {
			System.out.println("\nEnter ID: ");
			String id = input.next();
			UserInputValidation.isLetterNum(id);

			System.out.println("Enter password: ");
			String passWord = input.next();
			UserInputValidation.isLetterNumSpecial(passWord);

			if (login.containsKey(id)) {
				user1 = (User) login.get(id);

				if (user1.getPassWord().equals(passWord)) {
					authenticate = true;

					System.out.println("\nLogging in...");
					if (joint.containsKey(id)) {

					}
				}
			} else {
				attempts++;
			}
		} while (!authenticate && attempts < 3);

		if (!authenticate) {
			System.out.println("Log-ins failed!");
			return;
		}

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
		accType();

		Boolean newAccount = true;
		String id = generateID(firstName, lastName);
		System.out.println(id);
		System.out.println();

		dh1.addProcessing();
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

		} while (data.containsKey(temp));

		return temp;

	}

	public static void userSwitch() {
		switch (user1.getUser()) {
		case "CUSTOMER":
			customer1 = (Customer) data.get(user1.getId());
			customerView.customerScreen();
			break;
		case "EMPLOYEE":
			employeeView.employeeScreen();
			break;
		case "ADMIN":
			adminView.adminScreen();
			break;

		}
	}

	public static void welcome() {
		int number;
		System.out.println("Welcome to West Bank\nPlease choose one of the "
				+ "following:\nEnter 1 to login to your account.\nEnter 2 to create an account.\nEnter 3 to exit.");
		number = UserInputValidation.isInRange(input, 1, 3);

		switch (number) {
		case 1:
			login();
			break;
		case 2:
			register();
			login();
			break;
		case 3:
			return;
		}
	}

	public static void accType() {
		int number;
		System.out.println("\nEnter 1 to open a regular account.\nEnter 2 to create a joint account.");
		number = UserInputValidation.isInRange(input, 1, 2);

		switch (number) {
		case 1:
			rAcc = true;
			break;
		case 2:
			jAcc = true;
			break;

		}
	}

}
