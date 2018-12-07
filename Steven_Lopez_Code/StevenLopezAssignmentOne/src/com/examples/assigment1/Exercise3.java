package com.examples.assigment1;

public class Exercise3 {
	// Q3. Reverse a string without using a temporary variable. Do NOT use reverse()
	// in the StringBuffer or the StringBuilder APIs.
	public static void main(String[] args) {
		String text = "Hello";
		String reverse = "";
		//Go from last to first take every char and send it to the output string.
		for (int i = text.length(); i > 0; i--) {
			reverse += text.substring(i - 1, i);
		}
		//Print the results
		System.out.println(reverse);
	}
}
