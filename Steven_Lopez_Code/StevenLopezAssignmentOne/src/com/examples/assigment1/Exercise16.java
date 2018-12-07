package com.examples.assigment1;

public class Exercise16 {
	// Q16. Write a program to display the number of characters for a string input.
	// The string should be entered as a command line argument using (String [ ]
	// args).
	public static void main(String[] args) {
		//String entered as a command line argument is: "Hello World!"
		int num=0;
		//Print the command line argument
		for (String s : args) {
			System.out.print(s + " ");
		}
		
		//Calculate the number of characters in the cmd argument
		for (String s : args) {
			for(char c: s.toCharArray()) {
				num++;
			}
		}
		//Print out the results.
		System.out.println("\nThe numbers of characters: " + num);
	}
}
