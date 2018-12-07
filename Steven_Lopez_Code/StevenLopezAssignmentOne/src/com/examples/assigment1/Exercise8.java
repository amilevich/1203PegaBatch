package com.examples.assigment1;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Exercise8 {
	// Q8. Write a program that stores the following strings in an ArrayList and
	// saves all the palindromes in another ArrayList.
	// “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”, “refer”,
	// “billy”, “did”

	public static void main(String[] args) {
		ArrayList<String> ar = new ArrayList<String>();
		ArrayList<String> pld = new ArrayList<String>();
		ar.add("karan");
		ar.add("madam");
		ar.add("tom");
		ar.add("civic");
		ar.add("radar");
		ar.add("jimmy");
		ar.add("kayak");
		ar.add("john");
		ar.add("refer");
		ar.add("billy");
		ar.add("did");
		//List of possible palindromes added to the array list
		StringBuilder rev;
		for (String name : ar) {
			rev = new StringBuilder(name);
			if (name.equals((rev.reverse()).toString())) {	// Verify that elements in the arrayList are 
				System.out.println(name);					// indeed palindromes by using the .equals() method
				pld.add(name);		

			}
		}
	}
}
