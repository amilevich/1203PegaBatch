package com.assignment_one.question_3;

// Q3. Reverse a string without using a temporary variable.  Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.
public class StringReverse {

	// reverse() returns the reverse of the given String s
	public static String reverse(String s) {

		/*// converts the input string into a character array as String's are immutable in
		// Java :(
		char[] charArr = s.toCharArray();

		// Loops through the first half of the string, swapping characters with
		for (int i = 0; i < (int) (charArr.length / 2); i++) {

			// XOR Swap of corresponding characters in the char array, using their ASCII
			// values
			charArr[i] = (char) (charArr[i] ^ charArr[charArr.length - i - 1]);
			charArr[charArr.length - i - 1] = (char) (charArr[i] ^ charArr[charArr.length - i - 1]);
			charArr[i] = (char) (charArr[i] ^ charArr[charArr.length - i - 1]);
		}

		// new String instantiated with the character Array that now contains the
		// reversed String
		String reversed = new String(charArr);*/
		
		// Switched to using a second String variable
		// in order to truly avoid using a 'temporary variable'
		// Keeping above code commented bc it was pretty cool though
		String reversed = "";
		// Loops through the given string starting at the end and looping to the beginning
		// concatenates each seen character to reversed
		for(int i = s.length()-1; i >= 0; i--) {
			reversed+=s.charAt(i);
		}
		return reversed;
	}

	public static void main(String args[]) {
		// "MyString" used as an example but works for any given string
		String s = "MyString";
		System.out.println("String: " + s);
		System.out.println("String Reversed: " + reverse(s));
	}
}
