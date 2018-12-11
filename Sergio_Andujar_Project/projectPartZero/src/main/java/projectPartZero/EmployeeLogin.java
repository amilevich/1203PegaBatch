package projectPartZero;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class EmployeeLogin {
	
	public static void loginEmployee() {
		int response = 0;
		boolean flag = true;
		
		while(flag) {
			System.out.println("Welcome ");
			System.out.println("1.) Login");
			System.out.println("2.) go back to main menu ");
			System.out.print("Enter response here: ");
			
			String givenUserName = "";
			String givenPassword = "";

			response = Main.input.nextInt();
		
			switch(response) {
			case 1:
				System.out.println("Enter your username and password ");
				System.out.print("Username: ");
				givenUserName = Main.input.next();
				System.out.print("Password: ");
				givenPassword = Main.input.next();
				try(FileInputStream file = new FileInputStream("Employees.txt")){
					BufferedReader br = new BufferedReader(new InputStreamReader(file));
						ArrayList<String> employeeInformation = new ArrayList<String>();
						String line = "";
						String userName = "";
						String type = "";
						String password = "";
						while((line = br.readLine()) != null) {
							String[] data = line.split(":");
							type = data[0];
							userName = data[1];
							password = data[2];
							if(givenUserName.equals(userName) && givenPassword.equals(password)) {
								System.out.println("Welcome " + "" + type + " " + userName);
								if(type.equals("Employee")) {
									Employee aEmployee = new Employee(userName);
									EmployeeMenu.employeeMenu(aEmployee);
								}
								if(type.equals("BankAdmin")){
									BankAdmin aBankAdmin = new BankAdmin(userName);
									EmployeeMenu.employeeMenu(aBankAdmin);
								}
								break;
							}
						}
						br.close();
						file.close();
				break;
					
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case 2:
				flag = false;
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("This is not a valid option." );
			}
		}
	}
}
