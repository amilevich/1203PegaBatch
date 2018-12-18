package projectPartZero;

public class EmployeeMenu {
	
	public static void employeeMenu(BankAdmin workerClass) {
		
		boolean flag = true;
		
		while(flag) {
			System.out.println("Select what you want to do");
			System.out.println("1.) Accept/Cancel accounts");
			System.out.println("2.) View and edit all accounts");
			System.out.println("3.) cancel an account");
			System.out.println("4.) log out");
			System.out.print("Enter response here: ");
			int response = 0;
			response = Main.input.nextInt();
			switch(response) {
			case 1:
				((BankAdmin) workerClass).getApplications();
				break;
			case 2:
				((BankAdmin) workerClass).editAllAccounts();
				break;
			case 3:
				((BankAdmin) workerClass).cancelAnAccount();
				break;
			case 4:
				flag = false;
				break;
			default:
				System.out.println("This is not a valid option");
				break;	
		}
		}
	}
	
	public static void employeeMenu(Employee workerClass) {
		
		boolean flag = true;
		
		while(flag) {
				System.out.println("Select what you want to do");
				System.out.println("1.) Approve/deny open applications");
				System.out.println("2.) Approve/deny open joint applications (under construction)");
				System.out.println("3.) View Customer Information");
				System.out.println("4.) Log out");
				System.out.print("Enter response here: ");
				int response = 0;
				response = Main.input.nextInt();
				Employee theEmployee = ((Employee) workerClass);
				switch(response) {
				case 1:
					theEmployee.getApplications();
					break;
				case 2:
					//theEmployee.getJointApplications();
					break;
				case 3:
					theEmployee.displayAllInfo();
					break;
				case 4:
					flag = false;
					break;
				default:
					System.out.println("This is not a valid option");
					break;	
				
			}
		}
	}
}
