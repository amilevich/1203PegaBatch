package partone;

public class Admin extends Employee {
	
	private UserAuthorizer userAuth = UserAuthorizer.getUserAuthSingleton();
	
	public Admin() {
		super();
	}
	
	public Admin(String username, String password) {
		super(username, password);
	}
	
	@Override
	public void menu() {
		Input in = Input.getInputSingleton();
		
		boolean returnToMenu = true;
		
		while(returnToMenu) {
			System.out.println("1. Admin Operations");
			System.out.println("2. Employee Operations");
			System.out.println("3. Quit");
			
			int choice = in.getInt();
			
			switch (choice) {
			case 1:
				adminMenu();
				break;
			case 2:
				super.menu();
				break;
			case 3:
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection.");
				break;
			}
		}
		
	}
	
	
	/**
	 * menu to house admin operations
	 */
	public void adminMenu() {
		Input in = Input.getInputSingleton();
		boolean returnToMenu = true;
		
		while(returnToMenu) {
			System.out.println("Top Secret Admin Menu. Shhhhh");
			System.out.println("1. Admin Withdraw");
			System.out.println("2. Admin Deposit");
			System.out.println("3. Admin Transfer");
			System.out.println("4. Cancel Account");
			System.out.println("5. Exit Admin Menu");
			
			int choice = in.getInt();
			
			switch (choice) {
			// Admin Withdraw
			case 1:
				adminWithdraw();
				break;
			// Admin Deposit
			case 2:
				adminDeposit();
				break;
			// Admin Transfer
			case 3:
				adminTransfer();
				break;
			// Cancel Account
			case 4:
				cancelAccount();
				break;
			// Exit Admin Menu
			case 5:
				returnToMenu = false;
				break;
			default:
				System.out.println("Invalid Selection");
				break;
			}
		}
	}
	
	// Menu methods, modularized:
	
	public void adminWithdraw() {
		
	}
	
	public void adminDeposit() {
		
	}
	
	public void adminTransfer() {
		
	}
	
	public void cancelAccount() {
		
	}
}
