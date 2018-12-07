package com.homework.questions;

/**
 * String Reversal
 * 
 * @author Blake Biskner
 * @version 1.3
 */

public class Question_3 {

	public static void main(String[] args) {
		// Variable Declaration and Initialization
		String str="testString";
		char charArray[]=str.toCharArray(); // Convert immutable String to mutable array
		char currChar, lastChar;
		int arrayEnd=charArray.length-1;
		
		// In the for loop i only need to iterate over half of the chars 
		// if there is even number (for an odd also does not need to flip middle char) thus we employ integer division
		// For example if 3 chars in array arr.length/2 will return 1, as we only need to flip the first number with the last
		for(int i=0; i<(charArray.length/2);i++) {
			currChar=charArray[i];
			lastChar=charArray[arrayEnd-i];
			charArray[i]=lastChar;
			charArray[arrayEnd-i]=currChar;
		}
		str=String.valueOf(charArray); // Convert str back to String
		System.out.println(str);
	}
}
