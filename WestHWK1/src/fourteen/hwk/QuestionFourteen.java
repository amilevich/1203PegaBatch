package fourteen.hwk;

import java.util.Calendar;
import java.util.Scanner;

public class QuestionFourteen {
	/*
	 * The problem is Question fourteen:
	 * 
	 * Write a program that demonstrates the switch case.
	 * 
	 * Implement the following functionalities in the cases:java
	 * 
	 * Case 1: Find the square root of a number using the Math class method.
	 * 
	 * Case 2: Display today’s date.
	 * 
	 * Case 3: Split the following string and store it in a string array. “I am
	 * learning Core Java”
	 * 
	 */

	/*
	 * (1) So, I used the Calendar class and created an object from which I could
	 * call and return the fields of month, day, and year. Hold them in my int
	 * variables called month, day, year.
	 * 
	 * (2) Allowing the user to make a selection for the switch-case. Whatever case
	 * 
	 * (3) Here we're using switch-case and I had the user previously state what
	 * number they want to use and what there selection would be doing. The break
	 * keyword will end the switch-case. The default keyword will happen if a number
	 * that wasn't between 1-3 was selected.
	 * 
	 * Side-notes: I learned about the calendar class about a month ago while
	 * solving a problem on HackerRank that required me to use the Calendar class.
	 */
	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance(); // Look at (1)
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DATE);
		int year = cal.get(Calendar.YEAR);

		String words[] = new String[5];
		String word = "I am learning Core Java";

		Scanner input = new Scanner(System.in); // Look at (2)

		System.out.println("Type a number between 1-3: \n(1) For Sqrt of a random"
				+ "number.\n(2) Print today's date \n(3) To split a String into an array");
		int value = input.nextInt();
		input.close();
		switch (value) { // Look at (3)
		case 1:
			double num = Math.random() / 2;
			System.out.println(Math.sqrt(num));
			break;
		case 2:
			System.out.println(month + "/" + day + "/" + year);
			break;
		case 3:
			for (int x = 0; x < 5; x++) {
				words = word.split(" ");
			}
			for (String wd : words)
				System.out.println(wd);
			break;
		default:
			break;
		}
	}

}
