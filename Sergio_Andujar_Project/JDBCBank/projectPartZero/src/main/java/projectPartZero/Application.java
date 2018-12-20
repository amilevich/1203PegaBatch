package projectPartZero;

import java.io.IOError;
import java.sql.SQLException;

import com.revature.daoImp.FileManipulation;

import oracle.net.ns.NetException;

public class Application {
	
	public static void aApplication() {
		
		int response = 0;
		
		System.out.println("Which account would you like to open? ");
		System.out.println("1.) single account ");
		System.out.println("2.) exit ");
		System.out.print("Enter response here: ");
		
		response = Main.input.nextInt();
		
		switch(response) {
		case 1:
			System.out.println("Enter the following information");
			System.out.print("First Name: ");
			
			String firstName = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Last Name: ");
			
			String lastName = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Username: ");
			
			String alias = Main.input.next();
			
			System.out.print("\n");
			System.out.print("Password: ");
			
			String password = Main.input.next();
			
			System.out.println("Thank you. Your application is being processed by the next available employee");
			
			FileManipulation dao = new FileManipulation();
			try {
				dao.createApplication(firstName, lastName, alias, password);
			} catch (SQLException e) {
				System.out.println("You've entered incorrect information");
			} catch(IOError e) {
				System.out.println("Connection error, have a good day");
			}
			
			break;
		default:
			break;
		}
	}

}
