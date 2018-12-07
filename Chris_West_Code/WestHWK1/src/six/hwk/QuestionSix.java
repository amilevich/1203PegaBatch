package six.hwk;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionSix {
	/*
	 * The problem is Question 6:
	 * 
	 * Write a program to determine if an integer is even without using the modulus operator (%)
	 * 
	 */

	/*
	 * (1) So, regex aka regular expression is pattern based. We can use it to find
	 * specific patterns that we are looking for in our strings. So, for this
	 * problem we needed to find out if a problem is EVEN without using modulus(%).
	 * How do we know if a problem is even? if a number is single digit then we
	 * already know that 0,2,4,6,8 are all even numbers. But how about for bigger
	 * numbers? we could just check and see if the last number is even then were
	 * good. So, the [] contain what I'm looking for and the $ is telling it to look at the end of the line.
	 * 
	 * (2) If a match was found from comparing the pattern against the
	 * incoming input then it will give it a 1 else the count will hold to its
	 * original value.
	 * 
	 * (3) So, don't forget to add -1 into the second input to exit. It only checks after the initial input. 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String t = input.next();
		do {
			String rg = "[02468]$"; // Look at (1)
			Pattern p = Pattern.compile(rg);
			Matcher m = p.matcher(t);
			int count = 0;
			while (m.find()) { // Look at (2)
				count++;

			}

			if (count > 0)
				System.out.println("Is Even");
			else
				System.out.println("Not Even");

			System.out.println("Enter another number to check if even or put in -1 to exit: ");
			t = input.next();
		} while (!t.equals("-1")); // Look at (3)
		input.close();
		System.out.println("Exited");
	}
}
