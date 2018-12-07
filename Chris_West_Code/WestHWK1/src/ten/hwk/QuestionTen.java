package ten.hwk;

public class QuestionTen {
	/*
	 * The problem is Question 10:
	 * 
	 * ​​Find the minimum of two numbers using ternary operators.
	 * 
	 */

	/*
	 * (1) I allow Math.random() to generate 2 random numbers in the int variables of 'e' and 'd'. 
	 * 
	 * (2) Here I compare e to d if e < d is true then print out that number else print the other one.
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int e = (int) (Math.random() * 100), d = (int) (Math.random() * 100); // Look at (1)

		System.out.println(e < d ? e : d); // Look at (2)
	}

}
