package com.questions.palindrome;
import java.util.ArrayList;

//Author: Steven Jean-Paul
//Q8 - Palindromes in Reverse
public class Palindrome {

	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>(); //This is the stored string
		ArrayList<StringBuffer> palindromes = new ArrayList<StringBuffer>(); //This is the result set of each string in the strings array reversed

		strings.add("karan"); //Add name strings to strings arraylist
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
		
		StringBuffer s = new StringBuffer(""); 
				
		for (int i = 0;i < strings.size(); i++) {
			s = new StringBuffer(strings.get(i));
			s.reverse();
			palindromes.add(s); //Add reversed strings from the strings arraylist
		}
		for(int ii = 0; ii < strings.size(); ii++) {
			System.out.println(palindromes.get(ii)); //Print all values from the palindromes arraylist.
		}
		
	}

}
