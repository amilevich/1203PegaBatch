package com.test.asmt01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q08 {
	
	public static void main(String[] args) {
	
		// Populate an ArrayList of Employees (name, dept, age).
		List<String> strings = new ArrayList<String>(); 
		strings.add("karan"); 
		strings.add("madam"); 
		strings.add("tom"); 
		strings.add("civic"); 
		strings.add("radar"); 
		strings.add("jimmy"); 
		strings.add("kayak"); 
		strings.add("john"); 
		strings.add("refer"); 
		strings.add("billy"); 
		strings.add("did"); 
		
		List<String> palindromes = new ArrayList<String>();
	    
	    // Print the unsorted ArrayList.
	    System.out.println("Strings - All"); 
        for (int i = 0; i < strings.size(); i++) { 
            System.out.println(strings.get(i));
        }
	    
	    // Print the unsorted ArrayList.
        for (int i = 0; i < strings.size(); i++) { 
        	boolean palindrome;
            palindrome = checkIfPalindrome(strings.get(i));
            if ( palindrome ) {
            	palindromes.add(strings.get(i));
            }
        }
	    
	    // Print the Palindromes.
	    System.out.println("\nStrings - Palindromes"); 
        for (int i = 0; i < palindromes.size(); i++) { 
            System.out.println(palindromes.get(i));
        }
	    
	}
	
	private static boolean checkIfPalindrome(String a) {
		
		String b;
		
		StringBuilder sb = new StringBuilder(a);
		sb.reverse();
		b = sb.toString();
		
		if ( a.equals(b) ) {
			return true;
		} else {
			return false;
		}
		
	}

}
