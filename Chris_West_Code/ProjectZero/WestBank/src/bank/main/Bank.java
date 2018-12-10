/*
 * The problem is Question/Project :
 * 
 * 
 * 
 * 
 */

/*
 * Comments to specific lines in code (come back here when it say's to look at
 * (#))
 * 
 * 
 * (1)
 * 
 * (2)
 * 
 * (3) So, I'm using the Random class to randomly generate a 4 digit number: the number inside the () is 
 * the max value the number added to the value is the min. number
 * 
 * Side notes (not related to comments): There's some code I need to fix such as acc. type for registration
 */

package bank.main;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bank {
	private static Scanner input = new Scanner(System.in);
	static Registration reg;
	static HashMap<String, Object> data = new HashMap<String, Object>();
	static HashMap<String, Object> login = new HashMap<String, Object>();
	private static User user1;
	static Customers customer1;
	static Account account1;
	private static Employees employee1;
	private static Admins admin1;
	static CustomerScreen customerView = new CustomerScreen();

	public static void main(String[] args) {
		/*
		 * Declarations in General
		 */
		String[] txtFile = { "UAccounts.txt", "InProgress.txt" };
		String txt;
		Boolean isRead;
		int number, countError = 0;

		/*
		 * Object Declarations
		 */
		HRDEPT hr1;
		DataHub dh1;

//		/*
//		 * Hashmap filled with random data for testing purposes
//		 */
//		data.put("cwest5960", new Registration()); // example
//		data.put("cwest5961", new Employees("", "", "")); // example

		dh1 = new DataHub();
		dh1.initializeTempData();

		/*
		 * 
		 * Welcome Screen
		 * 
		 */

		System.out.println("Welcome to West Bank\nPlease choose one of the "
				+ "following:\nEnter 1 to login to your account.\nEnter 2 to create an account.\nEnter 3 to exit.");
		number = UserInputValidation.isInRange(input);

		switch (number) {
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		case 3:
			return;

		}
		/*
		 * 
		 * User Switching
		 * 
		 */
		switch (user1.getUser()) {
		case "CUSTOMER":
			customer1 = (Customers) data.get(user1.getId());
			customerView.customerScreen();
			break;
		case "EMPLOYEE":

			employeeOptions();
			break;
		case "ADMIN":
			adminOptions();
			break;

		}

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
			System.out.println("Enter ID: ");
			String id = input.next();
			UserInputValidation.isLetterNum(id);

			System.out.println("Enter password: ");
			String passWord = input.next();
			UserInputValidation.isLetterNumSpecial(passWord);

			if (login.containsKey(id)) {
				user1 = (User) login.get(id);

				if (user1.getPassWord().equals(passWord)) {
					authenticate = true;

					System.out.println("Logging in...");

				}
			} else {
				attempts++;
			}
		} while (!authenticate && attempts < 3);

		if (!authenticate) {
			System.out.println("Good-Bye!");
			return;
		}

	}

	public static void register() {
		System.out.println("Please enter your first name, middle initial, and last name: ");
		String firstName = input.next();
		UserInputValidation.isLetters(firstName);

		String middleInitial = input.next();
		UserInputValidation.isALetter(middleInitial);

		String lastName = input.next();
		UserInputValidation.isLetters(lastName);

		System.out.println("Please enter your address: ");
		input.nextLine();
		String address = input.nextLine();
		UserInputValidation.isLetterNumLine(address);

		System.out.println("Please enter your city, state, zip: ");
		String city = input.next();
		UserInputValidation.isLetters(city);

		String state = input.next();
		UserInputValidation.isLetters(state);

		input = UserInputValidation.isInt(input);
		int zip = input.nextInt();

		System.out.println("Please enter your phone number: ");
		input = UserInputValidation.isLong(input);
		Long phoneNum = input.nextLong();

		System.out.println("Please enter your passWord(Letters, numbers, special characters(%^!@): ");
		String passWord = input.next();
		UserInputValidation.isLetterNumSpecial(passWord); // make this more functional later

		String accType = "single"; // edit this later I forgot to add implementation in thought process prob. be
									// switch-case
		Boolean newAccount = true;
		String id = generateID(firstName, lastName);
		System.out.println(id);
		reg = new Registration(firstName, middleInitial, lastName, address, city, state, zip, phoneNum, accType,
				newAccount, id, passWord, "CUSTOMER");
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

	/*
	 * 
	 * Employee Screen its other related methods
	 * 
	 */
	public static void employeeOptions() {

	}

	/*
	 * 
	 * Admin Screen its other related methods
	 * 
	 */
	public static void adminOptions() {

	}

}
