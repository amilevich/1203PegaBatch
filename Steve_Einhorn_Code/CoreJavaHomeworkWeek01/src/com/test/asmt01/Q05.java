package com.test.asmt01;

public class Q05 {
	
	public static void main(String[] args) {
		
		String testString = "colour";
		int index = 4;
		
		String subString = createSubString(testString, index);
		
		System.out.println(subString);
		
	}
	
	public static String createSubString(String testString, int index) {
		
		String subString = "";
		
		for (int i = 0;  i < index; i++) {
			subString = subString + testString.charAt(i);
		}
		
		return subString;
		
	}

}
