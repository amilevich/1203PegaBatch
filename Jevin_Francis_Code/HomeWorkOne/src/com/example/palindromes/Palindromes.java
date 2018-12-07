//Write a program that stores the following strings in an ArrayList and saves all the palindromes in another ArrayList.
//“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”

package com.example.palindromes;

import java.util.ArrayList;

public class Palindromes {
	public static void main(String[] args) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> palindromesNames = new ArrayList<String>();
		names.add("karan");
		names.add("madam");
		names.add("tom");
		names.add("civic");
		names.add("radar");
		names.add("jimmy");
		names.add("kayak");
		names.add("john");
		names.add("refer");
		names.add("billy");
		names.add("did");

		for (String name : names) {
			String reversed = stringReverse(name);
			// checking if the reversed string is the same as the original to
			// find out if the string is Palindrome
			if (reversed.equals(name)) {
				palindromesNames.add(name);
			}
		}
		System.out.println("All Names: " + names);
		System.out.println("Palindromes Names: " + palindromesNames);
	}

	// function to reverse the string by appending reverse to a new string
	public static String stringReverse(String str) {
		String result = "";

		for (int i = str.length() - 1; i >= 0; i--) {
			result = result + str.charAt(i);
		}
		return result;
	}
}
