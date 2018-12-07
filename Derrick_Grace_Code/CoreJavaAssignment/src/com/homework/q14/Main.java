package com.homework.q14;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Double x = 0.0;
		int select = 0;
		ZoneId zonedId = ZoneId.of( "America/Montreal" );
		LocalDate today = LocalDate.now( zonedId );
		String str = "I am learning Core Java";
		
		System.out.println("Enter 1 to find the square root of a number of your choice.");
		System.out.println("Enter 2 to get today's date.");
		System.out.println("Enter 3 to store a string into an array.");
		System.out.print("Enter selection: ");
		Scanner scan = new Scanner(System.in); 
		select = scan.nextInt();
		
		switch(select) {
		
		case 1 : System.out.print("Enter a number to find its square root: ");
				 x = scan.nextDouble();
				 System.out.println("Squre root of " + x + " is " + Math.sqrt(x));
				 scan.close();
				 break;
		case 2 : System.out.println("Today's date: " + today);
			 	 break;
		case 3 : String words[] = str.split(" ");
				 for(int i = 0; i < words.length; i++)
					 System.out.print(words[i] + " ");
		         break;
		default : System.out.println("Invalid selection.");
				  break;
		
		}

	}

}
