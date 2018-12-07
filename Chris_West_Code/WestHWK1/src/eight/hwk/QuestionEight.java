package eight.hwk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionEight {
	/*
	 * The problem is Question 8:
	 * 
	 * Write a program that stores the following strings in an ArrayList and saves
	 * all the palindromes in another ArrayList. “karan”, “madam”, ”tom”, “civic”,
	 * “radar”, “jimmy”, “kayak”, “john”, “refer”, “billy”, “did”
	 * 
	 * 
	 */

	/*
	 * (1) I set the ArrayList as global so that they could be accessed anywhere in
	 * the class.
	 * 
	 * (2) I inserted the data into a file called "Data2.txt" and accessed it using
	 * Scanner. I simply did this to allow whomever uses this program the simplicity
	 * of adding more words and checking to see if those words are palindromes.
	 * Literally, you can paste a dictionary of words into the text file and it
	 * should go them all. Much easier than typing the words out by hand.
	 * 
	 * (3) I just used StringBuilder that can manipulate the String. Has a method
	 * that will reverse the string. This will allow me to compare it back to the
	 * original string value. If there is a match between the reverse value and its
	 * original then there's a match.
	 * 
	 * 
	 */
	private static ArrayList<String> palindrome = new ArrayList<String>(); // Look at (1)
	private static ArrayList<String> words = new ArrayList<String>();

	public static void main(String[] args) {
		toStore();
		isPalindrome();
		printPalindrome();
	}

	public static void toStore() {

		File file = new File("Data2.txt"); // Look at (2)

		try {

			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				words.add(input.next());
			}
			input.close();

		} catch (IOException e) {
			System.out.println("File doesn't Exist/Not in Directory.");

		}
	}

	public static void isPalindrome() { // Look at (3)

		for (String word : words) {

			StringBuilder newWord = new StringBuilder();
			newWord.append(word);
			newWord = newWord.reverse();
			String temp = newWord.toString();

			if (word.equals(temp)) {
				palindrome.add(word);
			}
		}

	}

	public static void printPalindrome() {
		for (String word : palindrome)
			System.out.println(word);
	}
}
