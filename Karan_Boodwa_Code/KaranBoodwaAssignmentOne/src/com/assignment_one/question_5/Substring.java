package com.assignment_one.question_5;

/* Q5. Write a substring method that accepts a string str and an integer idx 
 * and returns the substring contained between 0 and idx-1 inclusive.  
 * Do NOT use any of the existing substring methods in the String, 
 * StringBuilder, or StringBuffer APIs.
 */
public class Substring {

	// substring() function returns the substring contained between
	// 0 and idx-1 inclusive of the given String str
	public static String substring(String str, int idx) {
		// StringBuilder object instantiated to hold the substring
		StringBuilder stringbuilder = new StringBuilder();

		/*
		 * Loop from index 0 to idx-1, appending each corresponding character from str
		 * to the stringbuilder, building up the substring Note: NOT using existing
		 * substring method!
		 */
		for (int i = 0; i < idx; i++) {
			stringbuilder.append(str.charAt(i));
		}

		// Gets the string contained in the StringBuilder and returns it
		return stringbuilder.toString();
	}

	public static void main(String[] args) {
		// String and idx used for the substring method
		String str = "MyString";
		int idx = 4;

		System.out.println("String: " + str);
		// Prints the substring contained between 0 and idx-1 inclusive of str
		System.out.println("Substring between 0 and " + (idx - 1) + ": " + substring(str, idx));

	}

}
