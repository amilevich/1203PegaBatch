package com.homework.questions;

/**
 * Substring Method
 * 
 * @author Blake Biskner
 * @version 1.5
 */

public class Question_5 {

	/**
	 * Creates substring of provided string up to but not including char at index given
	 * @param str is the provided string
	 * @param index is the noninclusive stopping index for substring
	 * @return returns substring
	 */

	public static String subString(String str, int idx) {
		// Variable Initiailization and Declaration
		String letter;
		String sub = String.valueOf(str.charAt(0)); // Create an initial substring to which to append

		for (int i = 1; i < idx; i++) {
			letter = String.valueOf(str.charAt(i)); // Iterate through the necessary characters
			sub = sub.concat(letter); // Append the collected characters to sub to create substring
		}
		return sub;
	}

	public static void main(String[] args) {
		String word = "testString";
		int index = 5;
		System.out.println(subString(word, index));
	}
}