package com.homework1;

public class Question5 {

	public static String getSubstring(String str, int idx) {
		StringBuilder toReturn = new StringBuilder();
		
		// make sure the integer desired for substring is not more than
		// the length of the string
		if (idx > str.length())
			return str;

		// loop through the string and take 
		for (int i=0; i<idx; i++) {
			toReturn.append(str.charAt(i));
		}
		
		return toReturn.toString();
	}
	
	public static void main(String[] args) {
		
		String testString = "Bilboh";
		int desiredSubstringLength = 4;
		
		System.out.println(getSubstring(testString, desiredSubstringLength));
		
	}
	
}
