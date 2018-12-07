package five.hwk;

import java.util.Scanner;

public class QuestionFive {
	/*
	 * The problem is Question 5:
	 * 
	 * Write a substring method that accepts a string str and an integer idx and
	 * returns the substring contained between 0 and idx-1 inclusive. Do NOT use any
	 * of the existing substring methods in the String, StringBuilder, or
	 * StringBuffer APIs.
	 * 
	 */

	/*
	 * (1) So, I'm using a for-loop to iterate through the word string at a specific
	 * index and end at specific index that the user stated. Every character from
	 * the start of the index to the specific index will appear in the temp string
	 * that I initialized before the for-loop. By doing this I'm creating a new
	 * string by using temp.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a word: ");
		String word = input.next();
		System.out.println("Enter a number: ");
		int idx = input.nextInt();
		input.close();
		String temp = "";

		for (int x = 0; x < idx; x++) { // Look at (1)
			temp += word.charAt(x);

		}
		System.out.println(temp);
	}
}
