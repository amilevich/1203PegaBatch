package com.assignment.weekone;

public class Q3 {
	static String reverseString (String s) {
		for (int i = 0; i < s.length();i++) {//grabs length of string and uses that for method
			s = s.substring(1,s.length()-i)+s.charAt(0)+s.substring(s.length()-i);//for loop, puts i index into end of string index minus i spot, or left to previous end of index
		}
		//System.out.println(s);
		return s;//returns string
	}
	public static void main(String[] args) {
		String assignment = "Hello World!";//standard string
		System.out.println(assignment);
		assignment = reverseString(assignment);//reversal
		System.out.println(assignment);//new output

	}

}
