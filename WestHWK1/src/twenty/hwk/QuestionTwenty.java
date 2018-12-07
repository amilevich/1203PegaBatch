package twenty.hwk;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QuestionTwenty {
	/*
	 * The problem is Question 20:
	 * 
	 * Create a notepad file called Data.txt and enter the following:
	 * 
	 * Mickey:Mouse:35:Arizona
	 * 
	 * Hulk:Hogan:50:Virginia
	 * 
	 * Roger:Rabbit:22:California
	 * 
	 * Wonder:Woman:18:Montana
	 * 
	 * Write a program that would read from the file and print it out to the screen
	 * in the following format:
	 * 
	 * 
	 */

	/*
	 * (1) So, I place the Scanner that's reading in the file in a try-catch block
	 * because it could give an exception if the file is spelled wrong, at the wrong
	 * path, or doesn't exist.
	 * 
	 * (2) The string of text is split between a special character such as ":" in
	 * the file. So, I can easily distinguish where I need to split the information.
	 * I use the method split from the String class.
	 * 
	 * (3) I close my resource when it's no longer in use. It's good practice to get into.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		File file = new File("Data.txt");

		try {// Look at (1)

			Scanner input = new Scanner(file);

			while (input.hasNext()) {
				String[] info = (input.next()).split(":"); // Look at (2)
				String first = info[0];
				String last = info[1];
				String age = info[2];
				String state = info[3];
				System.out.println(
						"Name: " + first + " " + last + "\nAge: " + age + " years\nState: " + state + " state\n\n");
			}
			input.close(); // Look at (3)
		} catch (IOException e) {
			System.out.println("File doesn't Exist/Not in Directory.");

		}
	}
}
