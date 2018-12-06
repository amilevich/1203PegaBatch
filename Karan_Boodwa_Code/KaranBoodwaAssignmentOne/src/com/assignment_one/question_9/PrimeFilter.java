package com.assignment_one.question_9;

import java.util.ArrayList;

// Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.
public class PrimeFilter {

	public static void main(String[] args) {

		// nums will hold the numbers 1 - 100
		ArrayList<Integer> nums = new ArrayList<Integer>();

		// loops from 1 to 100, adding each number to nums
		for (int i = 1; i <= 100; i++) {
			nums.add(i);
		}

		// primes will hold all the prime numbers in nums
		ArrayList<Integer> primes = new ArrayList<Integer>();

		// outer loop iterates from index 1 to 99 of nums (skipping the number 1 at
		// index 0 since 1 is not prime
		for (int i = 1; i < nums.size(); i++) {
			// flag to indicate whether or not nums.get(i) is prime
			boolean isPrime = true;

			// inner loop iterates through the entirety of the primes ArrayList, checking if
			// nums.get(i) is divisible by any of the primes encountered so far
			// saves time by checking against primes rather than checking against the entire
			// domain
			for (int j = 0; j < primes.size(); j++) {
				if (nums.get(i) % primes.get(j) == 0) {
					// if nums.get(i) is ever divisible, it's not prime, breaks out of inner loop
					isPrime = false;
					break;
				}
			}

			// if it is not divisible by any primes seen, it is prime and is added to the
			// list of primes
			if (isPrime) {
				primes.add(nums.get(i));
			}
		}
		
		// Prints list of primes
		for (int i : primes) {
			System.out.println(i);
		}

	}

}
