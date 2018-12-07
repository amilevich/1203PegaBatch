package eightteen.hwk;

import java.util.Scanner;

public class QuestionEightteen extends WordConversions {
	/*
	 * The problem is Question 18: Write a program having a concrete subclass that
	 * inherits three abstract methods from a superclass.
	 * 
	 * Provide the following three implementations in the subclass corresponding to
	 * the abstract methods in the superclass:
	 * 
	 * 1. Check for uppercase characters in a string, and return ‘true’ or ‘false’
	 * depending if any are found.
	 * 
	 * 2. Convert all of the lower case characters to uppercase in the input string,
	 * and return the result.
	 * 
	 * 3. Convert the input string to integer and add 10, output the result to the
	 * console. Create an appropriate class having a main method to test the above
	 * setup.
	 * 
	 * 
	 * 
	 */

	/*
	 * (1) Unlike in a interface when we use an abstract class we have to override
	 * the methods will be using in the child class.
	 * 
	 * (2) So, this is my abstract class WordConversions you must call it an
	 * abstract class to define it as abstract. It can have both abstract and
	 * non-abstract methods.
	 * 
	 * (3) With the Character and Integer I can use there distinct methods such as
	 * isUpperCase to figure if a Character is an upper case letter or for Integer I
	 * can take a char and find out what it's integer representation. With the
	 * String class I can use toUpperCase to simply convert a word to all upper
	 * case.
	 * 
	 * 
	 */

	public static void main(String[] args) { // Look at (1)
		Scanner input = new Scanner(System.in);
		System.out.println("Enter in a string: ");
		String words = input.nextLine();
		input.close();
		QuestionEightteen example = new QuestionEightteen();
		example.toString(words);
	}

	// Look at (3)
	@Override
	public Boolean isUpperCase(String words) {
		for (int a = 0; a < words.length(); a++) {
			char temp = words.charAt(a);

			if ((Character.isUpperCase(temp))) {
				return true;
			}

		}
		return false;
	}

	@Override
	public String toUpperCase(String words) {
		return words.toUpperCase();
	}

	@Override
	public int toInteger(String words) {
		int sum = 10;
		for (int x = 0; x < words.length(); x++) {
			char a = words.charAt(x);
			sum += Integer.valueOf(a);
		}
		return sum;
	}

	@Override
	public void toString(String words) {
		System.out.println(
				"Is a letter in the \"String\" uppercase? " + isUpperCase(words) + "\nConverted words to UPPERCASE: "
						+ toUpperCase(words) + "\nThe integer value of the words: " + toInteger(words));
	}

}
