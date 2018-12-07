//Write a program having a concrete ;subclass that inherits three abstract methods from a superclass.  
//Provide the following three implementations in the subclass corresponding to the abstract methods in the superclass:
// 
//1.   	Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending if any are found.
//2.    Convert all of the lower case characters to uppercase in the input string, and return the result.
//3.    Convert the input string to integer and add 10, output the result to the console.
//Create an appropriate class having a main method to test the above setup.

package com.example.inherits;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Child c = new Child();
		Scanner scanner = new Scanner(System.in);

		// Getting the input from User
		System.out.print("Enter a String to Check for UpperCase Letters: ");
		String toTest = scanner.nextLine();
		System.out.println("Checking the string \"" + toTest + "\" for Upper Case Letter: " + c.checkForUpper(toTest));

		// Converting to Upper Case
		System.out.print("Enter a String to make all Uppercase: ");
		toTest = scanner.nextLine();
		System.out.println("Converting \""+ toTest +"\" to Uppercase: " + c.convertToUpper(toTest));
		
		//Adding an int to a String 
		System.out.print("Enter a Number: ");
		toTest = scanner.nextLine();
		System.out.println("Adding 10 to the string "+toTest+": " + c.convertAddTen(toTest));

		scanner.close();
	}
}
