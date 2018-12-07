package com.example.commandline;

public class CommandLine {
	public static void main(String[] args) {
		int charcount = 0;
		// Checking if there is any string in the Command Line
		if (args.length > 0) {
			System.out.print("String in Command Line: ");
			//Counting how many char is in the String[] array
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i] + " ");
				charcount += args[i].length();
			}
			System.out.println("\nThe Number of Character in the command line String is: " + charcount);
		}
		else {
			System.out.println("No Command Line Arguments!");
		}
		
	}
}
