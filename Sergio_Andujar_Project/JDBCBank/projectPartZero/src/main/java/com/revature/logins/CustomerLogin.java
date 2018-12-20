package com.revature.logins;

import java.io.IOError;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.Menus.CustomerMenu;
import com.revature.Menus.TransactionMenu;
import com.revature.classes.Account;
import com.revature.classes.Customer;
import com.revature.daoImp.FileManipulation;

import projectPartZero.Main;

public class CustomerLogin {
	
	public static void loginCustomer() {
		
		int response = 0;
		boolean flag = true;
		
		while(flag) {
			System.out.println("Welcome ");
			System.out.println("1.) Login");
			System.out.println("2.) go back to main menu ");
			System.out.print("Enter response here: ");
			
			String givenUserName = "";
			String givenPassword = "";
			Customer aCustomer = null;
			FileManipulation dao = new FileManipulation();

			response = Main.input.nextInt();
		
			switch(response) {
			case 1:
				System.out.println("Enter your username and password ");
				System.out.print("Username: ");
				givenUserName = Main.input.next();
				System.out.print("Password: ");
				givenPassword = Main.input.next();
				try {
					aCustomer = dao.getACustomer(givenUserName, givenPassword);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("You have entered an incorrect username or password");
					break;
				}
				catch(IOError e) {
					System.out.println("Connection error, have a good day");
					break;
				}
				CustomerMenu.customerMenu(aCustomer);
				break;
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
