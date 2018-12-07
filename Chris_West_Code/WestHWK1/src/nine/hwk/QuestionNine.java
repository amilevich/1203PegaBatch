package nine.hwk;

import java.util.ArrayList;

public class QuestionNine {
	/*
	 * The problem is Question 9:
	 * 
	 * Create an ArrayList which stores numbers from 1 to 100 and prints out all the
	 * prime numbers to the console.
	 * 
	 */

	/*
	 * (1) Populating an array with the numbers from 1-100
	 * 
	 * (2) Checks to see through the if statements if a number is prime. No even numbers
	 * except 2 will be a prime number. No number that is modulus of 3,5,7 will be a prime number.
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int h = 1; h <= 100; h++) // Look at (1)
			arr.add(h);

		isPrime(arr);
	}

	public static void isPrime(ArrayList<Integer> arr) { // Look at (2)
		for (int n = 0; n < arr.size(); n++) {
			if ((n % 2 == 1) && (n != 1)) {
				if ((n % 3 != 0) && (n % 5 != 0) && (n % 7 != 0)) {
					System.out.print(n + " ");
				} else if (n == 3 || n == 5 || n == 7) {
					System.out.print(n + " ");
				}

			} else if (n == 2) {
				System.out.print(n + " ");
			}
		}

	}

}
