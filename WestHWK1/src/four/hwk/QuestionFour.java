package four.hwk;

import java.util.Scanner;

public class QuestionFour {
	/*
	 * The problem is Question 4: Write a program to compute N factorial.
	 * 
	 * 
	 * 
	 */

	/*
	 * (1) So, the if statement is checking if the value - 1 > 0 if it's not we
	 * don't need to check it because we don't want the value to be zero. I'll
	 * explain that in (2).
	 * 
	 * (2) In our return statement, we are taking i and timing it against are
	 * calling method. In the calling method, we are constantly substracting one
	 * from the previous number. If for instance I wasn't substracting the i-1 in
	 * the if statement and seeing that the next number would be zero. I called the
	 * method with the value of a zero. My complete result of this formula would be
	 * zero after it returns to the original call. But by checking to see if i-1 > 0
	 * we ignore the zero coming into play. We move to the second return statement
	 * and just return i back to the previous call and we times the value that got
	 * return to the previous i from that method and so forth. Until we get back to
	 * the original called method.
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		input.close();
		System.out.println(factorial(N));

	}

	public static int factorial(int i) {
		if (i - 1 > 0) { // Look at (1)
			return (i * factorial(i - 1)); // Look at (2)
		}
		return i;
	}

}
