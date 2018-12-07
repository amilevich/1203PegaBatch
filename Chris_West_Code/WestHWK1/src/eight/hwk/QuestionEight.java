package eight.hwk;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionEight {
	/*
	 * The problem is Question 8:
	 * 
	 * 
	 * 
	 */

	/*
	 * (1)
	 * 
	 * (2)
	 * 
	 * (3)
	 * 
	 * 
	 */
	private static ArrayList<String> palindrome = new ArrayList<String>();
	private static ArrayList<String> words = new ArrayList<String>();

	public static void main(String[] args) {
		toStore();
		isPalindrome();
		printPalindrome();
	}

	public static void toStore() {

		File file = new File("Data2.txt");

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
	public static void isPalindrome() {
		
		for (String word : words) {
			
			StringBuilder newWord = new StringBuilder();
			newWord.append(word);
			newWord = newWord.reverse();
			String temp = newWord.toString();
			
			if(word.equals(temp)) {
				palindrome.add(word);
			}
	}
		
}
	public static void printPalindrome() {
		for (String word : palindrome)
			System.out.println(word);
	}
}
