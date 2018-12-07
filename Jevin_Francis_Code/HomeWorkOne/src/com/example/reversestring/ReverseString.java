//Reverse a string without using a temporary variable.  Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.

package com.example.reversestring;

import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the String that needs to be reversed: ");
		String name = scanner.nextLine();
		String reversed = stringReverse(name);
		System.out.println("Reversed String: " + reversed);
		scanner.close();
	}

	// method to reverse the string
	public static String stringReverse(String str) {
		String result = "";
		// reversing the string with appending to string in reverse order
		for (int i = str.length() - 1; i >= 0; i--) {
			result = result + str.charAt(i);
		}
		return result;
	}
}
