package sixteen.hwk;

public class QuestionSixteen {
	/*
	 * The problem is Question 16:
	 * 
	 * Write a program to display the number of characters for a string input. The
	 * string should be entered as a command line argument using (String [ ] args).
	 * 
	 */

	/*
	 * (1) So, whatever values get submitted to cmd line into args will get its
	 * length simply printed out.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		for (String x : args) // Look at (1)
			System.out.println(x.length());

	}

}
