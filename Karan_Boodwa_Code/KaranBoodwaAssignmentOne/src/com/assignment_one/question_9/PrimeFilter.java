package com.assignment_one.question_9;

import java.util.ArrayList;

// Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.
public class PrimeFilter {

	/**
	 * filterPrimes() filters out all prime numbers from a given ArrayList<Integer>
	 * uses the 'Sieve of Eratosthenes' algorithm philosophy to actively take out
	 * non-primes from the ArrayList, leaving the primes
	 * 
	 * 
	 * @param: ArrayList<Integer>: ArrayList to be filtered
	 * @Precondition: ArrayList<Integer> contains all prime numbers below the
	 *                maximum element within Also, all prime numbers occur before
	 *                any multiple
	 * @author Karan aka Box Man TM
	 */
	public static ArrayList<Integer> filterPrimes(ArrayList<Integer> arr) {

		// Instantiating a copy of the given array
		ArrayList<Integer> primes = new ArrayList<Integer>(arr);

		// Learning about removeIf() was a game-changer.
		// Remove all elements that are <= 1
		primes.removeIf(n -> (n <= 1));

		// Loop through the ArrayList
		for (int i = 0; i < primes.size(); i++) {
			final int div = primes.get(i);
			// Note: obfuscates an inner-for loop
			// overall algorithm is still O(n^2)
			primes.removeIf(n -> ((n % div == 0) && (n != div)));
		}
		return primes;

	}

	public static void main(String[] args) {

		// nums will hold the numbers 1 - 100
		ArrayList<Integer> nums = new ArrayList<Integer>();

		// loops from 1 to 100, adding each number to nums
		for (int i = 1; i <= 100; i++) {
			nums.add(i);
		}

		// Filters the primes out of the initial list, storing them in a new list
		ArrayList<Integer> primes = filterPrimes(nums);

		// Displaying result:
		System.out.print("Primes: [ ");
		for (int i : primes) {
			System.out.print(i + " ");
		}
		System.out.println("]");
	}

}
