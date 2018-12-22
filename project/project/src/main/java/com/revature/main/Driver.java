package com.revature.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.methods.AccountInterface;
import com.revature.methods.BankAccounts;
import com.revature.methods.DriverMethods;
import com.revature.methods.UserMethodsInterface;
import com.revature.pojo.BankAccount;
import com.revature.pojo.User;

@SuppressWarnings("unused")
public class Driver {
	public static void main(String[] args) throws SQLException, IOException {
		Scanner input = new Scanner(System.in);
		boolean exitCode = false;
		System.out.println("Welcome!");
		while (!exitCode) {
			System.out.println("\nWhat would you like to do? ");			
			System.out.println("1. Login"
                                + "\n2. Register"
                                + "\n3. Exit");
			
			int action = input.nextInt();
                    switch (action) {
                        case 1:
                            boolean accountMade = false;
                            boolean cancel = false;
                            while (!accountMade && !cancel) {
                                System.out.println("Enter a valid username: ");
                                System.out.println("To cancel, enter 0. ");
                                String desiredName = input.nextLine();
                                if (desiredName.equals(0)) {
                                    cancel = true;
                                } else {
                                    UserMethodsInterface method = new DriverMethods();
                                    User possibleUser = method.getUserByName(desiredName);
                                    if (possibleUser != null) { //if name exists
                                        System.out.println("Username is taken, please enter another. ");
                                    } else {
                                        System.out.println("Enter your desired password: ");
                                        String password = input.nextLine();
                                        UserMethodsInterface dm = new DriverMethods();
                                        boolean success = dm.createUser(desiredName, password);
                                        
                                        if (success) {
                                            System.out.println("Account created! You may login now.");
                                            accountMade = true;
                                        }
                                        
                                    }
                                }
                            }
                            break;
                        case 2:
                            //logging in
                            UserMethodsInterface method = new DriverMethods();
                            boolean loggedIn = false;
                            boolean exit = false;
                            String username = "";
                            String password = "";
                            while (!exit) {
                                System.out.println("Please type in your username:");
                                username = input.nextLine();
                                if (username.equals(0)) {
                                    exit = true;
                                } else {
                                    User possibleUser = method.getUserByName(username);
                                    if (possibleUser == null) {
                                        //username non-existing
                                        System.out.println("Username does not exist; please try again. ");
                                        System.out.println("If you would like to cancel, please enter 0. ");
                                    } else {
                                        System.out.println("Please type in your password: ");
                                        password = input.nextLine();
                                        if (!possibleUser.getPassword().equals(password)) {
                                            System.out.println("Invalid login details. ");
                                        } else {
                                            //user has correct login details
                                            User user = new User(username, password);
                                            AccountInterface bankMethod = new BankAccounts();
                                            System.out.println("Welcome " + username + "!");
                                            while (!exit) {
                                                System.out.println("What would you like to do?:");
                                                System.out.println("\n1. Manage Accounts"
                                                		+ "\n2. Exit "
                                                		+ "\n3. Delete");
                                                int choice = input.nextInt();
                                                if (choice == 0) {
                                                    List<BankAccount> bm = bankMethod.viewAccount(user);
                                                    if (bm == null) {
                                                        System.out.println("No accounts available. ");
                                                    } else {
                                                        boolean backout = false;
                                                        while (!backout) {
                                                            System.out.println("Account Number | Balance");
                                                            for(BankAccount account : bm) {
                                                                System.out.println(account.getAccountID() + " | $" + account.getBalance());
                                                            }
                                                            System.out.println("Type an account number to manage the account or enter 0 to return to the menu. ");
                                                            String option = input.nextLine();                                                            if(option.equals("0")) {
                                                                backout= true;
                                                            }else {
                                                                int accountNumber = Integer.parseInt(option);
                                                                System.out.println("You have selected Account #" + accountNumber +". ");
                                                                System.out.println("Would you like to: ");
                                                                System.out.println("1. Deposit"
                                                                		+ "\n2. Withdraw"
                                                                		+ "\n3. Delete");
                                                                String accOption = input.nextLine();
                                                                
                                                                if(accOption.equals(1)) {
                                                                    System.out.println("How much would you like to deposit? ");
                                                                    String nLine = input.nextLine();
                                                                    double amount = input.nextDouble();
                                                                            Double.parseDouble(nLine);
                                                                    bankMethod.changeValue(accountNumber,amount);
                                                                    
                                                                } else if (accOption.equals(2)) {
                                                                    System.out.println("How much would you like to withdraw? ");
                                                                    String nLine = input.nextLine();
                                                                    double amount = input.nextDouble();
                                                                            Double.parseDouble(nLine);
                                                                    boolean test = false;
                                                                    try {
                                                                        bankMethod.changeValue(accountNumber,(-amount));
                                                                        test = true;
                                                                    } catch (SQLException e) {
                                                                        // TODO Auto-generated catch block
                                                                        e.printStackTrace();
                                                                    } catch (IOException e) {
                                                                        // TODO Auto-generated catch block
                                                                        e.printStackTrace();
                                                                    }
                                                                    if (test) {
                                                                        System.out.println("Withdraw Successful. ");
                                                                    } else {
                                                                        System.out.println("Withdraw Failed. ");
                                                                    }
                                                                    
                                                                } else if (accOption.equals(3)) {
                                                                    System.out.println("Which account would you like to delete?");
                                                                    String deleteID = input.nextLine();
                                                                    int accID = Integer.parseInt(deleteID);
                                                                    boolean success = bankMethod.deleteAccount(accID);
                                                                    if (success) {
                                                                        System.out.println("Delete Successful. ");
                                                                    } else {
                                                                        System.out.println("Delete Failed. ");
                                                                    }
                                                                } else {
                                                                    System.out.println("Invalid Entry. ");
                                                                }
                                                            }
                                                        }
                                                        
                                                    }
                                                } else if (choice == 1) {
                                                    exit = true;
                                                } else if (choice == 2) {
                                                    
                                                }
                                            }
                                            
                                        }
                                        
                                    }
                                }
                                
                            }
                            break;
                        case 3:
                            System.out.println("Are you sure? (y/n) ");
                            String isSure = input.nextLine();
                            if (isSure.equals("y")) {
                                exitCode = true;
                                System.out.println("Thank you! ");
                            }
                            break;
                        default:
                            System.out.println("Please enter a valid command");
                            break;
                    }
		}
		input.close();
	}
}
