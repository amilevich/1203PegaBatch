package com.assignment.weekone;

import java.util.Scanner;

public class Q18Main extends Q18SubClass {
	public Q18Main(String tester) {
		super(tester);//auto generated constructor from inheriting Q18SubClass
	}

	public static void main(String[] args) {
		String userInput;
		Q18SubClass userCheck;//object that will be used to check the string
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter a string: ");
		userInput = scanner.nextLine();
		userCheck = new Q18SubClass(userInput);
		
		System.out.println("The String is: " + userInput);
		
		if(userCheck.isUpperCase())//to check if string has an upper case letter
			System.out.println("It has at least one uppercase letter.");
		else
			System.out.println("It does not have uppercase letters");
		
		System.out.println("Here is the string with all uppercase letters: " + userCheck.capitalizeString());//will show capitalized string
		System.out.println("When converted to an Integer and add 10 it is: " + userCheck.convertToInt());//converts to int and +10
	}
}
