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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Bank {

	protected static Scanner input = new Scanner(System.in);
	static Registration reg;
	static HashMap<String, Object> data = new HashMap<String, Object>();
	static HashMap<String, Object> login = new HashMap<String, Object>();
	static HashMap<String, ArrayList<String>> joint = new HashMap<String, ArrayList<String>>();
	private static User user1;
	static Customers customer1, customer2;
	static Account account1 = new Account();
	private static Employees employee1;
	private static Admins admin1;
	private static HrDept hr1;
	static ArrayList<String> joints = new ArrayList<String>();
	protected static ArrayList<String> jointAccounts = new ArrayList<String>();
	protected static CustomerScreen customerView = new CustomerScreen();
	protected static EmployeeScreen employeeView = new EmployeeScreen();
	protected static AdminScreen adminView = new AdminScreen();

	static DataHub dh1;
	static HashMap<String, Object> processing = new HashMap<String, Object>();
	private static Boolean jAcc = false, rAcc = false;

	public static void main(String[] args) {
		/*
		 * Declarations
		 */

		dh1 = new DataHub();
		dh1.initializeTempData();
		dh1.initialize();
		// joints = joint.get("cwest5496");
		// System.out.println(joints.get(0));
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

		accType();

		Boolean newAccount = true;
		String id = generateID(firstName, lastName);
		System.out.println(id);
		reg = new Registration(firstName, middleInitial, lastName, address, city, state, zip, phoneNum, rAcc, jAcc,
				newAccount, id, passWord, "CUSTOMER");
		addProcessing();
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
			customer1 = (Customers) data.get(user1.getId());
			customerView.customerScreen();
			break;
		case "EMPLOYEE":

			employeeView.employeeScreen();
			break;
		case "ADMIN":
			adminView.adminScreen();
			// adminOptions();
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

	public static void addProcessing() {
		processing.put(reg.getId(),
				new Registration(reg.getFirstName(), reg.getMiddleInitial(), reg.getLastName(), reg.getAddress(),
						reg.getCity(), reg.getState(), reg.getZip(), reg.getPhoneNum(), reg.getrAcc(), reg.getjAcc(),
						reg.getNewAccount(), reg.getId(), reg.getPassWord(), reg.getUserType()));
	}

}
