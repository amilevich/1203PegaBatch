package com.assignment_one.question_16;

/*
 * Q16. Write a program to display the number of characters for a string input. 
 * The string should be entered as a command line argument using (String [ ] args).
 */

public class CommandLineArgs {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Error: no command line arg provided.");
		} else {
			System.out.println(args[0]);
			// Prints out the number of characters through the arguments length
			System.out.println("Number of characters: " + args[0].length());
		}

	}

}
