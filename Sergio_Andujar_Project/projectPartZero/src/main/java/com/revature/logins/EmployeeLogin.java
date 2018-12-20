package com.revature.logins;

import java.io.IOError;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Menus.EmployeeMenu;
import com.revature.classes.BankAdmin;
import com.revature.classes.Employee;
import com.revature.daoImp.FileManipulation;

import projectPartZero.Main;


public class EmployeeLogin {
	
	public static void loginEmployee() {
		int response = 0;
		boolean flag = true;
		FileManipulation dao = new FileManipulation();
		while(flag) {
			System.out.println("Welcome ");
			System.out.println("1.) Employee Login");
			System.out.println("2.) Bank Admin Login");
			System.out.println("3.) exit");
			System.out.print("Enter response here: ");
			
			String givenUserName = "";
			String givenPassword = "";

			response = Main.input.nextInt();
		
			switch(response) {
			case 1:
				System.out.println("Enter your username and password ");
				System.out.print("Username:");
				givenUserName = Main.input.next();
				System.out.print("Password:");
				givenPassword = Main.input.next();
				Employee aEmployee;
				try {
					aEmployee = dao.getAEmployee(givenUserName, givenPassword);
				} catch (SQLException e) {
					System.out.println("You've entered an incorrect username and password ");
					break;
				}catch(IOError e) {
					System.out.println("Connection error, have a good day");
					break;
				}
				EmployeeMenu.employeeMenu(aEmployee);
				break;
			case 2:
				System.out.println("Enter your username and password ");
				System.out.print("Username:");
				givenUserName = Main.input.next();
				System.out.print("Password:");
				givenPassword = Main.input.next();
				BankAdmin admin;
				try {
					admin = dao.getABankAdmin(givenUserName, givenPassword);
				} catch (SQLException e) {
					System.out.println("You've entered an incorrect username and password ");
					break;
				}catch(IOError e) {
					System.out.println("Connection error, have a good day");
					break;
				}
				EmployeeMenu.employeeMenu(admin);
				break;
			case 3:
				flag = false;
				System.out.println("Returning to main menu");
				break;
			default:
				System.out.println("This is not a valid option." );
			}
		}
	}
}
