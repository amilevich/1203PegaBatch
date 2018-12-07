package com.examples.assigment1;

public class Exercise5 {
	// Q5. Write a substring method that accepts a string str and an integer idx and
	// returns the substring contained between 0 and idx-1 inclusive. Do NOT use any
	// of the existing substring methods in the String, StringBuilder, or
	// StringBuffer APIs.
	public static void main(String[] args) {
	
		String text = "Hello";
		int max = 3;
		System.out.println(substring(text, max));//substring method implemented
	}
	//convert the string to a char array, go through the array and add it to result
	public static String substring(String str, Integer idx) {
		char[] ss = str.toCharArray();
		String result ="";
		for(int i=0; i<idx ; i++) {
			result+=ss[i];
		}
		return result;
	}

}
