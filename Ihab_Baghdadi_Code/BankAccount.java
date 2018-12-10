/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author ijb87
 */
public class BankAccount {
    int balance;
    int previousTransaction;
    public String customerID;
    public String customerPassword;
    Scanner scanner = new Scanner(System.in);
    
    BankAccount() {
    }
    
    void deposit(int amount) {
        if(amount > 0) {
            balance = balance + amount;
            previousTransaction = amount;
        }
        else if (amount < 0)
            System.out.println("Transaction declined. Cannot deposit "
                    + "a negative amount.");
        else
            System.out.println("Transaction declined. Invalid input.");
    }
    
    void withdraw(int amount) {
        if(amount < balance) {
            balance = balance - amount;
            previousTransaction = -amount;
        }
        else if(amount > balance)
            System.out.println("Transaction declined. Not enough in account.");
        else 
            System.out.println("Transaction declined. Invalid input.");
    }
    
    void getPreviousTransaction() {
        if(previousTransaction > 0) {
            System.out.println("Deposited: "+previousTransaction);
        }
        else if(previousTransaction < 0) {
            System.out.println("Withdrawn: "+Math.abs(previousTransaction));
        }
        else {
            System.out.println("No transaction occured");
        }
    }
    
    public void showMenu(String customerID) throws InputMismatchException {
        char option='\0';
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome");

        do {
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Transaction");
        System.out.println("E. Exit");
        
            System.out.println("Enter an option.");
            option = scanner.next().charAt(0);
            System.out.println("\n");
            
            switch(option) {
                case 'A':
                    System.out.println("Balance = "+ balance);
                    System.out.println("\n");
                    break;
                
                case 'B':
                    System.out.println("Enter an amount to deposit: ");
                    int amount = scanner.nextInt();
                    deposit(amount);
                    System.out.println("\n");
                    break;
                    
                case 'C':
                    System.out.println("Enter an amount to withdraw: ");
                    int amount2 = scanner.nextInt();
                    withdraw(amount2);
                    System.out.println("\n");
                    break;
                    
                case 'D':
                    getPreviousTransaction();
                    System.out.println("\n");
                    break;
                    
                case 'E':
                    System.out.println();
                    break;
                    
                default:
                    System.out.println("Invalid Option! Please try again.");
                    break;
            }
        } while(option != 'E');
        
        System.out.println("Thank you for using our services. ");
    } 
} 