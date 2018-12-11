package project0.bank;

import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Bank {
	Scanner keyboard = new Scanner(System.in);

	static private ArrayList<Accounts> openAccounts = new ArrayList<Accounts>();;
	static private ArrayList<Applications> openApplications = new ArrayList<Applications>();;
	static private ArrayList<Customer> customers = new ArrayList<Customer>();
	static private ArrayList<Users> users = new ArrayList<Users>();
	static private ArrayList<Admin> admins = new ArrayList<Admin>();
	static private ArrayList<Employee> employees = new ArrayList<Employee>();
	static private Users activeUser;

	public Bank() {

	}

	public void welcome() {
		System.out.println("Welcome to Bank of Neil");
		System.out.println("To start, Neil B4 Your King");
		System.out.println("Now that you're Neiling, I'll listen to your request");
		Customer harvey = new Customer("Harvey", "Jean-Paul", "Hypeman", "ImSleepy");
		createAccountsForEarlyAdopters(harvey);
		Bank.customers.add(harvey);
		Bank.users.add(harvey);
		Employee jakob = new Employee("jakob", "Pfeiffer", "NotoriousJRP", "ImHungry");
		createAccountsForEarlyAdopters(jakob);
		Bank.employees.add(jakob);
		Bank.users.add(jakob);
		Customer lemma = new Customer("Keywords", "Lemma", "youtube", "theorem");
		Bank.customers.add(lemma);
		Bank.users.add(lemma);
		createAccountsForEarlyAdopters(lemma);
		Employee charles = new Employee("Charles", "Toe", "Fufu", "Cassava");
		Bank.employees.add(charles);
		Bank.users.add(charles);
		createAccountsForEarlyAdopters(charles);
		Admin neil = new Admin("Neil", "Oza", "NeilB4YourKing", "TheKingHasTheFlu");
		Bank.admins.add(neil);
		Bank.users.add(neil);
		createAccountsForEarlyAdopters(neil);


		signInScreen();
	}
	public void createAccountsForEarlyAdopters(Users u) {
		u.myAccount= new Accounts(25.00, u.getUsername(), u);
		openAccounts.add(u.myAccount);
		u.approved=true;
	}

	public void signInScreen() {
		System.out.println("Type 1 to sign in");
		System.out.println("Type 2 to create an Application");
		System.out.println("Type 0 to close the program. You're money will totally be here when you come back");
		int input = keyboard.nextInt();
		System.out.println("you typed " + input);
		switch (input) {
		case 1:
			System.out.println("Type your username");
			String username = keyboard.next();
			System.out.println("Type your password");
			String password = keyboard.next();
			if (signIn(username, password)) {
				System.out.println("Congrats, you have signed in!");
				selectUI();
			} else {
				System.out.println("sorry, not a valid login");
				signInScreen();
			}

			break;
		case 2:

			startApplication();

			break;
		case 0:
			System.out.println("thanks for visiting Bank of Neil. See you next time");
			System.exit(0);
			break;

		default:
			System.out.println("invalid Input. Please try again");
			signInScreen();
			break;
		}

	}
	public void selectUI() {
		switch (activeUser.Type) {
		case Admin:
			adminScreen();
			
			break;
			
		case Employee:
			employeeScreen();
			break;
			
		case Customer:
			customerScreen();
			break;

		default:
			System.out.println("error. Can't determine type of user");
			signInScreen();
			break;
		}
		
	}
	public void customerScreen() {
		System.out.println("welcome "+activeUser.firstName );
		if(activeUser.approved==false) {
			System.out.println("chill dude, your account hasnt been made yet");
			System.out.println("you are being signed out now");
			activeUser=null;
			signInScreen();
		}
		System.out.println("Welcome to your account");
		System.out.println("your current balance is "+activeUser.myAccount.getBalance());
		System.out.println("Type 1 to withdraw, 2 to deposit, 3 to transfer, and 4 to sign out");
		System.out.println("every time you misinput, I take a dollar");
		int nextInput=keyboard.nextInt();
		switch (nextInput) {
		case 1:
			System.out.println("how much would you like to withdraw?");
			double amount= keyboard.nextDouble();
			activeUser.myAccount.withdraw(amount);
			customerScreen();
			break;
		case 2:
			System.out.println("how much would you like to deposit?");
			double depositAmount= keyboard.nextDouble();
			activeUser.myAccount.deposit(depositAmount);
			customerScreen();
			
		case 3: 
			System.out.println("how much would you like to transfer?");
			double transfer=keyboard.nextDouble();
			System.out.println("Which account do you want to transfer to (enter username)");
			String b=keyboard.next();
			for(Accounts a: openAccounts) {
				if (a.name.equals(b)) {
					activeUser.myAccount.transferFunds(transfer, a);
					System.out.println("funds transfered");
					break;

				}
			}
			customerScreen();

			break;
			
		case 4:
			activeUser=null;
			signInScreen();

		default:
			System.out.println("try again");
			activeUser.myAccount.withdraw(1.00);
			customerScreen();
			
			break;
		}
		
	}
	public void adminScreen() {
		System.out.println("Now for fun stuff");
		System.out.println("Press 1 to check pending applications,press 2 to withdraw from an account");
		System.out.println("Press 3 to deposit funds from an application ,press 4 to transfer between accounts. "
				+ "press 5 to close an account. press 6 to sign out");
		int switchValue= keyboard.nextInt();
		switch (switchValue) {
		case 1:
			for(Applications a: openApplications) {
				a.getApplicant().toString();
				boolean approval= a.approveAccount();
				if(approval) {
					openAccounts.add((a.getApplicant().myAccount));
				}
				openApplications.remove(a);
			}
			adminScreen();
			break;
		case 2:
			System.out.println("Where would you like to withdraw from (enter username)");
			String b=keyboard.next();
			System.out.println("how much would you like to withdraw?");
			double wAmount=keyboard.nextDouble();
			for(Users u: users) {
				if (u.getUsername().equals(b)) {
					u.myAccount.withdraw(wAmount);
					System.out.println("funds withdrawn");
					break;

				}
			}
			adminScreen();
			break;
			
		case 3:

			System.out.println("Where would you like to withdraw from (enter username)");
			String c=keyboard.next();
			System.out.println("how much would you like to withdraw?");
			double dAmount=keyboard.nextDouble();
			for(Users u: users) {
				if (u.getUsername().equals(c)) {
					u.myAccount.deposit(dAmount);
					System.out.println("funds withdrawn");
					break;

				}
			}
			adminScreen();
			break;
		case 4:

			System.out.println("Where would you like to transfer from (enter username)");
			String t1=keyboard.next();
			System.out.println("Where would you like to transfer to (enter username)");
			String t2=keyboard.next();
			System.out.println("how much would you like to withdraw?");
			double tAmount=keyboard.nextDouble();

			
			for(Users u: users) {
				if (u.getUsername().equals(t1)) {
					u.myAccount.withdraw(tAmount);
					System.out.println("funds withdrawn");
					break;
				}
			}
			for(Users u: users) {
				if (u.getUsername().equals(t2)) {
					u.myAccount.deposit(tAmount);
					System.out.println("funds withdrawn");
					break;
				}
			}
			adminScreen();
			break;

		case 5:
			System.out.println("type the username of the account you would like to delete");
			String delete= keyboard.next();
			for (Users u: users) {
				if(u.getUsername().equals(delete)) {
					u.myAccount=null;
					openAccounts.remove(u.myAccount);
					System.out.println("deleted");
				}
			}
			adminScreen();
			break;
		case 6:
			activeUser=null;
			signInScreen();

		default:
			System.out.println("you meesed up. Try again");
			adminScreen();
			break;
		}
	}
	public void employeeScreen() {
		if(!activeUser.approved) {
			System.out.println("You haven't been hired yet");
			activeUser=null;
			signInScreen();
		}
		System.out.println("welcome " +activeUser.firstName);
		System.out.println("What would you like to do");
		System.out.println("enter 1 to approve/deny pending accounts");
		System.out.println("enter 2 to view a particular account");
		System.out.println("enter 3 to sign out");
		int key=keyboard.nextInt();
		switch (key) {
		case 1:
			for(Applications a: openApplications) {
				a.getApplicant().toString();
				boolean approval= a.approveAccount();
				if(approval) {
					openAccounts.add((a.getApplicant().myAccount));
				}
				openApplications.remove(a);
			}
			employeeScreen();
			break;
		case 2: 
			System.out.println("Which user are you looking for");
			String desiredUsername= keyboard.next();
			boolean accountExists=false;
			for(Users u: users ) {
				if (u.getUsername().equals(desiredUsername)) {
					System.out.println(u.toString());
					System.out.println("balance= " + u.myAccount.getBalance());
					accountExists=true;
				}
				
			}
			if (!accountExists) {
				System.out.println("invalid username");
			}
			employeeScreen();
			
		case 3:
			System.out.println("Bye. I love you");
			activeUser=null;
			signInScreen();

		default:
			System.out.println("Please learn to type");
			employeeScreen();
			break;
		}

	}


	public void startApplication() {

		System.out.println("Thank you for starting your application. Please answer the following questions.");
		System.out.println("what is your first name");
		String firstName = keyboard.next();

		System.out.println("what is your last name");
		String lastName = keyboard.next();
		boolean usernameAvailable = true;
		String username;
		do {
			usernameAvailable=true;
			System.out.println("What would you like your username to be?");
			username = keyboard.next();
			for (Users u : users) {
				if (u.getUsername().equals(username)) {
					usernameAvailable = false;
					System.out.println("username Unavailable. Please try again.");
				}
				
			}
		} while (usernameAvailable == false);

		String password;
		String passwordCopy;
		boolean passwordsMatch = true;
		do {
			System.out.println("please type in a password");
			password = keyboard.next();
			System.out.println("please retype in a password");
			passwordCopy = keyboard.next();
			if (password.equals(passwordCopy))
				passwordsMatch = true;
			else {
				passwordsMatch = false;
				System.out.println("your passwords dont match. try harder");
			}
		} while (passwordsMatch == false);
		System.out.println("there is a $25 application fee. Will you pay?");
		String answer = keyboard.next();
		if (answer.equals("NO")) {
			signInScreen();
		}
		System.out.println("I'll take that as a yes");
		createUser(firstName, lastName, username, password);
		System.out.println("congrats, you submitted an application. Come back later to see if you were approved!");
		signInScreen();

	}

	public void createUser(String FirstName, String LastName, String Username, String Password) {

		System.out.println("What type of User are you?");
		System.out.println("type 1 for Customer, 2 for employee,and 3 for Admin");
		String usernameDuplicate = Username;
		int input = keyboard.nextInt();
		switch (input) {
		case 1:
			Customer c = new Customer(FirstName, LastName, Username, Password);
			customers.add(c);
			users.add(c);
			Applications appC = new Applications(c);
			openApplications.add(appC);

			break;

		case 2:
			Employee e = new Employee(FirstName, LastName, Username, Password);
			employees.add(e);
			users.add(e);
			Applications appE = new Applications(e);
			openApplications.add(appE);

			break;
		case 3:
			Admin a = new Admin(FirstName, LastName, Username, Password);
			admins.add(a);
			users.add(a);
			Applications appA = new Applications(a);
			openApplications.add(appA);

			break;

		default:
			System.out.println("Not a valid input. Try again");
			System.out.println("it's really not that complicated");
			createUser(FirstName, LastName, usernameDuplicate, Password);

			break;
		}
	}

	public Users getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Users activeUser) {
		this.activeUser = activeUser;
	}

	public boolean signIn(String username, String password) {
		boolean isRegistered = false;
		for (Users u : users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				setActiveUser(u);
				System.out.println(u.Type);
				isRegistered = true;
			}
		}
		return isRegistered;
	}

	public void closeAccount(Accounts a) {
		if (activeUser.Type.equals("Admin")) {

			openAccounts.remove(a);
		}
	}

}
