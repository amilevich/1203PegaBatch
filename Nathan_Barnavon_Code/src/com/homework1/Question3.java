package com.homework1;

public class Question3 {

	public static void main(String[] args) {
	
		String toReverse = "Bilboh";
		String newString = "";

		// Using string literals throws strings into the string pool
		for (int i=toReverse.length()-1; i >= 0; i--) {
			newString += toReverse.charAt(i);
		}
		
		System.out.println(newString);
		
	}
	
}
