package twelve.hwk;

public class QuestionTwelve {
	/*
	 * The problem is Question 12:
	 * 
	 * Write a program to store numbers from 1 to 100 in an array. Print out all the
	 * even numbers from the array. Use the enhanced FOR loop for printing out the
	 * numbers.
	 * 
	 * 
	 * 
	 */

	/*
	 * (1) So, I initialize an array with a size of 100 and use a for-loop to store
	 * the array with 100 values going from 1 - 100.
	 * 
	 * (2) So, I'm using a for-each loop here with an if-statement that checks to
	 * see if the value is even. If it's even it prints that number.
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		int arr[] = new int[100]; // Look at (1)
		for (int w = 0; w < 100; w++) {
			arr[w] = w + 1;
		}
		for (int x : arr) // Look at (2)
			if (x % 2 == 0)
				System.out.println(x);
	}

}
