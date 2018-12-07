package com.example.javaquestions;

public class Q3 {
	// Q3. Reverse a string without using a temporary variable. Do NOT use reverse()
	// in the StringBuffer or the StringBuilder APIs.

	public static void main(String[] args) {
		String string = "123456789";
		System.out.println(string);
		// substring(int beginIndex, int endIndex)
		// Returns a new string that is a substring of this string.
		for (int i = 0; i < string.length(); i++) {

			// 1st substring creates string from 2nd index to last and it gets small as the loop iterates only grabbing whats not used
			// 2nd substring add what was the first index to the back
			// 3rd substring doesnt do anything until its less than 9 it gets bigger as the loop iterates
			string = string.substring(1, string.length() - i) + string.substring(0, 1)
					+ string.substring(string.length() - i, string.length());
		}

		System.out.println(string);

	}

}
