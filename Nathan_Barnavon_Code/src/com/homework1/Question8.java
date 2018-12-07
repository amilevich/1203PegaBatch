package com.homework1;


import java.util.ArrayList;

public class Question8 {

	public static ArrayList<String> extractPalindromes(ArrayList<String> words) {
		
		// Create a new array list to populate with palindromes
		ArrayList<String> toReturn = new ArrayList<String>();
		
		// StringBuffers will be utilized to access the built-in reverse method
		StringBuffer str = new StringBuffer("");
		
		// loop through all the words in the arraylist passed inside
		for(int i=0; i<words.size(); i++) {
			
			// change what is in the StringBuffer to the current word
			str.replace(0, str.length(), words.get(i));
			
			// check if the current word is equal to the reversed string (by the string buffer)
			if (words.get(i).compareTo(str.reverse().toString()) == 0) {
				toReturn.add(str.toString());
			}
		}
		
		return toReturn;
	}
	
	public static void main(String[] args) {
		
		ArrayList<String> words = new ArrayList<String>();
		words.add("karan");
		words.add("madam");
		words.add("tom");
		words.add("civic");
		words.add("radar");
		words.add("jimmy");
		words.add("kayak");
		words.add("john");
		words.add("refer");
		words.add("billy");
		words.add("did");
		
		ArrayList<String> palindromes = extractPalindromes(words);
		
		for (int i=0; i<palindromes.size(); i++) {
			System.out.println(palindromes.get(i));
		}
	}
	
}
