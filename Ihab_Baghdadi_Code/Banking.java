/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author ijb87
 */
public class Banking {

    private static BankAccount bankAccount = new BankAccount();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        String[] IDs = new String[20];
        String[] Passwords = new String[20];

        System.out.println("Welcome to the Banking Application. ");
        do {
        System.out.println("Enter an option: ");
        System.out.println("A. Login/Existing User");
        System.out.println("B. Register/New User");
        System.out.println("C. Exit");
        char option = scanner.next().charAt(0);
        switch(option) {
            case 'A':
                System.out.println("Enter Username: ");
                String tempCustomerUsername = scanner.nextLine();
                scanner.nextLine();
                if(Arrays.asList(IDs).contains(tempCustomerUsername)) {
                    System.out.println("Username Found.");
                }
                else {
                    System.out.println("Username Not Found.");
                    break;
                }
                System.out.println("Enter Password: ");
                String tempCustomerPassword = scanner.nextLine();
                if(Arrays.asList(Passwords).contains(tempCustomerPassword)) {
                    System.out.println("You are logged in.");
                    System.out.println(tempCustomerUsername);
                    bankAccount.showMenu(tempCustomerUsername);
                }
                else {
                    System.out.println("Login Failed.");
                }
                break;
                
            case 'B':
                int i = 0;
                System.out.println("Please enter an ID: ");
                IDs[i] = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Please enter a password: ");
                Passwords[i] = scanner.nextLine();
                i++;
                System.out.println("Thank you for signing up!");
                break;
                
            case 'C':
                break;
                
            default:
                System.out.println("Invalid Option! Please try again.");
                break;
            } 
        } while (option != 'C');
        
    }
}

