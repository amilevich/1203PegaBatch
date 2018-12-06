package com.assignment_one.question_18;

import java.util.Scanner;

/*
 * Q18. Write a program having a concrete subclass that inherits three abstract methods from a superclass.  
 * Provide the following three implementations in the subclass corresponding to the abstract methods in the superclass:
 * 1. Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending if any are found
 * 2. Convert all of the lower case characters to uppercase in the input string, and return the result.
 * 3. Convert the input string to integer and add 10, output the result to the console.
 */
abstract class StringCheckerParent {
	public abstract boolean hasUpperCase(String s);

	public abstract String toUpper(String s);

	public abstract void toIntPlusTen(String s);
}

class StringCheckerChild extends StringCheckerParent {

	@Override
	public boolean hasUpperCase(String s) {
		// Convert the String parameter into a char array
		char c[] = s.toCharArray();

		// Loop through the char array, checking each char to see if it's UPPERCASE
		for (char letter : c) {
			if (Character.isUpperCase(letter)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toUpper(String s) {
		return new String(s.toUpperCase());
	}

	@Override
	public void toIntPlusTen(String s) {
		Scanner scan = new Scanner(s);
		System.out.println(scan.nextInt() + 10);
	}

}

public class Inheritance {

	public static void main(String[] args) {
		// Demonstrating the usage of all 3 inherited then implemented methods
		StringCheckerChild strchk = new StringCheckerChild();

		String s = "hello";
		String num = "9";

		if (strchk.hasUpperCase(s)) {
			System.out.println(s + " contains uppercase characters");
		} else {
			System.out.println(s + " does not contain any uppercase characters");
		}
		System.out.println(s + " in all UPPERCASE: " + strchk.toUpper(s));
		System.out.print(num + " + 10 = ");
		strchk.toIntPlusTen(num);

	}

}
