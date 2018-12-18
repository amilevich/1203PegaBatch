package projectPartZero;

public class Menu {
	
	public static void aMenu() {
		int choice = 0;
		while(true){
			System.out.println("Greetings! Welcome to the New World Bank, How can we help you today?");
			System.out.println("1.) Open an account");
			System.out.println("2.) Login to an exisitng account");
			System.out.println("3.) Employee login");
			System.out.println("4.) End session");
			System.out.print("Enter response here: ");
			choice = Main.input.nextInt();
			switch(choice) {
			case 1:
				Application.aApplication();
				break;
			case 2:
				CustomerLogin.loginCustomer();
				break;
			case 3:
				EmployeeLogin.loginEmployee();
				break;
			case 4:
				Terminate end = new Terminate();
				end.endSession();
				break;
			default:
				System.out.println("This is not a valid option." );
				break;
			}


		}
	}
}
