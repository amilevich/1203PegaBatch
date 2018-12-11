package projectPartZero;

public class Application {
	
	public static void aApplication() {
		
		int response = 0;
		
		System.out.println("Which account would you like to open? ");
		System.out.println("1.) single account ");
		//application file will be submitted but nothing will be done with it for now
		System.out.println("2.) joint account (under construction)");
		System.out.print("Enter response here: ");
		
		response = Main.input.nextInt();
		
		switch(response) {
		case 1:
			System.out.println("Enter the following information");
			System.out.print("Name: ");
			
			String name = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Social Security Number without dashses: ");
			
			String ss = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Username: ");
			
			String alias = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Password: ");
			
			String password = Main.input.next();
			
			System.out.println("Thank you. Your application is being processed by the next avaiable employee");
			
			FileManipulation.createApplication(name, ss, alias, password);
			
			break;
		case 2:
			
			System.out.println("User 1");
			System.out.println("Enter the following information");
			System.out.print("Name: ");
			
			String aName = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Social Security Number without dashses: ");
			
			String social = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Username: ");
			
			String userName = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Password: ");
			
			String passWord= Main.input.next();
			
			System.out.println("User 2");
			System.out.println("Enter the following information");
			System.out.print("Name: ");
			
			String anName = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Social Security Number without dashses: ");
			
			String SocialSecurity = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Username: ");
			
			String userName2 = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Password: ");
			
			String passWord2 = Main.input.next();
			
			System.out.println("Thank you. Your application is being processed by the next avaiable employee");
			
			
			FileManipulation.createJointApplication(aName, social, userName, passWord, anName, SocialSecurity, userName2, passWord2);
			break;
		}
		
	}

}
