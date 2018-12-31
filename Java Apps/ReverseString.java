package com.questions.revstr;
//Author: Steven Jean-Paul
//Q3 - Reverse a String with no temporary variable.
public class ReverseString {

	public static void main(String[] args) {
		String word = "peter parker"; //0 - 5
		int decreaser = word.length(); //Decrease this counter to iterate through the word String.
		
		for (int i = (word.length() - 1); i > -1; i--) {
		System.out.print(word.substring(i, decreaser)); //Uses a for loop to iterate starting from i
		decreaser--;								//To the end of the substring.
		}
	}
}
