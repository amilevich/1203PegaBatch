package com.example.javaquestions;
import java.util.Scanner;

public class Q5 {
	// Q5. Write a substring method that accepts a string str and an integer idx and
	// returns the substring contained between 0 and idx-1 inclusive.
	// Do NOT use any of the existing substring methods in the String,
	// StringBuilder, or StringBuffer APIs.

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String userString;
		int userInt;
		System.out.println("Enter a String: ");
		Scanner scanner = new Scanner(System.in);
		userString = scanner.nextLine();
		System.out.println("Enter an integer: ");
		userInt = scanner.nextInt();
		System.out.println(substring(userString, userInt));
		
		scanner.close();
	}

	public static String substring(String str, int idx) {

		String result = "";

		if (idx > str.length()) {
			idx = str.length();
			System.out.println("Integer must not be longer than length of the string.\nDefaults to sting length.");
		} else if (idx <= 1) {
			idx = 2;
			System.out.println("Integer must not be less than 2 because idx - 1 must be >= 1.\nDefaults to 2.");
		}
		for (int i = 0; i < idx - 1; i++) {
			result = result + str.charAt(i);
		}

		return result;
	}
}
