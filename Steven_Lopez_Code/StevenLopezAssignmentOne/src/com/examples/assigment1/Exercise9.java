package com.examples.assigment1;

import java.util.ArrayList;

public class Exercise9 {
	// Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all
	// the prime numbers to the console.

	public static void main(String[] args) {
		ArrayList<Integer> prime = new ArrayList<Integer>();
		int max = 100;

		//Add all 100 digits to the ArrayList
		for (int idx = 1; idx <= max; idx++) {
			prime.add(idx);
		}

		//Mark any non-prime number found with -1 for later handling
		for (int idx = 2; idx * idx <= max + 1; idx++) {
			if (prime.get(idx - 1) != -1) {
				for (int i = idx * idx; i <= max; i += idx) {
					prime.set(i - 1, -1);
				}
			}
		}
		//Print the prime numbers by denying output to those with a value of -1
		for (Integer i : prime) {
			if (i != -1) {
				System.out.println(i);

			}
		}

	}

}
