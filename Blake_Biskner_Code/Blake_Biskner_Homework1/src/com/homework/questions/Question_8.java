package com.homework.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * Palindrome ArrayList
 * @author Blake Biskner
 * @version 1.8
 */

public class Question_8{
	
	public static void main(String[] args) {
		// Variable Declaration
		int iterLength, mirrorIndex;
		char firstChar, lastChar;
		// ArrayList Instantiation
		List<String> nameList=new ArrayList<String>();
		List<String> palList=new ArrayList<String>();
		
		//ArrayList Initialization
		nameList.add("karan");
		nameList.add("madam");
		nameList.add("tom");
		nameList.add("civic");
		nameList.add("radar");
		nameList.add("jimmy");
		nameList.add("kayak");
		nameList.add("john");
		nameList.add("refer");
		nameList.add("billy");
		nameList.add("did");
		
		// Iterate through nameList
		for(String name:nameList) {
			boolean isPalindrome=true; // Initialize each String to be a palindrome
			iterLength=name.length()/2;
			// Initialize variable to iterate through half of name using integer division (thus not including middle character for odd length Strings)
			for(int i=0;i<iterLength;i++) {
				mirrorIndex=(name.length()-1)-i;
				// Index of mirrored character; for example if 5 characters and i=0 mirrored index will be index (5-1)-0=4
				firstChar=name.charAt(i);
				lastChar=name.charAt(mirrorIndex);
				//System.out.print(firstChar+" ");
				//System.out.println(lastChar);
				if(firstChar!=lastChar) { // If indexed char is not reflected within String to mirror index
					isPalindrome=false;
				}
			}
			if(isPalindrome) {
				palList.add(name); // Add to list of palindromes
			}
		}
		
		// Print list of names and palindromes
		System.out.println("List of Names");
		for(String name:nameList) {
			System.out.print(name+" ");
		}
		System.out.print('\n');
		System.out.println("List of Palindromes");
		for(String pal:palList) {
			System.out.print(pal+" ");
		}
	}
}
