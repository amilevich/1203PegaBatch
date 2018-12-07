package com.example.javaquestions;

import java.util.Scanner;

public class Q16 {
	// Q16. Write a program to display the number of characters for a string input.
	// The string should be entered as a command line argument using (String [ ]
	// args).
	public static void main(String[] args) {
		// Scanner scanner = new Scanner(System.in);
		// System.out.println(scanner.nextLine().length());
		System.out.println(args[1]);
		System.out.println(args[1].length());
	}

}
