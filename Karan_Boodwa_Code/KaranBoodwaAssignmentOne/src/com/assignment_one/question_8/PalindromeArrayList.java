package com.assignment_one.question_8;

import java.util.ArrayList;
import java.util.Arrays;

// Q8. Write a program that stores the following strings in an ArrayList and 
// saves all the palindromes in another ArrayList.
// “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  
// “refer”, “billy”, “did”

public class PalindromeArrayList {
	
	// isPalindrome() returns true if the given String s is a palindrome
	// otherwise, returns false
	public static boolean isPalindrome(String s) {
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != s.charAt(s.length()-i-1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		// First the list of strings above is stored in the ArrayList<String> allWords
		ArrayList<String> allWords = new ArrayList<String>(Arrays.asList("karan", "madam", "tom", "civic", "radar",
				"jimmy", "kayak", "john", "refer", "billy", "did"));

		// palindromes will be used to store the palindromes of the above list
		ArrayList<String> palindromes = new ArrayList<String>();

		// The ArrayList is iterated through and any palindromes encountered are added
		// to the 'palindromes' ArrayList<String>
		for(String str : allWords) {
			if( isPalindrome(str) ) {
				palindromes.add(str);
			}
		}
		
		System.out.println("Palindromes: ");
		for(String str: palindromes) {
			System.out.println(str);
		}
		
	}

}
